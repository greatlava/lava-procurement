<template>
  <div style="width: 62%;margin: 20px auto">
    <!-- 添加或修改招标公告对话框 -->
      <el-form :model="form" :rules="rules" label-width="100px">
        <el-form-item label="公告标题" prop="uTitle">
          <el-input v-model="form.uTitle" placeholder="请输入公告标题" :disabled="this.routeType==='details'"/>
        </el-form-item>
        <el-form-item label="关联项目" prop="uProject" class="form-input">
          <el-input v-model="form.uProject" placeholder="请输入关联项目" :disabled="this.routeType==='details'"/>
        </el-form-item>
        <el-form-item label="项目资金" prop="uMoney" class="form-input">
          <el-input v-model="form.uMoney" placeholder="请输入项目资金" :disabled="this.routeType==='details'"/>
        </el-form-item>
        <el-form-item label="标注获取时间" prop="uGetTime" class="form-input">
          <el-date-picker clearable
                          v-model="form.uGetTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择标注获取时间" :disabled="this.routeType==='details'">
          </el-date-picker>
        </el-form-item >
        <el-form-item label="接受答疑时间" prop="uAcceptTime" class="form-input">
          <el-date-picker clearable
                          v-model="form.uAcceptTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择接受答疑时间" :disabled="this.routeType==='details'">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="投标截止时间" prop="uEndTime" class="form-input">
          <el-date-picker clearable
                          v-model="form.uEndTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择投标截止时间" :disabled="this.routeType==='details'">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开标时间" prop="uKaiTime" class="form-input">
          <el-date-picker clearable
                          v-model="form.uKaiTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择开标时间" :disabled="this.routeType==='details'">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="附件" prop="fjAnnex">
          <el-upload
            class="upload-demo"
            action="https://jsonplaceholder.typicode.com/posts/"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            multiple
            :limit="3"
            :on-exceed="handleExceed"
            :file-list="fileList"
            :disabled="this.routeType==='details'"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <span slot="tip" class="el-upload__tip"> 支持文件格式：doc，docx,pdf.xis......</span>
          </el-upload>
        </el-form-item>
        <el-form-item label="备注" prop="fjRemark">
          <el-input v-model="form.fjRemark" type="textarea" placeholder="请输入内容" :disabled="this.routeType==='details'"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
  </div>
</template>

<script>
import { listNotice, getNotice, delNotice, addNotice, updateNotice } from "@/api/system/notice";

export default {
  name: "Notice",
  data() {
    return {
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
      // 招标公告表格数据
      noticeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: true,
      routeType:null,
      fileList:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
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
        fjRemark: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.routeType = this.$route.query.type;
    this.queryParams.uid = this.$route.query.uid;
    if(this.routeType ==="update" || this.routeType ==="details"){
     this.getList(this.queryParams.uid);
    }
  },
  methods: {
    // 取消按钮
    cancel() {
      this.open = false;
      this.$router.go(-1);
      // this.reset();
    },
    /** 查询招标公告列表 */
    getList(uid) {
      getNotice(uid).then(res=>{
        this.form = res.data;
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
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const uid = row.uid || this.ids
      getNotice(uid).then(response => {
        this.form = response.data;
        this.title = "修改招标公告";
      });
    },
    /** 提交按钮 */
    submitForm() {
     if(this.routeType ==="details"){
       this.$router.go(-1);
     }else if (this.routeType === "update"){
       updateNotice(this.form).then(res=>{
         if(res.msg === "操作成功"){
           this.$modal.msgSuccess("修改成功！");
         }else{
           this.$modal.msgError("修改失败！");
         }
         this.$router.go(-1);
       });
     }else{
       this.form.sid = this.$route.query.uid;
       addNotice(this.form).then(res=>{
         if(res.msg === "操作成功"){
           this.$modal.msgSuccess("添加成功！");
         }else{
           this.$modal.msgError("添加失败！");
         }
         this.$router.go(-1);
       })
     }
    },
  //上传附件
  handleRemove(file, fileList) {
    console.log(file, fileList)
  },
  handlePreview(file) {
    console.log(file)
  },
  handleExceed(files, fileList) {
    this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
  },
  beforeRemove(file, fileList) {
    return this.$confirm(`确定移除 ${file.name}？`)
  }
  }
};
</script>
<style>
.form-input{
  width: 500px;
  display: inline-block;
}
.dialog-footer{
  margin-left: 100px;
}
</style>
