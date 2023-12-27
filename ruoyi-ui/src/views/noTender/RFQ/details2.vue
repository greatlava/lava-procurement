<template>
  <div class="app-container">
    <div class="con">
      <h2>基本信息</h2>
      <el-divider direction="horizontal"/>
      <el-form ref="elForm" :model="form" :rules="rules1" size="medium" label-width="180px" label-position="left">
        <h3>非招标基本信息</h3>
        <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
          <el-form-item label="项目名称" prop="gName" style="width: 45%">
            <el-input v-model="form.gName" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="项目编号" prop="gCode" style="width: 45%">
            <el-input v-model="form.gCode" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="采购方式" prop="gTendertype" style="width: 45%">
            <el-input v-model="form.gTendertype" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="公司" prop="eType" style="width: 45%">
            <el-input v-model="form.gCompany" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="创建人" prop="createBy" style="width: 45%">
            <el-input v-model="form.createBy" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="创建部门" prop="createDept" style="width: 45%">
            <el-input v-model="form.createDept" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="公开/邀请" prop="gIsPublic" style="width: 45%">
            <el-input v-model="form.gIsPublic" clearable class="cInput" readonly/>
          </el-form-item>
          <el-form-item label="报价开始时间" prop="gSpawnTime" style="width: 45%">
            <el-input v-model="form.gSpawnTime" type="text" class="cInput"/>
          </el-form-item>
          <el-form-item label="报价截止时间" prop="gDeadline" style="width: 45%">
            <el-input v-model="form.gDeadline" type="text" class="cInput"/>
          </el-form-item>
        </el-row>
      </el-form>

      <!--合同标的清单-->
      <h3>合同标的清单</h3>
      <div class="cl">
        <el-table
            :data="lTableData"
            :row-key="row => row.id"
            @selection-change="lHandleSelectionChange"
            border
            stripe
            :style="{marginTop:'10px'}"
        >
          <el-table-column label="序号" prop="id" width="60"/>
          <el-table-column label="产品名称" prop="inName" width="170">
            <template slot-scope="scope">
              <el-input v-model="scope.row.inName" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="产品编码" prop="tid" width="163">
            <template slot-scope="scope">
              <el-input v-model="scope.row.tid" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="规格型号" prop="inModel">
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
              <el-input v-model="scope.row.inCount" readonly/>
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

      <!--供应商报价信息-->
      <h3>供应商报价信息</h3>
      <div class="cl">
        <el-table
            :data="ComQuotation"
            :row-key="row => row.id"
            @selection-change="lHandleSelectionChange"
            border
            stripe
            :style="{marginTop:'10px'}"
        >
          <el-table-column label="序号" type="index" width="80"/>
          <el-table-column label="供应商名称" prop="hName" width="300">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bsSupplier.hName" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="报价次数" prop="bjSecond" width="200">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bjSecond" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="报价金额" prop="bjTotal">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bjTotal" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="报价时间" prop="createTime">
            <template slot-scope="scope">
              <el-input v-model="scope.row.createTime" readonly/>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <!--合同签署文件-->
      <h3>合同签署文件</h3>
      <el-form ref="elForm" :model="form" size="medium" label-width="180px" label-position="left">
        <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
          <el-form-item label="附件上传" prop="ComPubAttachments" style="width: 45%">
            <el-upload
                ref="up2"
                class="upload-demo"
                :action="url"
                :before-remove="beforeRemove2"
                :auto-upload="false"
                :limit="3"
                :on-exceed="handleExceed2"
                :on-success="success2"
                :on-change="onchange2"
            >
              <el-button size="small" type="primary">上传附件</el-button>
            </el-upload>
          </el-form-item>
        </el-row>
      </el-form>
    </div>

    <div style="margin-top: 20px">
      <el-button size="medium" @click="back1">返回</el-button>
      <!--      <el-button size="medium" type="primary" @click="addXy">发布</el-button>-->
    </div>
  </div>
</template>

<script>
import { getPlan, getPro, getItemsDevice, getQuotation } from '../../../api/system/noTender'
import { listDevice } from '../../../api/system/addContract'

