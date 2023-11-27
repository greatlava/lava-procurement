<template>
  <div class="app-container">
    <div>
      <el-form ref="elForm" :model="form" :rules="rules" size="medium" label-width="150px">
        <el-row type="flex" justify="start" align="middle" :gutter="15">
          <el-form-item label="采购计划编码" prop="field107">
            <el-input v-model="form.field107" placeholder="请输入采购计划编码" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item label="采购业务类型">
            <el-select ref="cClear" v-model="form.aBtype" placeholder="请选择业务类型" @change="change">
              <el-option
                v-for="dict in dict.type.ppm_procurement_plan"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item size="medium">
            <el-button type="primary" @click="query">查询</el-button>
            <router-link to="insert">
              <el-button style="margin: 0px 20px" type="primary" @click="handleAdd">新建</el-button>
            </router-link>
            <el-button @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-row>
      </el-form>
    </div>

    <div class="tcl">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="待提交" name="first">
          <el-table v-loading="loading" :data="planList">
            <el-table-column label="序号" align="center" prop="aid" width="80"/>
            <el-table-column label="采购计划编号" align="center" prop="aCode"/>
            <el-table-column label="采购业务类型" align="center" prop="aBtype">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.ppm_procurement_plan" :value="scope.row.aBtype"/>
              </template>
            </el-table-column>
            <el-table-column label="采购计划名称" align="center" prop="aName">
              <template slot-scope="scope">
                <span style="color: #008bcb;cursor: pointer" @click="handleClick(scope.row)">{{
                    scope.row.aName
                  }}</span>
              </template>
            </el-table-column>
            <el-table-column label="行项目数量" align="center" prop="aProjectCount"/>
            <el-table-column label="创建人" align="center" prop="createBy"/>
            <el-table-column label="创建日期" align="center" prop="createTime"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                  v-hasPermi="['system:plan:edit']"
                >修改
                </el-button>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['system:plan:view']"
                >删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="待审批" name="second">
          <el-table v-loading="loading" :data="planList">
            <el-table-column label="序号" align="center" prop="aid" width="80"/>
            <el-table-column label="采购计划编号" align="center" prop="aCode"/>
            <el-table-column label="计划名称" align="center" prop="aName"/>
            <el-table-column label="创建部门" align="center" prop="aCreateDept"/>
            <el-table-column label="附件" align="center" prop="fjAnnex"/>
            <el-table-column label="采购计划审核意见" align="center" prop="aOpinion"/>
            <el-table-column label="行项目数量" align="center" prop="aProjectCount"/>
            <el-table-column label="采购业务类型名称" align="center" prop="aBtype"/>
            <el-table-column label="采购审批状态" align="center" prop="aAstate"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="已生效" name="third">
          <el-table v-loading="loading" :data="planList">
            <el-table-column label="序号" align="center" prop="aid" width="80"/>
            <el-table-column label="采购计划编号" align="center" prop="aCode"/>
            <el-table-column label="采购业务类型" align="center" prop="aCreateDept"/>
            <el-table-column label="采购计划名称" align="center" prop="aName"/>
            <el-table-column label="行项目数量" align="center" prop="aProjectCount"/>
            <el-table-column label="创建人" align="center" prop="createBy"/>
            <el-table-column label="创建日期" align="center" prop="createTime"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
      <!-- 添加或修改采购计划对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
        <el-tabs @tab-click="handleClick" v-model="paneName">
          <el-tab-pane label="基础资料" name="basic">
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
              <el-table-column
                prop="duUsedMoney"
                align="center"
                label="已使用金额">
              </el-table-column>
            </el-table>
            <!-- 行项目 -->
            <el-table max-height="250" v-loading="loading" :data="device" style="margin-top: 20px">
              <el-table-column label="产品编码" align="center" prop="tid"/>
              <el-table-column label="行项目编号" align="center" prop="vCode" width="150"/>
              <el-table-column label="产品名称" align="center" prop="tName"/>
              <el-table-column label="数量" align="center" prop="tUnit"/>
              <el-table-column label="税率" align="center" prop="shui"/>
              <el-table-column label="计量单位" align="center" prop="tPrice"/>
              <el-table-column label="预算总价" align="center" prop="tTotalPrice"/>
              <el-table-column label="交付时间" align="center" prop="tDate" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.tDate, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="交付地点" align="center" prop="tAddress"/>
              <el-table-column label="需求说明" align="center" prop="tIllustrate"/>
              <el-table-column label="采购人" align="center" prop="tPurchaser"/>
              <el-table-column label="预算科目编号" align="center" prop="duCode" width="120"/>
              <el-table-column label="备注" align="center" prop="tNotes"/>
              <el-table-column label="采购方式" align="center" prop="aWay"/>
              <el-table-column label="采购计划状态" align="center" prop="aState" width="120"/>
            </el-table>
          </el-tab-pane>

          <el-tab-pane label="审批记录" name="record_approval">

            <el-steps space="200" simple :active="form.aAstate + 1">
              <el-step title="待提交"></el-step>
              <el-step title="待审核"></el-step>
              <el-step title="已生效"></el-step>
            </el-steps>

            <el-table style="margin-top: 20px" max-height="250" v-loading="loading" :data="record_approval">
              <el-table-column label="id" align="center" prop="rid"/>
              <el-table-column label="采购计划id" align="center" prop="aid" width="110"/>
              <el-table-column label="流程节点" align="center" prop="node" width="110"/>
              <el-table-column label="处理人" align="center" prop="processedBy"/>
              <el-table-column label="所属部门" align="center" prop="depnt"/>
              <el-table-column label="处理时间" align="center" prop="updateTime" width="180">
                <template slot-scope="scope">
                  <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
                </template>
              </el-table-column>
              <el-table-column label="处理意见" align="center" prop="opinion"/>
              <el-table-column label="意见详情" align="center" prop="opinionDetails" width="200"/>
              <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-delete"
                    @click="handleDelete(scope.row)"
                    v-hasPermi="['system:record:remove']"
                  >删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
        <div slot="footer" class="dialog-footer">
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
      <!--   显示新增修改   -->
