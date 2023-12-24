<template>
  <div>
    <div class="app-container" style="padding: 10px">
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
    <div class="app-container" style="padding: 10px">
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
            <el-radio label="1" v-model="queryParams.xWay" :disabled='xWaydialog'>随机抽取</el-radio>
            <el-radio label="2" v-model="queryParams.xWay" :disabled='xWaydialog'>直接指定</el-radio>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            评标开始时间
          </template>
          <template>
            <el-date-picker v-model="queryParams.xStartTime" id="input-common1" type="date" placeholder="请选择评标开始时间">
            </el-date-picker>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            评标结束时间
          </template>
          <template>
            <el-date-picker v-model="queryParams.xEndTime" id="input-common2" type="date" placeholder="请选择评标结束时间">
            </el-date-picker>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            专家人数
          </template>
          <template>
            <!-- <el-input v-model="queryParams.xCount" id="input-common3" /> -->
            <el-input-number v-model="queryParams.xCount" :min="1" :max="10" :disabled='xWaydialog'
              label="描述文字"></el-input-number>
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
    <div class="app-container" style="padding: 10px" v-show="queryParams.xWay == 2">
      <div class="box">直接指定</div>
      <div class="cl">
        <el-button @click="lAddRow" :disabled="addDialog">新增</el-button>
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
              <el-dialog title="专家列表" :visible.sync="cpDialog">
                <el-table ref="singleTable"  v-loading="expertLoading" :data="expertList" highlight-current-row style="width: 100%"
                  @row-click="handleRowClick">
                  <el-table-column prop="jid" label="专家ID" width="80" align="center"/>
                  <el-table-column prop="jName" label="专家名称" width="120" align="center"/>
                  <el-table-column prop="jSex" label="性别" width="100" align="center">
                    <template slot-scope="scope">
                      <span v-if="scope.row.jSex==1">男</span>
                      <span v-else>女</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="jIdentity" label="身份证号" width="150" align="center"/>
                  <el-table-column prop="jPhone" label="手机号" width="150" align="center"/>
                  <el-table-column prop="email" label="邮箱" width="200" align="center"/>
                  <el-table-column prop="jUnit" label="工作单位" align="center"/>
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
          <el-table-column label="专家ID" prop="jid">
            <template slot-scope="scope">
              <el-input readonly v-model="scope.row.jid" id="input-common10" />
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="text-align: right;margin-top: 10px;">
        <el-button type="primary" @click="zjcq" v-show="lTableData.length > 0">确定</el-button>
      </div>
    </div>
    <div class="app-container" style="padding: 10px" v-show="queryParams.xWay == 1">
      <div class="box">随机抽取</div>
      <el-button style="margin-bottom: 10px;" :disabled="addDialog">点击抽取</el-button>
      <div class="cl">
        <el-table border stripe>
          <el-table-column label="序号" prop="id" width="100" />
          <el-table-column label="专家姓名" prop="jName" width="200" />
          <el-table-column label="性别" prop="jSex" width="200" />
          <el-table-column label="身份证号" prop="jIdentity" width="200" />
          <el-table-column label="手机号" prop="jPhone" width="200" />
          <el-table-column label="邮箱" prop="email" width="200" />
          <el-table-column label="工作单位" prop="jUnit" />
          <el-table-column label="专家ID" prop="jid"/>
        </el-table>
      </div>
      <div style="text-align: right;margin-top: 10px;">
        <el-button type="primary" @click="sjcq" v-show="lTableData.length > 0">确定</el-button>
      </div>
    </div>
  </div>
