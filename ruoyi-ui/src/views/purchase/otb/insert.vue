<script setup>

import {addBudget} from "@/api/system/budget";
</script>

<template>
  <div class="box">
    <div class="top">
      <h1 class="title">{{ title }}</h1>
      <span class="describe">基本信息</span>
      <el-form class="form" label-position="top" :inline="true" size="medium" ref="form" :model="form"
               :rules="rules" label-width="140px">
        <el-row>
          <el-col :span="6">
            <el-form-item label="采购计划编号" prop="aCode">
              <el-input disabled value="系统自动生成"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="计划名称" prop="aName">
              <el-input v-model="form.aName" placeholder="请输入计划名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="创建人" prop="createBy">
              <el-input v-model="form.createBy" :disabled="form.aid!=null"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="创建部门" prop="aCreateDept">
              <el-input v-model="form.aCreateDept" :disabled="form.aid!=null"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="附件" prop="fjAnnex">
              <div style="width: 202px">
                <el-button type="primary">
                  上传附件<i class="el-icon-upload"></i>
                </el-button>
              </div>
            </el-form-item>
          </el-col>

          <el-col :span="6">
            <el-form-item label="行项目数量" prop="aProjectCount">
              <el-input disabled v-model.number="form.aProjectCount" placeholder="系统自动生成"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="采购业务类型名称" prop="aBtype">
              <el-select style="width: 202px" v-model="form.aBtype" placeholder="请选择采购业务类型名称">
                <el-option
                  v-for="dict in dict.type.ppm_procurement_plan"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <!--      预算列表     -->
    </div>
    <div class="top">
      <h1 class="title">预算信息</h1>
      <span class="describe">添加预算</span>
      <div class="material_information">
        <div class="botton">
          <el-button type="primary" @click="showAddBudget" size="small">添加预算</el-button>
        </div>
        <el-table max-height="250" v-loading="loading" :data="budgetList">
          <el-table-column label="预算ID" align="center" prop="duId">
            <template slot-scope="scope">
              <span>{{ scope.row.duId }}</span>
            </template>
          </el-table-column>
          <el-table-column label="部门名称" align="center" prop="duDept"/>
          <el-table-column label="预算科目名称" align="center" prop="duName"/>
          <el-table-column label="总金额" align="center" prop="duTotal"/>
          <el-table-column label="已使用金额" align="center" prop="duUsedMoney"/>
          <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
            <!--            @click="handleDeleteBudget(scope.row,scope.$index)"-->
            <template slot-scope="scope">
              <el-popconfirm
                confirm-button-text='删除'
                cancel-button-text='取消'
                icon="el-icon-info"
                icon-color="red"
                :title="'你确定要删除编号为'+scope.row.aid+'吗？'"
                @confirm="handleDeleteBudget(scope.row,scope.$index)"
              >
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  slot="reference"

                >删除
                </el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <!--    物料信息     -->
    <div class="content top">
      <h1 class="title">物料信息</h1>
      <span class="describe">添加物料</span>
      <div class="material_information">
        <div class="botton">
          <el-button type="primary" icon="el-icon-plus" @click="addMaterial" size="mini"></el-button>
          <el-button type="primary" icon="el-icon-minus" @click="delMaterial" size="mini"></el-button>
          <el-button type="primary" icon="el-icon-copy-document" size="mini"></el-button>
        </div>
        <el-table ref="elTable" max-height="250" v-loading="loading" :data="device" border style="margin-top: 20px">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="序号" align="center">
            <template slot-scope="scope">
              <span>{{ scope.$index + 1 }}</span>
            </template>
          </el-table-column>
          <el-table-column label="行项目编号" align="center" prop="vCode" width="150">
            <template slot-scope="scope">
              <span v-if="form.aid==null">自动生成</span>
              <el-input v-model="scope.row.vCode" class="borderNone" disabled></el-input>
            </template>
          </el-table-column>
          <el-table-column label="物料编号" align="center" prop="tid" width="150">
            <template slot-scope="scope">
              <span>{{ scope.row.tid }}</span>
              <i class="el-icon-search" @click="search(scope.$index)"></i>
            </template>
          </el-table-column>
          <el-table-column label="数量" align="center" prop="vCount" width="230">
            <template slot-scope="scope">
              <el-input-number :step="1" step-strictly v-model="scope.row.vCount"></el-input-number>
            </template>
          </el-table-column>
          <el-table-column label="采购人" align="center" prop="vPerson" width="120">
            <template slot-scope="scope">
              <el-input class="borderNone" v-model="scope.row.vPerson"></el-input>
            </template>
          </el-table-column>
          <el-table-column label="交付时间" align="center" prop="vDeliveryTime" width="200">
            <template slot-scope="scope">
              <el-input class="borderNone" v-model="scope.row.vDeliveryTime"></el-input>
            </template>
          </el-table-column>
          <el-table-column label="交付地点" align="center" prop="vDeliveryArea" width="180">
            <template slot-scope="scope">
              <el-input class="borderNone" v-model="scope.row.vDeliveryArea">
                {{ parseTime(scope.row.tDate, '{y}-{m}-{d}') }}
              </el-input>
            </template>
          </el-table-column>
          <el-table-column label="需求说明" align="center" prop="vIllustrate" width="160">
            <template slot-scope="scope">
              <el-input class="borderNone" v-model="scope.row.vIllustrate"></el-input>
            </template>
          </el-table-column>
          <el-table-column label="预算" align="center" fixed="right">
            <template slot-scope="scope">
              <span style="margin-right: 10px">{{ scope.row.did }}</span>
              <i class="el-icon-search" @click="searchBudget(scope.$index)"></i>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row,scope.$index)"
                v-hasPermi="['system:record:remove']"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <!--    底部按钮  -->
    <div slot="footer" class="button">
      <el-button type="primary" @click="sumbitPlan" v-if="this.form.aid==null">提 交
      </el-button>
      <el-button type="warning" v-loading.fullscreen.lock="fullscreenLoading" @click="updatePlanByAid" v-else>修 改
      </el-button>
      <el-button @click="goBack">返 回</el-button>
    </div>
    <!--  -------------------------------------- 弹窗开始 --------------------------------  -->
    <!--    搜索设备/物料    -->
    <el-dialog title="收货地址" :visible.sync="show.open" width="42%">
      <el-form :model="form" ref="queryForm" size="small" :inline="true" label-width="68px">
        <el-form-item label="产品编码" prop="tid">
          <el-input
            v-model="queryParams.tid"
            placeholder="请输入产品编码"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="产品名称" prop="tName">
          <el-input
            v-model="queryParams.tName"
            placeholder="请输入产品名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
      <el-table @cell-dblclick="selectedMateria" max-height="300" border :data="newDevice"
                v-loading="show.deviceLoding">
        <el-table-column label="产品编码" align="center" prop="tid"/>
        <el-table-column label="产品名称" align="center" prop="tName"/>
      </el-table>
      <div :class="{'hidden':hidden}" class="pagination-container">
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getMaterialList"
        />
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">关 闭</el-button>
      </div>
    </el-dialog>
    <!--    添加预算     -->
    <el-dialog title="添加预算" :visible.sync="show.openBudget" width="500px" append-to-body>
      <el-form style="display: flex; flex-wrap: wrap; justify-content: space-between;" :inline="true"
               label-position="top" ref="budgetValidateForm" :model="budgetForm" :rules="budRules" label-width="100px">
        <el-form-item label="预算编号" prop="newCheckedRuleForm">
          <el-input value="系统自动生成" disabled/>
        </el-form-item>
        <el-form-item label="部门名称" prop="duDept">
          <el-input v-model="budgetForm.duDept" placeholder="请输入部门名称"/>
        </el-form-item>
        <el-form-item label="预算科目名称" prop="duName">
          <el-input v-model="budgetForm.duName" placeholder="请输入预算科目名称"/>
        </el-form-item>
        <el-form-item label="总金额" prop="duTotal">
          <el-input v-model.number="budgetForm.duTotal" placeholder="请输入总金额"/>
        </el-form-item>
        <el-form-item label="已使用金额" prop="duUsedMoney">
          <el-input v-model.number="budgetForm.duUsedMoney" placeholder="请输入已使用金额"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitBudgetForm" v-loading.fullscreen.lock="fullscreenLoading">提 交
        </el-button>
        <el-button @click="cancel">返 回</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="show.changeBudget">
      <el-table @cell-dblclick="doubleClickBudget" max-height="300" v-loading="loading" :data="budgetList">
        <el-table-column label="预算ID" align="center" prop="aid">
          <template slot-scope="scope">
            <span>{{ scope.$index + 1 }}</span>
          </template>
        </el-table-column>
        <el-table-column label="部门名称" align="center" prop="duDept"/>
        <el-table-column label="预算科目名称" align="center" prop="duName"/>
        <el-table-column label="总金额" align="center" prop="duTotal"/>
        <el-table-column label="已使用金额" align="center" prop="duUsedMoney"/>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">返 回</el-button>
      </div>
    </el-dialog>
    <!--  -------------------------------------- 弹窗结束 --------------------------------  -->
  </div>
