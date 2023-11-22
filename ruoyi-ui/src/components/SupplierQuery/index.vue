<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-row type="flex" justify="start" align="middle" gutter="15">
        <el-form-item label="供应商名称" prop="field107">
          <el-input v-model="formData.field103" placeholder="请输入供应商名称" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="机构类型" prop="field108">
          <el-input v-model="formData.field102" placeholder="请输入机构类型" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item size="medium">
          <el-button type="primary" @click="query">查询</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import sqe from '@/views/supplier/sqe/index.vue';

export default {
  name: 'SupplierQuery',
  // 其他组件定义
  components: {
    sqe
  },
  props: [],
  data() {
    return {
      formData: {
        field103: undefined,
        field102: undefined
      },
      rules: {
        field103: [],
        field102: []
      }
    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() {
  },
  methods: {
    query() {
      this.$nextTick(() => {
        this.$refs['elForm'].validate(valid => {
          if (valid) {
            this.$emit("updateData", this.formData)
          }
        })
      })
    },
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    }
  }
}

</script>
