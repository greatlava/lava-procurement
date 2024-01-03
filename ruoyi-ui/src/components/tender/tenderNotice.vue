<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:notice:add']"
          :disabled="status"
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="noticeList">
      <el-table-column label="序号" align="center" prop="orderNum" />
      <el-table-column label="公告标题" align="center" prop="uTitle" />
      <el-table-column label="关联项目" align="center" prop="uProject"/>
      <el-table-column label="审批状态" align="center" prop="fjStatus">
      <template slot-scope="scope">
        <el-tag type="info"  v-show="scope.row.fjStatus === 1">
          <dict-tag :options="dict.type.bid_notice_state" :value="scope.row.fjStatus"/>
        </el-tag>
        <el-tag v-show="scope.row.fjStatus === 2">
          <dict-tag :options="dict.type.bid_notice_state" :value="scope.row.fjStatus"/>
        </el-tag>
        <el-tag type="success"  v-show="scope.row.fjStatus === 3 || scope.row.fjStatus === 5">
        <dict-tag :options="dict.type.bid_notice_state" :value="scope.row.fjStatus"/>
        </el-tag>
        <el-tag type="warning"  v-show="scope.row.fjStatus === 4">
          <dict-tag :options="dict.type.bid_notice_state" :value="scope.row.fjStatus"/>
        </el-tag>
      </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-if="scope.row.fjStatus === 1 || scope.row.fjStatus === 4"
                     size="mini"
                     type="text"
                     icon="el-icon-s-check"
                     @click="handleUpdateState2(scope.row,1)"
                     :disabled="status"
          >审核</el-button>
          <el-button v-if="scope.row.fjStatus === 1 || scope.row.fjStatus === 4"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row,1)"
            :disabled="status"
            v-has-role="['common']"
          >编辑</el-button>
          <el-button v-if="scope.row.fjStatus === 1 || scope.row.fjStatus === 4"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            :disabled="status"
            v-has-role="['common']"
          >删除</el-button>
          <el-button v-if="scope.row.fjStatus === 2 || scope.row.fjStatus === 3 || scope.row.fjStatus === 5"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row,2)"
          >查看</el-button>
          <el-button v-if="scope.row.fjStatus === 3"
            size="mini"
            type="text"
            icon="el-icon-s-promotion"
            @click="handleUpdateState(scope.row)"
            :disabled="status"
            v-has-role="['common']"
          >发布</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改招标公告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1080px" append-to-body>
      <el-form ref="form"  :model="form" :rules="rules" label-width="120px"  v-loading="loading">
        <el-form-item label="公告标题" prop="uTitle">
          <el-input v-model="form.uTitle" placeholder="请输入公告标题" :disabled="noUpdate"/>
        </el-form-item>
        <el-form-item label="关联项目" prop="uProject" class="form-input">
          <el-input v-model="form.uProject" disabled="disabled"/>
        </el-form-item>
        <el-form-item label="项目资金(万元)" prop="uMoney" class="form-input">
          <el-input v-model="form.uMoney" placeholder="请输入项目资金" :disabled="noUpdate"/>
        </el-form-item>
        <el-form-item label="标书获取时间" prop="uGetTime" class="form-input">
          <el-date-picker
            v-model="form.uGetTime"
            type="datetime"
            placeholder="请选择标书获取时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="09:00:00"
            :disabled="noUpdate">
          </el-date-picker>
        </el-form-item >
        <el-form-item label="接受答疑时间" prop="uAcceptTime" class="form-input">
          <el-date-picker
            v-model="form.uAcceptTime"
            type="datetime"
            placeholder="请选择接受答疑时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="09:00:00"
            :disabled="noUpdate">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="投标截止时间" prop="uEndTime" class="form-input">
          <el-date-picker
            v-model="form.uEndTime"
            type="datetime"
            placeholder="请选择投标截止时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="09:00:00"
            :disabled="noUpdate">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开标时间" prop="uKaiTime" class="form-input">
          <el-date-picker
            v-model="form.uKaiTime"
            type="datetime"
            placeholder="请选择开标时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="09:00:00"
            :disabled="noUpdate">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="附件" prop="fjAnnex">
                      <el-upload ref="upload" class="upload-demo" :limit="4" accept=".doc, .docx, .rar, .txt, .png, .jpg"
                                 multiple
                                 :action="upload.url"
                                 :on-change="changeFileLength"
                                 :headers="upload.headers" :file-list="upload.fileList" :before-remove="beforeRemove"
                                 :on-progress="handleFileUploadProgress"
                                 :on-success="handleFileSuccess" :auto-upload="false">
                        <el-button slot="trigger" size="small" type="primary" :disabled="noUpdate">选取文件</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传.doc, .docx, .rar, .txt, .png, .jpg文件，且不超过5MB</div>
                      </el-upload>
        </el-form-item>
        <el-form-item label="内容" prop="fjRemark">
          <editor v-model="form.fjRemark" :min-height="192" :disabled="noUpdate"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
