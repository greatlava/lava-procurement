<template>
  <div class="app-container">
    <tender-details></tender-details>
    <device-details></device-details>
    <div style="margin: 10px 0px 10px 20px">
      <el-button type="primary" @click="submit">提交</el-button>
      <el-button @click="over">取消</el-button>
    </div>

  </div>
</template>

<script>
import TenderDetails from '@/components/tender/tenderDetails.vue'
import DeviceDetails from '@/components/device/deviceDetails.vue'

export default {
  components: {
    'tender-details': TenderDetails,
    'device-details': DeviceDetails
  },
  methods:{
    over(){
      this.$router.go(-1);
    },
    submit() {
      // 调用子组件的修改方法
      this.$children[0].updateInfo();
    }
  }
}
</script>

