<script setup>

</script>

<template>
  <div class="app-container">
    <div>
      <el-form ref="elForm" :model="form" :rules="rules" size="medium" label-width="150px">
        <el-row type="flex" justify="start" align="middle" :gutter="15">
          <el-form-item label="采购计划名称" prop="field107">
            <el-input v-model="form.aName" placeholder="请输入采购计划名称" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item label="创建部门">
            <el-input v-model="form.aCreateDept" placeholder="请输入创建部门" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item size="medium">
            <el-button type="primary" @click="query">查询</el-button>
            <el-button @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-row>
      </el-form>
    </div>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          size="mini"
          @click="handleSubmit"
          v-hasPermi="['system:rules:add']"
        >执行采购寻源
        </el-button>
      </el-col>
    </el-row>
    <el-table @selection-change="handleSelectionChange" v-loading="loading" :data="planList">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="序号" align="center" prop="aid" width="80"/>
      <el-table-column label="采购计划编号" align="center" prop="aCode"/>
      <el-table-column label="采购业务类型" align="center" prop="aBtype">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.ppm_procurement_plan" :value="scope.row.aBtype"/>
        </template>
      </el-table-column>
      <el-table-column label="采购计划名称" align="center" prop="aName"/>
      <el-table-column label="行项目数量" align="center" prop="aProjectCount"/>
      <el-table-column label="创建人" align="center" prop="createBy"/>
      <el-table-column label="创建日期" align="center" prop="createTime"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleClick(scope.row)"
            v-hasPermi="['system:plan:edit']"
          >查看
          </el-button>
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
    <el-dialog title="查看采购计划" :visible.sync="open" width="1000px" append-to-body>
      <el-tabs @tab-click="handleClick" v-model="paneName">
        <el-descriptions direction="vertical" class="margin-top" :column="4" size="medium" border>
          <el-descriptions-item label="采购计划编号">{{ form.aCode }}</el-descriptions-item>
          <el-descriptions-item label="计划名称">{{ form.aName }}</el-descriptions-item>
          <el-descriptions-item label="创建人">{{ form.createBy }}</el-descriptions-item>
          <el-descriptions-item label="创建部门">{{ form.aCreateDept }}</el-descriptions-item>
          <el-descriptions-item label="附件">
            <el-button type="primary" @click="download(form.fjAnnex)">
              下载附件<i class="el-icon-download"></i>
            </el-button>
          </el-descriptions-item>
        </el-descriptions>
        <el-table max-height="250" :data="budgetData" style="margin-top: 20px">
          <el-table-column
            fixed
            height="250"
            align="center"
            header-align="center"
            prop="duDept"
            label="部门名称">
          </el-table-column>
          <el-table-column
            prop="duCode"
            align="center"
            label="预算科目编号">
          </el-table-column>
          <el-table-column
            prop="duName"
            align="center"
            label="预算科目名称">
          </el-table-column>
          <el-table-column
            prop="duTotal"
            align="center"
            label="总金额">
          </el-table-column>
        </el-table>
        <!-- 行项目 -->
        <el-table @cell-click="click" max-height="250" v-loading="loading" :data="itemList"
                  style="margin-top: 20px">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-descriptions border>
                <el-descriptions-item label="产品名称">{{ props.row.ppmDevice.tName }}</el-descriptions-item>
                <el-descriptions-item label="计量单位">{{ props.row.ppmDevice.tUnit }}</el-descriptions-item>
                <el-descriptions-item label="税率">{{ props.row.ppmDevice.shui }}</el-descriptions-item>
                <el-descriptions-item label="类别">
                  <el-tag size="small">{{ props.row.ppmDevice.category }}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item label="需求说明">{{ props.row.ppmDevice.tIllustrate }} 1188 号
                </el-descriptions-item>
                <el-descriptions-item label="规格型号">{{ props.row.ppmDevice.tModel }}</el-descriptions-item>
              </el-descriptions>
            </template>
          </el-table-column>
          <el-table-column label="行项目编号" align="center" prop="vCode" width="160"/>
          <el-table-column label="数量" align="center" prop="vCount"/>
          <el-table-column label="采购人" align="center" prop="vPerson"/>
          <el-table-column label="交付时间" align="center" prop="vDeliveryTime" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.vDeliveryTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="交付地点" align="center" prop="vDeliveryArea" width="150"/>
          <el-table-column label="需求说明" align="center" prop="vIllustrate"/>
          <el-table-column label="采购方式" align="center" prop="procurementMethod"/>
        </el-table>
      </el-tabs>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style scoped lang="scss">

</style>

<script>
import {listPlan, selectProcurementPlanByIdForThreeTables} from '@/api/system/plan'

export default {
  dicts: ['ppm_procurement_plan'],
  name: 'Plan',
  components: {},
  props: [],
  data() {
    return {
      planList: [],
      itemList: [],
      form: {},
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        aAstate: 2
      },
      loading: false,
      total: 0,
      budgetData: [],
      open: false,
      yilist: [],
      paneName: '',
      rules: {}
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.loading = true;
      this.planList = [];
      listPlan(this.queryParams).then(response => {
        this.total = response.total;
        this.loading = false;
        this.planList = response.rows;
      });
    },
    query() {
      this.$nextTick(() => {
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
        })
        this.getList();
      })
    },
    resetQuery() {
      this.form = {
        aid: null,
        aCode: null,
        aName: null,
        createBy: null,
        aCreateDept: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        fjAnnex: null,
        aOpinion: null,
        aProjectCount: null,
        aBtype: null,
        aAstate: 0
      }
      this.$nextTick(() => {
        this.$refs['elForm'].resetFields()
      })
    },
    handleClick(row) {
      selectProcurementPlanByIdForThreeTables(row.aid).then(res => {
        this.open = true;
        this.itemList = res.data.items;
        this.loading = false;
        this.form = res.data;
      })
    },
    handleSubmit() {
      console.log("list", this.yilist)
    },
    cancel() {
      this.open = false;
    },
    click(row) {
      if (row.ppmBudget.duName != null && row.ppmBudget.duName != undefined) {
        let list = [];
        list.push(row.ppmBudget)
        this.budgetData = list;
      } else {
        this.budgetData = null;
      }
    },
    handleSelectionChange(e) {
      this.yilist = e;
    },
    download() {

    }

  }
}
</script>
