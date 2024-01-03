<template>
 <div>
   <div class="app-container">
     <div class="box">中标候选人</div>
     <el-row :gutter="10" class="mb8">
       <el-col :span="1.5">
         <el-button
           type="primary"
           plain
           icon="el-icon-plus"
           size="mini"
           @click="handleAdd"
           :disabled="status"
         >新增</el-button>
       </el-col>
       <right-toolbar @queryTable="getList"></right-toolbar>
     </el-row>
     <el-table v-loading="loading" :data="candidateList" :default-sort = "{prop: 'zRanking', order: 'ascending'}">
       <el-table-column label="序号" type="index" align="center"/>
       <el-table-column label="供应商名称" align="center" prop="hName"/>
       <el-table-column label="最终报价(万元)" align="center" prop="zFinal" />
       <el-table-column label="最终得分" align="center" prop="zFraction" sortable/>
       <el-table-column label="是否推荐" align="center" prop="zRecommend" >
         <template slot-scope="scope">
             <span>{{scope.row.zRecommend == 0?'是':'否'}}</span>
         </template>
       </el-table-column>
       <el-table-column label="排名" align="center" prop="zRanking" sortable/>
       <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
         <template slot-scope="scope">
           <el-button
             size="mini"
             type="text"
             icon="el-icon-edit"
             @click="handleUpdate(scope.row)"
           >编辑</el-button>
           <el-button
             size="mini"
             type="text"
             icon="el-icon-delete"
             @click="handleDelete(scope.row)"
             :disabled="status"
           >删除</el-button>
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

     <!-- 添加或修改中标候选人对话框 -->
     <el-dialog :title="title" :visible.sync="open" width="1080px" append-to-body>
       <el-form ref="form" :model="form" :rules="rules" label-width="120px">
         <el-form-item label="招标项目ID" prop="sid" class="form-input">
           <el-input v-model="form.sid" placeholder="请输入招标项目ID" :disabled="true"/>
         </el-form-item>
         <el-form-item label="供应商名称" prop="hid" class="form-input">
           <el-select v-model="form.hid" @change="changeSelect3" placeholder="请选择">
             <el-option
               v-for="(item,index) in submissionList"
               :key="index"
               :label="item.bsSuppliers[0].hName"
               :value="item.bsSuppliers[0].hid">
             </el-option>
           </el-select>
         </el-form-item>
         <el-form-item label="最终报价" prop="zFinal" class="form-input">
           <el-input v-model="form.zFinal" placeholder="请输入最终报价" />
         </el-form-item>
         <el-form-item label="最终得分" prop="zFraction" class="form-input">
           <el-input v-model="form.zFraction" placeholder="请输入最终得分" />
         </el-form-item>
         <el-form-item label="是否推荐" prop="zRecommend" class="form-input">
           <el-select v-model="form.zRecommend+''" @change="changeSelect1" placeholder="请选择">
             <el-option value="0" label="是"/>
             <el-option value="1" label="否"/>
           </el-select>
