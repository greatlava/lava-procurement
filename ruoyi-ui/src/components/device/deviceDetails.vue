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
        prop="date"
        label="序号"
        width="100"
      >
      </el-table-column>
      <el-table-column
        fixed
        prop=""
        label="行项目编号"
        width="140"
      >
      </el-table-column>
      <el-table-column
        fixed
        prop=""
        label="产品名称"
        width="140"
      >
      </el-table-column>
      <el-table-column
        fixed
        prop=""
        label="产品编码"
        width="140"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="大类"
        width="130"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="中类"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="数量"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="计量单位"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="税率(%)"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="预算单价(元)"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="预算总价(元)"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="交付时间"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="交付地点"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="需求说明"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="采购人"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="预算科目编号"
        width="120"
      >
      </el-table-column>
      <el-table-column
        prop=""
        label="备注"
        width="120"
      >
      </el-table-column>
    </el-table>
  </div>

</template>


<script>
export default {
  methods: {
    handleClick(row) {
      console.log(row)
    },
    getList(){
      this.queryParams.sid = this.$route.query.sid;
      listItems(this.queryParams).then(response => {
        this.tableData = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    }
  },
  created() {
    this.getList();
  },
  data() {
    return {
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sid:null,
        vCode:null,
        jhId:null,
        aid: null,
        gid:null,
        vCount:null,
        vPerson:null,
        vTotal:null,
        vDeliveryTime:null,
        vDeliveryArea:null,
        vIllustrate:null
      },
      tableData: []
    }
  }
}
</script>
