<template>
  <div class="app-container">
    <div>
      <el-form inline ref="elForm" :model="form" :rules="rules" size="medium" label-width="150px">
        <el-form-item label="采购计划编码" prop="field107">
          <el-input v-model="queryParams.aCode" placeholder="请输入采购计划编码" clearable :style="{width: '100%'}">
          </el-input>
        </el-form-item>
        <el-form-item label="采购业务类型">
          <el-select ref="cClear" v-model="queryParams.aBtype" placeholder="请选择业务类型" @change="change">
            <el-option
              v-for="dict in dict.type.ppm_procurement_plan"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="采购计划名称">
          <el-input v-model="queryParams.aName" placeholder="请输入采购计划名称"></el-input>
        </el-form-item>
        <el-form-item size="medium">
          <el-button type="primary" @click="query">查询</el-button>
          <router-link to="insert">
            <el-button style="margin: 0px 20px" type="primary" @click="handleAdd">新建</el-button>
          </router-link>
          <el-button @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <div class="tcl">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="待提交" name="first">
          <el-table v-loading="loading" :data="planList">
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
            <el-table-column label="采购计划名称" align="center" prop="aName">
              <template slot-scope="scope">
                <span v-hasPermi="['system:attachments:list']" style="color: #008bcb;cursor: pointer"
                      @click="handleClick(scope.row)">{{
                    scope.row.aName
                  }}</span>
              </template>
            </el-table-column>
            <!--            <el-table-column label="行项目数量" align="center" prop="aProjectCount"/>-->
            <el-table-column label="创建人" align="center" prop="createBy"/>
            <el-table-column label="创建日期" align="center" prop="createTime"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                  v-hasPermi="['system:procurement:edit']"
                >修改
                </el-button>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['system:procurement:remove']"
                >删除
                </el-button>
                <el-button v-hasPermi="['system:attachments:list']" @click="handleClick(scope.row)" type="text"
                           size="small">查看
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="待审批" name="second">
          <el-table border v-loading="loading" :data="planList">
            <el-table-column type="expand" label="附件" align="center">
              <template slot-scope="scope">
                <el-descriptions border :column="4" direction="vertical">
                  <el-descriptions-item label="附件名称">
                    <p v-for="i in scope.row.file.anName.length">
                      <a target="_blank" :href="scope.row.file.anUrl.split(',')[i - 1]">
                        {{ scope.row.file.anName.split(",")[i - 1] }}
                      </a>
                    </p>
                  </el-descriptions-item>
                </el-descriptions>
              </template>
            </el-table-column>
            <el-table-column label="序号" align="center" prop="aid" width="80">
              <template slot-scope="scope">
                {{ scope.$index + 1 }}
              </template>
            </el-table-column>
            <el-table-column label="采购计划编号" align="center" prop="aCode"/>
            <el-table-column label="计划名称" align="center" prop="aName"/>
            <el-table-column label="创建部门" align="center" prop="aCreateDept"/>
            <el-table-column label="采购业务类型名称" align="center" prop="aBtype">
              <template slot-scope="scope">
                <dict-tag :options="dict.type.ppm_procurement_plan" :value="scope.row.aBtype"/>
              </template>
            </el-table-column>
            <el-table-column label="采购审批状态" align="center" prop="aAstate">
              <template slot-scope="scop">
                <el-tag size="danger">待审批</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button v-hasPermi="['system:attachments:list']" @click="handleClick(scope.row)" type="text"
                           size="small">查看
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="已生效" name="third">
          <el-table v-loading="loading" :data="planList">
            <el-table-column label="序号" align="center" prop="aid" width="80">
              <template slot-scope="scope">
                {{ scope.$index + 1 }}
              </template>
            </el-table-column>
            <el-table-column label="采购计划编号" align="center" prop="aCode"/>
            <el-table-column label="采购业务类型" align="center" prop="aCreateDept"/>
            <el-table-column label="采购计划名称" align="center" prop="aName"/>
            <!--            <el-table-column label="行项目数量" align="center" prop="aProjectCount"/>-->
            <el-table-column label="创建人" align="center" prop="createBy"/>
            <el-table-column label="创建日期" align="center" prop="createTime"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button v-hasPermi="['system:attachments:list']" @click="handleClick(scope.row)" type="text"
                           size="small">查看
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
      <!-- 添加或修改采购计划对话框 -->
      <el-dialog @close="cancel" :title="title" :visible.sync="open" width="1000px" append-to-body>
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
                <p v-for="i in file.names.length">
                  <a :href="file.urls[i-1]" target="_blank">{{ file.names[i - 1] }}</a>
                  <!--                  <i @click="onlinePreViewFile(file.urls[i-1],file.names[i - 1])"-->
                  <!--                     class="el-icon-folder-opened file_icon"></i>-->
                </p>
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
<!--              <el-table-column label="采购方式" align="center" prop="procurementMethod"/>-->
            </el-table>
            <el-card v-if="form.aAstate != 2" ref="card" style="margin-top: 20px" class="never">
              <div slot="header" class="clearfix">
                <span v-if="form.aAstate == 0">提交意见</span>
                <span v-if="form.aAstate == 1">审核意见</span>
                <el-button @click="item.show = ! item.show" style="float: right; padding: 3px 0" type="text">
                  {{ item.show ? '隐藏意见' : '显示意见' }}
                </el-button>
              </div>
              <div v-if="item.show" class="text item">
                <editor v-model="form.editor" :min-height="120"/>
              </div>
            </el-card>
          </el-tab-pane>
          <el-tab-pane label="审批记录" name="record_approval">

            <el-steps space="200" simple :active="form.aAstate + 1">
              <el-step title="待提交"></el-step>
              <el-step title="待审核"></el-step>
              <el-step title="已生效"></el-step>
            </el-steps>

            <el-table style="margin-top: 20px" max-height="250" v-loading="loading" :data="record_approval">
              <el-table-column type="expand">
                <template slot-scope="scope">
                  <el-card class="box-card">
                    <div slot="header" class="clearfix">
                      <span>意见详情</span>
                      <el-button @click="expand(scope.row.opinionDetails)" style="float: right; padding: 3px 0"
                                 type="text">查看更多
                      </el-button>
                    </div>
                    <div style="height: 100px" v-html="scope.row.opinionDetails" class="text item"></div>
                  </el-card>
                </template>
              </el-table-column>
              <el-table-column label="序号" align="center" prop="rid">
                <template slot-scope="scope">
                  {{ scope.$index + 1 }}
                </template>
              </el-table-column>
              <!--              <el-table-column label="采购计划id" align="center" prop="aid" width="110"/>-->
              <el-table-column label="流程节点" align="center" prop="node" width="135"/>
              <el-table-column label="处理人" align="center" prop="processedBy"/>
              <el-table-column label="所属部门" align="center" prop="depnt"/>
              <el-table-column label="处理时间" align="center" prop="updateTime" width="180">
                <template slot-scope="scope">
                  <span>{{ scope.row.updateTime }}</span>
                </template>
              </el-table-column>
              <el-table-column label="状态" align="center" prop="opinion"/>
            </el-table>
          </el-tab-pane>
        </el-tabs>
        <div slot="footer" class="dialog-footer">
          <el-button v-hasPermi="['system:procurement:submit']"
                     v-if="form.aAstate == 0" type="primary" v-loading.fullscreen.lock="fullscreenLoading"
                     @click="sumbitPlan">提 交
          </el-button>

          <div style="display: inline-block;margin-right: 10px" v-if="form.aAstate == 1">
            <el-button v-hasPermi="['system:procurement:allow']"
                       type="primary" @click="approved" v-loading.fullscreen.lock="fullscreenLoading">审核
            </el-button>
            <el-button v-hasPermi="['system:procurement:reject']"
                       type="danger" @click="rejectPlan" v-loading.fullscreen.lock="fullscreenLoading">驳回
            </el-button>
          </div>

          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
      <el-drawer
        append-to-body
        title="意见查看"
        direction="btt"
        size="40%"
        :visible.sync="drawer">
        <div style="padding: 10px" v-html="opinionDetails"></div>
      </el-drawer>
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
import {
  listPlan,
  getPlan,
  delPlan,
  selectProcurementPlanByIdForThreeTables,
  fileDownload,
  updatePlan, selectePpmProcurementPlanAndComPubAttamentByAid
} from '@/api/system/plan'
import {listRecord} from "@/api/system/approval";
import {listRules} from "@/api/code/rules";
import {Message} from "element-ui";
import {selectedComPubAttamentsByAid} from "@/api/file/attachments";
import modal from "@/plugins/modal";


