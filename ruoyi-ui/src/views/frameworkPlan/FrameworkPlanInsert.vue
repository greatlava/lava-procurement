
<template>
  <el-form :model="formData" ref="vForm" :rules="rules" label-position="left" label-width="120px"
           size="medium" @submit.native.prevent>
    <div class="static-content-item" v-show="false">
      <el-divider direction="horizontal"></el-divider>
    </div>
    <div class="card-container">
      <el-card style="{width: 100% !important}" shadow="never">
        <div slot="header" class="clear-fix">
          <span>基本信息</span>
          <i class="float-right el-icon-arrow-down"></i>
        </div>
        <el-row>
          <el-col :span="12" class="grid-cell">
            <el-form-item label="计划编码" prop="计划编码" class="label-center-align">
              <el-input v-model="formData.计划编码" :disabled="true" type="text" placeholder="自动生成"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" class="grid-cell">
            <el-form-item label="计划名称" prop="jhName" class="required label-center-align">
              <el-input v-model="formData.jhName" type="text" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" class="grid-cell">
            <el-form-item label="业务类型" prop="BusinessType" class="required label-center-align">
              <el-select v-model="formData.BusinessType" class="full-width-input" clearable>
                <el-option v-for="(item, index) in BusinessTypeOptions" :key="index" :label="item.label"
                           :value="item.value" :disabled="item.disabled"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" class="grid-cell">
            <el-form-item label="采购方式" prop="jhPmethod" class="required label-center-align">
              <el-select v-model="formData.jhPmethod" class="full-width-input" clearable>
                <el-option v-for="(item, index) in jhPmethodOptions" :key="index" :label="item.label"
                           :value="item.value" :disabled="item.disabled"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" class="grid-cell">
            <el-form-item label="计划预算" prop="jhYu" class="required label-center-align">
              <el-select v-model="formData.jhYu" class="full-width-input" clearable>
                <el-option v-for="(item, index) in jhYuOptions" :key="index" :label="item.label"
                           :value="item.value" :disabled="item.disabled"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" class="grid-cell">
            <el-form-item label="供应商" prop="supplier" class="required label-center-align">
              <el-select v-model="formData.supplier" class="full-width-input" clearable>
                <el-option v-for="(item, index) in supplierOptions" :key="index" :label="item.label"
                           :value="item.value" :disabled="item.disabled"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" class="grid-cell">
            <el-form-item label="创建人" prop="jhFounder" class="required label-center-align">
              <el-input v-model="formData.jhFounder" type="text" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12" class="grid-cell">
            <el-form-item label="创建部门" prop="dept" class="required label-center-align">
              <el-input v-model="formData.dept" type="text" clearable></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24" class="grid-cell">
            <el-form-item label="上传附件" prop="annex" class="label-center-align">
              <template #label><span class="custom-label"><i class="el-icon-upload"></i>上传附件
                </span></template>
              <el-upload :file-list="annexFileList" :headers="annexUploadHeaders" :data="annexUploadData"
                         list-type="picture-card" show-file-list :limit="3"> <template #default><i
                class="el-icon-plus"></i></template></el-upload>
            </el-form-item>
          </el-col>
          <el-col :span="24" class="grid-cell">
            <el-form-item label="备注" prop="notes" class="label-center-align">
              <el-input type="textarea" v-model="formData.notes" rows="3"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-card>
    </div>
    <div class="static-content-item" v-show="false">
      <el-divider direction="horizontal"></el-divider>
    </div>
    <div class="card-container">
      <el-card style="{width: 100% !important}" shadow="never">
        <div slot="header" class="clear-fix">
          <span>项目信息</span>
          <i class="float-right el-icon-arrow-down"></i>
        </div>
        <div class="static-content-item">
          <el-button>添加项目</el-button>
        </div>
      </el-card>
    </div>
  </el-form>

</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
      formData: {
        计划编码: "自动生成",
        jhName: "",
        BusinessType: "",
        jhPmethod: "",
        jhYu: "",
        supplier: "",
        jhFounder: "",
        dept: "",
        annex: null,
        notes: "",
      },
      rules: {
        jhName: [{
          required: true,
          message: '字段值不可为空',
        }],
        BusinessType: [{
          required: true,
          message: '字段值不可为空',
        }],
        jhPmethod: [{
          required: true,
          message: '字段值不可为空',
        }],
        jhYu: [{
          required: true,
          message: '字段值不可为空',
        }],
        supplier: [{
          required: true,
          message: '字段值不可为空',
        }],
        jhFounder: [{
          required: true,
          message: '字段值不可为空',
        }],
        dept: [{
          required: true,
          message: '字段值不可为空',
        }],
      },
      BusinessTypeOptions: [],
      jhPmethodOptions: [],
      jhYuOptions: [],
      supplierOptions: [],
      annexFileList: [],
      annexUploadHeaders: {},
      annexUploadData: {},
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['vForm'].validate(valid => {
        if (!valid) return
        //TODO: 提交表单
      })
    },
    resetForm() {
      this.$refs['vForm'].resetFields()
    }
  }
}

</script>

<style lang="scss" scoped>
.el-input-number.full-width-input,
.el-cascader.full-width-input {
  width: 100% !important;
}

.el-form-item--medium {
  .el-radio {
    line-height: 36px !important;
  }

  .el-rate {
    margin-top: 8px;
  }
}

.el-form-item--small {
  .el-radio {
    line-height: 32px !important;
  }

  .el-rate {
    margin-top: 6px;
  }
}

.el-form-item--mini {
  .el-radio {
    line-height: 28px !important;
  }

  .el-rate {
    margin-top: 4px;
  }
}

.clear-fix:before,
.clear-fix:after {
  display: table;
  content: "";
}

.clear-fix:after {
  clear: both;
}

.float-right {
  float: right;
}

</style>

<style lang="scss" scoped>
div.table-container {
  table.table-layout {
    width: 100%;
    table-layout: fixed;
    border-collapse: collapse;

    td.table-cell {
      display: table-cell;
      height: 36px;
      border: 1px solid #e1e2e3;
    }
  }
}

div.tab-container {}

.label-left-align ::v-deep .el-form-item__label {
  text-align: left;
}

.label-center-align ::v-deep .el-form-item__label {
  text-align: center;
}

.label-right-align ::v-deep .el-form-item__label {
  text-align: right;
}

.custom-label {}

.static-content-item {
  min-height: 20px;
  display: flex;
  align-items: center;

  ::v-deep .el-divider--horizontal {
    margin: 0;
  }
}

</style>
