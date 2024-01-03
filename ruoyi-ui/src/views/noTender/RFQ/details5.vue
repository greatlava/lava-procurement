<template>
  <div class="app-container">
    <div class="con">
      <h2>基本信息</h2>
      <el-divider direction="horizontal"/>
      <el-form ref="elForm" :model="form" :rules="rules1" size="medium" label-width="180px" label-position="left">
        <h3>非招标基本信息</h3>
        <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
          <el-form-item label="项目名称" prop="gName" style="width: 45%">
            <el-input v-model="form.gName" clearable class="cInput" v-if="form.gRelease===0"/>
            <el-input v-model="form.gName" clearable class="cInput" v-else readonly/>
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
          <el-form-item label="目标供应商" prop="gUnit" style="width: 45%" clearable class="cInput">
            <template slot-scope="scope">
              <el-input v-model="form.gUnit" clearable class="cInput" readonly>
                <i slot="suffix" class="el-icon-search" @click="openGys()" style="margin-top: 10px" v-if="form.gRelease===0"/>
              </el-input>
              <el-dialog title="产品名称" :visible.sync="GysDialog">
                <el-table
                  ref="singleTable"
                  :data="supplierList"
                  highlight-current-row
                  style="width: 100%"
                  @row-click="handleRowClick1"
                >
                  <el-table-column type="index" label="序号" width="100"/>
                  <el-table-column prop="hName" label="委托方名称" width="140"/>
                  <el-table-column prop="hSignPhone" label="电话" width="130"/>
                  <el-table-column prop="hQuality" label="企业性质" width="150"/>
                  <el-table-column prop="hRange" label="经营范围"/>
                </el-table>
                <pagination
                  v-show="total1>0"
                  :total="total1"
                  :page.sync="queryParams1.pageNum"
                  :limit.sync="queryParams1.pageSize"
                  @pagination="selectGysList"
                />
                <el-button @click="rest">清空</el-button>
              </el-dialog>
            </template>
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
      <h3 v-if="!show">供应商报价信息</h3>
      <div class="cl" v-if="!show">
        <el-button @click="bAddRow" :disabled="this.form.gRelease===2">新增</el-button>
        <el-button @click="bDeleteRows" :disabled="this.form.gRelease===2">删除</el-button>
        <el-button @click="bCopyRows" :disabled="this.form.gRelease===2">复制</el-button>
        <el-table
          :data="ComQuotation"
          :row-key="row => row.id"
          @selection-change="bHandleSelectionChange"
          border
          stripe
          :style="{marginTop:'10px'}"
        >
          <el-table-column type="selection" width="55" :selectable="selectable"/>
          <el-table-column label="序号" prop="id" width="80"/>
          <el-table-column label="供应商名称" prop="hName" width="300">
            <template slot-scope="scope">
              <el-input v-model="scope.row.hName" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="报价次数" prop="bjSecond" width="200">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bjSecond" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="报价金额" prop="bjTotal">
            <template slot-scope="scope">
              <el-input v-model="scope.row.bjTotal" @blur="bHandleBlur(scope.row)" @input="bHandleInput(scope.row)" readonly v-if="scope.row.gRelease===2"/>
              <el-input v-model="scope.row.bjTotal" @blur="bHandleBlur(scope.row)" @input="bHandleInput(scope.row)" v-else/>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div>
        <el-button type="primary" style="margin-top: 20px" @click="addBj" v-if="this.form.gRelease!==2">添加</el-button>
      </div>

      <!--合同签署文件-->
      <h3>合同签署文件</h3>
      <el-form ref="elForm1" :model="form" size="medium" label-width="180px" label-position="left">
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
              :file-list="fileList2"
              :disabled="this.form.gRelease===2"
            >
              <el-button size="small" type="primary">上传附件</el-button>
            </el-upload>
          </el-form-item>
        </el-row>
      </el-form>
    </div>

    <div style="margin-top: 20px">
      <el-button size="medium" @click="back1">返回</el-button>
      <el-button size="medium" type="primary" @click="faBu" v-if="this.form.gRelease!==2">完成</el-button>
    </div>
  </div>
</template>

