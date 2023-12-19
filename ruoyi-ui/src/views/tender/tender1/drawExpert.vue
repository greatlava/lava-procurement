<template>
  <div>
    <div class="app-container">
      <div class="box">招标项目</div>
      <el-table v-loading="loading" :data="tenderList" border>
        <el-table-column label="项目编号" align="center" prop="sCode" width="200" />
        <el-table-column label="招标单位" align="center" prop="sUnit" />
        <el-table-column label="负责人" align="center" prop="sLeader" />
        <el-table-column label="项目名称" align="center" prop="sName" />
        <el-table-column label="业务类型" align="center" prop="sType" width="200">
          <template slot-scope="scope">
            <el-tag type="success">
              <dict-tag :options="dict.type.ppm_procurement_plan" :value="scope.row.sType" />
            </el-tag>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="app-container">
      <el-descriptions class="margin-top" title="抽取申请" style="color: #409eff;" :column="2" border>
        <el-descriptions-item>
          <template slot="label">
            抽取申请编号
          </template>
          编码自动生成
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            招标项目名称
          </template>
          <template>
            {{sName}}
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            资格审查方式
          </template>
          <template slot="default">
            {{queryParams.xType == "0"?'':queryParams.xType == 1?'资格预审':'资格后审'}}
            <!-- <el-radio v-model="queryParams.xType" label="1">资格预审</el-radio>
            <el-radio v-model="queryParams.xType" label="2">资格后审</el-radio> -->
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            专家确定方式
          </template>
          <template slot="default">
            <el-radio label="1" v-model="queryParams.xWay">随机抽取</el-radio>
            <el-radio label="2" v-model="queryParams.xWay">直接指定</el-radio>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            评标开始时间
          </template>
          <template>
             <el-date-picker
                 v-model="queryParams.xStartTime" id="input-common1"
                  type="date"
                  placeholder="请选择评标开始时间">
                </el-date-picker>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            评标结束时间
          </template>
          <template> 
             <el-date-picker
                 v-model="queryParams.xEndTime" id="input-common2"
                  type="date"
                  placeholder="选择日期">
                </el-date-picker>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            专家人数
          </template>
          <template>
            <!-- <el-input v-model="queryParams.xCount" id="input-common3" /> -->
            <el-input-number v-model="queryParams.xCount" :min="1" :max="10" label="描述文字"></el-input-number>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            采购方代表人数
          </template>
          <template>
            <!-- <el-input  v-model="queryParams.xDaiCount" id="input-common4" /> -->
            <el-input-number v-model="queryParams.xDaiCount" :min="1" :max="10" label="描述文字"></el-input-number>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            评标地点
          </template>
          <template>
            <el-input v-model="queryParams.xArea" id="input-common6" />
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            操作
          </template>
          <template>
            <el-button type="primary" @click="subApplication">确定</el-button>
            <el-button @click="cancel">取消</el-button>
          </template>
        </el-descriptions-item>
      </el-descriptions>
    </div>
    <div class="app-container" v-show="queryParams.xWay == 2">
      <div class="box">直接指定</div>
      <div class="cl">
        <el-button @click="lAddRow">新增</el-button>
        <el-button @click="lDeleteRows" :disabled="lSelectedRows.length === 0">删除</el-button>
        <el-button @click="lCopyRows" :disabled="lSelectedRows.length === 0">复制</el-button>
        <el-table :data="lTableData" :row-key="row => row.id" @selection-change="lHandleSelectionChange" border stripe
          :style="{marginTop:'10px'}">
          <el-table-column type="selection" width="55" />
          <el-table-column label="序号" prop="id" width="100" />
          <el-table-column label="专家姓名" prop="jName" width="200">
            <template slot-scope="scope">
              <el-input v-model="scope.row.jName" id="input-common" readonly>
                <i slot="suffix" class="el-icon-search" @click="openCp(scope.row)" style="margin-top: 10px" />
              </el-input>
              <el-dialog title="专家姓名" :visible.sync="cpDialog">
                <el-table ref="singleTable" :data="expertList" highlight-current-row style="width: 100%"
                  @row-click="handleRowClick">
                  <el-table-column prop="jName" label="专家名称" width="140" />
                  <el-table-column prop="jSex" label="性别" width="130">
                    <template slot-scope="scope">
                      <span v-if="scope.row.jSex==1">男</span>
                      <span v-else>女</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="jIdentity" label="身份证号" width="150" />
                  <el-table-column prop="jPhone" label="手机号" width="85" />
                  <el-table-column prop="email" label="邮箱" width="120" />
                  <el-table-column prop="jUnit" label="工作单位" width="100" />
                </el-table>
                <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum"
                  :limit.sync="queryParams.pageSize" @pagination="selectBdList" />
                <div style="margin-top: 20px">
                  <el-button @click="closeDialog1">取消</el-button>
                </div>
              </el-dialog>
            </template>
          </el-table-column>
          <el-table-column label="性别" prop="jSex" width="200">
            <template slot-scope="scope">
              <el-input readonly v-model="scope.row.jSex" id="input-common5" />
            </template>
          </el-table-column>
          <el-table-column label="身份证号" prop="jIdentity" width="200">
            <template slot-scope="scope">
              <el-input readonly v-model="scope.row.jIdentity" id="input-common7" />
            </template>
          </el-table-column>
          <el-table-column label="手机号" prop="jPhone" width="200">
            <template slot-scope="scope">
              <el-input readonly v-model="scope.row.jPhone" id="input-common8" />
            </template>
          </el-table-column>
          <el-table-column label="邮箱" prop="email" width="200">
            <template slot-scope="scope">
              <el-input readonly v-model="scope.row.email" id="input-common9" />
            </template>
          </el-table-column>
          <el-table-column label="工作单位" prop="jUnit">
            <template slot-scope="scope">
              <el-input readonly v-model="scope.row.jUnit" id="input-common10" />
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="app-container" v-show="queryParams.xWay == 1">
      <div class="box">随机抽取</div>
      <el-button style="margin-bottom: 10px;">点击抽取</el-button>
      <div class="cl">
        <el-table border stripe>
          <el-table-column label="序号" prop="id" width="100" />
          <el-table-column label="专家姓名" prop="jName" width="200" />
          <el-table-column label="性别" prop="jSex" width="200" />
          <el-table-column label="身份证号" prop="jIdentity" width="200" />
          <el-table-column label="手机号" prop="jPhone" width="200" />
          <el-table-column label="邮箱" prop="email" width="200" />
          <el-table-column label="工作单位" prop="jUnit" />
        </el-table>
      </div>
    </div>
  </div>