export default {
  dicts: ['ppm_procurement_plan'],
  name: 'Plan',
  components: {},
  props: [],
  data() {
    return {
      opinionDetails: '',
      drawer: false,
      item: {
        show: true,
      },
      fileUrls: "",
      file: {
        urls: [],
        names: []
      },
      fullscreenLoading: false,
      tableData: [/* 表格数据 */],
      tableColumns: [/* 表格列配置 */],
      itemList: [],
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
    expand(content) {
      this.drawer = true;
      this.opinionDetails = content;
    },
    //点击在线预览文件
    onlinePreViewFile(url, fileName) {
      console.log("url", url)
    },
    download(fileName) {
      if (this.fileUrls.length == 0) {
        this.$modal.msgError("没有附件可下载，请上传附件！！")
        return;
      }
      let name = encodeURIComponent(this.fileUrls);
      var url = `http://localhost:8080/ppm/file/downloadFiles?file=${name}`;
      const a = document.createElement('a')
      a.setAttribute('target', '_blank')
      a.setAttribute('href', url)
      a.click()
    },
    // 新增窗口关闭
    cancelInsert() {
      this.showInsert = false;
    },
    /*查看采购计划*/
    handleClick(activeName, oldActiveName) {
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
          }).catch(err => {
            this.$modal.msgError("服务器出错，请联系管理员！！！");
          })
          break;
        default:
          let aid = activeName.aid == undefined ? this.form.aid : activeName.aid;
          this.queryParams.aAstate = null;
          this.reset()
          this.loading = true;
          selectProcurementPlanByIdForThreeTables(aid).then(res => {
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
          }).catch(err => {
            this.$modal.msgError("服务器出错，请联系管理员！！！");
          })
          selectedComPubAttamentsByAid(aid).then(res => {
            if (res.data) {
              this.fileUrls = res.data.anUrl;
              this.file.urls = res.data.anUrl.split(",");
              this.file.names = res.data.anName.split(",");
            } else {
              this.fileUrls = [];
              this.file.urls = [];
              this.file.names = [];
            }
          }).catch(err => {
            this.$modal.closeLoading();
            this.$modal.msgError("服务器出错，请联系管理员！！！");
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
      this.planList = [];
      selectePpmProcurementPlanAndComPubAttamentByAid(this.queryParams).then(response => {
        this.loading = false;
        this.total = response.total;
        this.planList = response.rows;
      }).catch(err => {
        this.$modal.msgError("服务器出错，请联系管理员！！！");
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.item.show = true;
      setTimeout(() => {
        this.budgetData = [];
        this.itemList = [];
        this.paneName = "basic";
        this.reset()
      }, 500)
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
        aAstate: 0,
        editor: ""
      }
      this.queryParams.aBtype = null;
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.queryParams.aCode = null;
      this.queryParams.aName = null;
      this.queryParams.aBtype = null;
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.reset('elForm');
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
        this.codeRuleForm = res.rows;
      }).catch(err => {
        this.$modal.msgError("服务器出错，请联系管理员！！！");
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const aid = row.aid || this.ids
      getPlan(aid).then(response => {
        console.log(response.data)
        this.$router.push({path: '/purchase/insert', query: response.data})
      }).catch(err => {
        this.$modal.msgError("服务器出错，请联系管理员！！！");
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
      this.$modal.confirm('采购计划下有行项目,是否确认删除采购计划编号为"' + aids + '"的数据项？')
        .then(() => {
          this.$modal.loading("删除中....")
          delPlan(aids).then(data => {
            this.$modal.closeLoading();
            this.$modal.msgSuccess('删除成功')
            this.getList()
          }).catch(err => {
            this.$modal.closeLoading();
            this.$modal.msgSuccess('删除失败' + err)
          })
        })
    },
    //单击查看预算
    click(row, column, cell, event) {
      console.log("row", row)
      if (row.ppmBudget.duName != null && row.ppmBudget.duName != undefined) {
        let list = [];
        list.push(row.ppmBudget)
        this.budgetData = list;
      } else {
        this.budgetData = null;
      }
    },
    //提交采购计划修改状态
    sumbitPlan() {
      this.$modal.confirm("你确定要提交采购计划吗？").then(() => {
        this.ComUpdatePlan(1);
      })

    },
    //审核采购计划
    approved() {
      if (!this.form.editor) {
        this.$modal.msgError("请输入审核意见")
        return;
      }
      this.$modal.confirm("你确定要审核该采购计划吗？").then(() => {
        this.ComUpdatePlan(2);
      })
    },
    //驳回采购计划
    rejectPlan() {
      if (!this.form.editor) {
        this.$modal.msgError("请输入审核意见")
        return;
      }
      this.$modal.confirm("你确定要驳回采购计划吗？").then(() => {
        this.ComUpdatePlan(0);
      })
    },
    //修改采购计划通用方法
    ComUpdatePlan(state) {
      this.fullscreenLoading = true;
      let obj = {...this.form};
      obj['aAstate'] = state;
      obj['editor'] = {
        opinionDetails: this.form.editor
      }
      updatePlan(obj).then(res => {
        this.fullscreenLoading = false;
        Message.success("操作成功！！");
        this.open = false;
        this.budgetData = [];
        this.itemList = [];
        this.paneName = "basic";
        this.activeName = 'first';
        setTimeout(() => {
          this.queryParams.aAstate = 0;
          this.getList();
        }, 500)
      }).catch(err => {
        this.fullscreenLoading = false;
        this.$modal.msgError("服务器出错，请联系管理员！！！");
      })
    }
  }
}
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}

.file_icon {
  cursor: pointer;
  margin-left: 20px;
}
</style>