<!--      <el-dialog :title="title" width="800px" :visible.sync="showInsert" append-to-body>-->
<!--        <el-form style="display: flex;flex-wrap: wrap; justify-content: space-around;" :inline="true" size="medium" ref="form" :model="form" :rules="rules" label-width="140px">-->
<!--          <el-form-item label="采购计划编号" prop="aCode">-->
<!--            &lt;!&ndash;            <el-input v-model="form.aCode" disabled placeholder="请输入采购计划编号"/>&ndash;&gt;-->
<!--            <el-select style="width: 202px" v-model="checkedRuleForm" placeholder="请选择">-->
<!--              <el-option-->
<!--                v-for="item in codeRuleForm"-->
<!--                :key="item.targetForm"-->
<!--                :label="item.targetForm"-->
<!--                :value="item.targetForm">-->
<!--              </el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="计划名称" prop="aName">-->
<!--            <el-input v-model="form.aName" placeholder="请输入计划名称"/>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="创建人" prop="createBy">-->
<!--            <el-input v-model="form.createBy" :disabled="form.aid!=null"/>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="创建部门" prop="aCreateDept">-->
<!--            <el-input v-model="form.aCreateDept" :disabled="form.aid!=null"/>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="附件" prop="fjAnnex">-->
<!--            <div style="width: 202px">-->
<!--              <el-button type="primary">-->
<!--                上传附件<i class="el-icon-upload"></i>-->
<!--              </el-button>-->
<!--            </div>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="采购计划审核意见" prop="aOpinion">-->
<!--            <el-input style="width: 202px" v-model="form.aOpinion" type="textarea" placeholder="请输入内容"/>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="行项目数量" prop="aProjectCount">-->
<!--            <el-input v-model="form.aProjectCount" placeholder="请输入行项目数量"/>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="采购业务类型名称" prop="aBtype">-->
<!--            <el-select style="width: 202px" v-model="form.aBtype" placeholder="请选择采购业务类型名称">-->
<!--              <el-option-->
<!--                v-for="dict in dict.type.ppm_procurement_plan"-->
<!--                :key="dict.value"-->
<!--                :label="dict.label"-->
<!--                :value="dict.value"-->
<!--              ></el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->
<!--        </el-form>-->
<!--        <div slot="footer" class="dialog-footer">-->
<!--          <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--          <el-button @click="cancelInsert">取 消</el-button>-->
<!--        </div>-->

