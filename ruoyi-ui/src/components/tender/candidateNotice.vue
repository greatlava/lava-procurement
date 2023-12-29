<template>
  <div class="app-container">
    <el-table v-loading="loading" :data="candidateList" :default-sort = "{prop: 'zRanking', order: 'ascending'}">
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

export default {
  name: "candidateNotice",
  data() {
    return {
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
        zSendTime: null
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
  }
};
</script>
<style scoped lang="scss">

</style>
