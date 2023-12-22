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
       <bid_submission></bid_submission>
     </el-tab-pane>
   </el-tabs>
  </div>
  <div v-if="currentStep === 2" class="div2">
    <el-tabs v-model="activeName2">
      <el-tab-pane label="组件评标委员会" name="expert" style="font-size: 16px">
       <expert></expert>
      </el-tab-pane>
      <el-tab-pane label="开标" name="kb" style="font-size: 16px">

      </el-tab-pane>
    </el-tabs>
  </div>
  <div v-if="currentStep === 3" class="div2">
    <el-tabs v-model="activeName3">
      <el-tab-pane label="评标" name="bidEval" style="font-size: 16px">
        <bid-eval></bid-eval>
      </el-tab-pane>
    </el-tabs>
  </div>
  <div v-if="currentStep === 4" class="div2">
    <el-tabs v-model="activeName4">
      <el-tab-pane label="候选人公示" name="annCandidate" style="font-size: 16px">
        <expert></expert>
      </el-tab-pane>
      <el-tab-pane label="确定中标人" name="determineWin" style="font-size: 16px">
        <determine-win></determine-win>
      </el-tab-pane>
      <el-tab-pane label="中标结果公示" name="annWin" style="font-size: 16px">
        <expert></expert>
      </el-tab-pane>
      <el-tab-pane label="发送中标通知书" name="noticeWin" style="font-size: 16px">
        <expert></expert>
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
import Bid_submission from "@/components/tender/bid_submission.vue";
import Expert from "@/components/tender/expert.vue";
import BidEval from "@/components/tender/bidEval.vue";
import DetermineWin from "@/components/tender/determineWin.vue";

export default {
  components: {
    'tender-details': TenderDetails,
    'device-details': DeviceDetails,
    'tender-notice': TenderNotice,
    'tender-document': TenderDocument,
    'getTender':GetTender,
    'bid_submission':Bid_submission,
    'expert':Expert,
    'bidEval':BidEval,
    'determineWin':DetermineWin
  },
  data() {
    return {
      currentStep: 0, // 当前活动的步骤索引
      activeName: 'subject',
      activeName1:'getTender',
      activeName2:'expert',
      activeName3:'bidEval',
      activeName4:'annCandidate',
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

