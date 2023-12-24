<template>
  <div class="app-container">
    <div class="con">
      <h2>采购合同</h2>
      <el-divider direction="horizontal"/>
      <el-form ref="elForm" :model="form" :rules="rules1" size="medium" label-width="180px" label-position="left">
        <h3>合同基本信息</h3>
        <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
          <el-form-item label="合同名称" prop="eHname" style="width: 45%">
            <el-input v-model="form.eHname" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="合同编号" prop="eHcode" style="width: 45%">
            <el-input v-model="form.eHcode" clearable class="cInput" placeholder="系统自动生成" disabled/>
          </el-form-item>
          <el-form-item label="类型" prop="eType" style="width: 45%">
            <el-input v-model="form.eType" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="密级" prop="eCon" style="width: 45%">
            <el-select v-model="form.eCon" placeholder="请选择" class="cInput">
              <el-option
                  v-for="item in mjOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="开始时间" prop="eStartdate" style="width: 45%">
            <el-date-picker v-model="form.eStartdate" type="date" class="cInput"/>
          </el-form-item>
          <el-form-item label="结束时间" prop="eEnddate" style="width: 45%">
            <el-date-picker v-model="form.eEnddate" type="date" class="cInput"/>
          </el-form-item>
          <el-form-item label="交付日期" prop="eDeliveryTime" style="width: 45%">
            <el-date-picker v-model="form.eDeliveryTime" type="date" class="cInput"/>
          </el-form-item>
          <el-form-item label="合同金额" prop="eAmount" style="width: 45%">
            <el-input v-model="form.eAmount" clearable class="cInput" readonly/>
            <span style="color: red"> * 合同标的清单总价</span>
          </el-form-item>
          <el-form-item label="合同情况说明" prop="eDescription" style="width: 100%">
            <el-col>
              <el-input v-model="form.eDescription" type="textarea" :rows="4" clearable class="cInput"/>
            </el-col>
          </el-form-item>
        </el-row>
        <h3>相关项目信息</h3>
        <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
          <!--相关项目-->
          <el-form-item label="相关项目名称" prop="tenderName" style="width: 45%">
            <el-input v-model="form.tenderName" class="cInput" readonly/>
          </el-form-item>
          <!--项目编号-->
          <el-form-item label="项目编号" prop="tenderNo" style="width: 45%">
            <el-input v-model="form.tenderNo" class="cInput" readonly/>
          </el-form-item>
          <!--采购方式-->
          <el-form-item label="招标方式" prop="tenderWay" style="width: 45%">
            <el-input v-model="form.tenderWay" class="cInput" readonly/>
          </el-form-item>
          <!--业务类型-->
          <el-form-item label="业务类型" prop="tenderType" style="width: 45%">
            <el-input v-model="form.tenderType" class="cInput" readonly/>
          </el-form-item>
        </el-row>
      </el-form>

      <!--合同标的清单-->
      <h3>合同标的清单</h3>
      <div class="cl">
        <el-button @click="lAddRow">新增</el-button>
        <el-button @click="lDeleteRows" :disabled="lSelectedRows.length === 0">删除</el-button>
        <el-button @click="lCopyRows" :disabled="lSelectedRows.length === 0">复制</el-button>
        <el-table
            :data="lTableData"
            :row-key="row => row.id"
            @selection-change="lHandleSelectionChange"
            border
            stripe
            :style="{marginTop:'10px'}"
        >
          <el-table-column type="selection" width="55"/>
          <el-table-column label="序号" prop="id" width="60"/>
          <el-table-column label="产品名称" prop="inName" width="170">
            <template slot-scope="scope">
              <el-input v-model="scope.row.inName" readonly>
                <i slot="suffix" class="el-icon-search" @click="openCp(scope.row)" style="margin-top: 10px"/>
              </el-input>
              <el-dialog title="产品名称" :visible.sync="cpDialog">
                <el-table
                    ref="singleTable"
                    :data="deviceList"
                    highlight-current-row
                    style="width: 100%"
                    @row-click="handleRowClick"
                >
                  <el-table-column prop="tName" label="产品名称" width="140"/>
                  <el-table-column prop="tid" label="产品编号" width="130"/>
                  <el-table-column prop="tModel" label="规格型号" width="150"/>
                  <el-table-column prop="tUnit" label="单位" width="85"/>
                  <el-table-column prop="tPrice" label="单价" width="120"/>
                  <el-table-column prop="shui" label="税点" width="100"/>
                </el-table>
                <pagination
                    v-show="total>0"
                    :total="total"
                    :page.sync="queryParams.pageNum"
                    :limit.sync="queryParams.pageSize"
                    @pagination="selectBdList"
                />
                <div style="margin-top: 20px">
                  <el-button @click="closeDialog1">取消</el-button>
                </div>
              </el-dialog>
            </template>
          </el-table-column>
          <el-table-column label="产品编码" prop="tid" width="163">
            <template slot-scope="scope">
              <el-input v-model="scope.row.tid" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="规格型号" prop="inModel" width="140">
            <template slot-scope="scope">
              <el-input v-model="scope.row.inModel" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="单位" prop="inUnit" width="120">
            <template slot-scope="scope">
              <el-input v-model="scope.row.inUnit" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="含税单价" prop="inVat" width="130">
            <template slot-scope="scope">
              <el-input v-model="scope.row.inVat" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="数量" prop="inCount" width="150">
            <template slot-scope="scope">
              <el-input-number
                  v-model="scope.row.inCount"
                  :min="1"
                  :precision="0"
                  controls-position="right"
                  style="width: 120px;"
                  @blur="spCountBlur(scope.row)"
                  @change="spCountChange(scope.row)"
              />
            </template>
          </el-table-column>
          <el-table-column label="小计" prop="inSubtotal" width="140">
            <template slot-scope="scope">
              <el-input v-model="scope.row.inSubtotal" readonly/>
            </template>
          </el-table-column>
        </el-table>
        <div :style="{textAlign:'right'}">
          <span :style="{marginRight:'100px'}">总价:</span>
          <span :style="{fontWeight:'700'}">{{ lTotalSubtotal.toFixed(2) }}</span>
        </div>
      </div>

      <!--合同付款约定-->
      <h3>合同付款约定</h3>
      <div class="cl">
        <el-button @click="payAddRow">新增</el-button>
        <el-button @click="payDeleteRows" :disabled="paySelectedRows.length === 0">删除</el-button>
        <el-button @click="payCopyRows" :disabled="paySelectedRows.length === 0">复制</el-button>
        <el-table
            :data="payTableData"
            :row-key="row => row.id"
            @selection-change="payHandleSelectionChange"
            border
            stripe
            :style="{marginTop:'10px'}"
        >
          <el-table-column type="selection" width="55"/>
          <el-table-column label="序号" prop="id" width="60"/>
          <el-table-column label="款项内容" prop="payContent">
            <template slot-scope="scope">
              <el-select v-model="scope.row.payContent" class="cInput">
                <el-option
                    v-for="item in payTypes"
                    :key="item.dictValue"
                    :label="item.dictLabel"
                    :value="Number(item.dictValue)"
                />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="付款日期">
            <template slot-scope="scope">
              <div class="block">
                <el-date-picker v-model="scope.row.payDate" type="date" placeholder="选择日期"/>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="付款单位" prop="payer">
            <template slot-scope="scope">
              <el-input v-model="scope.row.payer" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="收款合同方" prop="hName">
            <template slot-scope="scope">
              <el-input v-model="scope.row.hName" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="付款条件" prop="payTerms">
            <template slot-scope="scope">
              <el-input v-model="scope.row.payTerms"/>
            </template>
          </el-table-column>
          <el-table-column label="付款金额" prop="payAmount">
            <template slot-scope="scope">
              <el-input v-model="scope.row.payAmount" @blur="payHandleBlur(scope.row)" @input="payHandleInput(scope.row)"/>
            </template>
          </el-table-column>
          <el-table-column label="违约责任" prop="debty">
            <template slot-scope="scope">
              <el-input v-model="scope.row.debty"/>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <h3>合同签署状态</h3>
      <el-form ref="qsForm" :model="qsFormData" :rules="rules2" size="medium" label-width="180px" label-position="left">
        <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
          <el-form-item label="签署方数" prop="gnSignatorycount" style="width: 45%">
            <el-select v-model="qsFormData.gnSignatorycount" class="cInput" @change="qsHandleChange">
              <el-option
                  v-for="item in qsOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="我方主体" prop="gnSub" style="width: 45%">
            <el-input v-model="qsFormData.gnSub" class="cInput"/>
          </el-form-item>
          <el-form-item label="乙方供应商" prop="gnPbname" style="width: 45%">
            <el-input v-model="qsFormData.gnPbname" class="cInput"/>
          </el-form-item>
          <el-form-item label="乙方供应商地址" prop="gnPbaddress" style="width: 45%">
            <el-input v-model="qsFormData.gnPbaddress" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="乙方联系人" prop="gnPbcontact" style="width: 45%">
            <el-input v-model="qsFormData.gnPbcontact" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="乙方联系方式" prop="gnPbcif" style="width: 45%">
            <el-input v-model="qsFormData.gnPbcif" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="乙方开户行" prop="gnPbbank" style="width: 45%">
            <el-input v-model="qsFormData.gnPbbank" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="乙方开户行账户" prop="gnPbaccount" style="width: 45%">
            <el-input v-model="qsFormData.gnPbaccount" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="合同方金额" prop="gnPbamount" style="width: 45%">
            <el-input v-model="qsFormData.gnPbamount" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="币别" prop="gnPbcurrency" style="width: 45%">
            <el-input v-model="qsFormData.gnPbcurrency" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="已支付金额" prop="gnPbpayment" style="width: 45%">
            <el-input v-model="qsFormData.gnPbpayment" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="锁定金额" prop="gnPbfixedprice" style="width: 45%">
            <el-input v-model="qsFormData.gnPbfixedprice" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="剩余金额" prop="gnPbbalance" style="width: 45%">
            <el-input v-model="qsFormData.gnPbbalance" clearable class="cInput"/>
          </el-form-item>
        </el-row>
        <div v-if="qsFormData.gnSignatorycount === 1">
          <el-divider direction="horizontal"/>
          <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
            <el-form-item label="丙方供应商" prop="gnPcname" style="width: 45%">
              <el-input v-model="qsFormData.gnPcname" clearable class="cInput"/>
            </el-form-item>
            <el-form-item label="丙方供应商地址" prop="gnPcaddress" style="width: 45%">
              <el-input v-model="qsFormData.gnPcaddress" clearable class="cInput"/>
            </el-form-item>
            <el-form-item label="丙方联系人" prop="gnPccontact" style="width: 45%">
              <el-input v-model="qsFormData.gnPccontact" clearable class="cInput"/>
            </el-form-item>
            <el-form-item label="丙方联系方式" prop="gnPccif" style="width: 45%">
              <el-input v-model="qsFormData.gnPccif" clearable class="cInput"/>
            </el-form-item>
            <el-form-item label="丙方开户行" prop="gnPcbank" style="width: 45%">
              <el-input v-model="qsFormData.gnPcbank" clearable class="cInput"/>
            </el-form-item>
            <el-form-item label="丙方开户行账户" prop="gnPcaccount" style="width: 45%">
              <el-input v-model="qsFormData.gnPcaccount" clearable class="cInput"/>
            </el-form-item>
            <el-form-item label="合同方金额" prop="gnPcamount" style="width: 45%">
              <el-input v-model="qsFormData.gnPcamount" clearable class="cInput"/>
            </el-form-item>
            <el-form-item label="币别" prop="gnPccurrency" style="width: 45%">
              <el-input v-model="qsFormData.gnPccurrency" clearable class="cInput"/>
            </el-form-item>
            <el-form-item label="已支付金额" prop="gnPcpayment" style="width: 45%">
              <el-input v-model="qsFormData.gnPcpayment" clearable class="cInput"/>
            </el-form-item>
            <el-form-item label="锁定金额" prop="gnPcfixedprice" style="width: 45%">
              <el-input v-model="qsFormData.gnPcfixedprice" clearable class="cInput"/>
            </el-form-item>
            <el-form-item label="剩余金额" prop="gnPcbalance" style="width: 45%">
              <el-input v-model="qsFormData.gnPcbalance" clearable class="cInput"/>
            </el-form-item>
          </el-row>
        </div>
      </el-form>

      <!--合同签署文件-->
      <h3>合同签署文件</h3>
      <el-form ref="elForm" :model="form" :rules="rules3" size="medium" label-width="180px" label-position="left">
        <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
          <el-form-item label="合同影像上传" prop="eImage" style="width: 45%">
            <el-upload
                ref="up1"
                class="upload-demo"
                multiple
                :action="url"
                :before-remove="beforeRemove1"
                :auto-upload="true"
                :limit="5"
                :on-exceed="handleExceed1"
                :on-success="success1"
                :file-list="fileList1"
            >
              <el-button size="small" type="primary">上传合同影像</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
            </el-upload>
          </el-form-item>
          <el-form-item label="附件上传" prop="ComPubAttachments" style="width: 45%">
            <el-upload
                ref="up2"
                class="upload-demo"
                multiple
                :action="url"
                :before-remove="beforeRemove2"
                :auto-upload="true"
                :limit="5"
                :on-exceed="handleExceed2"
                :on-success="success2"
                :file-list="fileList2"
            >
              <el-button size="small" type="primary">上传附件</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item label="合同文件" prop="eDocuments" style="width: 45%">
            <el-upload
                ref="up3"
                class="upload-demo"
                multiple
                :action="url"
                :before-remove="beforeRemove3"
                :auto-upload="true"
                :limit="1"
                :on-exceed="handleExceed3"
                :on-success="success3"
                :file-list="fileList3"
            >
              <el-button size="small" type="primary">上传合同文件</el-button>
            </el-upload>
          </el-form-item>
        </el-row>
      </el-form>
    </div>
    <div style="margin-top: 20px">
      <el-button size="medium" @click="back1">返回</el-button>
      <el-button size="medium" type="primary" @click="addXy">创建合同</el-button>
    </div>
  </div>
