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
            <el-input v-model="queryParams.name" placeholder="请输入协议名称"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            协议编号
          </template>
          <template>
            <el-input v-model="queryParams.name" placeholder="系统自动生成" disabled/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            协议类型
          </template>
          <template>
            <el-input v-model="queryParams.type" readonly/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item :span="2">
          <template slot="label">
            协议说明
          </template>
          <template slot="default">
            <el-input v-model="queryParams.name" type="textarea" :rows="3" clearable class="cInput"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            签署主体
          </template>
          <template slot="default">
            <el-input v-model="queryParams.zt" readonly class="cll"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            相对方
          </template>
          <template slot="default">
            <el-input v-model="queryParams.xdf" readonly class="cll"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            签署日期
          </template>
          <template>
            <el-date-picker v-model="queryParams.eStartDate" type="date" class="cInput"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            失效日期
          </template>
          <template>
            <el-date-picker v-model="queryParams.eEndDate" type="date" class="cInput"/>
          </template>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            协议文件
          </template>
          <template>
            <el-upload
              class="upload-demo"
              action="https://jsonplaceholder.typicode.com/posts/"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              multiple
              :limit="3"
              :on-exceed="handleExceed"
              :file-list="fileList"
            >
              <el-button size="small" type="primary">上传框架协议文件</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
          </template>
        </el-descriptions-item>
      </el-descriptions>

      <div class="cl1">
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
          <el-table-column label="序号" type="index" width="60"/>
          <el-table-column label="产品名称" prop="tName" width="170">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ppmDevice.tName" readonly>
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
          <el-table-column label="产品编码" prop="tModel" width="165">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ppmDevice.tModel" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="规格型号" prop="inModel" width="180">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ppmDevice.inModel" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="单位" prop="inUnit" width="120">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ppmDevice.inUnit" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="含税单价" prop="inVat" width="130">
            <template slot-scope="scope">
              <el-input v-model="scope.row.ppmDevice.inVat" readonly/>
            </template>
          </el-table-column>
          <el-table-column label="数量" prop="inCount" width="150">
            <template slot-scope="scope">
              <el-input-number
                v-model="scope.row.ppmDevice.inCount"
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
              <el-input v-model="scope.row.ppmDevice.inSubtotal" readonly/>
            </template>
          </el-table-column>
        </el-table>
        <div :style="{textAlign:'right'}">
          <span :style="{marginRight:'100px'}">总价:</span>
          <span :style="{fontWeight:'700'}">{{ lTotalSubtotal.toFixed(2) }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { listSupplier } from '../../../api/system/supplier'
import { listDevice, selectItemsDevice } from '../../../api/system/addContract'

export default {
  name: 'AddFa',
  data() {
    return {
      //获取框架计划ID
      jhId: this.$route.query.jhId,
      //设置label的样式
      labelStyle: {
        width: '180px',
        textAlign: 'center'
      },
      textColor: {
        color: '#00afff'
      },
      size: '0',
      //数据列表
      queryParams: {
        name: null,
        type: '采购框架协议',
        zt: '鸿鹄科技有限公司',
        xdf: null,
        eStartDate: null,
        eEndDate: null
      },
      //协议文件
      fileList: [],
      /* ------------------------添加产品信息------------------------ */
      lTotalSubtotal: 0, // 初始化总价格为0
      lTableColumns: [],
      lSelectedRows: [],
      //产品信息列表
      lTableData: [],
      //产品集合
      deviceList: [],
      cpDialog: false,
      total: 0
      /* ------------------------添加产品信息------------------------ */
    }
  },
  created() {
    this.hh()
  },
  watch: {
    '$route.query.jhId': function(newJhId, oldJhId) {
      if (newJhId != oldJhId) {
        this.jhId = newJhId
        this.hh()
      }
    }
  },
  methods: {
    hh() {
      alert(this.jhId)
      selectItemsDevice({ 'jhId': this.jhId }).then(response => {
        console.log(response)
        console.log(response.rows[0].ppmFramePlan.bsSupplier.hName)
        this.queryParams.xdf = response.rows[0].ppmFramePlan.bsSupplier.hName
        this.lTableData = response.rows
        // this.total1 = response.total
      })
    },
    //上传协议文件-------------------------------------------------
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    //上传协议文件-------------------------------------------------
    /* ------------------------添加产品信息------------------------ */
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
    //显示产品对话框
    openCp(row) {
      this.selectRow = row
      this.cpDialog = true
      this.selectBdList()
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
    //查询产品信息
    selectBdList() {
      listDevice(this.queryParams).then(response => {
        console.log(response)
        this.deviceList = response.rows
        this.total = response.total
      })
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
    // 计算产品总价格方法
    lCalculateTotalSubtotal() {
      this.lTotalSubtotal = this.lTableData.reduce((total, row) => {
        let totalValue = row.inSubtotal ? parseFloat(row.inSubtotal) : 0
        let kk = total + totalValue // 将每行的小计相加得到总价格
        return kk
      }, 0)
    }
    /* ------------------------添加产品信息------------------------ */
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
