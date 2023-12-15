<template>
<div>
  <div class="div1">
    <el-steps :active="currentStep" simple style="background: #e8f4ff;">
      <el-step title="招标" icon="el-icon-edit" @click.native="changeStep(0)"></el-step>
      <el-step title="投标" icon="el-icon-message"  @click.native="changeStep(1)"></el-step>
      <el-step title="开标" icon="el-icon-connection" @click.native="changeStep(2)"></el-step>
      <el-step title="评标" icon="el-icon-chat-line-square" @click.native="changeStep(3)"></el-step>
      <el-step title="定标" icon="el-icon-position" @click.native="changeStep(4)"></el-step>
    </el-steps>
  </div>
  <div v-if="currentStep === 0" class="div2">
    <el-tabs v-model="activeName" >
      <el-tab-pane label="招标项目" name="subject">
        <tender-details></tender-details>
        <device-details></device-details>
      </el-tab-pane>
      <el-tab-pane label="招标公告" name="notice">
        <tender-notice></tender-notice>
      </el-tab-pane>
      <el-tab-pane label="招标文件" name="file">
        <tender-document></tender-document>
      </el-tab-pane>
    </el-tabs>
  </div>
  <div v-if="currentStep === 1" class="div2">
   <el-tabs v-model="activeName1">
     <el-tab-pane label="获取标书" name="getTender" style="font-size: 16px">
       <get-tender></get-tender>
     </el-tab-pane>
     <el-tab-pane label="投递标书" name="operator" style="font-size: 16px">
       <tender-notice></tender-notice>
     </el-tab-pane>
   </el-tabs>
  </div>
</div>

</template>

<script>
import TenderDetails from '@/components/tender/tenderDetails.vue'
import DeviceDetails from '@/components/device/deviceDetails.vue'
import TenderNotice from "@/components/tender/tenderNotice.vue"
import TenderDocument from "@/components/tender/tenderDocument.vue";
import GetTender from "@/components/tender/getTender.vue";

export default {
  components: {
    'tender-details': TenderDetails,
    'device-details': DeviceDetails,
    'tender-notice': TenderNotice,
    'tender-document': TenderDocument,
    'getTender':GetTender,
  },
  data() {
    return {
      currentStep: 0, // 当前活动的步骤索引
      activeName: 'subject',
      activeName1:'getTender',
    };
  },
  methods: {
    changeStep(stepIndex) {
      console.log(stepIndex);
      this.currentStep = stepIndex;
    }
  }
};
</script>
<style>
.div1{
  width: 95%;
  height: 100%;
  background-color: rgba(221, 221, 221, 0.99);
  margin: 20px auto;
}
.div2{
  width: 95%;
  height: 100%;
  margin: 20px auto;
}
</style>