<!--        v-show="showPass"-->
        <el-button type="success"  @click="handleUpdateState2(form,2)" :disabled="status" v-if="form.fjStatus == 2" v-has-role="['controller']">通 过</el-button>
        <el-button v-show="showPass" type="danger" @click="handleUpdateState2(form ,3)" :disabled="status"  v-if="form.fjStatus == 2" v-has-role="['controller']">驳 回</el-button>
<!--        v-show="showBtn"-->
        <el-button type="primary"  @click="submitForm" v-has-role="['common']" :disabled="status">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listNotice, getNotice, delNotice, addNotice, updateNotice,findStatus,delYfb,selMax} from "@/api/system/tender/tenderNotice";
import { getTender ,updateTender} from '@/api/system/tender/tender'
import {findTwoDocInfo,addDocuments} from "@/api/system/document";
import {getToken} from "@/utils/auth";

export default {
  dicts:["bid_notice_state"],
  name: "Notice",
  data() {
    return {
      status:false,
      // 遮罩层
      loading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 招标公告表格数据
      noticeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      showBtn:true,
      showPass:false,
      //是否可以编辑
      noUpdate:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderNum:null,
        uid:null,
        sid: null,
        uTitle: null,
        uProject: null,
        uMoney: null,
        uGetTime: null,
        uAcceptTime: null,
        uEndTime: null,
        uKaiTime: null,
        fjAnnex: null,
        fjStatus: null,
        fjRemark: null,
        max:null,
        type:null,
      },
      queryParams2:{
        sid:0,
        sCode:"",
        sName:"",
        sWay: "0",
        sMust:"0",
        sSway: "0",
        sType: "0",
        sBudget:"",
        sUnit:"",
        sPerson:"",
        sPhone:"",
        email:"",
        sAddress:"",
        fjFiles:null,
        sProjectState:null,
        sLeader:null,
      },
      // 表单参数
      form: {
      },
      // 表单校验
      rules: {
        uTitle: [
          { required: true, message: "公告标题不能为空", trigger: "blur" }
        ],
        uMoney: [
          { required: true, message: "项目资金不能为空", trigger: "blur" }
        ],
        uGetTime: [
          { required: true, message: "标注获取时间不能为空", trigger: "blur" }
        ],
        uAcceptTime: [
          { required: true, message: "接受答疑时间不能为空", trigger: "blur" }
        ],
        uEndTime: [
          { required: true, message: "投标截止时间不能为空", trigger: "blur" }
        ],
        uKaiTime: [
          { required: true, message: "开标时间不能为空", trigger: "blur" }
        ],
        fjRemark: [
          { required: true, message: "内容不能为空", trigger: "blur" }
        ],
      },
      //附件
      fileList:[],
      // 收集——上传文件的列表
      uploadFiles: [],
      // 收集——上传文件的个数
      filesLength: 0,
      urls:[],
      fjList:[],
      //未上传前文件列表
      noFiles:[],
      //未上传清除后文件列表
      noFiles2:[],
      //接收过滤的已上传文件
      filterList:[],
      // 上传参数
      upload: {
        // 上传的文件列表
        fileList: [],
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: {
          Authorization: "Bearer " + getToken()
        },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/bidding/documents/upload1",
      }
    };
  },
  created() {
    this.loading = true;
    setTimeout(()=>{
      this.loading = false;
    },"3000");
    this.queryParams.sid = this.$route.query.sid;
    this.getList();
    getTender(this.queryParams.sid).then(res=>{
      if(res.data.sProjectState === 7){
        this.status=true;
      }
    });
  },
  methods: {
    rest2(){
      this.fileList=[],
        // 收集——上传文件的列表
        this.uploadFiles=[]
        // 收集——上传文件的个数
        this.filesLength=0
        this.urls=[]
        this.fjList=[]
        //未上传前文件列表
        this.noFiles=[]
        //未上传清除后文件列表
        this.noFiles2=[]
        //接收过滤的已上传文件
        this.filterList=[]
        this.upload.fileList=[];
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.showBtn=true;
      this.showPass=false;
      this.noUpdate = false;
      this.upload.fileList =[];
      this.reset();
    },
    /** 查询招标公告列表 */
    getList() {
      this.loading = true;
      this.noticeList=[];
      listNotice(this.queryParams).then(response => {
        response.rows.forEach((e,i)=>{
          e.orderNum = i+1;
          this.noticeList.push(e);
        })
        this.total = response.total;
        this.loading = false;
      });

    },
    // 表单重置
    reset() {
      this.form = {
        uid: null,
        sid: null,
        uTitle: null,
        uProject: null,
        uMoney: null,
        uGetTime: null,
        uAcceptTime: null,
        uEndTime: null,
        uKaiTime: null,
        fjAnnex: null,
        fjStatus: null,
        fjRemark: null
      };
      this.resetForm("form");
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getTender(this.queryParams.sid).then(res=>{
          this.form.uProject =res.data.sName;
      });
      this.upload.fileList=[];
      this.open = true;
      this.loading = true;
      this.title = "添加招标公告";
      setTimeout(() => {
        this.loading = false;
      }, 500);
      this.queryParams.type = 'add';
    },

    /** 修改按钮操作 */
    handleUpdate(row,num) {
      this.reset();
      if (row.fjStatus === 2){
        this.showBtn=false;
        this.showPass=true;
      }
      const uid = row.uid || this.ids;
      getNotice(uid).then(response => {
        this.form = response.data;
        if(this.form.fjAnnex != null){
          this.upload.fileList = JSON.parse(this.form.fjAnnex);
        }
        getTender(this.queryParams.sid).then(res=>{
          this.form.uProject =res.data.sName;
        });
        this.open = true;
        if (num === 1){
          this.title = "修改招标公告";
        }else{
          this.title = "招标公告详情";
          this.noUpdate = true;
        }
      });
      this.queryParams.type = 'update';
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const uids = row.uid || this.ids;
        this.$modal.confirm('是否确认删除招标公告编号为"' + uids + '"的数据项？').then(function() {
        return delNotice(uids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },

    /**发布按钮操作*/
    handleUpdateState(row){
       //判定是否已存在已发布公告
      findStatus(this.$route.query.sid).then(res=>{
        if(res.data.length === 0){
          //进行发布  无发布的公告
          this.$modal.confirm('是否确认发布编号为"' + row.uid + '"的招标公告？').then(function() {
            row.fjStatus = 5;//改变状态
            return updateNotice(row);
          }).then(() => {
              //修改招标项目状态
           this.queryParams2={
                sid:0,
                sCode:null,
                sName:null,
                sWay: null,
                sMust:null,
                sSway: null,
                sType: null,
                sBudget:null,
                sUnit:null,
                sPerson:null,
                sPhone:null,
                email:null,
                sAddress:null,
                fjFiles:null,
                sProjectState:null,
                sLeader:null,
              };
              this.queryParams2.sid = this.$route.query.sid;
              this.queryParams2.sProjectState=3;//修改状态为投标中
              return updateTender(this.queryParams2);
            }).then(() => {
            this.getList();
            this.$modal.msgSuccess("公告发布成功");
          }).catch(() => {});
        }else{
          //存在发布公告
          this.$modal.confirm('察觉到当前项目已存在发布的公告，是否替换为当前公告？')
            .then(() => {
              return delYfb(this.$route.query.sid);//删除已发布公告
            })
            .then(() => {
              row.fjStatus = 5; // 改变状态
              return updateNotice(row);
            })
            .then(()=>{
              this.getList();
              this.$modal.msgSuccess("公告发布成功");
            }).catch(() => {
              // 在这里处理错误
              console.error("An error occurred");
            });
        }
      });
    },

    /**审核按钮操作*/
    handleUpdateState2(row,num){
      console.log(row,"row.....");
      if(num === 1){
        this.$modal.confirm('是否确认发起审核编号为"' + row.uid + '"的招标公告？').then(function() {
          row.fjStatus = 2;//改变状态
          return updateNotice(row);
        }).then(() => {
          // this.getList();
          this.$modal.msgSuccess("公告处于审核中状态");
        }).catch(() => {});
      }else if(num === 2){
        this.$modal.confirm('是否确认通过编号为"' + row.uid + '"的招标公告？').then(function() {
          row.fjStatus = 3;//改变状态 通过
          return updateNotice(row);
        }).then(() => {
          this.open=false;
          this.getList();
          this.$modal.msgSuccess("审核通过");
        }).catch(() => {});
      }else if(num === 3){
        this.$modal.confirm('是否确认驳回编号为"' + row.uid + '"的招标公告？').then(function() {
          row.fjStatus = 4;//改变状态 驳回
          return updateNotice(row);
        }).then(() => {
          this.open=false;
          this.getList();
          this.$modal.msgError("驳回公告");
        }).catch(() => {});
      }

    },

    /** 提交按钮 表单提交*/
    submitForm() {
      this.$refs["form"].validate(valid => {
        //已发布状态 点击退出
        if(this.form.fjStatus === 5 || this.form.fjStatus === 3 || this.form.fjStatus === 2){
          this.open = false;
        }else{
          //表单验证
          if (valid) {
            //1:如果没有文件，直接上传form表单
            if(this.noFiles2.length == 0){
              console.log("pt add update");
             //拿到剩余文件生成字符串
             this.form.fjAnnex = JSON.stringify(this.upload.fileList);
              //判断type值  update：修改  add：新增
              if (this.queryParams.type === 'update') {
                updateNotice(this.form).then(response => {
                  this.$modal.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                });
              } else if(this.queryParams.type === 'add'){
                this.form.sid = this.$route.query.sid;//确定对应招标项目
                //新增公告
                addNotice(this.form).then(response => {
                  this.$modal.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                });

              }
            }else{
              console.log("upload add update");
              this.noFiles2=[];
              //2:如果有文件
              //2.1文件上传执行submit  即触发 handleFileSuccess函数
              this.$refs.upload.submit();
              //2.2:上传返回的URL地址，在handleFileSuccess中处理
              //2.3：表单提交
            }
          }
        }
      });
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 修改当前文件列表长度
    changeFileLength(file, fileList){
      this.noFiles.push(file);
      this.noFiles2.push(file);
      // console.log( this.noFiles,"noFiles cg");
      // console.log( this.noFiles2,"noFiles2 cg");
      this.filesLength = fileList.length;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      //拿到招标项目id
      this.form.sid = this.$route.query.sid;
      const url =response.data.data.url;
      this.urls.push({id: this.urls.length + 1, url});
      this.fjList.push({id: this.fjList.length + 1, ...file });
      this.uploadFiles.push(file);
      console.log(this.urls,"urls");
      console.log(this.fjList,"fjList");
      console.log(this.uploadFiles.length,"uploadFiles len");
      console.log(this.filesLength,"len2");
      console.log(this.upload.fileList.length,"len3");
      //每上传完一个文件都会执行该函数，所以必须等上传完成后再提交表单
      // this.upload.fileList = this.filterList;
      // console.log(this.upload.fileList,"upload.fileList");
      if(this.filterList.length > 0){
        this.upload.fileList = this.filterList;
      }
      if (this.uploadFiles.length === this.filesLength - this.upload.fileList.length){
        // console.log(this.fjList,"open jr");
        //清空未上传文件列表
        this.noFiles2=[];
        // console.log( this.noFiles2,"noFiles2 sc");
        let fjLists = this.fjList.map(obj=>{
          let newObj = obj;
          delete newObj.percentage;
          delete newObj.raw;
          delete newObj.status;
          delete newObj.response;
          obj.url = '';
          return newObj;
        });
        console.log( fjLists,"fjLists");
        const result = fjLists.map((item, index) => {
          item.url = this.urls[index].url;
          return item;
        });
        console.log( result,"result");
        if(this.upload.fileList.length > 0){
          // console.log("open  upload");
          this.upload.fileList.forEach(f=>{
            f.id = result.length+1;
            result.push(f);
          });
          console.log( result,"result");
          this.upload.fileList=[];//清空
        }
        this.form.fjAnnex = JSON.stringify(result);
        console.log( this.form.fjAnnex,"fjAnnex");
       if(this.queryParams.type ==='update'){
         //修改公告
         updateNotice(this.form).then(response => {
           this.$modal.msgSuccess("修改成功");
           this.open = false;
           this.getList();
           this.rest2();
         });
       }else if (this.queryParams.type ==='add'){
         //新增公告
         addNotice(this.form).then(response => {
           this.$modal.msgSuccess("新增成功");
           this.open = false;
           this.getList();
           this.rest2();
         });
       }
      }
      this.upload.isUploading = false;
    },
    beforeRemove(file, fileList) {
      if(this.queryParams.type ==='add'){
        this.noFiles2 =  this.noFiles.filter(f=>f.name !== file.name);
      }else if(this.queryParams.type ==='update'){

        this.filterList = this.upload.fileList.filter(f=>f.name!==file.name);
        console.log( this.filterList,"filterList");
      }
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    beforeUpload(file) {
      const isLt5M = file.size / 1024 / 1024 < 5;
      if (!isLt5M) {
        this.$message.error('文件大小不能超过5MB');
      }
      return isLt5M;
    }
  }
};
</script>
<style scoped>
.form-input{
  width: 500px;
  display: inline-block;
}
</style>
