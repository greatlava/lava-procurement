<template>
  <div class="app-container">
    <el-row :gutter="15">
      <el-col :span="24">
        <el-card shadow="never" class="title">
          <h2>采购合同</h2>
        </el-card>
      </el-col>
    </el-row>
    <div class="con">
      <el-form ref="elForm" :model="form" :rules="rules" size="medium" label-width="100px" label-position="left">
        <h3>合同基本信息</h3>
        <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
          <el-form-item label="合同名称" prop="field101" style="width: 45%">
            <el-input v-model="form.field101" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="合同编号" prop="field102" style="width: 45%">
            <el-input v-model="form.field102" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="类型" prop="field103" style="width: 45%">
            <el-input v-model="form.field103" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="密级" prop="field104" style="width: 45%">
            <el-input v-model="form.field104" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="开始时间" prop="field101" style="width: 45%">
            <el-input v-model="form.field101" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="结束时间" prop="field102" style="width: 45%">
            <el-input v-model="form.field102" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="合同金额" prop="field103" style="width: 45%">
            <el-input v-model="form.field103" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="合同情况说明" prop="field104" style="width: 100%">
            <el-col>
              <el-input v-model="form.field104" type="textarea" :rows="4" clearable class="cInput"/>
            </el-col>
          </el-form-item>
        </el-row>
        <h3>合同采购方式</h3>
        <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
          <!--采购方式-->
          <el-form-item label="采购方式" prop="field101" style="width: 45%">
            <el-select v-model="cgValue" placeholder="请选择" class="cInput">
              <el-option
                v-for="item in cgOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <!--招投标信息-->
          <el-form-item label="招投标信息" style="width: 45%">
            <el-input v-model="ztbInputValue" @click="ztbShowDialog" class="cInput">
              <template slot="append">
                <el-button icon="el-icon-search" @click="ztbShowDialog"></el-button>
              </template>
            </el-input>
            <el-dialog :visible.sync="ztbDialogVisible" @close="ztbHandleClose">
              <el-table :data="ztbOptions" @row-click="ztbHandleRowClick">
                <el-table-column prop="value" label="数值"/>
                <el-table-column prop="label" label="标签"/>
              </el-table>
              <el-button @click="ztbClearInput">清除</el-button>
              <el-button @click="ztbCloseDialog">取消</el-button>
            </el-dialog>
          </el-form-item>
          <!--相关框架协议-->
          <el-form-item label="相关框架协议" style="width: 45%">
            <el-input v-model="kjInputValue" @click="kjShowDialog" class="cInput">
              <template slot="append">
                <el-button icon="el-icon-search" @click="kjShowDialog"></el-button>
              </template>
            </el-input>
            <el-dialog :visible.sync="kjDialogVisible" @close="kjHandleClose">
              <el-table :data="kjOptions" @row-click="kjHandleRowClick">
                <el-table-column prop="value" label="数值"/>
                <el-table-column prop="label" label="标签"/>
              </el-table>
              <el-button @click="kjClearInput">清除</el-button>
              <el-button @click="kjCloseDialog">取消</el-button>
            </el-dialog>
          </el-form-item>
          <!--采购清单-->
          <el-form-item label="采购清单" style="width: 45%">
            <el-input v-model="qdInputValue" @click="qdShowDialog" class="cInput">
              <template slot="append">
                <el-button icon="el-icon-search" @click="qdShowDialog"></el-button>
              </template>
            </el-input>
            <el-dialog :visible.sync="qdDialogVisible" @close="qdHandleClose">
              <el-table :data="qdOptions" @row-click="qdHandleRowClick">
                <el-table-column prop="value" label="数值"/>
                <el-table-column prop="label" label="标签"/>
              </el-table>
              <el-button @click="qdClearInput">清除</el-button>
              <el-button @click="qdCloseDialog">取消</el-button>
            </el-dialog>
          </el-form-item>
        </el-row>
        <!--合同标的清单-->
        <h3>合同标的清单</h3>
        <div class="cl">
          <el-button @click="lAddRow">新增</el-button>
          <el-button @click="lDeleteRows" :disabled="lSelectedRows.length === 0">删除</el-button>
          <el-button @click="lCopyRows" :disabled="lSelectedRows.length === 0">复制</el-button>
          <el-table :data="lTableData" :row-key="row => row.id" @selection-change="lHandleSelectionChange" border stripe :style="{marginTop:'10px'}">
            <el-table-column type="selection" width="55"/>
            <el-table-column v-for="column in lTableColumns" :key="column.prop" :label="column.label" :prop="column.prop">
              <template slot-scope="scope">
                <el-input v-model="scope.row[column.prop]" @input="lHandleInput(scope.row, column.prop)"/>
              </template>
            </el-table-column>
          </el-table>
          <div :style="{textAlign:'right'}">
            <span :style="{marginRight:'100px'}">总计:</span>
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
            <el-table-column label="序号" prop="id" width="60">
              <template slot-scope="scope">
                <span>{{ scope.row.id }}</span>
              </template>
            </el-table-column>
            <el-table-column label="款项内容" prop="name">
              <template slot-scope="scope">
                <el-select v-model="scope.row.payDate">
                  <el-option
                    v-for="option in payDateOptions"
                    :key="option.value"
                    :label="option.label"
                    :value="option.value"
                  />
                </el-select>
              </template>
            </el-table-column>
            <el-table-column label="付款日期">
              <template slot-scope="scope">
                <div class="block">
                  <el-date-picker v-model="payTimeValue" type="date" placeholder="选择日期"/>
                </div>
              </template>
            </el-table-column>
            <el-table-column label="付款单位" prop="age">
              <template slot-scope="scope">
                <el-input required/>
              </template>
            </el-table-column>
            <el-table-column label="收款合同方" prop="age">
              <template slot-scope="scope">
                <el-input required/>
              </template>
            </el-table-column>
            <el-table-column label="付款条件">
              <template slot-scope="scope">
                <el-input v-model="scope.row.total"/>
              </template>
            </el-table-column>
            <el-table-column label="付款金额" prop="total">
              <template slot-scope="scope">
                <el-input v-model="scope.row.total" @blur="payHandleBlur(scope.row)" @input="payHandleInput(scope.row)"/>
              </template>
            </el-table-column>
            <el-table-column label="违约责任" prop="total">
              <template slot-scope="scope">
                <el-input v-model="scope.row.total"/>
              </template>
            </el-table-column>
          </el-table>
        </div>

        <h3>合同签署状态</h3>
        <el-row type="flex" justify="space-between" align="top" :gutter="15" style="flex-wrap: wrap;">
          <el-form-item label="合同名称" prop="field101" style="width: 45%">
            <el-input v-model="form.field101" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="合同编号" prop="field102" style="width: 45%">
            <el-input v-model="form.field102" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="类型" prop="field103" style="width: 45%">
            <el-input v-model="form.field103" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="密级" prop="field104" style="width: 45%">
            <el-input v-model="form.field104" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="开始时间" prop="field101" style="width: 45%">
            <el-input v-model="form.field101" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="结束时间" prop="field102" style="width: 45%">
            <el-input v-model="form.field102" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="合同金额" prop="field103" style="width: 45%">
            <el-input v-model="form.field103" clearable class="cInput"/>
          </el-form-item>
          <el-form-item label="合同情况说明" prop="field104" style="width: 100%">
            <el-col>
              <el-input v-model="form.field104" type="textarea" :rows="4" clearable class="cInput"/>
            </el-col>
          </el-form-item>
        </el-row>

        <!--        <el-form-item size="large">
                  <el-button type="primary" @click="submitForm">提交</el-button>
                  <el-button @click="resetForm">重置</el-button>
                </el-form-item>-->
      </el-form>
    </div>


  </div>
