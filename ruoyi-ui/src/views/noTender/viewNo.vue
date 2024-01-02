<template>
  <div class="app-container">
    <div class="con">
      <h2>采购合同</h2>
      <el-divider direction="horizontal"/>
      <el-form ref="elForm" :model="form" :rules="rules1" size="medium" label-width="180px" label-position="left">
        <h3>合同基本信息</h3>
        <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
          <el-form-item label="合同名称" prop="eHname" style="width: 45%">
            <el-input v-model="form.eHname" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="合同编号" prop="eHcode" style="width: 45%">
            <el-input v-model="form.eHcode" clearable class="cInput" placeholder="系统自动生成" readonly/>
          </el-form-item>
          <el-form-item label="类型" prop="eType" style="width: 45%">
            <el-input v-model="form.eType" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="密级" prop="eCon" style="width: 45%">
            <el-select v-model="form.eCon" placeholder="请选择" class="cInput" disabled>
              <el-option
                v-for="item in mjOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="开始时间" prop="eStartdate" style="width: 45%">
            <el-date-picker v-model="form.eStartdate" type="date" class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="结束时间" prop="eEnddate" style="width: 45%">
            <el-date-picker v-model="form.eEnddate" type="date" class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="交付日期" prop="eDeliveryTime" style="width: 45%">
            <el-date-picker v-model="form.eDeliveryTime" type="date" class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="合同金额" prop="eAmount" style="width: 45%">
            <el-input v-model="form.eAmount" clearable class="cInput" readonly/>
            <span style="color: red"> * 合同标的清单总价</span>
          </el-form-item>
          <el-form-item label="合同情况说明" prop="eDescription" style="width: 100%">
            <el-col>
              <el-input v-model="form.eDescription" type="textarea" :rows="4" clearable class="cInput" readonly/>
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
        <el-table
          :data="lTableData"
          :row-key="row => row.id"
          border
          stripe
          :style="{marginTop:'10px'}"
        >
          <el-table-column label="序号" prop="id" width="60"/>
          <el-table-column label="产品名称" prop="inName" width="170">
            <template slot-scope="scope">
              <el-input v-model="scope.row.inName" readonly>
                <i slot="suffix" class="el-icon-search" @click="" style="margin-top: 10px"/>
              </el-input>
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
            <template slot-scope="scope" disabled="">
              <el-input-number
                v-model="scope.row.inCount"
                :min="1"
                :precision="0"
                controls-position="right"
                style="width: 120px;"
                @blur="spCountBlur(scope.row)"
                @change="spCountChange(scope.row)"
                :disabled="true"
              />
            </template>
          </el-table-column>
          <el-table-column label="小计" prop="inSubtotal">
            <template slot-scope="scope">
              <el-input v-model="scope.row.inSubtotal" readonly/>
            </template>
          </el-table-column>
        </el-table>
        <div :style="{textAlign:'right'}">
          <span :style="{marginRight:'100px'}">总价:</span>
          <span :style="{fontWeight:'700'}">{{ lTotalSubtotal }}</span>
        </div>
      </div>

      <!--合同付款约定-->
      <h3>合同付款约定</h3>
      <div class="cl">
        <el-table
          :data="payTableData"
          :row-key="row => row.id"
          border
          stripe
          :style="{marginTop:'10px'}"
        >
          <el-table-column label="序号" prop="id" width="60"/>
          <el-table-column label="款项内容" prop="payContent">
            <template slot-scope="scope">
              <el-select v-model="scope.row.payContent" class="cInput" disabled>
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
                <el-date-picker v-model="scope.row.payDate" type="date" placeholder="选择日期" readonly/>
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
              <el-input v-model="scope.row.payTerms" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="付款金额" prop="payAmount">
            <template slot-scope="scope">
              <el-input v-model="scope.row.payAmount" @blur="payHandleBlur(scope.row)" @input="payHandleInput(scope.row)" :disabled="true"/>
            </template>
          </el-table-column>
          <el-table-column label="违约责任" prop="debty">
            <template slot-scope="scope">
              <el-input v-model="scope.row.debty" readonly/>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <h3>合同签署状态</h3>
      <el-form ref="qsForm" :model="qsFormData" :rules="rules2" size="medium" label-width="180px" label-position="left">
        <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
          <el-form-item label="签署方数" prop="gnSignatorycount" style="width: 45%">
            <el-select v-model="qsFormData.gnSignatorycount" class="cInput" disabled>
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
            <el-input v-model="qsFormData.gnSub" class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="乙方供应商" prop="gnPbname" style="width: 45%">
            <el-input v-model="qsFormData.gnPbname" class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="乙方供应商地址" prop="gnPbaddress" style="width: 45%">
            <el-input v-model="qsFormData.gnPbaddress" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="乙方联系人" prop="gnPbcontact" style="width: 45%">
            <el-input v-model="qsFormData.gnPbcontact" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="乙方联系方式" prop="gnPbcif" style="width: 45%">
            <el-input v-model="qsFormData.gnPbcif" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="乙方开户行" prop="gnPbbank" style="width: 45%">
            <el-input v-model="qsFormData.gnPbbank" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="乙方开户行账户" prop="gnPbaccount" style="width: 45%">
            <el-input v-model="qsFormData.gnPbaccount" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="合同方金额" prop="gnPbamount" style="width: 45%">
            <el-input v-model="qsFormData.gnPbamount" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="币别" prop="gnPbcurrency" style="width: 45%">
            <el-input v-model="qsFormData.gnPbcurrency" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="已支付金额" prop="gnPbpayment" style="width: 45%">
            <el-input v-model="qsFormData.gnPbpayment" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="锁定金额" prop="gnPbfixedprice" style="width: 45%">
            <el-input v-model="qsFormData.gnPbfixedprice" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="剩余金额" prop="gnPbbalance" style="width: 45%">
            <el-input v-model="qsFormData.gnPbbalance" clearable class="cInput" readonly/>
          </el-form-item>
        </el-row>
        <div v-if="qsFormData.gnSignatorycount === 1">
          <el-divider direction="horizontal"/>
          <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
            <el-form-item label="丙方供应商" prop="gnPcname" style="width: 45%">
              <el-input v-model="qsFormData.gnPcname" clearable class="cInput" readonly/>
            </el-form-item>
            <el-form-item label="丙方供应商地址" prop="gnPcaddress" style="width: 45%">
              <el-input v-model="qsFormData.gnPcaddress" clearable class="cInput" readonly/>
            </el-form-item>
            <el-form-item label="丙方联系人" prop="gnPccontact" style="width: 45%">
              <el-input v-model="qsFormData.gnPccontact" clearable class="cInput" readonly/>
            </el-form-item>
            <el-form-item label="丙方联系方式" prop="gnPccif" style="width: 45%">
              <el-input v-model="qsFormData.gnPccif" clearable class="cInput" readonly/>
            </el-form-item>
            <el-form-item label="丙方开户行" prop="gnPcbank" style="width: 45%">
              <el-input v-model="qsFormData.gnPcbank" clearable class="cInput" readonly/>
            </el-form-item>
            <el-form-item label="丙方开户行账户" prop="gnPcaccount" style="width: 45%">
              <el-input v-model="qsFormData.gnPcaccount" clearable class="cInput" readonly/>
            </el-form-item>
            <el-form-item label="合同方金额" prop="gnPcamount" style="width: 45%">
              <el-input v-model="qsFormData.gnPcamount" clearable class="cInput" readonly/>
            </el-form-item>
            <el-form-item label="币别" prop="gnPccurrency" style="width: 45%">
              <el-input v-model="qsFormData.gnPccurrency" clearable class="cInput" readonly/>
            </el-form-item>
            <el-form-item label="已支付金额" prop="gnPcpayment" style="width: 45%">
              <el-input v-model="qsFormData.gnPcpayment" clearable class="cInput" readonly/>
            </el-form-item>
            <el-form-item label="锁定金额" prop="gnPcfixedprice" style="width: 45%">
              <el-input v-model="qsFormData.gnPcfixedprice" clearable class="cInput" readonly/>
            </el-form-item>
            <el-form-item label="剩余金额" prop="gnPcbalance" style="width: 45%">
              <el-input v-model="qsFormData.gnPcbalance" clearable class="cInput" readonly/>
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
              :action="url"
              :auto-upload="false"
              :limit="1"
              :file-list="fileList1"
              disabled
            >
              <el-button size="small" type="primary">上传合同影像</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item label="附件上传" prop="ComPubAttachments" style="width: 45%">
            <el-upload
              ref="up2"
              class="upload-demo"
              :action="url"
              :auto-upload="false"
              :limit="1"
              :file-list="fileList2"
              disabled
            >
              <el-button size="small" type="primary">上传附件</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item label="合同文件" prop="eDocuments" style="width: 45%">
            <el-upload
              ref="up3"
              class="upload-demo"
              :action="url"
              :auto-upload="false"
              :limit="1"
              :file-list="fileList3"
              disabled
            >
              <el-button size="small" type="primary">上传合同文件</el-button>
            </el-upload>
          </el-form-item>
        </el-row>
      </el-form>
    </div>
    <div style="margin-top: 20px">
      <el-button size="medium" @click="back1">返回</el-button>
    </div>
  </div>