</template>
<script>
  import {
    findTenderNotice
  } from "@/api/system/tender/tender";
  import {
    operatorList
  } from "@/api/system/tender/getTender";
  import {
    listExpert
  } from "../../../api/system/expert";
  import {
    addApplications,getMaxApp
  } from "@/api/system/tender/bidApplication";

  export default {
    dicts: ["ppm_procurement_plan"],
    name: "Tender",
    data() {
      return {
        //合同标的表格
        lTableData: [],
        lSelectedRows: [],
        lTableColumns: [],
        cpDialog: false,
        selectRow: null,
        expertList: [],
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        // 获取项目和公告数据
        tenderList: [],
        // 弹出层标题
        title: "",
        sName: null,
        // 是否显示弹出层
        open: false,
        // 查询参数(抽取申请)
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          pbId: null,
          xCode: null,
          xName: null,
          xWay: "2",
          xType: "0",
          xStartTime: null,
          xEndTime: null,
          xCount: null,
          xDaiCount: null,
          xArea: null,
          sid:null
        },
        // 查询参数(专家)
        queryParams2: {
          pageNum: 1,
          pageSize: 10,
          qid: null,
          pbId: null,
          jName: null,
          jSex: null,
          jIdentity: null,
          jPhone: null,
          email: null,
          jState: null,
          jUnit: null,
          jRetire: null,
          jAddress: null,
          jCard: null,
          jIdentityPhoto: null,
          jDocumentsPhoto: null,
          jBank: null,
          jShState: null,
          jOpinion: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {}
      };
    },
    created() {
      this.queryParams.sid = this.$route.query.sid;
      this.getList(this.queryParams.sid);
    },
    methods: {
      /** 查询招标项目及公告 */
      getList(sid) {
        this.loading = true;
        findTenderNotice(sid).then(response => {
          this.tenderList.push(response.data);
          this.sName = response.data.sName;
          this.queryParams.xType = response.data.sSway;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.queryParams.xStartTime = null;
        this.queryParams.xEndTime = null;
        this.queryParams.xDaiCount = null;
        this.queryParams.xCount = null;
        this.queryParams.xArea = null;
        this.queryParams.xWay = "0";
      },
      //查询所有准入专家
      selectBdList() {
        listExpert(this.queryParams2).then(res => {
          console.log(res, "res");
          this.expertList = res.rows;
        });
      },
      //添加一条抽取申请
      subApplication() {
        console.log(this.lTableData, "sub");
        addApplications(this.queryParams).then(res => {
            this.maxId(this.queryParams.sid);
        });
      },
      //查询当前项目最大Id
      maxId(sid) {
        getMaxApp(sid).then(res=>{
          console.log(res,"resssss");
        });
      },
      /* 直接抽取专家 */
      //添加
      lAddRow() {
        if (this.lTableData.length >= this.queryParams.xCount) {
          this.$alert("添加评标专家不能超过" + this.queryParams.xCount + "人!", "温馨提示");
        } else {
          const newRow = {}
          this.lTableColumns.forEach(column => {
            newRow[column.prop] = ''
          })
          newRow.id = this.lTableData.length + 1
          console.log(this.lTableColumns, "it");
          this.lTableData.push(newRow);
          console.log(this.lTableColumns, "it2");
        }
      },
      //删除
      lDeleteRows() {
        this.$confirm('确定删除选中的行吗?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.lTableData = this.lTableData.filter(row => !this.lSelectedRows.includes(row))
          this.lSelectedRows = []
          this.lUpdateRowIds()
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      //复制
      lCopyRows() {
        const copiedRows = this.lSelectedRows.map(row => ({
          ...row
        }))
        copiedRows.forEach(row => {
          row.id = this.lTableData.reduce((maxId, row) => Math.max(row.id, maxId), 0) + 1
          this.lTableData.push(row)
        })
      },
      //行数变化
      lUpdateRowIds() {
        this.lTableData.forEach((row, index) => {
          row.id = index + 1;
        })
      },
      //单选多选
      lHandleSelectionChange(selection) {
        this.lSelectedRows = selection
      },
      //专家名称行点击事件
      handleRowClick(row) {
        // 在这里处理行点击事件
        this.selectRow.jName = row.jName;
        if (row.jSex == 1) {
          this.selectRow.jSex = "男"
        } else {
          this.selectRow.jSex = "女"
        }
        this.selectRow.jIdentity = row.jIdentity
        this.selectRow.jPhone = row.jPhone
        this.selectRow.email = row.email
        this.selectRow.jUnit = row.jUnit
        this.cpDialog = false;
      },
      //显示专家对话框
      openCp(row) {
        console.log(row, "row");
        this.selectRow = row;
        console.log(this.selectRow, "rows");
        this.cpDialog = true;
        this.selectBdList();
      },
      //关闭专家对话框
      closeDialog1() {
        this.cpDialog = false
      },
    }
  };
</script>
<style>
  .box {
    margin: 10px 0 15px 0px;
    color: #409eff;
    font-size: 16px;
    font-weight: bold;
  }

  .input-common {
    border: none;
  }
</style>
