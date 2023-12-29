<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >确定中标人</el-button>
      </el-col>
      <right-toolbar @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="candidateList" @selection-change="handleSelectionChange" :default-sort = "{prop: 'zRanking', order: 'ascending'}">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="序号" type="index" align="center" />
      <el-table-column label="供应商名称" align="center" prop="hName"/>
      <el-table-column label="最终报价(万元)" align="center" prop="zFinal" />
      <el-table-column label="最终得分" align="center" prop="zFraction" sortable/>
      <el-table-column label="是否推荐" align="center" prop="zRecommend" >
        <template slot-scope="scope">
          <span>{{scope.row.zRecommend == 0?'是':'否'}}</span>
        </template>
      </el-table-column>
      <el-table-column label="排名" align="center" prop="zRanking" sortable/>
      <el-table-column label="是否中标" align="center" prop="zBidder" >
        <template slot-scope="scope">
          <span>{{scope.row.zBidder == 0?'是':'否'}}</span>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>
<script>
import { listCandidate, getCandidate, delCandidate, addCandidate, updateCandidate } from "@/api/system/tender/bidEval";
import {delNotice} from "@/api/system/tender/tenderNotice";
import {getTender} from "@/api/system/tender/tender";

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
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
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
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.queryParams.sid=this.$route.query.sid;
    this.getList();
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
      this.queryParams.zBidder = null;
      listCandidate(this.queryParams).then(response => {
        this.candidateList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.zid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      if(this.status){
        this.$alert("该项目已定标，不可进行操作！");
        this.getList();
      }else{
        this.reset();
        const zids = row.zid || this.ids[0];
        this.queryParams.zBidder = "0";//修改是否中标
        listCandidate(this.queryParams).then(r=>{
          this.queryParams.zid = zids;//zid
          getCandidate(this.queryParams.zid).then(re=>{
            if(re.data.zRanking !== 1){
              this.$modal.confirm('检测到该供应商评估排名不为优先，是否确定该供应商为中标人？').then(()=>{
                //已存在中标人
                if(r.rows.length > 0){
                  this.queryParams.zid = r.rows[0].zid;
                  this.$modal.confirm('检测到已存在中标人，是否仍然确定？').then(()=> {
                    this.queryParams.zBidder = "1";
                    //修改中标为未中标
                    return updateCandidate(this.queryParams);
                  }).then(() => {
                    this.queryParams.zBidder = "0";
                    this.queryParams.zid = zids;//zid
                    return updateCandidate(this.queryParams);
                  }).then(() => {
                    this.queryParams.zBidder = null;
                    this.queryParams.zid = null;
                    this.getList();
                    this.$modal.msgSuccess("确定成功");
                  }).catch(() => {});
                }else{
                  updateCandidate(this.queryParams).then(res=>{
                    this.queryParams.zBidder = null;
                    this.queryParams.zid = null;
                    this.getList();
                    this.$modal.msgSuccess("确定成功");
                  });
                }
              })
            }else{
              //已存在中标人
              if(r.rows.length > 0){
                this.queryParams.zid = r.rows[0].zid;
                this.$modal.confirm('检测到已存在中标人，是否仍然确定？').then(()=> {
                  this.queryParams.zBidder = "1";
                  //修改中标为未中标
                  return updateCandidate(this.queryParams);
                }).then(() => {
                  this.queryParams.zBidder = "0";
                  this.queryParams.zid = zids;//zid
                  return updateCandidate(this.queryParams);
                }).then(() => {
                  this.queryParams.zBidder = null;
                  this.queryParams.zid = null;
                  this.getList();
                  this.$modal.msgSuccess("确定成功");
                }).catch(() => {});
              }else{
                updateCandidate(this.queryParams).then(res=>{
                  this.queryParams.zBidder = null;
                  this.queryParams.zid = null;
                  this.getList();
                  this.$modal.msgSuccess("确定成功");
                });
              }
            }
          });
        });
      }
    },
  }
};
</script>
<style scoped lang="scss">

</style>
