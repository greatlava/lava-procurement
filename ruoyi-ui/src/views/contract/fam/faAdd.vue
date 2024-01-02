<template>
  <div class="app-container" style="padding: 30px 80px">
    <div class="cl">
      <el-descriptions
          class="cl"
          title="| 协议基本信息"
          :column="2"
          :size="size"
          :label-style="labelStyle"
          border
      >
        <el-descriptions-item :span="2">
          <template slot="label">
            协议名称
          </template>
          <template>
            <el-input v-model="queryParams.oName" placeholder="请输入协议名称"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            协议编号
          </template>
          <template>
            <el-input v-model="queryParams.oCode" placeholder="系统自动生成" disabled/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            协议类型
          </template>
          <template>
            <el-input v-model="queryParams.oType" readonly/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item :span="2">
          <template slot="label">
            协议说明
          </template>
          <template slot="default">
            <el-input v-model="queryParams.oDescribe" type="textarea" :rows="3" clearable class="cInput"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            签署主体
          </template>
          <template slot="default">
            <el-input v-model="queryParams.oSubject" readonly class="cll"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            相对方
          </template>
          <template slot="default">
            <el-input v-model="queryParams.hName" readonly class="cll"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            签署日期
          </template>
          <template>
            <el-date-picker v-model="queryParams.oStartdate" type="date" class="cInput"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            失效日期
          </template>
          <template>
            <el-date-picker v-model="queryParams.oEnddate" type="date" class="cInput"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            协议文件
          </template>
          <template>
            <el-upload
                ref="up1"
                class="upload-demo"
                :action="url"
                :before-remove="beforeRemove"
                :auto-upload="false"
                :limit="1"
                :on-exceed="handleExceed"
                :on-success="success"
                :file-list="fileList"
                :on-change="onchange1"
            >
              <el-button size="small" type="primary">上传框架协议文件</el-button>
            </el-upload>
          </template>
        </el-descriptions-item>
      </el-descriptions>

      <div class="cl1">
        <h4><strong>| 设备信息</strong></h4>
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
              </el-dialog>
            </template>
          </el-table-column>
          <el-table-column label="产品编码" prop="tid" width="165">
            <template slot-scope="scope">
              <el-input v-model="scope.row.tid" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="规格型号" prop="inModel" width="180">
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
          <span :style="{fontWeight:'700'}">{{ oTotalprice.toFixed(2) }}</span>
        </div>
      </div>
    </div>
    <el-button size="medium" @click="back1">返回</el-button>
    <el-button type="primary" @click="addFa">创建框架协议</el-button>
  </div>
</template>

<script>
import { addManagement, listDevice, selectItemsDevice, upOidbyOid } from '../../../api/system/addContract'

