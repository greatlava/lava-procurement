<template>
  <div class="app-container">
    <el-table
      stripe
      :data="tableData"
      border
      style="width: 100%"
      max-height="200"
      :cell-style="{'text-align':'center'}"
    >
      <el-table-column
        fixed
        prop="orderNum"
        label="序号"
        width="100"
      >
      </el-table-column>
<!--      <el-table-column-->
<!--        fixed-->
<!--        prop=""-->
<!--        label="行项目编号"-->
<!--        width="140"-->
<!--      >-->
<!--      </el-table-column>-->
      <el-table-column
        fixed
        prop="tid"
        label="产品编码"
        width="140"
      >
      </el-table-column>
      <el-table-column
        fixed
        prop="tName"
        label="产品名称"
        width="140"
      >
      </el-table-column>
      <el-table-column
        prop="tDa"
        label="大类"
        width="130"
      >
      </el-table-column>
      <el-table-column
        prop="tZhong"
        label="中类"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop="tXiao"
        label="小类"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop="tAmount"
        label="数量"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop="tUnit"
        label="计量单位"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop="shui"
        label="税率(%)"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop="tPrice"
        label="预算单价(元)"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop="tTotalPrice"
        label="预算总价(元)"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop="tDate"
        label="交付时间"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop="tAddress"
        label="交付地点"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop="tIllustrate"
        label="需求说明"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop="tPurchaser"
        label="采购人"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop="duCode"
        label="预算科目编号"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop="tNotes"
        label="备注"
        width="120"
      >
      </el-table-column>
    </el-table>
  </div>

</template>


<script>

import { listDevice,getDevice } from '@/api/system/tender/tenderDetails'

export default {
  data() {
    return {
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
      this.queryParams.tSid = this.$route.query.sid;
      listDevice(this.queryParams).then(response => {
        response.rows.forEach((e,i)=>{
          e.orderNum = i+1;
          this.tableData.push(e);
        })
        this.total = response.total;
        this.loading = false;
      });
    }
  },
  created() {
    this.getList();
  }

}
</script>
