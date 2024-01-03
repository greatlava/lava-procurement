<template>
  <div>
    <div class="app-container">
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            size="mini"
            :disabled="single"
            @click="handleUpdate"
          >发送通知</el-button>
        </el-col>
        <right-toolbar @queryTable="getList"></right-toolbar>
      </el-row>
      <el-table v-loading="loading" :data="candidateList" @selection-change="handleSelectionChange" :default-sort = "{prop: 'zBidder', order: 'ascending'}">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="序号" type="index" align="center" />
        <el-table-column label="供应商名称" align="center" prop="hName"/>
        <el-table-column label="最终报价(万元)" align="center" prop="zFinal" />
        <el-table-column label="最终得分" align="center" prop="zFraction" />
        <el-table-column label="是否推荐" align="center" prop="zRecommend" >
          <template slot-scope="scope">
            <span>{{scope.row.zRecommend == 0?'是':'否'}}</span>
          </template>
        </el-table-column>
        <el-table-column label="排名" align="center" prop="zRanking" />
        <el-table-column label="是否中标" align="center" prop="zBidder" sortable>
          <template slot-scope="scope">
            <span>{{scope.row.zBidder == 0?'是':'否'}}</span>
          </template>
        </el-table-column>
      </el-table>


      <!-- 添加或修改招标公告对话框 -->
      <el-dialog title="发送通知" :visible.sync="open" width="1080px" append-to-body>
        <el-form ref="form"  :model="form" :rules="rules" label-width="120px" >
          <el-form-item label="招标项目ID" prop="sid">
            <el-input v-model="form.sid" disabled />
          </el-form-item>
          <el-form-item label="供应商ID" prop="hid" class="form-input">
            <el-input v-model="form.hid" disabled/>
          </el-form-item>
          <el-form-item label="供应商名称" prop="uProject" class="form-input">
            <el-input v-model="form.hName" disabled/>
          </el-form-item>
          <el-form-item label="公告标题" prop="resultTitle" class="form-input">
            <el-input v-model="form.resultTitle" placeholder="请输入公告标题"/>
          </el-form-item>
          <el-form-item label="内容" prop="resultDesc">
            <editor v-model="form.resultDesc" :min-height="192"/>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary"  @click="submitForm" :disabled="status">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
    <div  class="app-container">
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <div class="box">已发送</div>
        </el-col>
        <right-toolbar @queryTable="getResultList(queryParams.sid)"></right-toolbar>
      </el-row>
      <el-table v-loading="loading" :data="resultList">
        <el-table-column label=  "序号" type="index" align="center" />
        <el-table-column label="供应商" align="center">
          <template slot-scope="scope">
            <span>{{scope.row.hName}}</span>
          </template>
        </el-table-column>
        <el-table-column label="联系人" align="center">
          <template slot-scope="scope">
            <span>{{scope.row.bsSupplier[0].bsOperator.ywName}}</span>
          </template>
        </el-table-column>
        <el-table-column label="联系方式" align="center">
          <template slot-scope="scope">
            <span>{{scope.row.bsSupplier[0].bsOperator.ywPhone}}</span>
          </template>
        </el-table-column>
        <el-table-column label="邮箱" align="center">
          <template slot-scope="scope">
            <span>{{scope.row.bsSupplier[0].bsOperator.ywMailbox}}</span>
          </template>
        </el-table-column>
        <el-table-column label="发送时间" align="center" prop="zBidder" >
          <template slot-scope="scope">
            <span>{{scope.row.gsResDate}}</span>
          </template>
        </el-table-column>
      </el-table>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getResultList(queryParams.sid)"
      />
    </div>
  </div>
</template>
<script>
import { listCandidate, getCandidate, delCandidate, addCandidate, updateCandidate } from "@/api/system/tender/bidEval";
import {addResults, selectResultSupp, listResults, getResults} from "@/api/system/tender/winningBidNotice";
import {getTender, updateTender} from "@/api/system/tender/tender";
import {updateNotice,updateStatus} from "@/api/system/tender/tenderNotice";