export default {
  name: 'AddFa',
  data() {
    return {
      url: process.env.VUE_APP_BASE_API + '/basic/supplier/upload1',
      oTotalprice: 0,
      size: '0',
      labelStyle: {
        width: '180px',
        textAlign: 'center'
      },
      //获取框架计划ID
      jhId: this.$route.query.jhId,
      queryParams: {
        tid: null,
        oCode: null,
        oName: null,
        oSubject: '鸿鹄科技有限公司',
        hid: null,
        hName: null,
        oStartdate: null,
        oEnddate: null,
        oFile: null,
        oType: '采购框架协议',
        oHstatus: 1,
        oDescribe: null,
        oOpinion: null
      },
      //协议文件
      fileList: [],
      lSelectedRows: [],
      lTableData: [],
      //产品显示
      cpDialog: false,
      deviceList: [],
      total: 1,
      lTableColumns: []
    }
  },
  created() {
    this.hh()
  },
  methods: {
    //上传协议文件-------------------------------------------------
    success(response) {
      console.log(111)
      this.queryParams.oFile = response.data.url
      console.log('打印up1-------------------------')
      console.log(this.queryParams.oFile)
      //调用下一个上传的方法
      this.submitNextUpload()
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    onchange1(files, fileList) {
      this.fileList = fileList
      console.log(this.fileList, 'fileList onchange')
      if (files.size === 0) {
        this.$message.error('选择的文件不能为空，请重新选择！')
        this.fileList.splice(this.fileList.indexOf(files[0]), 1)
      }
    },
    //上传协议文件-------------------------------------------------
    //查询计划信息信息
    hh() {
      selectItemsDevice({ 'jhId': this.jhId }).then(response => {
        console.log(response, 'er')
        this.queryParams.hName = response.rows[0].ppmFramePlan.bsSupplier.hName
        this.queryParams.hid = response.rows[0].ppmFramePlan.bsSupplier.hid
        this.queryParams.oName = response.rows[0].ppmFramePlan.jhName + '协议'
        this.queryParams.jhId = this.jhId
        response.rows.forEach((e, i) => {
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
          this.oTotalprice += e.vCount * e.ppmDevice.tPrice * 1.13.toFixed(2)
        })
      })
    },
    addFa() {
      this.submitNextUpload()
    },
    add() {
      this.queryParams['oTotalprice'] = parseFloat(this.oTotalprice).toFixed(2)
      // this.queryParams['oTotalprice'] = '20000'
      this.queryParams['bsInventoryList'] = [...this.lTableData].filter(e => {
        delete e.id
        return e.tid != null
      })
      addManagement(this.queryParams).then(response => {
        console.log(response)
        if (response.msg === '操作成功') {
          return upOidbyOid({ 'oid': response.data.frameManagement.oid, 'jhId': response.data.frameManagement.jhId })
        } else {
          this.$message.error('添加失败')
        }
      }).then(res => {
        console.log('打印二------------------')
        console.log(res)
        console.log('打印二------------------')
        if (res.msg === '修改成功') {
          this.$message.success('添加成功')
          this.$router.push('/contract/fam')
        } else {
          this.$message.error('修改失败')
        }
      })
    },
    submitNextUpload() {
      // 根据条件判断调用下一个上传
      if (this.fileList.length > 0 && this.queryParams.oFile == null) {
        console.log('打印提交1-------------------------')
        this.$refs.up1.submit()
      } else {
        console.log('打印提交2-------------------------')
        this.add()
      }
    },
    back1() {
      this.$router.back()
    },
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
    //显示产品对话框
    openCp(row) {
      this.selectRow = row
      this.cpDialog = true
      this.selectBdList()
    },    //查询产品信息
    selectBdList() {
      listDevice(this.queryParams).then(response => {
        console.log(response)
        this.deviceList = response.rows
        this.total = response.total
      })
    },
    //产品行点击事件
    handleRowClick(row) {
      this.selectRow.inName = row.tName
      this.selectRow.tid = row.tid
      this.selectRow.inModel = row.tModel
      this.selectRow.inUnit = row.tUnit
      this.selectRow.inVat = (row.tPrice * 1.13).toFixed(2)
      this.selectRow.inCount = 1
      this.selectRow.inSubtotal = (this.selectRow.inCount * this.selectRow.inVat).toFixed(2)
      this.cpDialog = false
      this.lCalculateTotalSubtotal()
    },
    //添加
    lAddRow() {
      let newRow = {}
      this.lTableColumns.forEach(column => {
        newRow[column.prop] = ''
      })
      newRow.id = this.lTableData.length + 1
      newRow.inVat = (0).toFixed(2)
      newRow.inSubtotal = (0).toFixed(2)
      newRow.inCount = 1
      this.lTableData.push(newRow)
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
    // 计算产品总价格方法
    lCalculateTotalSubtotal() {
      this.oTotalprice = this.lTableData.reduce((total, row) => {
        let totalValue = row.inSubtotal ? parseFloat(row.inSubtotal) : 0
        return total + totalValue// 将每行的小计相加得到总价格
      }, 0)
    }
  }
}
</script>

<style scoped>
.cl >>> .el-input__inner {
  border: none;
}

.cll /deep/ .el-input__inner {
  color: #00afff;
}

.cl1 {
  margin-top: 20px;
}
</style>
