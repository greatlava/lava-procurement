<template>
<div>
  <div class="div1">
    <el-steps :active="currentStep" simple style="background: #e8f4ff;">
      <el-step title="招标" icon="el-icon-edit" @click.native="changeStep(0)"/>
      <el-step title="投标" icon="el-icon-message"  @click.native="changeStep(1)"/>
      <el-step title="开标" icon="el-icon-connection" @click.native="changeStep(2)"/>
      <el-step title="评标" icon="el-icon-chat-line-square" @click.native="changeStep(3)"/>
      <el-step title="定标" icon="el-icon-position" @click.native="changeStep(4)"/>
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
      <el-tab-pane label="组建评标委员会" name="expert" style="font-size: 16px">
       <expert></expert>
      </el-tab-pane>
      <el-tab-pane label="开标" name="kb" style="font-size: 16px">
         <div class="kb">
           <p>开标开始时间：<span>{{ this.queryParams.uKaiTime || '————' }}</span></p>
           <p>预计时间：<span style="color: red">2</span>小时</p>
           <p>进度：<span>{{ finish }}</span></p>
         </div>
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
        <candidate-notice></candidate-notice>
      </el-tab-pane>
      <el-tab-pane label="确定中标人" name="determineWin" style="font-size: 16px">
        <determine-win></determine-win>
      </el-tab-pane>
      <el-tab-pane label="中标结果公示" name="annWin" style="font-size: 16px">
        <winning-bid></winning-bid>
      </el-tab-pane>
      <el-tab-pane label="发送中标通知书" name="noticeWin" style="font-size: 16px">
        <winning-bid-notice></winning-bid-notice>
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
import CandidateNotice from "@/components/tender/candidateNotice.vue";
import WinningBid from "@/components/tender/winningBid.vue";
import WinningBidNotice from "@/components/tender/winningBidNotice.vue";
import {listNotice} from "@/api/system/tender/tenderNotice";

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
    'determineWin':DetermineWin,
    'candidateNotice':CandidateNotice,
    'winningBid':WinningBid,
    'winningBidNotice':WinningBidNotice,
  },
  data() {
    return {
      currentStep: 0, // 当前活动的步骤索引
      activeName: 'subject',
      activeName1:'getTender',
      activeName2:'expert',
      activeName3:'bidEval',
      activeName4:'annCandidate',
      queryParams:{
        sid:null,
        fjStatus:null,
        uKaiTime:null
      },
      noStep:false,
      finish:'',
    };
  },
  created() {
    this.queryParams.sid = this.$route.query.sid;
    this.getDetails();
  },
  methods: {
    changeStep(stepIndex) {
      console.log(stepIndex);
      if(stepIndex == 3 || stepIndex == 4){
         if(this.noStep){
             this.$alert("开标尚未开始！");
         }else{
           this.currentStep = stepIndex;
         }
      }else if(stepIndex == 0 ||  stepIndex == 1 || stepIndex == 2){
        this.currentStep = stepIndex;
      }

    },
    getDetails(){
      this.queryParams.fjStatus = 5;
      listNotice(this.queryParams).then(res=>{
        if(res.rows.length > 0){
          this.queryParams.uKaiTime = res.rows[0].uKaiTime;
          this.computeDate();
        }
      });
    },
    computeDate(){
      // 获取当前时间
      const now = new Date();//当前时间
      const date = new Date(`${this.queryParams.uKaiTime}`);//开标开始时间
      // 增加2小时
      const twoHours = 2 * 60 * 60 * 1000; // 2小时转换为毫秒
      const futureDate = new Date(date.getTime() + twoHours);//开标结束时间
      if(now > futureDate){
         this.finish ='已完成';
      }else{
        this.finish ='未开始';
        this.noStep = true;
      }
    }
  }
};
</script>
<style scoped>
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
.kb{
  text-align: left;
  width: 270px;
  margin: 0 auto;
}
</style>