<script>
import {
  getPlan, getPro, getItemsDevice, getQuotation,
  getAttachments, getAttachmentsByAid, updatePro,
  upePro, addQuotation, delQuotation
} from '../../../api/system/noTender'
import { listDevice } from '../../../api/system/addContract'
import router from '../../../router'
import { listSupplier } from '../../../api/system/supplier'

export default {
  data() {
    return {
      ff: false,
      GysDialog: false,
      total1: 0,
      show: false,
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
        gRelease: null,
        gUnit: null,
        hid: null,
        gNotes: null
      },
      // 表单校验
      rules1: {
        gUnit: [
          {
            required: true,
            message: '请选择委托人',
            trigger: 'blur'
          }
        ]
      },
      selectRow: null,
      fileList2: [],
      // fileList1: {
      //   anName: [],
      //   anUrl: []
      // },
      //附件
      ComPubAttachments: {
        anSize: null,
        anUrl: null,
        anName: null
      },
      aid: null,
      gfId: null,
      //报价集合
      ComQuotation: [],
      info: {
        gid: null,
        hid: null,
        gRelease: null,
        comPubAttachments: {
          anId: null,
          aid: null,
          anName: null,
          anUrl: null
        }
      },
      chuan: [],
      show1: false,
      //供应商信息
      queryParams1: {
        pageNum: 1,
        pageSize: 10,
        hName: null,
        hid: null,
        hQuality: null
      },
      supplierList: [],
      hid: null,
      bSelectedRows: [],
      bTableColumns: [],
      info1: {
        gid: null,
        gRelease: null,
        hid: null
      }
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
      console.log('--------------')
      if (files.size === 0) {
        this.$message.error('选择的文件不能为空，请重新选择！')
        this.fileList2.splice(this.fileList2.indexOf(files[0]), 1)
      }
    },
    faBu() {
      this.info1.gid = this.gid
      this.info1.gRelease = 2
      upePro(this.info1).then((res) => {
        this.$message.success(res.msg)
        this.$router.push('/noTender/project')
      }).catch((error) => {
        this.$message.error('发布失败')
      })
    },
    addXy() {
      this.$confirm('提交后不可再修改！', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        addQuotation(this.ComQuotation).then((res) => {
        }).catch((err) => {
          this.$message.error(err)
        })
      }).catch(() => {
        this.$message({ type: 'info', message: '已取消' })
      })
    },
    //创建合同
    addXy1() {
      this.$refs.elForm.validate(valid => {
          if (valid) {
            if (this.fileList2.length === 0) {
              this.$message.warning('请上传附件')
            } else {
              if (this.hasNewFiles()) {
                console.log(1)
                this.chuan.push(...this.fileList2)
                this.$refs.up2.submit()
              } else {
                this.add()
              }
            }
          } else {
            this.$message.error('请填写完整信息')
            return false
          }
        }
      )
    },
    hasNewFiles() {
      // 检查 fileList2 中是否包含新文件
      for (let file of this.fileList2) {
        if (!file.url) {
          return true // 如果有文件没有 url 属性，表示是新文件
        }
      }
      return false // 如果所有文件都有 url 属性，表示没有新文件
    },
    add() {
      this.info.gRelease = 1
      this.info.comPubAttachments.anUrl = this.chuan.map(item => item.response ? item.response.data.url : item.url).join(',')
      this.info.comPubAttachments.anName = this.chuan.map(item => item.response ? item.response.data.name : item.name).join(',')
      console.log(this.info, 'this.info')
      upePro(this.info).then((res) => {
        this.$message.success(res.msg)
        this.$router.push('/noTender/project')
      }).catch((error) => {
        this.$message.error('提交失败')
      })
    },
    //上传协议文件-------------------------------------------------
    handleExceed2(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove2(file, fileList) {
      return this.$modal.confirm(`确定移除 ${file.name}？`).then(() => {
        this.fileList2 = fileList
        this.$message.success('删除成功!')
      })
    },
    success2(response, fileList) {
      this.add()
    },
    //上传协议文件-------------------------------------------------
    //产品数量输入框失去焦点时
    //查询采购计划信息
    getList() {
      getPro(this.gid).then(response => {
        this.ComQuotation.gfId = response.data.gCode
        this.aid = response.data.xyId
        this.hid = response.data.hid
        this.ComQuotation.hid = response.data.hid
        this.info.comPubAttachments.aid = response.data.xyId
        this.info.gid = response.data.gid
        this.form = response.data
        if (response.data.gRelease === 0) {
          this.show = true
        }
        if (response.data.gTendertype == 3) {
          this.form.gTendertype = '询价'
        } else if (response.data.gTendertype == 5) {
          this.form.gTendertype = '竞争性谈判'
        } else if (response.data.gTendertype == 4) {
          this.form.gTendertype = '委托'
        } else if (response.data.gTendertype == 6) {
          this.form.gTendertype = '单一来源'
        }
        if (response.data.gIsPublic == 1) {
          this.form.gIsPublic = '公开'
        } else {
          this.form.gIsPublic = '邀请'
        }
        this.form.gCompany = '鸿鹄科技有限公司'
        this.gfId = this.form.gCode
        this.getComPubAttachments()
        return Promise.all([getPlan(this.aid), getQuotation({ 'gfId': this.gfId })])
      }).then(res => {
        this.form.createBy = res[0].data.createBy
        this.form.createDept = res[0].data.aCreateDept
        console.log(res[1], 'res[1].rows')

        if (res[1].rows === null) {
          this.form.gUnit = null
        } else {
          this.ComQuotation = res[1].rows
          this.form.gUnit = res[1].rows[0].bsSupplier.hName
          this.ComQuotation.forEach((row, id) => {
            row.hName = res[1].rows[0].bsSupplier.hName
            if (id < res[1].rows.length) {
              res[1].rows.forEach((row, index) => {
                row.id = index + 1
              })
            }
          })
        }
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
        this.selectGysList()
      })
    },
    addBj() {
      this.$confirm('确定添加本次新增报价信息?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.ComQuotation.length === 0) {
          this.$message({ type: 'warning', message: '报价金额不能为空' })
          return false
        } else {
          delQuotation(this.form.gCode).then((res) => {
            this.ComQuotation.forEach((e) => {
              e.bjHid = this.hid
              e.gfId = this.form.gCode
              addQuotation(e).then((res) => {
                this.$message({ type: 'success', message: '添加成功' })
                this.$router.push('/noTender/project')
              })
            })
          })
        }
      }).catch(() => {
        this.$message({ type: 'info', message: '已取消添加' })
      })
    }
    ,
    selectable(row, index) {
      return row.gRelease === 2
    },
    //查询附件信息
    getComPubAttachments() {
      getAttachmentsByAid(this.aid).then(res => {
        console.log(res, 'res')
        if (res.data != null) {
          this.info.comPubAttachments.anId = res.data.anId
          if (res.data.anName && res.data.anUrl) {
            const names = res.data.anName.split(',')
            const urls = res.data.anUrl.split(',')
            let fileList = []
            for (let i = 0; i < names.length; i++) {
              fileList.push({
                name: names[i],
                url: urls[i]
              })
            }
            this.fileList2 = fileList
            this.info.comPubAttachments.anName = res.data.anName
            this.info.comPubAttachments.anUrl = res.data.anUrl
          } else {
            this.fileList2 = []
            this.info.comPubAttachments.anName = null
            this.info.comPubAttachments.anUrl = null
          }
        } else {
          this.info.comPubAttachments.anId = null
          this.fileList2 = []
          this.info.comPubAttachments.anName = null
          this.info.comPubAttachments.anUrl = null
        }

      })
    }
    ,
    //查询供应商信息
    selectGysList() {
      listSupplier(this.queryParams1).then(response => {
        this.supplierList = response.rows
        this.total1 = response.total
        // //根据hid获取供应商信息
        // let foundSupplier = this.supplierList.find(supplier => supplier.hid === this.hid)
        // this.form.gUnit = foundSupplier.hName
      })
    }
    ,
    spCountBlur(row) {
      if (row.inVat == null) {
        row.inVat = 0.00
      }
      row.inSubtotal = (row.inCount * row.inVat).toFixed(2)
      this.lCalculateTotalSubtotal()
    }
    ,
    //产品数量输入框的值改变时
    spCountChange(row) {
      if (row.inVat == null) {
        row.inVat = 0.00
      }
      row.inSubtotal = (row.inCount * row.inVat).toFixed(2)
      this.lCalculateTotalSubtotal()
    }
    ,
    // 计算产品总价格方法
    lCalculateTotalSubtotal() {
      this.lTotalSubtotal = this.lTableData.reduce((total, row) => {
        let totalValue = row.inSubtotal ? parseFloat(row.inSubtotal) : 0
        let kk = total + totalValue // 将每行的小计相加得到总价格
        this.form.eAmount = kk.toFixed(2)
        this.qsFormData.gnPbamount = kk.toFixed(2)
        return kk
      }, 0)
    }
    ,
    //查询产品信息
    selectBdList() {
      listDevice(this.queryParams).then(response => {
        this.deviceList = response.rows
        this.total = response.total
      })
    }
    ,
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
    }
    ,
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
    }
    ,
    //产品行点击事件
    handleRowClick1(row) {
      // console.log(row)
      // 在这里处理行点击事件
      this.form.gUnit = row.hName
      this.info.hid = row.hid
      this.GysDialog = false
    }
    ,
    //关闭产品名称对话框
    closeDialog1() {
      this.cpDialog = false
    }
    ,
    //显示产品对话框
    openGys() {
      this.GysDialog = true
      this.selectGysList()
    }
    ,
    //显示产品对话框
    openCp(row) {
      this.selectRow = row
      this.cpDialog = true
      this.selectBdList()
    }
    ,
    //返回
    back1() {
      this.$router.push('/noTender/project')
    }
    ,
    //清空
    rest() {
      this.form.gUnit = null
      this.GysDialog = false
    }
    ,
    //单选多选
    lHandleSelectionChange(selection) {
      this.lSelectedRows = selection
    }
    ,
    bAddRow() {
      const newRow = {}
      this.bTableColumns.forEach(column => {
        newRow[column.prop] = ''
      })
      newRow.id = this.ComQuotation.length + 1
      newRow.bjSecond = this.ComQuotation.length + 1
      newRow.hName = this.form.gUnit
      this.ComQuotation.push(newRow)
    }
    ,
    bDeleteRows() {
      this.$confirm('确定删除选中的行吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.ComQuotation = this.ComQuotation.filter(row => !this.bSelectedRows.includes(row))
        this.bSelectedRows = []
        this.bUpdateRowIds()
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
    }
    ,
    //复制
    bCopyRows() {
      const copiedRows = this.bSelectedRows.map(row => ({ ...row }))
      copiedRows.forEach(row => {
        row.id = this.ComQuotation.reduce((maxId, row) => Math.max(row.id, maxId), 0) + 1
        this.ComQuotation.push(row)
      })
    }
    ,
    //行数变化
    bUpdateRowIds() {
      this.ComQuotation.forEach((row, index) => {
        row.id = index + 1
      })
    }
    ,
    //单选多选
    bHandleSelectionChange(selection) {
      this.bSelectedRows = selection
    }
    ,
    bHandleBlur(row) {
      if (row.bjTotal) {
        row.bjTotal = parseFloat(row.bjTotal).toFixed(2)
        if (isNaN(row.bjTotal)) {
          row.bjTotal = null
        }
      }
    }
    ,
    bHandleInput(row) {
      // 只保留数字和一个小数点
      row.bjTotal = row.bjTotal.replace(/[^\d.]/g, '')
      // 只能输入一个小数点
      let dotIndex = row.bjTotal.indexOf('.')
      if (dotIndex !== -1) {
        row.bjTotal = row.bjTotal.slice(0, dotIndex + 1) + row.bjTotal.slice(dotIndex + 1).replace(/\./g, '')
      }
      // 只能输入到小数点后两位
      let parts = row.bjTotal.split('.')
      if (parts[1] && parts[1].length > 2) {
        row.bjTotal = parts[0] + '.' + parts[1].slice(0, 2)
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
