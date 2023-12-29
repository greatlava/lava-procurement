<template>
  <div class="app-container">
    <div class="box">行项目信息</div>
    <el-table stripe :data="tableData" v-loading="loading" border style="width: 100%" max-height="200" :cell-style="{'text-align':'center'}">
      <el-table-column fixed label="序号" width="100" type="index" align="center"/>
      <el-table-column fixed label="部门名称" width="140" align="center">
        <template slot-scope="scope">
          {{scope.row.ppmBudget.duName || '——'}}
        </template>
      </el-table-column>
      <el-table-column fixed label="预算科目编号" width="140" align="center">
        <template slot-scope="scope">
          {{scope.row.ppmBudget.duId || '——'}}
        </template>
      </el-table-column>
      <el-table-column fixed  label="预算科目名称" width="140" align="center">
        <template slot-scope="scope">
          {{scope.row.ppmBudget.duName || '——'}}
        </template>
      </el-table-column>
      <el-table-column fixed label="总金额" width="140" align="center">
        <template slot-scope="scope">
          {{scope.row.ppmBudget.duTotal || '——'}}
        </template>
      </el-table-column>
      <el-table-column label="行项目编号" width="200" align="center">
        <template slot-scope="scope">
          {{scope.row.vCode || '——'}}
        </template>
      </el-table-column>
      <el-table-column label="数量" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.vCount || '——'}}
        </template>
      </el-table-column>
      <el-table-column label="采购人" width="120" align="center">
        <template slot-scope="scope">
        {{scope.row.vPerson  || '——'}}
      </template>

      </el-table-column>
      <el-table-column label="交付时间" width="200" align="center">
        <template slot-scope="scope">
          {{parseTime(scope.row.vDeliveryTime, '{y}-{m}-{d} {h}:{i}:{s}')}}
        </template>
      </el-table-column>
      <el-table-column label="交付地点" width="200" align="center">
        <template slot-scope="scope">
          {{scope.row.vDeliveryArea || '——'}}
        </template>
      </el-table-column>
      <el-table-column label="采购方式" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.procurementMethod || '——'}}
        </template>
      </el-table-column>
      <el-table-column  label="产品名称" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.ppmDevice.tName || '——'}}
        </template>
      </el-table-column>
      <el-table-column label="计量单位" width="100" align="center">
        <template slot-scope="scope">
          {{scope.row.ppmDevice.tUnit || '——'}}
        </template>
      </el-table-column>
      <el-table-column label="税率" width="100" align="center">
        <template slot-scope="scope">
          {{scope.row.ppmDevice.shui || '——'}}
        </template>
      </el-table-column>
      <el-table-column label="类别" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.ppmDevice.category || '——'}}
        </template>
      </el-table-column>
      <el-table-column label="需求说明" width="200" align="center">
        <template slot-scope="scope">
          {{scope.row.ppmDevice.tIllustrate || '——'}}
        </template>
      </el-table-column>
      <el-table-column label="规格型号" width="200" align="center">
        <template slot-scope="scope">
          {{scope.row.ppmDevice.tModel || '——'}}
        </template>
      </el-table-column>
    </el-table>
  </div>

</template>


<script>

import { listDevice,getDevice } from '@/api/system/tender/tenderDetails'
import {selectProcurementPlanByIdForThreeTables} from "@/api/system/plan";
import { getTender } from '@/api/system/tender/tender'

export default {
  data() {
    return {
      loading:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tid:"",
        aid:"",
        tSid:"",
        jhId:"",
        tName:null,
        tDa:null,
        tZhong:null,
        tXiao:null,
        tAmount:null,
        tUnit:null,
        shui:null,
        tPrice:null,
        tTotalPrice:null,
        tDate:null,
        tAddress:null,
        tIllustrate:null,
        tPurchaser:null,
        duCode:null,
        tNotes:null,
        aWay:null,
        aState:null,
        orderNum:0
      },
      tableData: []
    }
  },
  methods: {
    getList(){
      getTender(this.$route.query.sid).then(res=>{
        selectProcurementPlanByIdForThreeTables(res.data.xyId).then(response => {
          console.log(response,"res");
          this.tableData = response.data.items;
        });
      });

    }
  },
  created() {
    this.loading = true;
    setTimeout(()=>{
      this.loading = false;
    },1500)
    this.getList();
  }
}
</script>
<style scoped>
.box {
  margin: 10px 0 15px 0px;
  color: #409eff;
  font-size: 16px;
  font-weight: bold;
}
</style>
