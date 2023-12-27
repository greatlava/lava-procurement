<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <right-toolbar  @queryTable="getList(queryParams.sid)"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="submissionList" width="100%">
      <el-table-column label="序号" width="55" align="center" type="index"/>
<!--      <el-table-column label="招标项目ID" align="center" prop="sid">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ scope.row.bidNotices[0].uEndTime }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="投标单位名称" align="center" width="300" prop="bsSuppliers">
        <template slot-scope="scope">
          <span>{{ scope.row.bsSuppliers[0].hName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系人" align="center" width="200" prop="tdPerson">
        <template slot-scope="scope">
          <span>{{ scope.row.bsSuppliers[0].bsOperator.ywName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系方式" align="center" width="200" prop="tdPhone">
        <template slot-scope="scope">
          <span>{{ scope.row.bsSuppliers[0].bsOperator.ywPhone}}</span>
        </template>
      </el-table-column>
      <el-table-column label="投标状态" align="center" width="200" prop="tdStatus">
        <template slot-scope="scope">
          <span>{{scope.row.tdStatus == 0?'成功':'失败'}}</span>
        </template>
      </el-table-column>
      <el-table-column label="成功递交时间" align="center" prop="tdSuccessTime" width="220">
        <template slot-scope="scope">
          <span>{{scope.row.tdSuccessTime}}</span>
        </template>
      </el-table-column>
      <el-table-column label="文件撤回时间" align="center" prop="tdFailTime" width="220">
        <template slot-scope="scope">
          <span>{{scope.row.tdFailTime==null?'----':scope.row.tdFailTime}}</span>
        </template>
      </el-table-column>
      <el-table-column label="附件" type="expand" width="180">
        <template slot-scope="scope">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="投标书">
              <div>
                <el-upload ref="upload" class="upload-demo" :limit="4" accept=".doc, .docx, .rar, .txt, .png, .jpg"
                           multiple
                           action="#"
                           :file-list="JSON.parse(scope.row.fjFiles)"
                           :auto-upload="false">
                </el-upload>
                <a :href="JSON.parse(scope.row.fjFiles)[0].url">
                  <el-button
                      size="small"
                      type="primary"
                      icon="el-icon-download"
                  >下载</el-button>
                </a>
              </div>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改投递标书对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="招标项目ID" prop="sid">
          <el-input v-model="form.sid" placeholder="请输入招标项目ID" />
        </el-form-item>
        <el-form-item label="投标人名称" prop="tdName">
          <el-input v-model="form.tdName" placeholder="请输入投标人名称" />
        </el-form-item>
        <el-form-item label="联系人" prop="tdPerson">
          <el-input v-model="form.tdPerson" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="联系方式" prop="tdPhone">
          <el-input v-model="form.tdPhone" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="成功递交时间" prop="tdSuccessTime">
          <el-date-picker clearable
                          v-model="form.tdSuccessTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择成功递交时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="文件撤回时间" prop="tdFailTime">
          <el-date-picker clearable
                          v-model="form.tdFailTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择文件撤回时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSubmission, getSubmission, delSubmission, addSubmission, updateSubmission ,findSubmission} from "@/api/system/tender/submission";
import {updateTender} from "@/api/system/tender/tender";

export default {
  name: "Submission",
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
      // 投递标书表格数据
      submissionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sid: null,
        tdName: null,
        tdPerson: null,
        tdPhone: null,
        tdStatus: null,
        tdSuccessTime: null,
        tdFailTime: null,
        tdIp: null
      },
      queryParams2:{
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
      }
    };
  },
  created() {
    this.queryParams.sid = this.$route.query.sid;
    this.getList(this.queryParams.sid);
  },
  methods: {
    /** 查询投递标书列表 */
    getList(sid) {
      this.loading = true;
      findSubmission(sid).then(res => {
        console.log(res,'res');
        this.submissionList = res.data;
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
        tdId: null,
        sid: null,
        tdName: null,
        tdPerson: null,
        tdPhone: null,
        tdStatus: null,
        tdSuccessTime: null,
        tdFailTime: null,
        tdIp: null
      };
      this.resetForm("form");
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tdId != null) {
            updateSubmission(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSubmission(this.form).then(response => {
              listSubmission(this.queryParams).then(res=>{
                //至少存在三个投标供应商
                if(res.rows.length > 2){
                  this.queryParams2.sid = this.$route.query.sid;
                   updateTender(this.queryParams2).then(re=>{});
                }
              });
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    handleInfo(){
     this.open = true;
    }
  }
};
</script>
