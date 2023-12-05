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
        >新增</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="noticeList">
      <el-table-column label="序号" align="center" prop="orderNum" />
      <el-table-column label="公告标题" align="center" prop="uTitle" />
      <el-table-column label="关联项目" align="center" prop="uProject" />
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
            icon="el-icon-edit"
            @click="handleUpdate(scope.row,1)"
          >编辑</el-button>
          <el-button v-if="scope.row.fjStatus === 1 || scope.row.fjStatus === 4"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
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
      <el-form ref="form"  :model="form" :rules="rules" label-width="100px">
        <el-form-item label="公告标题" prop="uTitle">
          <el-input v-model="form.uTitle" placeholder="请输入公告标题" />
        </el-form-item>
        <el-form-item label="关联项目" prop="uProject" class="form-input">
          <el-input v-model="form.uProject" placeholder="请输入关联项目"/>
        </el-form-item>
        <el-form-item label="项目资金" prop="uMoney" class="form-input">
          <el-input v-model="form.uMoney" placeholder="请输入项目资金"/>
        </el-form-item>
        <el-form-item label="标注获取时间" prop="uGetTime" class="form-input">
          <el-date-picker clearable
                          v-model="form.uGetTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择标注获取时间">
          </el-date-picker>
        </el-form-item >
        <el-form-item label="接受答疑时间" prop="uAcceptTime" class="form-input">
          <el-date-picker clearable
                          v-model="form.uAcceptTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择接受答疑时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="投标截止时间" prop="uEndTime" class="form-input">
          <el-date-picker clearable
                          v-model="form.uEndTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择投标截止时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开标时间" prop="uKaiTime" class="form-input">
          <el-date-picker clearable
                          v-model="form.uKaiTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择开标时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="附件" prop="fjAnnex">
        </el-form-item>
        <el-form-item label="内容" prop="fjRemark">
          <editor v-model="form.fjRemark" :min-height="192"/>
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
import { listNotice, getNotice, delNotice, addNotice, updateNotice } from "@/api/system/tenderNotice";

export default {
  dicts:["bid_notice_state"],
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
      open: false,
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
    this.queryParams.sid = this.$route.query.sid;
    this.getList();
  },
  methods: {
    // 取消按钮
    cancel() {
      this.open = false;
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
      this.open = true;
      this.title = "添加招标公告";
    },
    /** 修改按钮操作 */
    handleUpdate(row,num) {
      this.reset();
      const uid = row.uid || this.ids
      getNotice(uid).then(response => {
        this.form = response.data;
        this.open = true;
        if (num === 1){
          this.title = "修改招标公告";
        }else{
          this.title = "招标公告详情";
        }
      });
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
      row.fjStatus = 5;//改变状态
      this.$modal.confirm('是否确认发布招标公告编号为"' + row.uid + '"的数据项？').then(function() {
        return updateNotice(row);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("发布成功");
      }).catch(() => {});
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.uid != null) {
            updateNotice(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            this.form.sid = this.$route.query.sid;//确定对应招标项目
            addNotice(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
  }
};
</script>
<style>
.form-input{
  width: 500px;
  display: inline-block;
}
</style>