<!--      </el-dialog>-->
    </div>
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
import {listPlan, getPlan, delPlan, addPlan, updatePlan, fileDownload} from '@/api/system/plan'
import {listBudget} from "@/api/system/budget";
import {listDevice} from "@/api/device/device";
import {listRecord} from "@/api/system/approval";
import {listRules} from "@/api/code/rules";

export default {
  dicts: ['ppm_procurement_plan'],
  name: 'Plan',
  components: {},
  props: [],
  data() {
    return {
      tableData: [/* 表格数据 */],
      tableColumns: [/* 表格列配置 */],
      device: [],
      activeName: 'first',
      //打开编辑或新增
      showInsert: false,
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
      // 采购计划表格数据
      planList: [],
      //编号规则目标表单
      codeRuleForm: [],
      //编号规则选择的值
      checkedRuleForm: '',
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      //pane选中name
      paneName: 'basic',
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        aCode: null,
        aName: null,
        aCreateDept: null,
        fjAnnex: null,
        aOpinion: null,
        aProjectCount: null,
        aBtype: null,
        aAstate: 0,
      },
      // 表单参数
      form: {
        field107: undefined,
        field106: undefined
      },
      // 表单校验
      rules: {
        field107: [],
        field106: []
      },
      field106Options: [{
        'label': '选项一',
        'value': 1
      }, {
        'label': '',
        'value': ''
      }],
      /*计划预算*/
      budgetData: [],
      /*审批记录*/
      record_approval: []
    }
  },
  created() {
    this.getList()
  },
  methods: {
    download(fileName) {
      fileDownload(fileName).then(res => {
        console.log("file", res)
      })
    },
    // 新增窗口关闭
    cancelInsert() {
      this.showInsert = false;
    },
    /*查看采购计划*/
    handleClick(activeName, oldActiveName) {
      console.log(activeName, oldActiveName)
      switch (activeName.name) {
        case 'first':
          this.queryParams.aAstate = 0;
          this.getList();
          break;
        case 'second':
          this.queryParams.aAstate = 1;
          this.getList();
          break;
        case 'third':
          this.queryParams.aAstate = 2;
          this.getList();
          break;
        case "record_approval":
          this.loading = true;
          listRecord({aid: this.form.aid}).then((res) => {
            this.loading = false;
            this.record_approval = res.rows;
          }).catch((err) => {

          })
          break;
        default:
          let aid = activeName.aid == undefined ? this.form.aid : activeName.aid;
          this.queryParams.aAstate = null;
          this.reset()
          this.loading = true;
          getPlan(aid).then(response => {
            this.form = response.data
            this.open = true
            listBudget({aid: aid}).then(res => {
              this.loading = false;
              this.budgetData = res.rows
            })
            listDevice(null).then(res => {
              this.loading = false;
              this.device = res.rows;
            })
          })
          break;
      }
    },
    query() {
      this.$nextTick(() => {
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
        })
        this.getList();
      })

    },
    resetForm() {
      this.$nextTick(() => {
        this.$refs['elForm'].resetFields()
      })
    },
    /** 查询采购计划列表 */
    getList() {
      this.loading = true
      listPlan(this.queryParams).then(response => {
        this.total = response.total;
        this.loading = false;
        this.planList = response.rows;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.budgetData = [];
      this.device = [];
      this.paneName = "basic";
      this.reset()
    },
    // 表单重置
    reset() {
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
      this.queryParams.aBtype = null;
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.reset('queryForm')
      this.handleQuery()
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.showInsert = true;
      this.selectRluesForm()
      this.title = '新增采购计划'
    },
    selectRluesForm() {
      listRules(null).then(res => {
        console.log("res", res.rows)
        this.codeRuleForm = res.rows;
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const aid = row.aid || this.ids
      getPlan(aid).then(response => {
        this.form = response.data
        this.showInsert = true;
        this.title = '修改采购计划'
      })
    },
    /** 提交按钮 */
    submitForm() {

    },
    /* 下拉框选中 */
    change(value) {
      if (value != 0)
        this.queryParams.aBtype = value;
      else
        this.queryParams.aBtype = null
      this.getList();
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const aids = row.aid || this.ids
      this.$modal.confirm('是否确认删除采购计划编号为"' + aids + '"的数据项？').then(function () {
        return delPlan(aids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    }
  }
}
</script>