</template>
<script>

  import {
    operatorList
  } from "@/api/system/tender/getTender";
  import {findTenderNotice} from "@/api/system/tender/tender";
  import {listExpert} from "@/api/system/expert";
  import {addApplications, getmaxApp} from "@/api/system/tender/bidApplication";
  import {addCommittee} from "@/api/system/tender/committee";

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
        xWaydialog: false,
        expertLoading:false,
        addDialog:true,
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
          xWay: "1",
          xType: "0",
          xStartTime: null,
          xEndTime: null,
          xCount: null,
          xDaiCount: null,
          xArea: null,
          sid: null
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
        //评标委员会
        committees:{
          jid:null,
          xid:null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {},
        xCount: null,
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
        this.queryParams.xWay = "1";
        this.lTableData=[];
        this.lSelectedRows=[];
        this.lTableColumns=[];
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
          this.maxId();
          this.xWaydialog = true;
          this.addDialog=false;
          this.$message({
            type: 'success',
            message: '申请成功!'
          });
        });
      },
      //查询当前项目最大Id
      maxId() {
        getmaxApp(this.queryParams.sid).then(res => {
          // console.log(res, "resssssss");
          this.committees.xid = res.data.xid;
          this.xCount = res.data.xCount;
        });
      },
      //添加评标委员会
      addCommitee() {
        // console.log(this.xCount, "1");
        //添加专家人数一致
        if (this.lTableData.length == this.queryParams.xCount) {
          this.lTableData.forEach(row => {
            this.committees.jid = row.jid;
            // console.log(this.committees,"comm");
            //执行添加方法
            addCommittee(this.committees).then(res=>{
                this.$modal.msgSuccess("添加成功！");
            });
            // console.log(row.jid, "jid");

          });
        }
      },
      //点击确定 （直接抽取）
      zjcq() {
        // console.log(this.lTableData, "wwww");
        this.addCommitee();
        this.xWaydialog = false;
        this.cancel();
        this.$router.go(-1);
      },
      //点击确定 （随机抽取）
      sjcq(){

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
          // console.log(this.lTableColumns, "it");
          this.lTableData.push(newRow);
          // console.log(this.lTableColumns, "it2");
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
        // console.log(this.lSelectedRows,"select");
        if (this.lTableData.length >= this.queryParams.xCount) {
          this.$alert("添加评标专家不能超过" + this.queryParams.xCount + "人!", "温馨提示");
        }else{
          var isJx= false;
          this.lSelectedRows.forEach(data=>{
            //判断集合里是否已存在该专家
            if(data.jid != null || data.jid > 0){
              isJx = true;
            }
          });
          if(!isJx){
            const copiedRows = this.lSelectedRows.map(row => ({
              ...row
            }))
            copiedRows.forEach(row => {

              row.id = this.lTableData.reduce((maxId, row) => Math.max(row.id, maxId), 0) + 1
              this.lTableData.push(row)
            })
          }else{
            this.$alert("检测到该专家已存在，不可复制！","提示");
          }
        }
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
        var isYes = false;
        this.lTableData.forEach(data=>{
          //判断集合里是否已存在该专家
          if(data.jid == row.jid){
             isYes = true;
          }
        });
        if(!isYes){
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
          this.selectRow.jid = row.jid
          this.cpDialog = false;
        }else{
           this.$alert("该专家已参与该项目评标！","提示");
        }
      },
      //显示专家对话框
      openCp(row) {
        // console.log(row, "row");
        this.selectRow = row;
        // console.log(this.selectRow, "rows");
        this.cpDialog = true;
        this.selectBdList();
        this.expertLoading =true;
        setTimeout(()=>{
          this.expertLoading =false;
        },1000);
      },
      //关闭专家对话框
      closeDialog1() {
        this.cpDialog = false;
      },
    }
  };
</script>
<style scoped>
  .box {
    margin: 10px 0 15px 0px;
    color: #409eff;
    font-size: 16px;
    font-weight: bold;
  }

  .input-common {
    border: none;
  }

  #input-common,
  #input-common1,
  #input-common2,
  #input-common3,
  #input-common4,
  #input-common5,
  #input-common6,
  #input-common7,
  #input-common8,
  #input-common9,
  #input-common10 {
    border: none;
  }
</style>