</template>


<script>
import { getTender } from '../../../api/system/tender/tender'
import { addContract, listDevice } from '../../../api/system/addContract'
import { getSupplier, listSupplier } from '../../../api/system/supplier'
import { getOperator } from '../../../api/system/operator'

export default {
  data() {
    return {
      fileList1: [],
      fileList2: [],
      fileList3: [],
      url: process.env.VUE_APP_BASE_API + '/basic/supplier/upload1',
      /* 招标项目ID */
      sid: this.$route.query.sid,
      /* 标的清单 */
      //合同标的表格
      lTableData: [],
      total: 0,
      total1: 0,
      //产品信息参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tName: null,
        tUnit: null,
        shui: null,
        tModel: null,
        tPrice: null,
        tid: null
      },
      //供应商信息
      queryParams1: {
        pageNum: 1,
        pageSize: 10,
        hName: null,
        hid: null,
        hQuality: null
      },
      //供应商集合
      supplierList: [],
      //产品集合
      deviceList: [],
      /* 签署执行状态 */
      //我方主体选择
      sub: '',
      cpDialog: false,
      GysDialog: false,
      //签署方数下拉框
      qsOptions: [
        { value: 0, label: '双方签署' },
        { value: 1, label: '三方签署' }
      ],
      payTimeValue: '',
      /* //合同标的清单 */
      lTotalSubtotal: 0, // 初始化总价格为0
      lTableColumns: [],
      lSelectedRows: [],
      //合同付款约定
      payTableData: [],
      paySelectedRows: [],
      payDateOptions: [],
      //框架协议标信息
      qdSelectedItem: '',
      //保密级别
      mjOptions: [{
        value: 1,
        label: '非密'
      }, {
        value: 2,
        label: '商密'
      }],
      //采购方式
      cgOptions: [{
        value: 1,
        label: '邀请招标'
      }, {
        value: 2,
        label: '公开招标'
      }],
      cgValue: '',
      // 遮罩层
      loading: true,
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {
        hid: null,
        sid: null,
        eHname: null,
        eHcode: null,
        eType: null,
        eCon: 1,
        eStartdate: null,
        eEnddate: null,
        eAmount: 0,
        eDescription: null,
        tenderName: null,
        tenderNo: null,
        tenderWay: null,
        tenderType: null,
        eImage: null,
        eDocuments: null,
        eOpinion: null,
        eDeliveryTime: null,
        bsSign: {}
      },
      //付款yued
      payForm: {
        eid: null,
        payContent: null,
        payDate: null,
        payer: null,
        hid: null,
        hName: null,
        payTerms: null,
        payAmount: 0,
        debty: null
      },
      //业务类型字典数据
      tenderTypes: [],
      payTypes: [],
      //合同签署状态
      qsFormData: {
        gnSignatorycount: 0, //签署方数
        gnSub: '鸿鹄科技有限公司',//我方主体
        gnPbid: null,//乙方供应商ID
        gnPbname: null,//乙方名称
        gnPbaddress: null,//乙方地址
        gnPbcontact: null,//乙方联系人
        gnPbcif: null,//乙方联系方式
        gnPbbank: null,//乙方开户行
        gnPbaccount: null,//乙方开户账号
        gnPbamount: null,//合同方金额
        gnPbcurrency: '人民币',//币别
        gnPbpayment: null,//已支付金额
        gnPbfixedprice: null,//锁定金额
        gnPbbalance: null,//剩余金额
        gnPcname: null,//丙方名称
        gnPcaddress: null,//乙方地址
        gnPccontact: null,//乙方联系人
        gnPccif: null,//乙方联系方式
        gnPcbank: null,//乙方开户行
        gnPcaccount: null,//乙方开户账号
        gnPcamount: null,//合同方金额
        gnPccurrency: null,//币别
        gnPcpayment: null,//已支付金额
        gnPcfixedprice: null,//锁定金额
        gnPcbalance: null//剩余金额
      },
      hid: 2,
      // 表单校验
      rules1: {
        eHname: [
          { required: true, message: '合同名称不能为空', trigger: 'blur' }
        ],
        eStartdate: [
          { required: true, message: '开始时间不能为空', trigger: 'blur' }
        ],
        eEnddate: [
          { required: true, message: '结束时间不能为空', trigger: 'blur' }
        ]
      },
      rules2: {
        // gnPbcif: [
        //   { required: true, message: '乙方联系方式不能为空', trigger: 'blur' },
        //   {
        //     pattern: /^1[3456789]\d{9}$/,
        //     message: '请输入正确的手机号码',
        //     trigger: 'blur'
        //   }
        // ]
      },
      rules3: {},
      selectRow: null,
      //附件
      ComPubAttachments: null
    }
  },
  mounted() {
    this.lCalculateTotalSubtotal()
  },
  created() {
    //查询相关项目信息
    this.selectTenderBySid()
    this.selectSupplier()
    this.getDicts('bs_contract_pay').then(res => {
      this.payTypes = res.data
    })
  },
  methods: {
    //创建合同
    addXy() {
      this.form.sid = this.sid
      alert(this.form.sid)
      this.form['bsInventoryList'] = [...this.lTableData].filter(e => {
        delete e.id
        if (e.tid == null) {
          // 如果存在空的tid，直接跳过当前元素
          return false
        }
        return true
      })
      this.form['bsPaymentList'] = [...this.payTableData].filter(e => {
        delete e.id
        if (e.payContent == null || e.payAmount == null) {
          // 如果存在空的tid，直接跳过当前元素
          return false
        }
        return true
      })

      this.form.bsSign = this.qsFormData
      this.form['ComPubAttachments'] = this.ComPubAttachments
      addContract(this.form).then(response => {
        console.log(response)
      })
    },
    //上传协议文件-------------------------------------------------
    handleExceed1(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove1(file) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    success1(response, file, fileList) {
      console.log(111)
      this.fileList1.push(file)
      console.log(fileList)
      console.log(response)
      console.log(file)
      let hhh = fileList.map(obj => {
        let newObj = obj
        delete newObj.url
        newObj.url = obj.response.data.url
        delete newObj.name
        newObj.name = obj.response.data.name
        delete newObj.response
        delete newObj.raw
        delete newObj.percentage
        delete newObj.status
        delete newObj.uid
        return newObj
      })
      this.form.eImage = JSON.stringify(hhh)
      console.log(this.form.eImage)
    },
    handleExceed2(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove2(file) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    success2(response, file, fileList) {
      console.log(222)
      this.fileList2.push(file)
      let hhh = fileList.map(obj => {
        let newObj = obj
        delete newObj.url
        newObj.url = obj.response.data.url
        delete newObj.name
        newObj.name = obj.response.data.name
        delete newObj.response
        delete newObj.raw
        delete newObj.percentage
        delete newObj.status
        delete newObj.uid
        return newObj
      })
      this.form.eDocuments = JSON.stringify(hhh)
      console.log(this.form.eDocuments)
    },
    handleExceed3(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove3(file) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    success3(response, file, fileList) {
      console.log(333)
      this.fileList3.push(file)
      let hhh = fileList.map(obj => {
        let newObj = obj
        delete newObj.url
        newObj.url = obj.response.data.url
        delete newObj.name
        newObj.name = obj.response.data.name
        delete newObj.response
        delete newObj.raw
        delete newObj.percentage
        delete newObj.status
        delete newObj.uid
        return newObj
      })
      this.ComPubAttachments = JSON.stringify(hhh)
      console.log(this.ComPubAttachments)
    },
    //上传协议文件-------------------------------------------------
    //产品数量输入框失去焦点时
    spCountBlur(row) {
      if (row.inVat == null) {
        row.inVat = 0.00
      }
      row.inSubtotal = (row.inCount * row.inVat).toFixed(2)
      this.lCalculateTotalSubtotal()
    },
    //产品数量输入框的值改变时
    spCountChange(row) {
      if (row.inVat == null) {
        row.inVat = 0.00
      }
      row.inSubtotal = (row.inCount * row.inVat).toFixed(2)
      this.lCalculateTotalSubtotal()
    },
    // 计算产品总价格方法
    lCalculateTotalSubtotal() {
      this.lTotalSubtotal = this.lTableData.reduce((total, row) => {
        let totalValue = row.inSubtotal ? parseFloat(row.inSubtotal) : 0
        let kk = total + totalValue // 将每行的小计相加得到总价格
        this.form.eAmount = kk.toFixed(2)
        this.qsFormData.gnPbamount = kk.toFixed(2)
        return kk
      }, 0)
    },
    //查询产品信息
    selectBdList() {
      listDevice(this.queryParams).then(response => {
        this.deviceList = response.rows
        this.total = response.total
      })
    },
    //查询供应商信息和业务经办人信息
    selectSupplier() {
      /* 业务经办人信息 */
      getOperator(this.hid).then(response => {
        console.log(response)
        let k = response.data
        this.qsFormData.gnPbcontact = k.ywName
        this.qsFormData.gnPbcif = k.ywPhone
      })
      /* 供应商信息 */
      getSupplier(this.hid).then(res => {
        console.log('打印了供应商的信息')
        console.log(res)
        let k = res.data
        this.qsFormData.gnPbname = k.hName
        this.qsFormData.gnPbaddress = k.hAddress
        this.qsFormData.gnPbbank = k.hBankAddress
        this.qsFormData.gnPbaccount = k.hAccount
        this.qsFormData.gnPbid = k.hid
        this.form.hid = k.hid
      })
    },
    //产品行点击事件
    handleRowClick(row) {
      // 在这里处理行点击事件
      this.selectRow.inName = row.tName
      this.selectRow.tid = row.tid
      this.selectRow.inModel = row.tModel
      this.selectRow.inUnit = row.tUnit
      this.selectRow.tid = row.tid
      this.selectRow.inVat = (row.tPrice * 1.13).toFixed(2)
      this.selectRow.inCount = 1
      this.selectRow.inSubtotal = (this.selectRow.inCount * this.selectRow.inVat).toFixed(2)
      this.cpDialog = false
      this.lCalculateTotalSubtotal()
    },
    //产品行点击事件
    handleRowClick1(row) {
      // 在这里处理行点击事件
      this.selectRow.hName = row.hName
      this.selectRow.tid = row.tid
      this.GysDialog = false
    },
    //关闭产品名称对话框
    closeDialog1() {
      this.cpDialog = false
    },
    //关闭产品名称对话框
    closeDialog2() {
      this.GysDialog = false
    },
    /* 查询相关项目信息 */
    selectTenderBySid() {
      getTender(this.sid).then(response => {
        // console.log(response)
        let k = response.data
        this.form.tenderName = k.sName
        this.form.tenderNo = k.sCode
        if (k.sWay == 1) {
          this.form.tenderWay = '公开招标'
        } else {
          this.form.tenderWay = '邀请招标'
        }
        if (k.sType == 1) {
          this.form.tenderType = '办公类型'
        } else if (k.sType == 1) {
          this.form.tenderType = '固定资产'
        } else if (k.sType == 2) {
          this.form.tenderType = '资讯类'
        } else if (k.sType == 3) {
          this.form.tenderType = '服务类'
        }
        this.form.eType = '一般采购合同'
      })
    },
    //显示产品对话框
    openCp(row) {
      this.selectRow = row
      this.cpDialog = true
      this.selectBdList()
    },
    //显示产品对话框
    openGys(row) {
      this.selectRow = row
      this.GysDialog = true
      this.selectGysList()
    },
    //查询供应商信息
    selectGysList() {
      listSupplier(this.queryParams1).then(response => {
        console.log(response)
        this.supplierList = response.rows
        this.total1 = response.total
      })
    },
    /* 签署执行状态 */
    qsHandleChange(value) {
      this.qsFormData.gnSignatorycount = value  // 更新选择项的值
      if (this.gnSignatorycount == 0) {
        this.qsFormData.gnPccurrency = null
      } else {
        this.qsFormData.gnPccurrency = '人民币'
      }
    },
    back1() {
      this.$router.back()
    },
    /* 合同标的清单 */
    lAddRow() {
      const newRow = {}
      this.lTableColumns.forEach(column => {
        newRow[column.prop] = ''
      })
      newRow.id = this.lTableData.length + 1
      newRow.inSubtotal = (0).toFixed(2)
      newRow.inCount = 1
      this.lTableData.push(newRow)
    },
    lDeleteRows() {
      this.$confirm('确定删除选中的行吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.lTableData = this.lTableData.filter(row => !this.lSelectedRows.includes(row))
        this.lSelectedRows = []
        this.lUpdateRowIds()
        this.lCalculateTotalSubtotal()
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
      const copiedRows = this.lSelectedRows.map(row => ({ ...row }))
      copiedRows.forEach(row => {
        row.id = this.lTableData.reduce((maxId, row) => Math.max(row.id, maxId), 0) + 1
        this.lTableData.push(row)
      })
      this.lCalculateTotalSubtotal()
    },
    //行数变化
    lUpdateRowIds() {
      this.lTableData.forEach((row, index) => {
        row.id = index + 1
      })
    },
    //单选多选
    lHandleSelectionChange(selection) {
      this.lSelectedRows = selection
    },
    /* 合同付款约定 */
    payAddRow() {
      const newRow = {}
      newRow.id = this.payTableData.length + 1
      newRow.payer = '鸿鹄科技有限公司'
      newRow.hName = this.qsFormData.gnPbname
      newRow.hid = this.form.hid
      this.payTableData.push(newRow)
    },
    //删除
    payDeleteRows() {
      this.$confirm('确定删除选中的行吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.payTableData = this.payTableData.filter(row => !this.paySelectedRows.includes(row))
        this.paySelectedRows = []
        this.payUpdateRowIds()
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
    payCopyRows() {
      const copiedRows = this.paySelectedRows.map(row => ({ ...row }))
      copiedRows.forEach(row => {
        row.id = this.payTableData.reduce((maxId, row) => Math.max(row.id, maxId), 0) + 1
        this.payTableData.push(row)
      })
    },
    //行数变化
    payUpdateRowIds() {
      this.payTableData.forEach((row, index) => {
        row.id = index + 1
      })
    },
    //单选多选
    payHandleSelectionChange(selection) {
      this.paySelectedRows = selection
    },
    payHandleBlur(row) {
      if (row.payAmount) {
        row.payAmount = parseFloat(row.payAmount).toFixed(2)
      }
    },
    payHandleInput(row) {
      // 只保留数字和一个小数点
      row.payAmount = row.payAmount.replace(/[^\d.]/g, '')
      // 只能输入一个小数点
      let dotIndex = row.payAmount.indexOf('.')
      if (dotIndex !== -1) {
        row.payAmount = row.payAmount.slice(0, dotIndex + 1) + row.payAmount.slice(dotIndex + 1).replace(/\./g, '')
      }
      // 只能输入到小数点后两位
      let parts = row.payAmount.split('.')
      if (parts[1] && parts[1].length > 2) {
        row.payAmount = parts[0] + '.' + parts[1].slice(0, 2)
      }
    }
  }
}
</script>

<style scoped>
.cl >>> .el-input__inner {
  border: none;
}

.cInput {
  width: 100%;
}

/*穿透隐藏el-input边框
.app-container>>>.el-input__inner {
}*/
.app-container {
  background-color: rgb(243, 243, 243);
}

.title {
  text-align: center;
}

.con {
  background-color: white;
  padding: 15px 80px 20px 80px;
  border: 1px solid #ececec;
}

h3 {
  font-weight: bold;
}

h2 {
  text-align: center;
  margin: 30px 0px;
}

</style>
