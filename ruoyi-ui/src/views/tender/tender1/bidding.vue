<template>
<div>
  <div class="div1">
    <el-steps :active="index" simple>
      <el-step title="招标" icon="el-icon-edit" @click="selected(1)"></el-step>
      <el-step title="投标" icon="el-icon-message" @click="selected(2)"></el-step>
      <el-step title="开标" icon="el-icon-connection" @click="selected(3)"></el-step>
      <el-step title="评标" icon="el-icon-chat-line-square" @click="selected(4)"></el-step>
      <el-step title="定标" icon="el-icon-position" @click="selected(5)"></el-step>
    </el-steps>
  </div>
  <div v-if="index === 1" class="div2">
    <el-tabs v-model="activeName" >
      <el-tab-pane label="招标项目" name="subject">
        <tender-details></tender-details>
        <device-details></device-details>
      </el-tab-pane>
      <el-tab-pane label="招标公告" name="notice">
        <tender-notice></tender-notice>
      </el-tab-pane>
      <el-tab-pane label="招标文件" name="file">招标文件</el-tab-pane>
    </el-tabs>
  </div>
</div>

</template>

<script>
import TenderDetails from '@/components/tender/tenderDetails.vue'
import DeviceDetails from '@/components/device/deviceDetails.vue'
import TenderNotice from "@/components/tender/tenderNotice.vue"

export default {
  components: {
    'tender-details': TenderDetails,
    'device-details': DeviceDetails,
    'tender-notice': TenderNotice,
  },
  data() {
    return {
      index: 1, // 当前活动的步骤索引
      activeName: 'subject'
    };
  },
  methods: {
    selected(stepIndex) {
      this.index = stepIndex;
    }
  }
};
</script>
<style>
.div1{
  width: 80%;
  height: 100%;
  background-color: rgba(221, 221, 221, 0.99);
  margin: 20px auto;
}
.div2{
  width: 80%;
  height: 100%;
  margin: 20px auto;
}
</style>
