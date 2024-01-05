<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="2">
<!--        <router-link :to="'/tender/drawExpert?sid='+sid">-->
<!--          <el-button type="primary" plain icon="el-icon-plus" size="mini">抽取专家</el-button>-->
<!--        </router-link>-->
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="openDrawExerpt">抽取专家</el-button>
      </el-col>
      <right-toolbar @queryTable="getExerpt(queryParams.sid)"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="committeeList">
      <el-table-column label="序号" align="center" type="index" />
      <el-table-column label="姓名" align="center" prop="pbName">
        <template slot-scope="scope">
          <span>{{scope.row.bsExpert.jName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" prop="pbSex">
        <template slot-scope="scope">
          <span>{{scope.row.bsExpert.jSex == 1?'女':'男'}}</span>
        </template>
      </el-table-column>
      <el-table-column label="身份证号" align="center" prop="pbIdCard">
        <template slot-scope="scope">
          <span>{{scope.row.bsExpert.jIdentity}}</span>
        </template>
      </el-table-column>
      <el-table-column label="联系方式" align="center" prop="pbPhone">
        <template slot-scope="scope">
          <span>{{scope.row.bsExpert.jPhone}}</span>
        </template>
      </el-table-column>
      <el-table-column label="邮箱" align="center" prop="pbGenre">
        <template slot-scope="scope">
          <span>{{scope.row.bsExpert.email}}</span>
        </template>
      </el-table-column>
      <el-table-column label="工作单位" align="center" prop="pbTypes">
        <template slot-scope="scope">
          <span>{{scope.row.bsExpert.jUnit}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" :disabled="status">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams1.pageNum" :limit.sync="queryParams1.pageSize"
      @pagination="getExerpt" />

    <!-- 抽取专家对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="80%" id="cqzj"  append-to-body>
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
                <el-date-picker
                  v-model="queryParams.xStartTime"
                  type="datetime"
                  id="input-common1"
                  placeholder="请选择评标开始时间"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  default-time="09:00:00">
                </el-date-picker>
<!--                <el-date-picker v-model="queryParams.xStartTime"  type="date" placeholder="请选择评标开始时间">-->
<!--                </el-date-picker>-->
              </template>
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                评标结束时间
              </template>
              <template>
                <el-date-picker
                  v-model="queryParams.xEndTime"
                  type="datetime"
                  id="input-common1"
                  placeholder="请选择评标结束时间"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  default-time="09:00:00">
                </el-date-picker>
<!--                <el-date-picker v-model="queryParams.xEndTime" id="input-common2" type="date" placeholder="请选择评标结束时间">-->
<!--                </el-date-picker>-->
              </template>
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                专家人数
              </template>
              <template>
                 <el-input v-model="queryParams.xCount" @input="validateInput"  :disabled='xWaydialog' id="input-common3" />
              </template>
            </el-descriptions-item>
            <el-descriptions-item>
              <template slot="label">
                采购方代表人数
              </template>
              <template>
                 <el-input  v-model="queryParams.xDaiCount" @input="validateInput2" id="input-common4" />
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
                <el-button type="primary" @click="subApplication" v-show="isType=='add'" :disabled="status">确定</el-button>
                <el-button type="primary" @click="updateApplication" v-show="isType=='update'" :disabled="status">修改</el-button>
                <el-button @click="cancel2">取消</el-button>
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
                  <el-dialog title="专家列表" :visible.sync="cpDialog" append-to-body>
                    <el-table ref="singleTable"  v-loading="expertLoading" :data="expertList" highlight-current-row style="width: 100%"
                              @row-click="handleRowClick">
                      <el-table-column prop="jid" label="专家ID" width="80" align="center"/>
                      <el-table-column prop="jName" label="专家名称" width="120" align="center"/>
                      <el-table-column prop="jSex" label="性别" width="100" align="center">
                        <template slot-scope="scope">
                          <span>{{scope.row.jSex == 1?'女':'男'}}</span>
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
            <el-button @click="cancelDrawExerpt" >取消</el-button>
          </div>
        </div>
        <div class="app-container" style="padding: 10px" v-show="queryParams.xWay == 1">
          <div class="box">随机抽取</div>
          <el-button style="margin-bottom: 10px;" @click="getRandomExpert" :disabled="addDialog">点击抽取</el-button>
          <el-button style="margin-bottom: 10px;" @click="delRandomExpert" v-show="randomExpertList.length > 0">清除专家</el-button>
          <div class="cl">
            <el-table :data="randomExpertList" border stripe>
              <el-table-column label="序号" prop="id" type="index" width="100" />
              <el-table-column label="专家姓名" prop="jName" width="200" />
              <el-table-column label="性别" prop="jSex" width="200">
                <template slot-scope="scope">
                  <span v-if="scope.row.jSex==1">男</span>
                  <span v-else>女</span>
                </template>
              </el-table-column>
              <el-table-column label="身份证号" prop="jIdentity" width="200" />
              <el-table-column label="手机号" prop="jPhone" width="200" />
              <el-table-column label="邮箱" prop="email" width="200" />
              <el-table-column label="工作单位" prop="jUnit" />
              <el-table-column label="专家ID" prop="jid"/>
            </el-table>
          </div>
          <div style="text-align: right;margin-top: 10px;">
            <el-button type="primary" @click="sjcq" v-show="randomExpertList.length > 0" :disabled="status">确定</el-button>
            <el-button @click="cancelDrawExerpt" >取消</el-button>
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {listCommittee, getCommittee, delCommittee, addCommittee, updateCommittee,findCommitAndExpert,delComBySid} from "@/api/system/tender/committee";
  // import DrawExpert from "@/views/tender/tender1/drawExpert.vue";
  import {findTenderNotice, getTender} from "@/api/system/tender/tender";
  import {listExpert} from "@/api/system/expert";
  import {addApplications, getmaxApp,listApplications,updateApplications} from "@/api/system/tender/bidApplication";

  export default {
    // components:{
    //   'draw-expert':DrawExpert,
    // },
    dicts: ["ppm_procurement_plan"],
    data() {
      return {
        status:false,
        // 遮罩层
        loading: true,
        // 评标委员会表格数据
        committeeList: [],
        // 弹出层标题
        title: "抽取专家",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams1: {
          pageNum: 1,
          pageSize: 10,
          xid: null,
          pbName: null,
          pbSex: null,
          pbIdcard: null,
          pbPhone: null,
          pbGenre: null,
          pbTypes: null,
          jid: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {},
        //合同标的表格
        lTableData: [],
        lSelectedRows: [],
        lTableColumns: [],
        cpDialog: false,
        selectRow: null,
        //专家列表
        expertList: [],
        //模拟专家列表(用于随机抽取)
        objList:[],
        //显示随机抽取专家
        randomExpertList:[],
        xWaydialog: false,
        expertLoading:false,
        addDialog:true,
        // 总条数
        total: 0,
        // 获取项目和公告数据
        tenderList: [],
        sName: null,
        // 查询参数(抽取申请)
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          pbId: null,
          xCode: null,
          xName: null,
          xWay: null,
          xType: null,
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
          sid:null
        },
        xCount: null,
        //添加或修改抽取专家
        isType:null,
      };
    },
    created() {
      this.queryParams.sid = this.$route.query.sid;
      this.committees.sid = this.$route.query.sid;
      this.getExerpt(this.queryParams.sid);
      this.getList(this.queryParams.sid);
      this.selectBdList();
      getTender(this.queryParams.sid).then(res=>{
        if(res.data.sProjectState === 7){
          this.status=true;
        }
      });

    },
    methods: {
      /** 查询评标委员会列表 */
      getExerpt(sid) {
        this.loading = true;
        findCommitAndExpert(sid).then(response => {
          // console.log(response,"findCommitAndExpert");
          this.committeeList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const pbIds = row.pbId || this.ids;
        this.$modal.confirm('是否确认删除评标委员会中姓名为"' + row.bsExpert.jName + '"的专家？').then(function() {
          return delCommittee(pbIds);
        }).then(() => {
          this.getExerpt(this.queryParams.sid);
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      //打开抽取专家对话框
      openDrawExerpt(){
        console.log(this.queryParams,"this.queryParams");
        listApplications(this.queryParams).then(res=>{
          // console.log(res,"resssssss");
          if(res.rows.length > 0){
            this.isType = "update";
            // alert(this.isType);
            this.queryParams.xStartTime = res.rows[0].xStartTime;
            this.queryParams.xEndTime = res.rows[0].xEndTime;
            this.queryParams.xDaiCount = res.rows[0].xDaiCount;
            this.queryParams.xCount = res.rows[0].xCount;
            this.queryParams.xArea = res.rows[0].xArea;
            this.queryParams.xWay = res.rows[0].xWay.toString();
            this.queryParams.xid = res.rows[0].xid;
          }else{
            this.isType = "add";
            // alert(this.isType);
            this.queryParams.xWay = "1";
          }
          this.queryParams.xType = this.tenderList[0].sSway;
          this.open = true;
        });
      },
      //关闭抽取专家对话框
      cancelDrawExerpt(){
        this.open = false;//是否打开抽取专家对话框
        this.xWaydialog = false;//是否禁用专家抽取方式及专家人数
        this.addDialog=true;//是否禁用新增及随机抽取按钮
        this.isType ="";//判定添加或修改抽取申请
        this.cancel();
        this.getExerpt(this.queryParams.sid);//评标委员会列表
      },
      /** 查询招标项目及公告 */
      getList(sid) {
        this.loading = true;
        findTenderNotice(sid).then(response => {
          this.tenderList.push(response.data);
          this.sName = response.data.sName;
          this.loading = false;
        });
      },
      // 取消按钮（关闭抽取专家对话框）
      cancel() {
        this.queryParams.xStartTime = null;
        this.queryParams.xEndTime = null;
        this.queryParams.xDaiCount = null;
        this.queryParams.xCount = null;
        this.queryParams.xArea = null;
        this.queryParams.xWay =null;
        this.lTableData=[];
        this.lSelectedRows=[];
        this.lTableColumns=[];
        this.getExerpt(this.queryParams.sid);
      },
      // 取消申请按钮
      cancel2() {
        this.queryParams.xStartTime = null;
        this.queryParams.xEndTime = null;
        this.queryParams.xDaiCount = null;
        this.queryParams.xCount = null;
        this.queryParams.xArea = null;
        this.lTableData=[];
        this.lSelectedRows=[];
        this.lTableColumns=[];
        this.getExerpt(this.queryParams.sid);
      },
      //查询所有准入专家
      selectBdList() {
        this.queryParams2.jShState = 1;
        listExpert(this.queryParams2).then(res => {
          this.expertList = res.rows;
          this.objList =this.expertList.slice();
        });
      },
      //添加一条抽取申请
      subApplication() {
        // console.log(this.lTableData, "sub");
        addApplications(this.queryParams).then(res => {
          this.maxId();
          this.xWaydialog = true;
          this.addDialog=false;
          this.$message({
            type: 'success',
            message: '添加申请成功!'
          });
        });
      },
      //修改抽取申请
      updateApplication(){
        updateApplications(this.queryParams).then(res=>{
          this.maxId();
          this.xWaydialog = true;
          this.addDialog=false;
          this.$message({
            type: 'success',
            message: '修改申请成功!'
          });
        });
      },
      //查询当前项目最大Id
      maxId() {
        getmaxApp(this.queryParams.sid).then(res => {
          console.log(res, "resssssss");
          this.committees.xid = res.data.xid;
          this.committees.sid = res.data.sid;
          this.xCount = res.data.xCount;
        });
      },
      //添加评标委员会(直接抽取)
      addCommitee() {
        //添加专家人数一致
        if (this.lTableData.length == this.queryParams.xCount) {
          this.lTableData.forEach(row => {
            this.committees.jid = row.jid;
            //执行添加方法
            addCommittee(this.committees).then(res=>{
              this.$modal.msgSuccess("添加成功！");
            });
          });
          //重置
          this.cancelDrawExerpt();
        }
      },
      //删除评标委员会(直接抽取)
      delCommittee(){
        this.committees.xid =null;
        this.committees.jid=null;
        listCommittee(this.committees).then(res=>{
          //评标委员会已存在专家
            if(res.rows.length > 0){
              //删除评标委员会原有专家
              delComBySid(this.queryParams.sid).then(res=>{
                if(res.code == 200){
                  //添加专家人数一致
                 this.addCommitee();
                }
              });
            }
        });
      },
      //点击确定 （直接抽取）
      zjcq() {
        if(this.isType =='add'){
          //增加
          this.addCommitee();
        }else if(this.isType =='update'){
          //先删除
          this.delCommittee();
        }
      },
      //添加评标委员会(随机抽取)
      addCommittee2(){
        //添加专家人数一致
        if (this.randomExpertList.length == this.queryParams.xCount) {
          this.randomExpertList.forEach(row => {
            this.committees.jid = row.jid;
            //执行添加方法
            addCommittee(this.committees).then(res=>{
              this.$modal.msgSuccess("添加成功！");
            });
          });
          //重置
          this.cancelDrawExerpt();
        }

      },
      //删除评标委员会(随机抽取)
      delCommittee2(){
        this.committees.xid =null;
        this.committees.jid=null;
        listCommittee(this.committees).then(res=>{
          //评标委员会已存在专家
          if(res.rows.length > 0){
            //删除评标委员会原有专家
            delComBySid(this.queryParams.sid).then(res=>{
              if(res.code == 200){
                //添加专家人数一致
                this.addCommittee2();
              }
            });
          }
        });
      },
      //点击确定 （随机抽取）
      sjcq(){
        if(this.isType =='add'){
          //增加
          this.addCommittee2();
        }else if(this.isType =='update'){
          //先删除
          this.delCommittee2();
        }
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
            this.selectRow.jSex = "女"
          } else {
            this.selectRow.jSex = "男"
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
      /**随机抽取*/
      //点击随机抽取
      getRandomExpert() {
        let result = [];
        const numObjects = Math.min(this.xCount, this.objList.length); // 最多选取3个对象

        while (result.length < numObjects) {
          const randomIndex = Math.floor(Math.random() * this.objList.length);
          if (!result.includes(this.objList[randomIndex])) { // 检查是否已经选取过该对象
            result.push(this.objList[randomIndex]);
            this.objList.splice(randomIndex, 1); // 从原数组中删除该对象
          }
        }
        this.randomExpertList = result;
      },
      //点击清除随机抽取专家
      delRandomExpert(){
        this.randomExpertList = [];//清空随机抽取专家列表
        this.objList =[];//清空剩余未抽取专家列表
        this.objList =this.expertList.slice();//将专家列表复制给模拟专家列表
      },
      //显示专家对话框
      openCp(row) {
        // console.log(row, "row");
        this.selectRow = row;
        // console.log(this.selectRow, "rows");
        this.cpDialog = true;
        this.expertLoading =true;
        setTimeout(()=>{
          this.expertLoading =false;
        },1000);
      },
      //关闭专家对话框
      closeDialog1() {
        this.cpDialog = false;
      },
      //专家人数输入验证
      validateInput(value) {
        // 判断输入是否为数字
        if (isNaN(value)) {
          this.queryParams.xCount = ''; // 清空输入框
          this.$message.error('专家人数只能输入数字！'); // 显示错误提示
        } else if (value <= 0) {
          this.queryParams.xCount = ''; // 清空输入框
          this.$message.error('专家人数必须大于0！'); // 显示错误提示
        }else if (value > this.expertList.length) {
          this.queryParams.xCount = ''; // 清空输入框
          this.$message.error('专家人数不能大于准入专家人数！'); // 显示错误提示
        }
        else {
          this.queryParams.xCount = value; // 更新输入框的值
        }
      },
      //代表人数输入验证
      validateInput2(value) {
        // 判断输入是否为数字
        if (isNaN(value)) {
          this.queryParams.xDaiCount = ''; // 清空输入框
          this.$message.error('采购方代表人数只能输入数字！'); // 显示错误提示
        } else if (value <= 0) {
          this.queryParams.xDaiCount = ''; // 清空输入框
          this.$message.error('采购方代表人数必须大于0！'); // 显示错误提示
        } else {
          this.queryParams.xDaiCount = value; // 更新输入框的值
        }
      }
    }
  };
</script>
<style scoped>
.el-dialog:not(.is-fullscreen) {
    margin-top: 4vh !important;
}
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
