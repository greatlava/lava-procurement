<template>
  <div class="app-container">
      <el-descriptions  class="margin-top" title="招标项目/查看" :column="2" :size="size" border>
        <el-descriptions-item >
          <template slot="label" >
            招标项目编号
          </template>
          {{queryParams.sCode}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            招标项目名称
          </template>
          <template>
            <span  v-if="routeType ==='details'"> {{queryParams.sName}}</span>
            <el-input id="input-common"  v-model:value="queryParams.sName"  v-if="routeType ==='update' || routeType ==='bidding'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            招标方式
          </template>
          <template slot="default">
            <el-radio v-model="queryParams.sWay" value="1" label="1">公开招标</el-radio>
            <el-radio v-model="queryParams.sWay" value="2" label="2">邀请招标</el-radio>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            是否招标
          </template>
          <template slot="default">
            <el-radio v-model="queryParams.sMust" label="1">依法必招</el-radio>
            <el-radio v-model="queryParams.sMust" label="2">自主招标</el-radio>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            资格审查方式
          </template>
          <template slot="default">
            <el-radio v-model="queryParams.sSway" label="1">资格预审</el-radio>
            <el-radio v-model="queryParams.sSway" label="2">资格后审</el-radio>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            业务类型
          </template>
          <template slot="default">
            <el-select v-model="queryParams.sType" placeholder="请选择">
              <el-option
                v-for="item in dict.type.ppm_procurement_plan"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            项目预算
          </template>
          <template>
            <span  v-if="routeType ==='details'"> {{queryParams.sBudget}}</span>
            <el-input id="input-common1"  v-model:value="queryParams.sBudget"  v-if="routeType ==='update' || routeType ==='bidding'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item/>

        <el-descriptions-item label="招标人信息"/>
        <el-descriptions-item/>
        <el-descriptions-item>
          <template slot="label">
            招标单位
          </template>
          <template>
            <span  v-if="routeType ==='details'"> {{queryParams.sUnit}}</span>
            <el-input id="input-common2"  v-model:value="queryParams.sUnit"  v-if="routeType ==='update' || routeType ==='bidding'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            联系人
          </template>
          <template>
            <span  v-if="routeType ==='details'"> {{queryParams.sPerson}}</span>
            <el-input id="input-common3"  v-model:value="queryParams.sPerson"  v-if="routeType ==='update' || routeType ==='bidding'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            电话
          </template>
          <template>
            <span  v-if="routeType ==='details'"> {{queryParams.sPhone}}</span>
            <el-input id="input-common4"  v-model:value="queryParams.sPhone"  v-if="routeType ==='update' || routeType ==='bidding'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            邮箱
          </template>
          <template>
            <span  v-if="routeType ==='details'"> {{queryParams.email}}</span>
            <el-input id="input-common5"  v-model:value="queryParams.email"  v-if="routeType ==='update' || routeType ==='bidding'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            地址
          </template>
          <template>
            <span  v-if="routeType ==='details'"> {{queryParams.sAddress}}</span>
            <el-input id="input-common6"  v-model:value="queryParams.sAddress"  v-if="routeType ==='update' || routeType ==='bidding'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"/>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            相关文件
          </template>
          <el-upload
            class="upload-demo"
            action="http://127.0.0.1:9300/statics/"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            :multiple="true"
            :limit="3"
            :on-exceed="handleExceed"
            :file-list="fileList"
          >
            <el-button size="small" type="primary">点击上传</el-button>
            <span slot="tip" class="el-upload__tip"> 支持文件格式：pdf.xis......</span>
          </el-upload>
        </el-descriptions-item>
        <el-descriptions-item/>
        <el-descriptions-item label="标的清单"/>
        <el-descriptions-item/>
      </el-descriptions>
  </div>
</template>

<script>
import { getTender,updateTender } from '@/api/system/tender'

export default {
  dicts:["ppm_procurement_plan"],
  name: 'Tender',
  data() {
    return {
      size:"0",
      fileList:[],
      routeType:"",
      queryParams:{
        sid:0,
        sCode:"",
        sName:"",
        sWay: "0",
        sMust:"0",
        sSway: "0",
        sType: "0",
        sBudget:"",
        sUnit:"",
        sPerson:"",
        sPhone:"",
        email:"",
        sAddress:""
      }
    }
  },
  created() {
    this.routeType = this.$route.query.type;
    this.queryParams.sid =this.$route.query.sid;
    this.getList(this.queryParams.sid);
  },
  methods: {
    getList(sid) {
      this.loading = false
      getTender(sid).then(response=>{
          this.queryParams.sCode=response.data.sCode;
          this.queryParams.sName=response.data.sName;
          if(response.data.sWay != null){
            this.queryParams.sWay=response.data.sWay.toString();
          }
        if(response.data.sMust != null){
          this.queryParams.sMust=response.data.sMust.toString();
        }
        if(response.data.sType != null){
          this.queryParams.sType=response.data.sType.toString();
        }
        if(response.data.sSway != null){
          this.queryParams.sSway=response.data.sSway.toString();
        }
          this.queryParams.sBudget=response.data.sBudget;
          this.queryParams.sUnit=response.data.sUnit;
          this.queryParams.sPerson=response.data.sPerson;
          this.queryParams.sPhone=response.data.sPhone;
          this.queryParams.email=response.data.email;
          this.queryParams.sAddress=response.data.sAddress;
      });
    },
    updateInfo() {
      updateTender(this.queryParams).then(res=>{
        this.$modal.msgSuccess(res.msg);
        this.$router.push("/tender/tender1");//回到列表
      });
    }  ,
    //上传附件
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file)
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    }
  }
}
</script>

<style>
.my-label {
  color: black;
  font-weight: bold;
}
#input-common,#input-common1,#input-common2,#input-common3,#input-common4,#input-common5,#input-common6{
  border: none;
}

</style>

