<template>
  <div>
    <div style="padding: 30px;background-color: #f3f3f3">
      <div class="top">
        <h1>合同风险处理</h1>
      </div>
      <el-form style="height: 680px" :model="formData" ref="vForm" label-position="left" label-width="150px"
               size="medium">

        <div class="static-content-item">
          <el-divider direction="horizontal" content-position="left"></el-divider>
        </div>

        <div class="card-container">
          <el-card style="{width: 100% !important}" shadow="hover" :body-style="{padding:'30px 20px 0px'}">
            <div slot="header" class="clear-fix">
              <span>基本信息</span>
              <i class="float-right el-icon-arrow-down"></i>
            </div>
            <el-row>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="合同编号" prop="eHcode" class="label-right-align">
                  <el-input v-model="formData.eHcode" type="text" clearable :readonly="true"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="合同名称" prop="eHname" class="label-right-align">
                  <el-input v-model="formData.eHname" type="text" clearable :readonly="true"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="供应商名称" prop="hName" class="label-right-align">
                  <el-input v-model="formData.hName" :readonly="true" type="text"
                            clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="风险项" prop="rmItem" class="label-right-align">
                  <el-select v-model="formData.rmItem" placeholder="请选择风险项" disabled class="readonly-select">
                    <el-option v-for="dict in rm_items"
                               :key="dict.dictValue"
                               :label="dict.dictLabel"
                               :value="Number(dict.dictValue)">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="发生日期" prop="rmHappenDate" class="label-right-align">
                  <el-date-picker clearable
                                  v-model="formData.rmHappenDate"
                                  type="date"
                                  value-format="yyyy-MM-dd"
                                  placeholder="请选择发生日期"
                                  :readonly="true">
                  </el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="风险说明" prop="rmIllustrate" class="label-right-align">
                  <el-input type="textarea" v-model="formData.rmIllustrate" placeholder="请输入风险说明"
                            :readonly="true"/>
                </el-form-item>
              </el-col>
            </el-row>
          </el-card>
        </div>
        <div class="static-content-item">
          <el-alert title="" type=info :closable="false" :center="false" :show-icon="false" effect="light">
          </el-alert>
        </div>
        <div class="card-container">
          <el-card style="{width: 100% !important}" :body-style="{padding:'30px 20px 10px'}" shadow="hover">
            <div slot="header" class="clear-fix">
              <span>风险处理信息</span>
              <i class="float-right el-icon-arrow-down"></i>
            </div>
            <el-row>
              <el-col :span="24" class="grid-cell">
                <el-row>
                  <el-col :span="12" class="grid-cell">
                    <el-form-item label="处理结果" prop="call" class="label-right-align">
                      <el-input
                        type="textarea"
                        :rows="8"
                        style="width: 200%"
                        placeholder="请输入处理结果"
                        v-model="formData.rmHandingResult"
                        :readonly="formData.rmStatus == 2 || formData.rmStatus == 3">
                      </el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
              </el-col>
            </el-row>
          </el-card>
        </div>
        <div class="static-content-item">
          <el-alert title="" type=info :closable="false" :center="true" :show-icon="false" effect="light">
          </el-alert>
        </div>
        <div class="static-content-item">
          <el-alert title="" type=info :closable="false" :center="false" :show-icon="false" effect="light">
          </el-alert>
        </div>
        <el-row style="text-align: center;width: 135%">
          <el-col :span="18" class="grid-cell">
            <el-button @click="submit" type="success" v-show="this.formData.rmStatus == 1">提交
            </el-button>
            <el-button @click="ignore" type="info" v-show="this.formData.rmStatus == 1">忽略</el-button>
            <el-button @click="fh">返回</el-button>
          </el-col>
        </el-row>
      </el-form>
    </div>
  </div>
</template>

<script>
import {getMonitor, updateMonitor} from "@/api/system/riskMonitor";

