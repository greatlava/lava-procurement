<template>
  <div>
    <el-form :model="purchaseContract" label-width="100px">
      <el-form-item label="合同编号">
        <el-input v-model="purchaseContract.contractNumber"></el-input>
      </el-form-item>
      <el-form-item label="供应商">
        <el-input v-model="purchaseContract.supplier"></el-input>
      </el-form-item>
      <el-form-item label="商品名称">
        <el-input v-model="purchaseContract.productName"></el-input>
      </el-form-item>
      <el-form-item label="数量">
        <el-input v-model.number="purchaseContract.quantity"></el-input>
      </el-form-item>
      <el-form-item label="单价">
        <el-input v-model.number="purchaseContract.unitPrice"></el-input>
      </el-form-item>
      <el-form-item label="总价">
        <el-input :value="purchaseContract.quantity * purchaseContract.unitPrice" disabled></el-input>
      </el-form-item>
      <el-form-item label="交货日期">
        <el-date-picker type="date" placeholder="选择日期" v-model="purchaseContract.deliveryDate"></el-date-picker>
      </el-form-item>
      <el-form-item label="备注">
        <el-input type="textarea" v-model="purchaseContract.remarks"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="generateContract">生成合同</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      purchaseContract: {
        contractNumber: '',
        supplier: '',
        productName: '',
        quantity: 0,
        unitPrice: 0,
        deliveryDate: '',
        remarks: ''
      }
    };
  },
  methods: {
    generateContract() {
      // 在这里执行生成合同的逻辑，可以将表单数据提交到后端或者进行其他处理
      console.log('生成合同', this.purchaseContract);
    }
  }
};
</script>