</template>


<script>
export default {
  data() {
    return {
      payTimeValue: '',
      //合同标的清单
      lTotalSubtotal: 0, // 初始化总价格为0
      lTableColumns: [
        { label: '序号', prop: 'id' },
        { label: '产品名称', prop: 'name' },
        { label: '产品编号', prop: 'age' },
        { label: '规格型号', prop: 'age' },
        { label: '含税单价', prop: 'age' },
        { label: '数量', prop: 'age' },
        { label: '小计', prop: 'total' }
      ],
      payTableColumns: {
        id: 0,
        total: null
      },
      lTableData: [{ id: 1, name: '张三', age: 20, total: 40 }, { id: 2, name: '李四', age: 16, total: 10 }],
      lSelectedRows: [],
      //合同付款约定
      payTableData: [],
      paySelectedRows: [],
      //招投标信息
      ztbInputValue: '',
      ztbDialogVisible: false,
      ztbOptions: [
        { value: 'option1', label: '选项1' },
        { value: 'option2', label: '选项2' },
        { value: 'option3', label: '选项3' }
      ],
      ztbSelectedItem: '',
      //框架协议标信息
      kjInputValue: '',
      kjDialogVisible: false,
      kjOptions: [
        { value: 'option1', label: '选项1' },
        { value: 'option2', label: '选项2' },
        { value: 'option3', label: '选项3' }
      ],
      kjSelectedItem: '',
      //采购清单
      qdInputValue: '',
      qdDialogVisible: false,
      qdOptions: [
        { value: 'option1', label: '选项1' },
        { value: 'option2', label: '选项2' },
        { value: 'option3', label: '选项3' }
      ],
      qdSelectedItem: '',
      //采购方式
      cgOptions: [{
        value: '选项1',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶'
      }],
      cgValue: '',
      // 遮罩层
      loading: true,
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        user: '',
        region: ''
      },
      // 表单参数
      form: {
        field103: undefined,
        field104: undefined
      },
      // 表单校验
      rules: {
        field103: undefined,
        field104: undefined
      }
    }
  },
  mounted() {
    this.lCalculateTotalSubtotal()
  },
  methods: {
    //招投标
    ztbShowDialog() {
      this.ztbDialogVisible = true
    },
    ztbHandleClose() {
      this.ztbDialogVisible = false
    },
    ztbHandleRowClick(row) {
      this.ztbInputValue = row.label
      this.ztbDialogVisible = false
    },
    ztbClearInput() {
      this.ztbInputValue = ''
    },
    ztbCloseDialog() {
      this.ztbDialogVisible = false
    },
    //相关框架协议
    kjShowDialog() {
      this.kjDialogVisible = true
    },
    kjHandleClose() {
      this.kjDialogVisible = false
    },
    kjHandleRowClick(row) {
      this.kjInputValue = row.label
      this.kjDialogVisible = false
    },
    kjClearInput() {
      this.kjInputValue = ''
    },
    kjCloseDialog() {
      this.kjDialogVisible = false
    },
    //采购清单
    qdShowDialog() {
      this.qdDialogVisible = true
    },
    qdHandleClose() {
      this.qdDialogVisible = false
    },
    qdHandleRowClick(row) {
      this.qdInputValue = row.label
      this.qdDialogVisible = false
    },
    qdClearInput() {
      this.qdInputValue = ''
    },
    qdCloseDialog() {
      this.qdDialogVisible = false
    },
    /* 合同标的清单 */
    lAddRow() {
      const newRow = {}
      this.lTableColumns.forEach(column => {
        newRow[column.prop] = ''
      })
      newRow.id = this.lTableData.length + 1
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
    // 处理输入框输入事件的方法
    lHandleInput(row, prop) {
      // 判断输入框的值是否为空或非数字
      if (prop === 'total') {
        // 计算总价格
        this.lCalculateTotalSubtotal()
      }
    },
    // 计算总价格方法
    lCalculateTotalSubtotal() {
      this.lTotalSubtotal = this.lTableData.reduce((total, row) => {
        let totalValue = row.total ? parseFloat(row.total) : 0
        return total + totalValue // 将每行的小计相加得到总价格
      }, 0)
    },
    /* 合同付款约定 */
    payAddRow() {
      const newRow = {}
      newRow.id = this.payTableData.length + 1
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
      // 将输入的值保留两位小数
      row.total = parseFloat(row.total).toFixed(2)
    },
    payHandleInput(row) {
      // 只保留数字和一个小数点
      row.total = row.total.replace(/[^\d.]/g, '')
      // 只能输入到小数点后两位
      let parts = row.total.split('.')
      if (parts[1] && parts[1].length > 2) {
        row.total = parts[0] + '.' + parts[1].slice(0, 2)
      }
    },
    /* ------------------------------- */
    // 提交
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
      })
    },
    //重置
    resetForm() {
      this.$refs['elForm'].resetFields()
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
  padding-left: 100px;
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
  margin-top: -15px;
}

h3 {
  font-weight: bold;
}

</style>
