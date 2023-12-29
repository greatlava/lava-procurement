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
          :disabled="status"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="documentsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" type="index"/>
      <el-table-column label="文件标题" align="center" prop="wTitle"/>
      <el-table-column label="文件大小(kb)" align="center" prop="wSize" />
      <el-table-column label="上传时间" align="center" prop="wUploadTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.wUploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <a :href="scope.row.url">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-download"
          >下载</el-button>
          </a>

          <el-button  style="margin-left: 10px"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
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

    <!-- 添加或修改招标文件对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px" v-loading="loading">
        <el-form-item label="招标项目ID" prop="uid">
          <el-input v-model="form.sid" placeholder="请输入招标项目ID" :disabled="true"/>
        </el-form-item>
        <el-form-item label="文件标题" prop="wTitle">
          <el-input v-model="form.wTitle" placeholder="请输入文件标题" />
        </el-form-item>
        <el-form-item label="文件大小" prop="wSize">
          <el-input v-model="form.wSize" placeholder="请输入文件大小" />
        </el-form-item>
        <el-form-item label="附件">
          <el-upload ref="upload" class="upload-demo" :limit="1" accept=".doc, .docx, .rar, .txt, .png, .jpg"
                     multiple
                     :action="upload.url"
                     :data="upload"
                     :on-change="changeFileLength"
                     :headers="upload.headers" :file-list="upload.fileList" :before-remove="beforeRemove"
                     :on-progress="handleFileUploadProgress"
                     :on-success="handleFileSuccess" :auto-upload="false">
            <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
            <div slot="tip" class="el-upload__tip">只能上传.doc, .docx, .rar, .txt, .png, .jpg文件，且不超过5MB</div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" :disabled="status">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDocuments, getDocuments, delDocuments, addDocuments, updateDocuments,findTwoDocInfo } from "@/api/system/document";
import {addNotice, findStatus, updateNotice} from "@/api/system/tender/tenderNotice";
import {getToken} from "@/utils/auth";
import * as url from "url";
import {getTender} from "@/api/system/tender/tender";

export default {
  name: "Documents",
  data() {
    return {
      status:false,
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
      // 招标文件表格数据
      documentsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      sCode:null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        uid: null,
        sid:null,
        wTitle: null,
        wSize: null,
        wUploadTime: null,
        type:null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        wTitle:[
          { required: true, message: "文件标题不能为空", trigger: "blur" }
        ],
        wSize:[
          { required: true, message: "文件大小不能为空", trigger: "blur" }
        ],
        wUploadTime:[
          { required: true, message: "上传时间不能为空", trigger: "blur" }
        ]
      },
      //附件
      fileList:[],
      // 收集——上传文件的列表
      uploadFiles: [],
      // 收集——上传文件的个数
      filesLength: 0,
      // 上传参数
      upload: {
        sid:this.$route.query.sid,
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
    this.getList();
    getTender(this.queryParams.sid).then(res=>{
      if(res.data.sProjectState === 7){
        this.status=true;
      }
    });
  },
  methods: {
    /** 查询招标文件列表 */
    getList() {
      this.loading = true;
      this.queryParams.sid = this.$route.query.sid;
      listDocuments(this.queryParams).then(res=>{
        this.documentsList = res.rows;
        this.total = res.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        wid: null,
        wTitle: null,
        sid:null,
        wSize: null,
        wUploadTime: null
      };
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.wid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加招标文件";
      this.loading =true;
      this.queryParams.type='add';
      this.upload.fileList=[];
      this.form.sid = this.$route.query.sid;
      setTimeout(()=>{
        this.loading = false;
      },500)
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.queryParams.type='update';
      const wid = row.wid || this.ids
      getDocuments(wid).then(response => {
        this.form = response.data;
        this.upload.fileList=[{name:this.form.fileName,url:this.form.url}];
        this.open = true;
        this.title = "修改招标文件";
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      if(this.status){
        this.$alert("该项目已定标，不可进行操作！");
        this.getList();
      }else{
        const wids = row.wid || this.ids;
        this.$modal.confirm('是否确认删除招标文件编号为"' + wids + '"的数据项？').then(function() {
          return delDocuments(wids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      }
    },
    /** 提交按钮 表单提交*/
    submitForm() {
      this.$refs["form"].validate(valid => {
        //已发布状态 点击退出
        if(this.form.fjStatus === 5){
          this.open = false;
        }else{
          //表单验证
          if (valid) {
            //1:如果没有文件，直接上传form表单
            if(this.filesLength == 0){
              //判断type值  update：修改  add：新增
              if (this.queryParams.type === 'update') {
                updateDocuments(this.form).then(response => {
                  this.$modal.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                });
              } else if(this.queryParams.type === 'add'){
                this.form.sid = this.$route.query.sid;//确定对应招标项目
                //新增公告
                addDocuments(this.form).then(response => {
                  this.$modal.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                });
              }
            }else{
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
    // 文件提交处理
    submitUpload() {
      this.$refs.upload.submit();
    },
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
      //拿到招标项目id
      this.form.sid = this.$route.query.sid;
      console.log(response,"res");
      console.log(file,"file");
      console.log(fileList,"filelist");

      this.form.fileName = file.name;//文件名
      this.form.url = response.data.data.url;//查询路径
      this.uploadFiles.push(file);
      //每上传完一个文件都会执行该函数，所以必须等上传完成后再提交表单
      if (this.uploadFiles.length > 0){
        //将上传文件信息从fileList中拼接
        this.form.fileList=fileList;
        if(this.queryParams.type ==='update'){
          //修改文件
          updateDocuments(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
        }else if (this.queryParams.type ==='add'){
          //新增文件
          // this.form.
          addDocuments(this.form).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          });

        }
        console.log("上传后文件列表:"+fileList);
      }
      this.upload.isUploading = false;
    },
    beforeRemove(file, fileList) {
      this.upload.fileList = [];
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