export default {
  data() {
    return {
      formattedDate: null,
      //风险项字典数据
      rm_items: [],
      formData: {
        rm_id: this.$route.query.rm_id,
        eHcode: null,
        eHname: null,
        hName: null,
        rmItem: null,
        rmStatus: null,
        rmIllustrate: null,
        rmHappenDate: null,
        rmHandingPerson: 'admin',
        rmHandingResult: null
      }
    }
  },
  components: {},
  created() {
    this.getInfo()
    //查询风险项字典
    this.getDicts("bs_risk_monitoring_item").then(res => {
      this.rm_items = res.data;
    });
  },
  methods: {
    getInfo() {
      getMonitor(this.formData.rm_id).then(res => {
        console.log(res)
        this.formData.eHcode = res.data.bsContract.eHcode
        this.formData.eHname = res.data.bsContract.eHname
        this.formData.hName = res.data.bsSupplier.hName
        this.formData.rmItem = res.data.rmItem
        this.formData.rmIllustrate = res.data.rmIllustrate
        this.formData.rmHappenDate = res.data.rmHappenDate
        this.formData.rmStatus = res.data.rmStatus
        this.formData.rmHandingResult = res.data.rmHandingResult
      })
    },
    fh() {
      this.$router.back()
    },
    getNow() {
      const date = new Date();
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0'); // 使用 padStart 方法补齐月份
      const day = String(date.getDate()).padStart(2, '0'); // 使用 padStart 方法补齐日期
      this.formattedDate = `${year}-${month}-${day}`;
    },
    ignore() {
      this.formData.rmStatus = 3
      this.getNow()
      updateMonitor({
        "rmId": this.formData.rm_id,
        "rmStatus": this.formData.rmStatus,
        "rmHandingPerson": this.formData.rmHandingPerson,
        "rmHandingDate": this.formattedDate
      }).then(res => {
        console.log(res)
        this.$modal.msgSuccess("忽略成功");
      })
    },
    submit() {
      this.formData.rmStatus = 2
      this.getNow()
      updateMonitor({
        "rmId": this.formData.rm_id,
        "rmStatus": this.formData.rmStatus,
        "rmHandingPerson": this.formData.rmHandingPerson,
        "rmHandingDate": this.formattedDate,
        "rmHandingResult": this.formData.rmHandingResult
      }).then(res => {
        console.log(res)
        this.$modal.msgSuccess("提交成功");
      })
    }
  }
}
</script>


<style lang="scss">
.el-input-number.full-width-input,
.el-cascader.full-width-input {
  width: 100% !important;
}

.el-form-item--medium {
  .el-radio {
    line-height: 36px !important;
  }

  .el-rate {
    margin-top: 8px;
  }
}

.el-form-item--small {
  .el-radio {
    line-height: 32px !important;
  }

  .el-rate {
    margin-top: 6px;
  }
}

.el-form-item--mini {
  .el-radio {
    line-height: 28px !important;
  }

  .el-rate {
    margin-top: 4px;
  }
}

.top {
  width: 100%;
  height: 100px;
  background-color: white;

  h1 {
    float: left;
    font-size: 30px;
    margin: 37px 37px 37px 680px;
  }
}

.clear-fix:before,
.clear-fix:after {
  display: table;
  content: "";
}

.clear-fix:after {
  clear: both;
}

.float-right {
  float: right;
}
</style>

<style lang="scss" scoped>
.readonly-select ::v-deep .el-input__inner {
  color: #606266;
}

div.table-container {
  table.table-layout {
    width: 100%;
    table-layout: fixed;
    border-collapse: collapse;

    td.table-cell {
      display: table-cell;
      height: 36px;
      border: 1px solid #e1e2e3;
    }
  }
}

div.tab-container {
}

.label-left-align ::v-deep .el-form-item__label {
  text-align: left;
}

.label-center-align ::v-deep .el-form-item__label {
  text-align: center;
}

.label-right-align ::v-deep .el-form-item__label {
  text-align: right;
}

.custom-label {
}

.static-content-item {
  min-height: 20px;
  display: flex;
  align-items: center;

  ::v-deep .el-divider--horizontal {
    margin: 0;
  }
}
</style>

