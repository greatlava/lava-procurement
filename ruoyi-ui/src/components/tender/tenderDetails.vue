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
            <span  v-if="routeType ==='details' || routeType ==='bidding'"> {{queryParams.sName}}</span>
            <el-input id="input-common"  v-model:value="queryParams.sName"  v-if="routeType ==='update'"/>
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
            项目预算（万元）
          </template>
          <template>
            <span  v-if="routeType ==='details'|| routeType ==='bidding'"> {{queryParams.sBudget}}</span>
            <el-input id="input-common1"  v-model:value="queryParams.sBudget"  v-if="routeType ==='update'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            项目负责人
          </template>
          <template>
            <span  v-if="routeType ==='details' || routeType ==='bidding'"> {{queryParams.sLeader}}</span>
            <el-input id="input-common8"  v-model:value="queryParams.sLeader"  v-if="routeType ==='update'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item label="招标人信息"/>
        <el-descriptions-item/>
        <el-descriptions-item>
          <template slot="label">
            招标单位
          </template>
          <template>
            <span  v-if="routeType ==='details' || routeType ==='bidding'"> {{queryParams.sUnit}}</span>
            <el-input id="input-common2"  v-model:value="queryParams.sUnit"  v-if="routeType ==='update'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            联系人
          </template>
          <template>
            <span  v-if="routeType ==='details'  || routeType ==='bidding'"> {{queryParams.sPerson}}</span>
            <el-input id="input-common3"  v-model:value="queryParams.sPerson"  v-if="routeType ==='update'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            电话
          </template>
          <template>
            <span  v-if="routeType ==='details' || routeType ==='bidding'"> {{queryParams.sPhone}}</span>
            <el-input id="input-common4"  v-model:value="queryParams.sPhone"  v-if="routeType ==='update'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            邮箱
          </template>
          <template>
            <span  v-if="routeType ==='details' || routeType ==='bidding'"> {{queryParams.email}}</span>
            <el-input id="input-common5"  v-model:value="queryParams.email"  v-if="routeType ==='update'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            地址
          </template>
          <template>
            <span  v-if="routeType ==='details' || routeType ==='bidding'"> {{queryParams.sAddress}}</span>
            <el-input id="input-common6"  v-model:value="queryParams.sAddress"  v-if="routeType ==='update'"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label"/>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            相关文件
          </template>
          <template>
            <el-upload ref="upload" class="upload-demo" :limit="4" accept=".doc, .docx, .rar, .txt, .png, .jpg"
                       multiple
                       :action="upload.url"
                       :on-change="changeFileLength"
                       :headers="upload.headers" :file-list="upload.fileList" :before-remove="beforeRemove"
                       :on-progress="handleFileUploadProgress"
                       :before-upload="beforeUpload"
                       :on-success="handleFileSuccess" :auto-upload="false">
              <el-button slot="trigger" size="small" type="primary" :disabled="routeType ==='details' || routeType ==='bidding' ">选取文件</el-button>
            </el-upload>
          </template>
        </el-descriptions-item>
        <el-descriptions-item/>
      </el-descriptions>
  </div>
</template>

<script>
import { getTender,updateTender } from '@/api/system/tender/tender'
import { addDocuments} from "@/api/system/document";
import {getToken} from "@/utils/auth";

export default {
  dicts:["ppm_procurement_plan"],
  name: 'Tender',
  data() {
    return {
      size:"0",
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
        sAddress:"",
        fjFiles:null,
        sProjectState:null,
        sLeader:null,
      },
      queryParams2:{
         sid:null,
         wTitle:null,
         wSize:null,
         fileName:null,
         url:null,
      },
      // 收集——上传文件的列表
      uploadFiles: [],
      // 收集——上传文件的个数
      filesLength: 0,
      urls:[],
      fjList:[],
      //未上传前文件列表
      noFiles:[],
      //未上传清除后文件列表
      noFiles2:[],
      // 上传参数
      upload: {
        // 上传的文件列表(专家签到表)
        fileList: [],
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: {
          Authorization: "Bearer " + getToken()
        },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/bidding/documents/upload1",
        mm:''
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
        if(response.data){
          this.queryParams.sCode=response.data.sCode;
          this.queryParams.sName=response.data.sName;
        }
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
          this.queryParams.fjFiles = response.data.fjFiles;
          if(this.queryParams.fjFiles){
            this.upload.fileList= JSON.parse(this.queryParams.fjFiles);
          }

      });
    },
    //编辑修改
    updateInfo() {
      if(this.filesLength == 0){
        this.queryParams.sProjectState = 2;//修改状态
        updateTender(this.queryParams).then(res=>{
          this.$modal.msgSuccess("修改成功！");
          this.$router.push("/tender/tender1");//回到列表
        });
      }else{
         this.$refs.upload.submit();
      }
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 修改当前文件列表长度
    changeFileLength(file, fileList){
      this.noFiles.push(file);
      console.log( this.noFiles,"noFiles");
      this.filesLength = fileList.length;
      // console.log(this.noFiles,"nofiles");
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      const url =response.data.data.url;
      this.urls.push({id: this.urls.length + 1, url});
      this.fjList.push({id: this.fjList.length + 1, ...file });
      this.uploadFiles.push(file);
      console.log(this.uploadFiles.length,"urls");
      console.log(this.filesLength,"fjList");
      //每上传完一个文件都会执行该函数，所以必须等上传完成后再提交表单
      if (this.uploadFiles.length == this.filesLength){
        //清空未上传文件列表
        this.noFiles2=[];
        let fjLists = this.fjList.map(obj=>{
          let newObj = obj;
          delete newObj.percentage;
          delete newObj.raw;
          delete newObj.status;
          delete newObj.response;
          obj.url = '';
          return newObj;
        });
        console.log(fjLists,"fjLists");
        const result = fjLists.map((item, index) => {
          item.url = this.urls[index].url;
          return item;
        });
        console.log(result,"result");
        //添加招标文件
        result.forEach(re=>{
          this.queryParams2.sid = this.$route.query.sid;//sid
          this.queryParams2.fileName = re.name;
          this.queryParams2.wTitle = "初始附件-"+this.queryParams2.fileName;
          this.queryParams2.wSize = re.size /1024;
          this.queryParams2.url = re.url;
          if(this.queryParams2.sid != null && this.queryParams2.wTitle!= null &&  this.queryParams2.wSize!= null &&  this.queryParams2.fileName!= null && this.queryParams2.url != null){
            addDocuments(this.queryParams2).then(res=>{});
          }
        });
        this.queryParams.fjFiles = JSON.stringify(result);
        this.queryParams.sProjectState = 2;//修改状态
        //修改招标项目
        updateTender(this.queryParams).then(res=>{
          this.$modal.msgSuccess("修改成功！");
          this.$router.push("/tender/tender1");//回到列表
        });
        }
    },
    beforeRemove(file, fileList) {
      this.noFiles2 =  this.noFiles.filter(f=>f.name !== file.name);
      console.log( this.noFiles2,"noFiles2");
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    beforeUpload(file) {
      const isLt5M = file.size / 1024 / 1024 < 5;
      if (!isLt5M) {
        this.$message.error('文件大小不能超过5MB');
      }
      return isLt5M;
    },
  }
}
</script>

<style>
.my-label {
  color: black;
  font-weight: bold;
}
#input-common,#input-common1,#input-common2,#input-common3,#input-common4,#input-common5,#input-common6,#input-common7,#input-common8,#input-common9,#input-common10{
  border: none;
}

</style>