<!--           <el-input v-model="form.zRecommend" placeholder="请输入是否推荐" />-->
         </el-form-item>
         <el-form-item label="排名" prop="zRanking" class="form-input">
           <el-input v-model="form.zRanking" placeholder="请输入排名" />
         </el-form-item>
         <el-form-item label="是否中标" prop="zBidder" class="form-input">
           <el-select v-model="form.zBidder+''"  @change="changeSelect2" disabled placeholder="请选择">
             <el-option value="0" label="是"/>
             <el-option value="1" label="否"/>
           </el-select>
         </el-form-item>
         <el-form-item label="发送时间" prop="zSendTime" class="form-input">
           <el-date-picker
             v-model="form.zSendTime"
             type="datetime"
             placeholder="请选择发送时间"
             value-format="yyyy-MM-dd HH:mm:ss"
             default-time="09:00:00">
           </el-date-picker>
         </el-form-item>
         <el-form-item label="专家签到表" prop="zSign" class="form-input">
           <el-upload ref="upload" class="upload-demo" :limit="1" accept=".doc, .docx, .rar, .txt, .png, .jpg"
                      multiple
                      :action="upload.url"
                      :on-change="changeFileLength"
                      :headers="upload.headers" :file-list="upload.fileList" :before-remove="beforeRemove"
                      :on-progress="handleFileUploadProgress"
                      :before-upload="beforeUpload"
                      :on-success="handleFileSuccess" :auto-upload="false">
             <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
           </el-upload>
           <!--           <el-input v-model="form.zSign" placeholder="请输入专家签到表" />-->
         </el-form-item>
         <el-form-item label="审查表" prop="zCensor" class="form-input">
           <el-upload ref="upload2" class="upload-demo" :limit="1" accept=".doc, .docx, .rar, .txt, .png, .jpg"
                      multiple
                      :action="upload.url"
                      :on-change="changeFileLength1"
                      :headers="upload.headers" :file-list="upload.fileList2" :before-remove="beforeRemove1"
                      :on-progress="handleFileUploadProgress1"
                      :before-upload="beforeUpload1"
                      :on-success="handleFileSuccess1" :auto-upload="false">
             <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
           </el-upload>
           <!--           <el-input v-model="form.zCensor" placeholder="请输入审查表" />-->
         </el-form-item>
         <el-form-item label="评审表" prop="zReview" class="form-input">
           <el-upload ref="upload3" class="upload-demo" :limit="1" accept=".doc, .docx, .rar, .txt, .png, .jpg"
                      multiple
                      :action="upload.url"
                      :on-change="changeFileLength2"
                      :headers="upload.headers" :file-list="upload.fileList3" :before-remove="beforeRemove2"
                      :on-progress="handleFileUploadProgress2"
                      :before-upload="beforeUpload2"
                      :on-success="handleFileSuccess2" :auto-upload="false">
             <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
           </el-upload>
           <!--           <el-input v-model="form.zReview" placeholder="请输入评审表" />-->
         </el-form-item>
         <el-form-item label="最终汇总表" prop="zSummary" class="form-input">
           <el-upload ref="upload4" class="upload-demo" :limit="1" accept=".doc, .docx, .rar, .txt, .png, .jpg"
                      multiple
                      :action="upload.url"
                      :on-change="changeFileLength3"
                      :headers="upload.headers" :file-list="upload.fileList4" :before-remove="beforeRemove3"
                      :on-progress="handleFileUploadProgress3"
                      :before-upload="beforeUpload3"
                      :on-success="handleFileSuccess3" :auto-upload="false">
             <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
           </el-upload>
           <!--           <el-input v-model="form.zSummary" placeholder="请输入最终汇总表" />-->
         </el-form-item>
       </el-form>
       <div slot="footer" class="dialog-footer">
         <el-button type="primary" @click="submitForm"  :disabled="status">确 定</el-button>
         <el-button @click="cancel">取 消</el-button>
       </div>
     </el-dialog>
   </div>
 </div>
</template>

<script>
import { listCandidate, getCandidate, delCandidate, addCandidate, updateCandidate } from "@/api/system/tender/bidEval";
import {getToken} from "@/utils/auth";
import {listSubmission,findSubmission} from "@/api/system/tender/submission";
import {getSupplier} from "@/api/system/supplier";
import {getTender, updateTender} from '@/api/system/tender/tender'

