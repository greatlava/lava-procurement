<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="2">
        <router-link :to="'/tender/drawExpert?sid='+sid">
          <el-button type="primary" plain icon="el-icon-plus" size="mini">抽取专家</el-button>
        </router-link>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="committeeList">
      <el-table-column label="序号" align="center" type="index" />
      <el-table-column label="姓名" align="center" prop="pbName" />
      <el-table-column label="性别" align="center" prop="pbSex" />
      <el-table-column label="身份证号" align="center" prop="pbIdCard" />
      <el-table-column label="联系方式" align="center" prop="pbPhone" />
      <el-table-column label="专家类型" align="center" prop="pbGenre" />
      <el-table-column label="专家类别" align="center" prop="pbTypes" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改评标委员会对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="抽取申请ID" prop="xid">
          <el-input v-model="form.xid" placeholder="请输入抽取申请ID" />
        </el-form-item>
        <el-form-item label="姓名" prop="pbName">
          <el-input v-model="form.pbName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="身份证号" prop="pbIdcard">
          <el-input v-model="form.pbIdcard" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="联系方式" prop="pbPhone">
          <el-input v-model="form.pbPhone" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="专家类型" prop="pbGenre">
          <el-input v-model="form.pbGenre" placeholder="请输入专家类型" />
        </el-form-item>
        <el-form-item label="专家类别" prop="pbTypes">
          <el-input v-model="form.pbTypes" placeholder="请输入专家类别" />
        </el-form-item>
        <el-form-item label="专家ID" prop="jid">
          <el-input v-model="form.jid" placeholder="请输入专家ID" />
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
  import {
    listCommittee,
    getCommittee,
    delCommittee,
    addCommittee,
    updateCommittee
  } from "@/api/system/tender/committee";

  export default {
    data() {
      return {
        sid: null,
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
        // 评标委员会表格数据
        committeeList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          xid: null,
          pbName: null,
          pbSex: null,
          pbIdcard: null,
          pbPhone: null,
          pbGenre: null,
          pbTypes: null,
          jid: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {}
      };
    },
    created() {
      this.sid = this.$route.query.sid;
      this.getList();
    },
    methods: {
      /** 查询评标委员会列表 */
      getList() {
        this.loading = true;
        listCommittee(this.queryParams).then(response => {
          this.committeeList = response.rows;
          this.total = response.total;
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
          pbId: null,
          xid: null,
          pbName: null,
          pbSex: null,
          pbIdcard: null,
          pbPhone: null,
          pbGenre: null,
          pbTypes: null,
          jid: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.pbId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加评标委员会";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const pbId = row.pbId || this.ids
        getCommittee(pbId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改评标委员会";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.pbId != null) {
              updateCommittee(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addCommittee(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const pbIds = row.pbId || this.ids;
        this.$modal.confirm('是否确认删除评标委员会编号为"' + pbIds + '"的数据项？').then(function() {
          return delCommittee(pbIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/committee/export', {
          ...this.queryParams
        }, `committee_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
