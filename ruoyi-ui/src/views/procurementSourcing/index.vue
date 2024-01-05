<script setup>

</script>

<template>
  <div class="app-container">
    <div>
      <el-form ref="elForm" :model="form" :rules="rules" size="medium" label-width="150px">
        <el-row type="flex" justify="start" align="middle" :gutter="15">
          <el-form-item label="采购计划名称" prop="field107">
            <el-input v-model="queryParams.aName" placeholder="请输入采购计划名称" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item label="创建部门">
            <el-input v-model="queryParams.aCreateDept" placeholder="请输入创建部门" clearable :style="{width: '100%'}">
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
      <el-table-column :selectable="selectable" type="selection" width="55" align="center"/>
      <el-table-column label="序号" align="center" prop="aid" width="80">
        <template slot-scope="scope">
          {{ scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="采购计划编号" align="center" prop="aCode"/>
      <el-table-column label="采购业务类型" align="center" prop="aBtype">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.ppm_procurement_plan" :value="scope.row.aBtype"/>
        </template>
      </el-table-column>
      <el-table-column label="采购计划名称" align="center" prop="aName"/>
      <!--      <el-table-column label="行项目数量" align="center" prop="aProjectCount"/>-->
      <el-table-column label="创建人" align="center" prop="createBy"/>
      <el-table-column label="创建部门" align="center" prop="aCreateDept"/>
      <el-table-column label="创建时间" align="center" prop="createTime"/>
      <el-table-column label="修改时间" align="center" prop="updateTime"/>
      <el-table-column label="采购计划状态" align="center" prop="aAstate">
        <template slot-scope="scope">
          <el-tag type="danger" v-if="scope.row.aAstate == 3">已寻源</el-tag>
          <el-tag v-else>待寻源</el-tag>
        </template>
      </el-table-column>
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
    <el-dialog @close="closeDialog" title="查看采购计划" :visible.sync="open" width="1000px" append-to-body>
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
            <p v-for="i in file.fileUrls.length">
              <a :href="file.fileUrls[i-1]" target="_blank">{{ file.fileName[i - 1] }}</a>
              <!--              <i @click="onlinePreViewFile(file.urls[i-1],file.names[i - 1])"-->
              <!--                 class="el-icon-folder-opened file_icon"></i>-->
            </p>
          </el-descriptions-item>
        </el-descriptions>
        <el-table max-height="250" :data="budgetData" style="margin-top: 20px">
          <el-table-column label="序号" align="center">
            <template slot-scope="scope">
              {{ scope.$index }}
            </template>
          </el-table-column>
          <el-table-column
            height="250"
            align="center"
            header-align="center"
            prop="duDept"
            label="部门名称">
          </el-table-column>
          <el-table-column
            prop="duId"
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
    <el-dialog @close="cancelType" :visible.sync="openByType" title="选择采购方式" width="35%">
      <div class="radio" style="text-align: center">
        <el-radio-group v-model="typeRadio">
          <el-radio :label="1">公开</el-radio>
          <el-radio :label="2">邀请</el-radio>
          <el-radio :label="3">询价</el-radio>
          <el-radio :label="4">委托</el-radio>
          <el-radio :label="5">竞争性谈判</el-radio>
          <el-radio :label="6">单一来源</el-radio>
        </el-radio-group>
      </div>
      <el-card shadow="never" v-if="typeRadio == 5 || typeRadio == 3" class="box-card">
        <div slot="header" class="clearfix">
          <span>是否公开/邀请</span>
          <!--          <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>-->
        </div>
        <div class="text item">
          <el-radio-group v-model="noBidType">
            <el-radio :label="1">公开</el-radio>
            <el-radio :label="2">邀请</el-radio>
          </el-radio-group>
        </div>
      </el-card>

      <div slot="footer" class="dialog-footer">
        <el-button v-loading.fullscreen.lock="fullscreenLoading" type="primary" @click="sumbitType">确 定</el-button>
        <el-button @click="cancelType">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style scoped lang="scss">

</style>

<script>
import {
  FindProcurementPlanBy,
  listPlan,
  selectProcurementPlanByIdForThreeTables,
  updateStateAndAddBidWinning
} from '@/api/system/plan'

export default {
  dicts: ['ppm_procurement_plan'],
  name: 'Plan',
  components: {},
  props: [],
  data() {
    return {
      file: {
        fileUrls: [],
        fileName: []
      },
      planList: [],
      itemList: [],
      openByType: false,
      fullscreenLoading: false,
      typeRadio: 1,
      noBidType: 1,
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
    let obj = this.$route.query;
    if (obj && obj.aid) {
      this.handleClick(obj);
    }
    this.getList()
  },
  methods: {
    closeDialog() {
      this.file.fileUrls = [];
      this.file.fileName = [];
      this.budgetData = [];
    },
    selectable(row, index) {
      if (row.aAstate == 2) {
        return true;
      } else {
        return false;
      }
    },
    getList() {
      this.loading = true;
      this.planList = [];
      FindProcurementPlanBy(this.queryParams).then(response => {
        this.total = response.total;
        this.loading = false;
        this.planList = response.rows;
      }).catch(err => {
        this.$modal.msgError("服务器出错，请联系管理员！！！");
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
    //关闭采购寻源方式选择
    cancelType() {
      this.typeRadio = 1;
      this.openByType = false;

    },
    //采购寻源方式选择确定按钮
    sumbitType() {
      this.fullscreenLoading = true;
      updateStateAndAddBidWinning(this.yilist, this.typeRadio, this.noBidType).then(res => {
        this.fullscreenLoading = false
        this.openByType = false;
        this.$modal.msgSuccess("操作成功！！");
        this.getList();
      }).catch(err => {
        this.fullscreenLoading = false
        this.$modal.msgError("服务器出错，请联系管理员！！！");
      })
    },
    reset() {
      this.queryParams = {
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
      this.queryParams.aBtype = null;
      this.resetForm('form')
    },
    resetForm() {
      this.$nextTick(() => {
        this.$refs['elForm'].resetFields()
      })
    },
    resetQuery() {
      this.reset();
      this.getList();
    },
    handleClick(row) {
      selectProcurementPlanByIdForThreeTables(row.aid).then(res => {
        this.open = true;
        this.itemList = res.data.items;
        this.loading = false;
        this.form = res.data;
        if (res.data.items) {
          res.data.items.forEach((e, i) => {
            if (e.ppmBudget.duId) {
              this.budgetData.push(e.ppmBudget);
            }
          })
        }
        if (res.data.file.anName && res.data.file.anUrl) {
          this.file.fileUrls = res.data.file.anUrl.split(",");
          this.file.fileName = res.data.file.anName.split(",");
        }
      }).catch(err => {
        this.$modal.msgError("服务器出错，请联系管理员！！！");
      })
    },
    //采购寻源按钮
    handleSubmit() {
      if (this.yilist.length > 0) {
        this.openByType = true;
      } else {
        this.$modal.msgError("请选择采购寻源")
      }
    },
    cancel() {
      this.open = false;
      this.reset();
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
      if (this.file.fileUrls.length == 0) {
        this.$modal.msgError("没有附件可下载，请上传附件！！")
        return;
      }
      let name = encodeURIComponent(this.file.fileUrls);
      var url = `http://localhost:8080/ppm/file/downloadFiles?file=${name}`;
      const a = document.createElement('a')
      a.setAttribute('target', '_blank')
      a.setAttribute('href', url)
      a.click()
    }

  }
}
</script>

<style scoped>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  margin: 20px auto;
//padding-left: 20px; width: 550px;
}
</style>