export default {
  name: "DetermineWin",
  data() {
    return {
      status:false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 中标候选人表格数据
      candidateList: [],
      //中标通知
      resultList:[],
      resultList2:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数(候选人)
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sid: null,
        zBname: null,
        zFinal: null,
        zFraction: null,
        zRecommend: null,
        zRanking: null,
        zSign: null,
        zCensor: null,
        zReview: null,
        zSummary: null,
        zBidder: null,
        zSendTime: null,
        zid: null,
        hid:null,
      },
      // 查询参数(中标结果公示)
      queryParams2: {
        pageNum: 1,
        pageSize: 10,
        sid: '',
        hid: null,
        hName: null,
        gsState: null,
        gsResDate: null
      },
      queryParams3:{
        sid:0,
        sCode:null,
        sName:null,
        sWay: null,
        sMust:null,
        sSway: null,
        sType: null,
        sBudget:null,
        sUnit:null,
        sPerson:null,
        sPhone:null,
        email:null,
        sAddress:null,
        fjFiles:null,
        sProjectState:null,
        sLeader:null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        resultTitle: [
          { required: true, message: "公告标题不能为空", trigger: "blur" }
        ]
      },
    };
  },
  created() {
    this.queryParams.sid=this.$route.query.sid;
    this.getList();
    this.getResultList(this.queryParams.sid);
    getTender(this.queryParams.sid).then(res=>{
      if(res.data.sProjectState === 7){
        this.status=true;
      }
    });
  },
  methods: {
    /** 查询中标候选人列表 */
    getList() {
      this.loading = true;
      listCandidate(this.queryParams).then(response => {
        this.candidateList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询已发送的通知 */
    getResultList(sid){
      selectResultSupp(sid).then(res=>{
        this.resultList = res.data;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        zid: null,
        sid: null,
        zBname: null,
        zFinal: null,
        zFraction: null,
        zRecommend: null,
        zRanking: null,
        zSign: null,
        zCensor: null,
        zReview: null,
        zSummary: null,
        zBidder: null,
        zSendTime: null
      };
    },
    // 参数重置
    reset2() {
      this.queryParams2 = {
        pageNum: 1,
        pageSize: 10,
        sid: '',
        hid: null,
        hName: null,
        gsState: null,
        gsResDate: null
      };
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.zid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      const zids = row.zid || this.ids;
      getCandidate(zids).then(res=>{
        this.form = res.data;
        this.queryParams2.sid = this.form.sid;
        this.queryParams2.hid = this.form.hid;
       if(this.queryParams2.sid!==null && this.queryParams2.hid!==null){
         console.log(this.queryParams2,"q2");
         listResults(this.queryParams2).then(response=>{
           //已存在
           if(response.rows.length > 0){
             this.$alert("该候选人已发送过通知！");
             this.getList();
           }else{
             this.open = true;
           }
         });
       }
      });
      // if(this.status){
      //   this.$alert("该项目已定标，不可进行操作！");
      //   this.getList();
      // }else {
      //   const zids = row.zid || this.ids;
      //   console.log(zids,"zids");
      //   console.log(this.resultList2,"result2  init");
      //   zids.forEach(zid=>{
      //     console.log(this.resultList2,"result2  open");
      //     //根据zid获取信息
      //     getCandidate(zid).then(res=>{
      //       this.reset2();//清空
      //       this.queryParams2.sid = res.data.sid;
      //       this.queryParams2.hid = res.data.hid;
      //       //查看是否hid已发送通知
      //       listResults(this.queryParams2).then(r=>{
      //         this.resultList2=[];
      //         if(r.rows.length  == 0){
      //           this.resultList2.push(res.data);
      //           console.log(this.resultList2,"result2 push");
      //         }
      //         if(this.resultList2.length > 0){
      //           this.resultList2.forEach(item=>{
      //             console.log(item,"row");
      //             this.reset2();//重置
      //             this.queryParams2.sid =item.sid;//招标项目Id
      //             this.queryParams2.hid =item.hid;//供应商ID
      //             this.queryParams2.hName =item.hName;//供应商名称
      //             if(this.queryParams2.sid != null && this.queryParams2.hName != null && this.queryParams2.hid != null){
      //               //添加发送通知
      //               addResults(this.queryParams2).then(re => {
      //                 console.log("open add-----");
      //                 this.queryParams2.hid =null;
      //                 this.queryParams2.hName =null;
      //                 console.log(this.queryParams2,"q2");
      //                 //验证已发送通知候选人和候选人是否一致
      //                 listResults(this.queryParams2).then(r=>{
      //                   console.log("open add----- listResults",r);
      //                   this.queryParams2.sid = r.rows[0].sid;
      //                   listCandidate(this.queryParams2).then(re=>{
      //                     console.log("open add----- listCandidate",re);
      //                     this.queryParams3.sid=this.queryParams2.sid;
      //                     this.queryParams3.sProjectState=7;//已定标
      //                     if(r.rows.length === re.rows.length){
      //                       console.log("open add----- updateTender");
      //                       updateTender(this.queryParams3).then(res=>{});
      //                     }
      //                   });
      //                 });
      //                 this.$modal.msgSuccess("发送成功！");
      //                 this.getList();
      //                 this.getResultList(this.queryParams.sid);
      //                 this.reset2();
      //               });
      //             }
      //           });
      //         }else{
      //           this.getResultList(this.queryParams.sid);
      //           this.getList();
      //         }
      //       });
      //     });
      //   });
      // }
    },
    submitForm(){
      this.$refs["form"].validate(valid => {
        if(valid){
          addResults(this.form).then(r=>{
            this.queryParams.sid = this.form.sid;
            this.queryParams.hid = this.form.hid;
            if(this.queryParams.sid!==null && this.queryParams.hid!==null){
              listCandidate(this.queryParams).then(re=>{
                //如果给中标人发送通知，则修改招标状态
                if(re.rows[0].zBidder === 0){
                  this.queryParams3.sid=this.queryParams2.sid;
                  this.queryParams3.sProjectState=7;//已定标
                  //修改招标项目状态
                  updateTender(this.queryParams3).then(res=>{});
                  //修改公示时间（已中标，直接关闭投标）
                  updateStatus(this.queryParams3.sid).then(res=>{});
                }
              });
            }
            this.open = false;
            this.$modal.msgSuccess("发送成功！");
            this.getList();
            this.getResultList(this.queryParams.sid);
          });
        }
      });
    }
  }
};
</script>
<style scoped>
.box {
  margin: 10px 0 0 0;
  color: #409eff;
  font-size: 16px;
  font-weight: bold;
}
</style>