export default {
  data() {
    return {
      //业务类型字典数据
      gTenderTypes: [],
      url: process.env.VUE_APP_BASE_API + '/basic/supplier/upload1',
      //招标项目ID
      gid: this.$route.query.gid,
      //寻源方式
      XyOptions: [{
        value: 1,
        label: '邀请'
      }, {
        value: 2,
        label: '公开'
      }],
      //合同标的表格
      lTableData: [],
      total: 0,
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
      //产品集合
      deviceList: [],
      /* 签署执行状态 */
      //我方主体选择
      sub: '',
      cpDialog: false,
      /* //合同标的清单 */
      lTotalSubtotal: 0, // 初始化总价格为0
      lTableColumns: [],
      lSelectedRows: [],
      cgValue: '',
      // 遮罩层
      loading: true,
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {
        gName: null,
        gCode: null,
        gIsPublic: null,
        gSpawnTime: null,
        xyId: null,
        gid: null,
        gCompany: null,
        gTendertype: null,
        gTimeon: null,
        gDeadline: null,
        createBy: null,
        createDept: null,
        gNotes: null,
        comPubAttachments: {}
      },
      // 表单校验
      rules1: {},
      selectRow: null,
      fileList2: [],
      //附件
      ComPubAttachments: {
        anSize: null,
        anUrl: null,
        anName: null
      },
      aid: null,
      gfId: null,
      //报价集合
      ComQuotation: []
    }
  },
  mounted() {
    this.lCalculateTotalSubtotal()
    this.getList()
  },
  created() {
    // this.selectSupplier()
    this.getDicts('bs_contract_pay').then(res => {
      this.payTypes = res.data
    })
  },
  methods: {
    onchange2(files, fileList) {
      this.fileList2 = fileList
      console.log(this.fileList2, 'fileList2 onchange')
      if (files.size == 0) {
        this.$message.error('选择的文件不能为空，请重新选择！')
        this.fileList2.splice(this.fileList2.indexOf(files[0]), 1)
      }
    },
    //创建合同
    addXy() {
      //判断是否上传文件
      this.submitNextUpload()
    },
    add() {
      this.form.sid = this.sid
      // alert(this.form.sid)
      this.form['bsInventoryList'] = [...this.lTableData].filter(e => {
        delete e.id
        if (e.tid == null) {
          // 如果存在空的tid，直接跳过当前元素
          return false
        }
        return true
      })
      this.form.comPubAttachments = this.ComPubAttachments
      console.log('打印this.form.ComPubAttachments')
      console.log(this.form.ComPubAttachments)
      //添加合同
      addContract(this.form).then(response => {
        console.log(response)
        if (response.msg == '添加成功') {
          this.$router.push('/contract/cm')
          this.$message.success('添加成功')
        }
      })
    },
    //上传协议文件-------------------------------------------------
    handleExceed2(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove2(file) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    success2(response, fileList) {
      console.log(222)
      this.ComPubAttachments.anName = response.data.name
      this.ComPubAttachments.anUrl = response.data.url
      this.ComPubAttachments.anSize = fileList.size
      console.log('打印up2-------------------------')
      console.log(this.ComPubAttachments)
      //调用下一个上传的方法
      this.submitNextUpload()
    },
    submitNextUpload() {
      // 根据条件判断调用下一个上传
      if (this.fileList2.length > 0 && this.ComPubAttachments.anUrl == null && this.ComPubAttachments.anSize == null && this.ComPubAttachments.anName == null) {
        this.$refs.up2.submit()
      } else {
        console.log('打印提交-------------------------')
        this.add()
      }
    },
    //上传协议文件-------------------------------------------------
    //产品数量输入框失去焦点时
    //查询采购计划信息
    getList() {
      getPro(this.gid).then(response => {
        // console.log(response)
        this.aid = response.data.xyId
        this.form = response.data
        if (response.data.gTendertype == 1) {
          this.form.gTendertype = '询价'
        } else if (response.data.gTendertype == 2) {
          this.form.gTendertype = '竞争性谈判'
        } else if (response.data.gTendertype == 3) {
          this.form.gTendertype = '委托'
        } else if (response.data.gTendertype == 4) {
          this.form.gTendertype = '单一来源'
        }
        if (response.data.gIsPublic == 1) {
          this.form.gIsPublic = '邀请'
        } else {
          this.form.gIsPublic = '公开'
        }
        this.form.gCompany = '鸿鹄科技有限公司'
        this.gfId = this.form.gCode
        return Promise.all([getPlan(this.aid), getQuotation({ 'gfId': this.gfId })])
      }).then(res => {
        this.form.createBy = res[0].data.createBy
        this.form.createDept = res[0].data.aCreateDept
        console.log('打印了---------------------------')
        console.log(res[0].data)
        this.ComQuotation = res[1].rows
        console.log(this.ComQuotation)
        console.log('打印了---------------------------')
        //查询产品信息
        return getItemsDevice({ 'aid': this.aid })
      }).then(res => {
        res.rows.forEach((e, i) => {
          this.lTableData.push({
            id: i + 1,
            inName: e.ppmDevice.tName,
            inModel: e.ppmDevice.tModel,
            inVat: (e.ppmDevice.tPrice * 1.13).toFixed(2),
            inUnit: e.ppmDevice.tUnit,
            inSubtotal: (e.vCount * e.ppmDevice.tPrice * 1.13).toFixed(2),
            tid: e.tid,
            inCount: e.vCount
          })
          this.lTotalSubtotal += e.vCount * e.ppmDevice.tPrice * 1.13.toFixed(2)
        })
      })
    },
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
        // console.log(response)
        let k = response.data
        this.qsFormData.gnPbcontact = k.ywName
        this.qsFormData.gnPbcif = k.ywPhone
      })
      /* 供应商信息 */
      getSupplier(this.hid).then(res => {
        // console.log('打印了供应商的信息')
        // console.log(res)
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
    //显示产品对话框
    openCp(row) {
      this.selectRow = row
      this.cpDialog = true
      this.selectBdList()
    },
    //返回
    back1() {
      this.$router.push('/noTender/project')
    },
    //单选多选
    lHandleSelectionChange(selection) {
      this.lSelectedRows = selection
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