</template>
<script>
import {listDevice} from "@/api/device/device";
import {addBudget, delBudget, selectPpmBudgetByAid} from '@/api/system/budget'
import {
  generatePlanID,
  addPlan,
  selectProcurementPlanByIdForThreeTables,
  updatePlan,
  ModifyPlanAndOtherInformation
} from '@/api/system/plan'
import {Message} from "element-ui";

export default {
  dicts: ['ppm_procurement_plan'],
  data() {
    return {
      hidden: {
        type: Boolean,
        default: false
      },
      fullscreenLoading: false,
      total: 0,
      //所有设备列表
      newDevice: [],
      //编号规则目标表单
      codeRuleForm: [],
      //编号规则选择的值
      checkedRuleForm: null,
      //添加预算选择编号规则
      newCheckedRuleForm: null,
      //选中设备信息
      device: [],
      //添加预算规则
      budRules: {
        duDept: [{required: true, message: '请输入部门名称', trigger: 'blur'}],
        duName: [{required: true, message: '请输入预算科目名称', trigger: 'blur'}],
        duTotal: [
          {required: true, message: '请输入总金额', trigger: 'blur'},
          {type: 'number', message: '已用金额必须是数字'}
        ],
        duUsedMoney: [
          {type: 'number', message: '已用金额必须是数字'}
        ]
      },
      //标题
      title: "新增采购计划",
      //form表单
      form: {},
      loading: false,
      //预览编号
      previewCode: null,
      //表单验证
      rules: {
        aName: [{required: true, message: '计划名称不能为空', trigger: 'blur'}],
        createBy: [{required: true, message: '创建人不能为空', trigger: 'blur'}],
        aCreateDept: [{required: true, message: '创建部门不能为空', trigger: 'blur'}],
        aBtype: [{required: true, message: '业务类型不能为空', trigger: 'blur'}]
      },
      //采购预算列表
      budgetList: [],
      //采购预算表单
      budgetForm: {},
      //显示弹窗
      show: {
        //添加预算
        open: false,
        openBudget: false,
        codeDisabled: false,
        //选择添加的预算
        changeBudget: false,
        deviceLoding: false,
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tName: null,
        tid: null
      },
      selectedIndex: 0
    }
  },
  created() {
    let obj = this.$route.query;
    if (obj.aid) {
      this.form = obj;
      this.getItemsByPlanId();
      return;
    }
    let planId = localStorage.getItem("procurementPlanID");
    if (planId == null && planId == undefined) {
      generatePlanID().then((res) => {
        localStorage.setItem("procurementPlanID", res);
        console.log('res', res)
      }).catch((err) => {
        Message.error("服务器异常请稍后重试！！");
        setTimeout(() => {
          this.$router.back();
        }, 2000)
      })
    }
  },
  beforeDestroy() {
    localStorage.removeItem("procurementPlanID");
    console.log("页面销毁---------------")
  },
  methods: {
    handleDelete(row, index) {
      this.device.splice(index, 1)
    },
    addMaterial() {
      this.device.push({})
    },
    delMaterial() {
      let table = this.$refs.elTable.selection;
      console.log("t", table)
      console.log("index", table[0])
    },
    //搜索icon 显示搜索窗口
    search(index) {
      console.log(index, "index")
      this.selectedIndex = index;
      this.show.open = true;
      if (this.newDevice.length == 0)
        this.getMaterialList();
    },
    //搜索按钮
    handleQuery() {
      this.getMaterialList();
    },
    //重置按钮
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        tName: null,
        tid: null
      }
      this.getMaterialList();
    },
    //获取物料列表
    getMaterialList() {
      this.show.deviceLoding = true;
      listDevice(this.queryParams).then((res => {
        this.newDevice = res.rows;
        this.total = res.total;
        this.show.deviceLoding = false;
      }))
    },
    //关闭按钮
    cancel() {
      this.show.open = false;
      this.show.openBudget = false;
      this.show.changeBudget = false;
    },
    //双击选中物料
    selectedMateria(row) {
      let obj = this.device[this.selectedIndex];
      obj = {...obj, tid: row.tid}
      this.device.splice(this.selectedIndex, 1, obj);
      this.show.open = false;
    },
    //显示添加预算
    showAddBudget() {
      if (this.budgetList.length > 0)
        this.show.codeDisabled = true;
      this.show.openBudget = true;
    },
    //添加预算按钮
    submitBudgetForm() {
      this.$refs['budgetValidateForm'].validate((valid) => {
        if (valid) {
          let budget = this.budgetForm;
          if (this.form.aid) {
            budget['aid'] = this.form.aid;
          } else {
            budget['aid'] = localStorage.getItem("procurementPlanID");
          }
          this.fullscreenLoading = true;
          addBudget(budget).then(res => {
            budget['duId'] = res.msg;
            this.budgetList.push(budget);
            this.budgetForm = {};
            this.fullscreenLoading = false;
            this.show.openBudget = false;
          }).catch(err => {

          })
        } else {
          console.log('error submit!!');
          return false;
        }
      })
    },
    //预算列表删除按钮
    handleDeleteBudget(row, index) {
      delBudget(row.duId).then(res => {
        this.budgetList.splice(index, 1)
      })
    },
    //添加预算弹窗编号选择事件
    changeCode(value) {
      let obj = this.codeRuleForm.filter(item => item.targetForm == value)[0];
      console.log("obj", obj)
      var now = new Date();
      let str = obj.prefix;
      if (obj.year != null) {
        str += now.getFullYear();
      }
      if (obj.mouth != null) {
        str += now.getMonth() + 1;
      }
      if (obj.day != null) {
        str += now.getDay();
      }
      if (obj.hour != null) {
        str += now.getHours();
      }
      if (obj.minute != null) {
        str += now.getMinutes();
      }
      if (obj.second != null) {
        str += now.getSeconds();
      }
      str += '001';
      this.budgetForm.previewCode = str;
    },
    //选择添加的预算
    searchBudget(index) {
      this.selectedIndex = index;
      this.show.changeBudget = true;
    },
    //双击选中预算
    doubleClickBudget(row) {
      let obj = this.device[this.selectedIndex];
      obj = {...obj, did: row.duId}
      this.device.splice(this.selectedIndex, 1, obj);
      this.show.changeBudget = false;
      console.log("row", obj)
    },
    goBack() {
      this.$router.back();
    },
    sumbitPlan() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.device.length == 0) {
            this.$modal.msgError("请至少添加一条物料信息");
            return false;
          }
          if (this.device.some(e => !e.tid)) {
            this.$modal.msgError("所有行项目设备不能为空");
            return; // 跳出整个函数
          }
          this.form['items'] = this.device;
          this.form["aCode"] = localStorage.getItem("procurementPlanID");
          addPlan(this.form).then(res => {
            Message.success("操作成功");
            setTimeout(() => {
              this.$router.back();
            }, 1000)
          })
        }
      })
    },
    getItemsByPlanId() {
      selectProcurementPlanByIdForThreeTables(this.form.aid).then(res => {
        console.log("data", res)
        this.device = res.data.items;
      })
      selectPpmBudgetByAid(this.form.aCode).then(res => {
        this.budgetList = res.data;
      })
    },
    updatePlanByAid() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.device.length == 0) {
            this.$modal.msgError("请至少添加一条物料信息");
            return false;
          }
          if (this.device.some(e => !e.tid)) {
            this.$modal.msgError("所有行项目设备不能为空");
            return; // 跳出整个函数
          }
          this.form['items'] = this.device;
          this.fullscreenLoading = true;
          ModifyPlanAndOtherInformation(this.form).then(res => {
            this.fullscreenLoading = false;
            this.$modal.msgSuccess("操作成功！！")
            setTimeout(() => {
              this.$router.back();
            },1000)
          })
        }
      })
    }
  }
}
</script>
<style scoped>
.top {
//background: #fafafa; padding: 20px 40px; box-shadow: -2px 2px 2px 2px #dadada; width: 1300px; margin: 50px auto; border: 1px solid #ececec; //background: #f5f5f5; //height: 500px;
}

.title {
  text-align: center;
  padding-bottom: 30px;
  border-bottom: 1px #eeeeee solid;
  margin: 10px 0px 0px 0px;
}

.describe {
  display: inline-block;
  margin-bottom: 15px;
  margin-top: 15px;
}

.form {
  padding: 20px 20px;
  padding-left: 80px;
  border: 1px #d2d2d2 solid
}

.material_information {
  padding: 10px;
  border: 1px #d2d2d2 solid
}

.botton {
  text-align: right;
  padding: 10px;
}

.button {
  text-align: right;
  padding: 0px 200px 50px 0px;
}

.borderNone >>> .el-input__inner {
  border: none;
  text-align: center;
//box-shadow: none;
}
</style>
