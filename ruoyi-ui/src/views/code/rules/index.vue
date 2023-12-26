<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="目标表单" prop="targetForm">
        <el-input
          v-model="queryParams.targetForm"
          placeholder="请输入目标表单"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编号前缀" prop="prefix">
        <el-input
          v-model="queryParams.prefix"
          placeholder="请输入编号前缀"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="操作时间" prop="updateTime">
        <el-date-picker
          v-model="queryParams.updateTime"
          type="date"
          placeholder="选择日期">
        </el-date-picker>
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
          v-hasPermi="['system:rules:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:rules:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="rulesList" @selection-change="handleSelectionChange">
      <el-table-column label="序号" align="center"  width="80">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="目标表单" align="center" prop="targetForm"/>
      <el-table-column label="编号前缀" align="center" prop="prefix"/>
      <el-table-column label="流水号" align="center" prop="serialNumber"/>
      <el-table-column label="步长" align="center" prop="step"/>
      <el-table-column label="编号规则" align="center" prop="codeRules" width="220"/>
      <el-table-column label="操作人" align="center" prop="operator"/>
      <el-table-column label="操作时间" align="center" prop="updateTime"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:rules:edit']"
          >修改
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog title="添加编号规则" :visible.sync="open" width="500px" append-to-body>
      <el-form style="display: flex; flex-wrap: wrap; justify-content: space-evenly" :rules="rules" :inline="true"
               label-position="top" ref="updateForm" :model="form" label-width="80px">
        <el-form-item label="目标表单" prop="targetForm">
          <el-input v-model="form.targetForm" placeholder="请输入目标表单"/>
        </el-form-item>
        <el-form-item label="编号前缀" prop="prefix">
          <el-input v-model="form.prefix" placeholder="请输入编号前缀"/>
        </el-form-item>
        <el-form-item label="流水号" prop="serialNumber">
          <el-input-number :step="1" :min="1" :max="10" step-strictly v-model="form.serialNumber"></el-input-number>
        </el-form-item>
        <el-form-item label="步长" prop="step">
          <el-input-number :step="1" :min="1" :max="10" step-strictly v-model="form.step"></el-input-number>
        </el-form-item>
        <el-form-item label="编号规则" prop="codeRules">
          <el-input disabled :value="str"/>
        </el-form-item>
        <el-form-item label="操作人" prop="operator">
          <el-input disabled value="系统自动生成"/>
        </el-form-item>
        <el-form-item label="时间规则">
          <el-checkbox-group @change="dateClick" v-model="checkTimeVule">
            <el-checkbox label="YYYY" name="year"></el-checkbox>
            <el-checkbox label="MM" name="MM"></el-checkbox>
            <el-checkbox label="DD" name="DD"></el-checkbox>
            <el-checkbox label="HH" name="HH"></el-checkbox>
            <el-checkbox label="mm" name="mm"></el-checkbox>
            <el-checkbox label="SS" name="SS"></el-checkbox>
          </el-checkbox-group>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('updateForm')">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!--    修改规则     -->
    <el-drawer
      title="修改编号规则"
      :before-close="handleClose"
      :visible.sync="show.edit"
      direction="rtl"
      custom-class="demo-drawer"
      size="29%"
      ref="drawer"
    >
      <div class="demo-drawer__content">
        <el-form ref="form" style="display: flex; flex-wrap: wrap; justify-content: space-between" size="small"
                 :rules="rules"
                 :inline="true" label-position="top" :model="form">
          <el-form-item label="目标表单" prop="targetForm">
            <el-input style="width: 200px" v-model="form.targetForm" disabled/>
          </el-form-item>
          <el-form-item label="编号前缀" prop="prefix">
            <el-input style="width: 250px" v-model="form.prefix"/>
          </el-form-item>
          <el-form-item label="时间规则">
            <el-checkbox-group v-model="checkTimeVule">
              <el-checkbox label="YYYY">YYYY</el-checkbox>
              <el-checkbox label="MM" name="MM">MM</el-checkbox>
              <el-checkbox label="DD" name="DD">DD</el-checkbox>
              <el-checkbox label="HH" name="HH">HH</el-checkbox>
              <el-checkbox label="mm" name="mm">mm</el-checkbox>
              <el-checkbox label="SS" name="SS">ss</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="流水号" prop="serialNumber">
            <el-input-number :step="1" :min="1" :max="10" step-strictly v-model="form.serialNumber"></el-input-number>
          </el-form-item>
          <el-form-item label="步长" prop="step">
            <el-input-number :step="1" :min="1" :max="10" step-strictly v-model="form.step"></el-input-number>
          </el-form-item>
          <el-form-item label="编号生成规则">
            <el-input disabled :value="str"/>
          </el-form-item>
          <el-form-item label="编号预览">
            <span>{{ previewTime }}</span>
          </el-form-item>
        </el-form>
        <div slot="footer" class="demo-drawer__footer">
          <el-button @click="cancel">取 消</el-button>
          <el-button type="primary" @click="submitForm('form')" :loading="loading">确定</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import {listRules, getRules, delRules, addRules, updateRules} from "@/api/code/rules";