</template>


<script>
import { getAttachments, getContract, listInventory, listPayment, SelectSign, updateContract } from '../../api/system/addContract'
import { getPro } from '../../api/system/noTender'

export default {
  data() {
    return {
      fileList1: [],
      fileList2: [],
      fileList3: [],
      url: process.env.VUE_APP_BASE_API + '/basic/supplier/upload1',
      /* 合同ID */
      eid: this.$route.query.eid,
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
        eid: null,
        eHname: null,
        eHcode: null,
        eType: null,
        eCon: null,
        eStatus: null,
        eStartdate: null,
        eEnddate: null,
        eAmount: null,
        eDescription: null,
        tenderName: null,
        tenderNo: null,
        tenderWay: null,
        tenderType: null,
        eImage: null,
        eDocuments: null,
        eOpinion: null,
        eDeliveryTime: null,
        bsSign: {},
        comPubAttachments: {}
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
      ComPubAttachments: {
        anSize: null,
        anUrl: null,
        anName: null
      }
    }
  },
  mounted() {
  },
  created() {
    //查询相关项目信息
    this.selectContractByEid()
    // this.selectSupplier()
    this.selectListInventory()
    this.selectListPayment()
    this.getSign()
    this.getComPubAttachments()
    this.getDicts('bs_contract_pay').then(res => {
      this.payTypes = res.data
    })
  },
  methods: {
    updateHt() {
      //通过
      this.form.eStatus = 1
      console.log(this.form)
      updateContract(this.form).then(response => {
        console.log(response.msg)
        this.$message.success('已通过')
        this.$router.push('/contract/cm')
      })
    },
    updateBh() {
      //驳回
      this.form.eStatus = 4
      updateContract(this.form).then(response => {
        console.log(response.msg)
        this.$message.error('已驳回')
        this.$router.push('/contract/cm')
      })
    },
    //上传协议文件-------------------------------------------------
    //产品数量输入框失去焦点时
    //查询合同信息
    selectContractByEid() {
      getContract(this.eid).then(response => {
        // console.log('打印了合同的信息')
        let k = response.data
        this.form.eid = k.eid
        this.form.eHname = k.eHname
        this.form.eHcode = k.eHcode
        this.form.eDescription = k.eDescription
        this.form.eStartdate = k.eStartdate
        this.form.eEnddate = k.eEnddate
        this.form.eDeliveryTime = k.eDeliveryTime
        this.form.eAmount = k.eAmount.toFixed(2)
        this.form.eType = k.eType
        this.form.eCon = k.eCon
        this.form.gid = k.gid
        this.form.eOpinion = k.eOpinion
        console.log(k.eOpinion)
        if (k.eImage != null && k.eImage != '') {
          //获取第一个文件的名称
          let imgName1 = (k.eImage).substring((k.eImage).lastIndexOf('/') + 1)
          let fileListData1 = [{
            name: imgName1,
            url: k.eImage
          }]
          this.fileList1 = fileListData1
          this.form.eImage = k.eImage
        } else {
          this.fileList1 = []
          this.fileList1.eImage = null
        }
        if (k.eDocuments != null && k.eDocuments != '') {
          //获取第三个文件的名称
          let imgName2 = (k.eDocuments).substring((k.eDocuments).lastIndexOf('/') + 1)
          let fileListData2 = [{
            name: imgName2,
            url: k.eDocuments
          }]
          this.fileList3 = fileListData2
          this.form.eDocuments = k.eDocuments
        } else {
          this.fileList3 = []
          this.form.eDocuments = null
        }
        //查询相关项目信息
        this.selectTenderBySid()
      })
    },
    //查询相关项目信息
    selectTenderBySid() {
      getPro(this.form.gid).then(res => {
        console.log(res, 'res')
        let k = res.data
        //项目名称
        this.form.tenderName = k.gName
        //项目编号
        this.form.tenderNo = k.gCode
        //采购方式
        if (k.gTendertype === 3) {
          this.form.tenderWay = '询价'
        } else if (k.gTendertype === 5) {
          this.form.tenderWay = '竞争性谈判'
        } else {
          this.form.tenderWay = '单一来源'
        }
        //公开/邀请
        if (k.gIsPublic === 1) {
          this.form.tenderType = '公开'
        } else {
          this.form.tenderType = '邀请'
        }
        this.form.eType = '一般采购合同'
        this.aid = k.xyId
        this.hid = k.hid
        // this.selectBdList()
      })
    },
    //查询合同内的产品信息
    selectListInventory() {
      this.loading = true
      listInventory({ 'eid': this.eid }).then(response => {
        let list = response.rows
        list.forEach((e, i) => {
          this.lTableData.push({
            id: i + 1,
            inName: e.inName,
            inModel: e.inModel,
            inVat: (e.inVat).toFixed(2),
            inUnit: e.inUnit,
            inSubtotal: (e.inCount * e.inVat).toFixed(2),
            tid: e.tid,
            inCount: e.inCount
            // inId: e.inId
          })
        })
        this.loading = false
      })
    },
    //查询支付信息
    selectListPayment() {
      listPayment({ 'eid': this.eid }).then(response => {
        // console.log(response.rows)
        let list = response.rows
        list.forEach((e, i) => {
          this.payTableData.push({
            id: i + 1,
            hid: e.hid,
            hName: e.hName,
            payContent: e.payContent,
            payAmount: (e.payAmount).toFixed(2),
            payDate: e.payDate,
            payTerms: e.payTerms,
            payer: e.payer,
            // payId: e.payId,
            debty: e.debty
          })
        })
      })
    },
    //查询签署执行状态信息
    getSign() {
      SelectSign({ 'eid': this.eid }).then(response => {
        this.qsFormData = response.data
        this.lTotalSubtotal = response.data.gnPbamount
      })
    },
    //查询附件信息
    getComPubAttachments() {
      getAttachments(this.eid).then(response => {
        console.log('查询附件信息')
        console.log(response)
        if (response.data != null) {
          let fileListData = [{
            name: response.data.anName,
            url: response.data.anUrl,
            size: response.data.anSize
          }]
          this.fileList2 = fileListData
          this.ComPubAttachments.anUrl = response.data.anUrl
          this.ComPubAttachments.anName = response.data.anName
          this.ComPubAttachments.anSize = response.data.anSize
        } else {
          this.fileList2 = []
          this.ComPubAttachments.anUrl = null
          this.ComPubAttachments.anName = null
          this.ComPubAttachments.anSize = null
        }
      })
    },
    back1() {
      this.$router.back()
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