export default {
  name: "Candidate",
  data() {
    return {
      status:false,
      isType:null,
      // 遮罩层
      loading: true,
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
      // 中标候选人表格数据
      candidateList: [],
      //参与投标供应商数据
      submissionList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sid: null,
        hid: null,
        zFinal: null,
        zFraction: null,
        zRecommend: null,
        zRanking: null,
        zSign: null,
        zCensor: null,
        zReview: null,
        zSummary: null,
        zBidder: null,
        zSendTime: null
      },
      queryParams2:{
        sid:null,
      },
      queryParams3:{
        sid:0,
        sCode:null,
        sName:null,
        sWay: null,
        sMust:null,
        sSway:null,
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
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // hid: [
        //   { required: true, message: "供应商不能为空", trigger: "change" }
        // ],
        zFinal: [
          { required: true, message: "最终报价不能为空", trigger: "blur" }
        ],
        zFraction: [
          { required: true, message: "最终得分不能为空", trigger: "blur" }
        ],
        zRecommend: [
          { required: true, message: "是否推荐不能为空", trigger: "change" }
        ],
        zRanking: [
          { required: true, message: "排名不能为空！", trigger: "blur" },
          { required: true, message: "排名不能输入非数字！", trigger: "input" },
        ],
        // zSign: [
        //   { required: true, message: "专家签到表不能为空", trigger: "blur" }
        // ],
        // zCensor: [
        //   { required: true, message: "审查表不能为空", trigger: "blur" }
        // ],
        // zReview: [
        //   { required: true, message: "评审表不能为空", trigger: "blur" }
        // ],
        // zSummary: [
        //   { required: true, message: "最终汇总表不能为空", trigger: "blur" }
        // ],
        zBidder: [
          { required: true, message: "是否中标不能为空", trigger: "change" }
        ],
        zSendTime: [
          { required: true, message: "发送时间不能为空", trigger: "blur" }
        ],

      },
      // 收集——上传文件的列表(专家签到表)
      uploadFiles: [],
      // 收集——上传文件的个数(专家签到表)
      filesLength: 0,
      // 收集——上传文件的列表(审查表)
      uploadFiles2: [],
      // 收集——上传文件的个数(审查表)
      filesLength2: 0,
      // 收集——上传文件的列表(评审表)
      uploadFiles3: [],
      // 收集——上传文件的个数(评审表)
      filesLength3: 0,
      // 收集——上传文件的列表(最终汇总表)
      uploadFiles4: [],
      // 收集——上传文件的个数(最终汇总表)
      filesLength4: 0,
      // 上传参数
      upload: {
        // 上传的文件列表(专家签到表)
        fileList: [],
        // 上传的文件列表(审查表)
        fileList2: [],
        // 上传的文件列表(评审表)
        fileList3: [],
        // 上传的文件列表(最终汇总表)
        fileList4: [],
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
    this.queryParams.sid = this.$route.query.sid;
    this.getList();
    this.queryParams2.sid = this.$route.query.sid;
    this.getSubmission();
    getTender(this.queryParams.sid).then(res=>{
      if(res.data.sProjectState === 7){
        this.status=true;
      }
    });
  },
  methods: {
    //是否推荐下拉框改变
    changeSelect3(value){
      //根据id拿供应商
      getSupplier(value).then(res=>{
        //获取供应商名称
        this.form.hName = res.data.hName;
      });
    },
    //是否推荐下拉框改变
    changeSelect1(value){
      this.form.zRecommend = value;
      //根据id拿供应商
      getSupplier(this.form.hid).then(res=>{
        //获取供应商名称
        this.form.hName = res.data.hName;
      });
    },
    //是否中标下拉框改变
    changeSelect2(value){
      this.form.zBidder = value;
    },
    /** 查询中标候选人列表 */
    getList() {
      this.loading = true;
      listCandidate(this.queryParams).then(response => {
        this.candidateList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //查询参与投标供应商
    getSubmission(){
      findSubmission(this.queryParams2.sid).then(res=>{
        console.log(res,"sdfghj");
        this.submissionList = res.data;
      });
    },
    // 取消按钮
    cancel() {
      // 收集——上传文件的列表(专家签到表)
      this.uploadFiles=[];
      // 收集——上传文件的个数(专家签到表)
      this.filesLength= 0;
      // 收集——上传文件的列表(审查表)
      this.uploadFiles2= [];
      // 收集——上传文件的个数(审查表)
      this.filesLength2= 0;
      // 收集——上传文件的列表(评审表)
      this.uploadFiles3= [];
      // 收集——上传文件的个数(评审表)
      this.filesLength3= 0;
      // 收集——上传文件的列表(最终汇总表)
      this.uploadFiles4= [];
      // 收集——上传文件的个数(最终汇总表)
      this.filesLength4= 0;

      this.upload.fileList=[];
      this.upload.fileList2=[];
      this.upload.fileList3=[];
      this.upload.fileList4=[];

      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        zid: null,
        sid: null,
        zBname: null,
        zFinal: null,
        zFraction: null,
        zRecommend: "0",
        zRanking: null,
        zSign: null,
        zCensor: null,
        zReview: null,
        zSummary: null,
        zBidder: "0",
        zSendTime: null
      };
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.isType = "add";
      this.form.sid =this.$route.query.sid;
      this.form.zBidder ="1";//默认没有中标
      this.open = true;
      this.title = "添加中标候选人";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.isType = "update";
      const zid = row.zid || this.ids
      getCandidate(zid).then(response => {
        this.form = response.data;
        this.upload.fileList=JSON.parse(this.form.zSign);
        this.upload.fileList2=JSON.parse(this.form.zCensor);
        this.upload.fileList3=JSON.parse(this.form.zReview);
        this.upload.fileList4=JSON.parse(this.form.zSummary);
        this.open = true;
        this.title = "修改中标候选人";
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const zids = row.zid || this.ids;
      this.$modal.confirm('是否确认删除中标候选人编号为"' + zids + '"的数据项？').then(function() {
        return delCandidate(zids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
          //表单验证
          if (valid) {
            if( this.filesLength == 0 && this.filesLength2 == 0 && this.filesLength3== 0 && this.filesLength4 == 0){
              //判断type值  update：修改  add：新增
              if (this.isType === 'update') {
                updateCandidate(this.form).then(response => {
                  this.$modal.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                });
              } else if(this.isType === 'add'){
                this.form.sid = this.$route.query.sid;//确定对应招标项目
                addCandidate(this.form).then(response => {
                  this.$modal.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                });
              }
              //修改招标项目状态
              this.upTender();
              this.cancel();
            }else{
              if(this.filesLength > 0 && this.form.zSign == null){
                this.$refs.upload.submit();
              }else
              if(this.filesLength2 > 0 && this.form.zCensor == null){
                this.$refs.upload2.submit();
              }else
              if(this.filesLength3 > 0 && this.form.zReview == null){
                this.$refs.upload3.submit();
              }else
              if(this.filesLength4 > 0 && this.form.zSummary == null){
                this.$refs.upload4.submit();
              }
            }
          }
      });
    },
    //修改招标项目状态(=>定标中)
    upTender(){
      //判断中标候选人是否至少三人
      console.log(this.queryParams,"query");
      this.queryParams.sid = this.$route.query.sid;
      listCandidate(this.queryParams).then(response => {
        console.log(response,"res");
        if(response.rows.length > 2){
          this.queryParams3.sid = this.$route.query.sid;
          this.queryParams3.sProjectState = 6;//定标中
          //修改招标项目状态
          updateTender(this.queryParams3).then(r=>{});
        }
      });

    },
    /** 专家签到表 */
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 修改当前文件列表长度
    changeFileLength(file, fileList){
      this.filesLength = fileList.length;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.uploadFiles.push(file);
      //每上传完一个文件都会执行该函数，所以必须等上传完成后再提交表单
      if (this.uploadFiles.length == this.filesLength){
        //已上传文件  --> 字符串
        let updatedArray = fileList.map(obj => {
          let newObj = obj;
          delete newObj.response;
          delete newObj.raw;
          delete newObj.percentage;
          delete newObj.status;
          obj.url = response.data.data.url;
          delete newObj.response;
          return newObj;
        });
        this.form.zSign = JSON.stringify(updatedArray);//写入专家签到表

        if(this.filesLength2 > 0 && this.form.zCensor == null){
          this.$refs.upload2.submit();
        }else
        if(this.filesLength3 > 0 && this.form.zReview == null){
          this.$refs.upload3.submit();
        }else
        if(this.filesLength4 > 0 && this.form.zSummary == null){
          this.$refs.upload4.submit();
        }else{
          //执行操作
          if(this.isType ==='update'){
            //修改公告
            updateCandidate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              this.cancel();
            });
          }else if (this.isType ==='add'){
            addCandidate(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
              this.cancel();
            });
          }
          //修改招标项目状态
          this.upTender();
        }
      }
      this.upload.isUploading = false;
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`).then(res=>{
        this.form.zSign=null;
        this.upload.fileList = [];
      });
    },
    beforeUpload(file) {
      const isLt5M = file.size / 1024 / 1024 < 5;
      if (!isLt5M) {
        this.$message.error('文件大小不能超过5MB');
      }
      return isLt5M;
    },

    /** 审查表 */
    // 文件上传中处理
    handleFileUploadProgress1(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 修改当前文件列表长度
    changeFileLength1(file, fileList){
      this.filesLength2 = fileList.length;
    },
    // 文件上传成功处理
    handleFileSuccess1(response, file, fileList) {
      this.uploadFiles2.push(file);
      //每上传完一个文件都会执行该函数，所以必须等上传完成后再提交表单
      if (this.uploadFiles2.length == this.filesLength2){
        //已上传文件  --> 字符串
        let updatedArray = fileList.map(obj => {
          let newObj = obj;
          delete newObj.response;
          delete newObj.raw;
          delete newObj.percentage;
          delete newObj.status;
          obj.url = response.data.data.url;
          delete newObj.response;
          return newObj;
        });
        this.form.zCensor = JSON.stringify(updatedArray);

        if(this.filesLength > 0 && this.form.zSign == null){
          this.$refs.upload.submit();
        }else
        if(this.filesLength3 > 0 && this.form.zReview == null){
          this.$refs.upload3.submit();
        }else
        if(this.filesLength4 > 0 && this.form.zSummary == null){
          this.$refs.upload4.submit();
        }else{
          //执行操作
          if(this.isType ==='update'){
            //修改公告
            updateCandidate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              this.cancel();
            });
          }else if (this.isType ==='add'){
            addCandidate(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
              this.cancel();
            });
          }
          //修改招标项目状态
          this.upTender();
        }
      }
      this.upload.isUploading = false;
    },
    beforeRemove1(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`).then(res=>{
        this.form.zCensor=null;
        this.upload.fileList2 = [];
      });
    },
    beforeUpload1(file) {
      const isLt5M = file.size / 1024 / 1024 < 5;

      if (!isLt5M) {
        this.$message.error('文件大小不能超过5MB');
      }
      return isLt5M;
    },

    /** 评审表 */
    // 文件上传中处理
    handleFileUploadProgress2(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 修改当前文件列表长度
    changeFileLength2(file, fileList){
      this.filesLength3 = fileList.length;
    },
    // 文件上传成功处理
    handleFileSuccess2(response, file, fileList) {
      this.uploadFiles3.push(file);
      //每上传完一个文件都会执行该函数，所以必须等上传完成后再提交表单
      if (this.uploadFiles3.length == this.filesLength3){
        //已上传文件  --> 字符串
        let updatedArray = fileList.map(obj => {
          let newObj = obj;
          delete newObj.response;
          delete newObj.raw;
          delete newObj.percentage;
          delete newObj.status;
          obj.url = response.data.data.url;
          delete newObj.response;
          return newObj;
        });
        this.form.zReview = JSON.stringify(updatedArray);
        if(this.filesLength > 0 && this.form.zSign == null){
          this.$refs.upload.submit();
        }else
        if(this.filesLength2 > 0 && this.form.zCensor == null){
          this.$refs.upload2.submit();
        }else
        if(this.filesLength4 > 0 && this.form.zSummary == null){
          this.$refs.upload4.submit();
        }else{
          //执行操作
          if(this.isType ==='update'){
            //修改公告
            updateCandidate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              this.cancel();
            });
          }else if (this.isType ==='add'){
            addCandidate(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
              this.cancel();
            });
          }
          //修改招标项目状态
          this.upTender();
        }
      }
      this.upload.isUploading = false;
    },
    beforeRemove2(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`).then(res=>{
        this.upload.fileList3 = [];
        this.form.zReview=null;
      });
    },
    beforeUpload2(file) {
      const isLt5M = file.size / 1024 / 1024 < 5;

      if (!isLt5M) {
        this.$message.error('文件大小不能超过5MB');
      }
      return isLt5M;
    },

    /** 最终汇总表 */
    // 文件上传中处理
    handleFileUploadProgress3(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 修改当前文件列表长度
    changeFileLength3(file, fileList){
      this.filesLength4 = fileList.length;
    },
    // 文件上传成功处理
    handleFileSuccess3(response, file, fileList) {
      //拿到招标项目id
      this.form.sid = this.$route.query.sid;
      this.uploadFiles4.push(file);
      //每上传完一个文件都会执行该函数，所以必须等上传完成后再提交表单
      if (this.uploadFiles4.length == this.filesLength4){
        //已上传文件  --> 字符串
        let updatedArray = fileList.map(obj => {
          let newObj = obj;
          delete newObj.response;
          delete newObj.raw;
          delete newObj.percentage;
          delete newObj.status;
          obj.url = response.data.data.url;
          delete newObj.response;
          return newObj;
        });
        this.form.zSummary = JSON.stringify(updatedArray);

        if(this.filesLength > 0 && this.form.zSign == null){
          this.$refs.upload.submit();
        }else
        if(this.filesLength2 > 0 && this.form.zCensor == null){
          this.$refs.upload2.submit();
        }else
        if(this.filesLength3 > 0 && this.form.zReview == null){
          this.$refs.upload3.submit();
        }else{
          //执行操作
          if(this.isType ==='update'){
            //修改公告
            updateCandidate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              this.cancel();
            });
          }else if (this.isType ==='add'){
            addCandidate(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
              this.cancel();
            });
          }
          //修改招标项目状态
          this.upTender();
        }
      }
      this.upload.isUploading = false;
    },
    beforeRemove3(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`).then(res=>{
        this.form.zSummary=null;
        this.upload.fileList4 = [];
      });
    },
    beforeUpload3(file) {
      const isLt5M = file.size / 1024 / 1024 < 5;

      if (!isLt5M) {
        this.$message.error('文件大小不能超过5MB');
      }
      return isLt5M;
    },
  }
};
</script>
<style scoped>
.box {
  margin: 10px 0 15px 0px;
  color: #409eff;
  font-size: 16px;
  font-weight: bold;
}
.form-input{
  width: 500px;
  display: inline-block;
}
</style>