import Log from "@/views/monitor/job/log.vue";

export default {
  name: "Rules",
  data() {
    return {
      formLabelWidth: '80px',
      show: {
        edit: false
      },
      // 遮罩层
      loading: true,
      newDateRulse: [],
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
      // 【请填写功能名称】表格数据
      rulesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        targetForm: null,
        prefix: '',
        serialNumber: null,
        step: null,
        codeRules: null,
        operator: null,
        year: null,
        mouth: null,
        day: null,
        hour: null,
        minute: null,
        second: null,
        updateTime: null
      },
      mantissa: null,
      checkTimeVule: [],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        prefix: [
          {required: true, message: '请输入前缀', trigger: 'blur'}
        ],
        targetForm: [{required: true, message: '请输入目标表单', trigger: 'blur'}],
        serialNumber: [
          {
            required: true, message: '请输入流水号', trigger: 'blur'
          }],
        step: [
          {
            required: true, message: '请输入步长', trigger: 'blur'
          }]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listRules(this.queryParams).then(response => {
        this.rulesList = response.rows;
        this.total = response.total;
        this.loading = false;
      }).catch(err => {
        this.$modal.msgError("服务器出错，请联系管理后重试！！")
      });
    },
    dateClick() {
      console.log("222")
    },
    handleClose() {
      this.show.edit = false;
      this.open = false;
      this.checkTimeVule = [];
      this.reset();
    },
    // 取消按钮
    cancel() {
      this.show.edit = false;
      this.open = false;
      this.checkTimeVule = [];
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        targetForm: null,
        prefix: null,
        serialNumber: null,
        step: null,
        codeRules: null,
        operator: null,
        updateTime: null,
        year: null,
        mouth: null,
        day: null,
        hour: null,
        minute: null,
        second: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      this.$modal.loading("加载中.....");
      getRules(id).then(response => {
        this.$modal.closeLoading();
        this.form = response.data;
        this.show.edit = true;
        this.splitDateString();
      }).catch(err => {
        this.$modal.closeLoading();
        this.$modal.msgError("服务器出错，请联系管理后重试！！")
      });
    },
    /** 提交按钮 */
    submitForm(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          if (this.checkTimeVule.length == 0) {
            this.$message.error('请至少选择一项时间规则');
            return;
          }
          this.splitDate();
          if (this.form.id != null) {
            this.$modal.loading("修改中.....");
            updateRules(this.form).then(response => {
              this.$modal.closeLoading();
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.show.edit = false;
              this.getList();
            }).catch(err => {
              this.$modal.closeLoading();
              this.$modal.msgError("服务器出错，请联系管理后重试！！")
            });
          } else {
            listRules({targetForm: this.form.targetForm}).then((res => {
              if (res.rows[0]) {
                this.$modal.confirm("检测你的目标表单已存在是否需要覆盖？").then(() => {
                  this.form.id = res.rows[0].id;
                  this.$modal.loading("修改中");
                  updateRules(this.form).then(response => {
                    this.$modal.closeLoading();
                    this.$modal.msgSuccess("修改成功");
                    this.open = false;
                    this.show.edit = false;
                    this.reset();
                    this.getList();
                  }).catch(() => {
                    this.$modal.closeLoading();
                    this.$modal.msgError("服务器出错，请联系管理后重试！！")
                  });
                }).catch(() => {
                  this.open = false;
                  this.reset();
                  return;
                })
              } else {
                let number = "";
                for (let i = 1; i <= this.form.serialNumber; i++) {
                  if (this.form.serialNumber == i) {
                    number += "1";
                    break;
                  }
                  number += "0";
                }
                this.form.maxMantissa = number;
                this.$modal.loading("新增中......");
                addRules(this.form).then(response => {
                  this.$modal.closeLoading();
                  this.$modal.msgSuccess("新增成功");
                  this.open = false;
                  this.getList();
                }).catch(err => {
                  this.$modal.closeLoading();
                  this.$modal.msgError("服务器出错，请联系管理后重试！！")
                });
              }
            }))
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除编号为"' + ids + '"的数据项？').then(function () {
        return delRules(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ppm/rules/export', {
        ...this.queryParams
      }, `rules_${new Date().getTime()}.xlsx`)
    },
    splitDateString() {
      const resultArray = [];
      resultArray.push(this.form.year)
      resultArray.push(this.form.mouth)
      resultArray.push(this.form.day)
      resultArray.push(this.form.hour)
      resultArray.push(this.form.minute)
      resultArray.push(this.form.second)
      this.checkTimeVule = resultArray;
    },
    splitDate() {
      this.form.prefix = this.form.prefix == null ? '' : this.form.prefix;
      this.form.year = this.checkTimeVule.includes("YYYY") == true ? "YYYY" : '';
      this.form.mouth = this.checkTimeVule.includes("MM") == true ? "MM" : '';
      this.form.day = this.checkTimeVule.includes("DD") == true ? "DD" : '';
      this.form.hour = this.checkTimeVule.includes("HH") == true ? "HH" : '';
      this.form.minute = this.checkTimeVule.includes("mm") == true ? "mm" : '';
      this.form.second = this.checkTimeVule.includes("SS") == true ? "SS" : '';
    }
  },
  computed: {
    str() {
      this.splitDate()
      let number = '';

      for (let i = 1; i <= this.form.serialNumber; i++) {
        if (i == this.form.serialNumber) {
          number += '1';
        } else {
          number += '0';
        }

      }
      let s = this.form.prefix + this.form.year + this.form.mouth + this.form.day + this.form.hour + this.form.minute + this.form.second + number;
      this.form.codeRules = s;
      return s;
    },
    previewTime() {
      // this.splitDate();
      let s = this.form.prefix;
      const now = new Date();
      if (this.form.year != null && this.form.year != '') {
        s += now.getFullYear();
      }
      if (this.form.mouth != null && this.form.mouth != '') {
        s += now.getMonth() + 1;
      }
      if (this.form.day != null && this.form.day != '') {
        s += now.getDate();
      }
      if (this.form.hour != null && this.form.hour != '') {
        s += now.getHours();
      }
      if (this.form.minute != null && this.form.minute != '') {
        s += now.getMinutes();
      }
      if (this.form.second != null && this.form.second != '') {
        s += now.getSeconds();
      }
      s += this.calculate_mantissa;
      return s;
    },
    calculate_mantissa() {
      return this.form.codeRules.substring(this.form.codeRules.length - this.form.serialNumber);
    }
  }
};
</script>

<style scoped>
.demo-drawer__content {
  padding: 20px;
}
</style>
