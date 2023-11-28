<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="风险项" prop="rmItem">
        <el-input
          v-model="queryParams.rmItem"
          placeholder="请输入风险项"
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
          v-hasPermi="['system:monitor:add']"
        >新增
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="monitorList">
      <el-table-column type="index" label="序号" align="center"/>
      <el-table-column label="合同编号" align="center" prop="eid"/>
      <el-table-column label="合同名称" align="center" prop="eid"/>
      <el-table-column label="供应商名称" align="center" prop="eid"/>
      <el-table-column label="风险说明" align="center" prop="rmIllustrate"/>
      <el-table-column label="发生时间" align="center" prop="rmHappenDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.rmHappenDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="风险项" align="center" prop="rmItem"/>
      <el-table-column label="风险处理状态" align="center" prop="rmStatus"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:monitor:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:monitor:remove']"
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

    <!-- 添加或修改供应商风险监控对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="合同ID" prop="eid">
          <el-input v-model="form.eid" placeholder="请输入合同ID"/>
        </el-form-item>
        <el-form-item label="风险说明" prop="rmIllustrate">
          <el-input v-model="form.rmIllustrate" placeholder="请输入风险说明"/>
        </el-form-item>
        <el-form-item label="发生日期" prop="rmHappenDate">
          <el-date-picker clearable
                          v-model="form.rmHappenDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择发生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="风险项" prop="rmItem">
          <el-input v-model="form.rmItem" placeholder="请输入风险项"/>
        </el-form-item>
        <el-form-item label="处理日期" prop="rmHandingDate">
          <el-date-picker clearable
                          v-model="form.rmHandingDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择处理日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="处理人" prop="rmHandingPerson">
          <el-input v-model="form.rmHandingPerson" placeholder="请输入处理人"/>
        </el-form-item>
        <el-form-item label="处理结果" prop="rmHandingResult">
          <el-input v-model="form.rmHandingResult" type="textarea" placeholder="请输入内容"/>
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
import {listMonitor, getMonitor, delMonitor, addMonitor, updateMonitor} from "@/api/system/riskMonitor";

export default {
  name: "Monitor",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 供应商风险监控表格数据
      monitorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        eid: null,
        rmIllustrate: null,
        rmHappenDate: null,
        rmItem: null,
        rmStatus: null,
        rmHandingDate: null,
        rmHandingPerson: null,
        rmHandingResult: null
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
    /** 查询供应商风险监控列表 */
    getList() {
      this.loading = true;
      listMonitor(this.queryParams).then(response => {
        console.log(response)
        this.monitorList = response.rows;
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
        rmId: null,
        eid: null,
        rmIllustrate: null,
        rmHappenDate: null,
        rmItem: null,
        rmStatus: null,
        rmHandingDate: null,
        rmHandingPerson: null,
        rmHandingResult: null
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
      this.title = "添加供应商风险监控";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const rmId = row.rmId || this.ids
      getMonitor(rmId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改供应商风险监控";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.rmId != null) {
            updateMonitor(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMonitor(this.form).then(response => {
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
      const rmIds = row.rmId || this.ids;
      this.$modal.confirm('是否确认删除供应商风险监控编号为"' + rmIds + '"的数据项？').then(function () {
        return delMonitor(rmIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
  }
};
</script>
