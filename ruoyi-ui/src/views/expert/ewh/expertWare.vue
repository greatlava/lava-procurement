<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="专家姓名" prop="jName">
        <el-input
          v-model="queryParams.jName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="jShState" label-width="120px">
        <el-input
          v-model="queryParams.jShState"
          placeholder="请输入专家审核状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:expert:add']"
        >新增
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="expertList">
      <el-table-column label="序号" align="center" type="index"/>
      <el-table-column label="专家姓名" align="center" prop="jName"/>
      <el-table-column label="联系方式" align="center" prop="jSex"/>
      <el-table-column label="工作单位" align="center" prop="jIdentity"/>
      <el-table-column label="主评专业" align="center" prop="jPhone"/>
      <el-table-column label="所在区域" align="center" prop="email"/>
      <el-table-column label="审核状态" align="center" prop="jState"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:expert:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:expert:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改专家对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任职经历ID" prop="qid">
          <el-input v-model="form.qid" placeholder="请输入任职经历ID"/>
        </el-form-item>
        <el-form-item label="评标ID" prop="pbId">
          <el-input v-model="form.pbId" placeholder="请输入评标ID"/>
        </el-form-item>
        <el-form-item label="姓名" prop="jName">
          <el-input v-model="form.jName" placeholder="请输入姓名"/>
        </el-form-item>
        <el-form-item label="身份证号" prop="jIdentity">
          <el-input v-model="form.jIdentity" placeholder="请输入身份证号"/>
        </el-form-item>
        <el-form-item label="手机号" prop="jPhone">
          <el-input v-model="form.jPhone" placeholder="请输入手机号"/>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱"/>
        </el-form-item>
        <el-form-item label="健康状况" prop="jState">
          <el-input v-model="form.jState" placeholder="请输入健康状况"/>
        </el-form-item>
        <el-form-item label="工作单位" prop="jUnit">
          <el-input v-model="form.jUnit" placeholder="请输入工作单位"/>
        </el-form-item>
        <el-form-item label="是否退休" prop="jRetire">
          <el-input v-model="form.jRetire" placeholder="请输入是否退休"/>
        </el-form-item>
        <el-form-item label="通讯地址" prop="jAddress">
          <el-input v-model="form.jAddress" placeholder="请输入通讯地址"/>
        </el-form-item>
        <el-form-item label="银行卡号" prop="jCard">
          <el-input v-model="form.jCard" placeholder="请输入银行卡号"/>
        </el-form-item>
        <el-form-item label="身份证照" prop="jIdentityPhoto">
          <el-input v-model="form.jIdentityPhoto" placeholder="请输入身份证照"/>
        </el-form-item>
        <el-form-item label="证件照" prop="jDocumentsPhoto">
          <el-input v-model="form.jDocumentsPhoto" placeholder="请输入证件照"/>
        </el-form-item>
        <el-form-item label="开户行" prop="jBank">
          <el-input v-model="form.jBank" placeholder="请输入开户行"/>
        </el-form-item>
        <el-form-item label="专家审核状态" prop="jShState">
          <el-input v-model="form.jShState" placeholder="请输入专家审核状态"/>
        </el-form-item>
        <el-form-item label="意见" prop="jOpinion">
          <el-input v-model="form.jOpinion" type="textarea" placeholder="请输入内容"/>
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
import {listExpert, getExpert, delExpert, addExpert, updateExpert} from "@/api/system/expert";

export default {
  name: "Expert",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 专家表格数据
      expertList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        qid: null,
        pbId: null,
        jName: null,
        jSex: null,
        jIdentity: null,
        jPhone: null,
        email: null,
        jState: null,
        jUnit: null,
        jRetire: null,
        jAddress: null,
        jCard: null,
        jIdentityPhoto: null,
        jDocumentsPhoto: null,
        jBank: null,
        jShState: null,
        jOpinion: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询专家列表 */
    getList() {
      this.loading = true;
      listExpert(this.queryParams).then(response => {
        this.expertList = response.rows;
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
        jid: null,
        qid: null,
        pbId: null,
        jName: null,
        jSex: null,
        jIdentity: null,
        jPhone: null,
        email: null,
        jState: null,
        jUnit: null,
        jRetire: null,
        jAddress: null,
        jCard: null,
        jIdentityPhoto: null,
        jDocumentsPhoto: null,
        jBank: null,
        jShState: null,
        jOpinion: null
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加专家";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const jid = row.jid || this.ids
      getExpert(jid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改专家";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.jid != null) {
            updateExpert(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExpert(this.form).then(response => {
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
      const jids = row.jid || this.ids;
      this.$modal.confirm('是否确认删除专家编号为"' + jids + '"的数据项？').then(function () {
        return delExpert(jids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/expert/export', {
        ...this.queryParams
      }, `expert_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
