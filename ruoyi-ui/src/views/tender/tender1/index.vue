<template>
  <div class="app-container">
    <div>
      <el-row :gutter="15">
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="70px">
          <el-col :span="23">
            <el-row type="flex" justify="start" align="middle" :gutter="30">
              <el-col :span="24">
                <el-form-item label="时间" prop="field101">
                  <el-date-picker type="daterange" v-model="formData.field101" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" :style="{width: '100%'}" start-placeholder="年/月/日"
                                  end-placeholder="年/月/日" range-separator="至" clearable
                  ></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="项目名称" prop="field108">
                  <el-input v-model="formData.field108" placeholder="请输入项目名称" clearable
                            :style="{width: '100%'}"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item size="medium">
                  <el-button type="primary" @click="query">查询</el-button>
                  <el-button @click="resetForm">重置</el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>

        </el-form>
      </el-row>
    </div>
    <el-table v-loading="loading" :data="tenderList" @selection-change="handleSelectionChange">
      <el-table-column label="序号" align="center" prop="orderNum" width="80"/>
      <el-table-column label="项目编号" align="center" prop="sCode"/>
      <el-table-column label="项目名称" align="center" prop="sName"/>
      <el-table-column label="招标方式" align="center" prop="sWay"/>
      <el-table-column label="项目状态" align="center" prop="sProjectState"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width"/>
    </el-table>
    <el-table-column v-for="column in tableColumns" :key="column.prop" :label="column.label" :align="column.align" :prop="column.prop" :width="column.width"/>
    <el-table-column label="操作" align="center" class-name="small-padding fixed-width"/>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
                @pagination="getList" />
  </div>
</template>

<script>
import { listTender, getTender, delTender, addTender, updateTender } from '@/api/system/tender'

export default {
  name: 'Tender',
  data() {
    return {
      tableColumns: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 招标项目表格数据
      tenderList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        orderNum: 0,
        pageNum: 1,
        pageSize: 10,
        sCode: null,
        sName: null,
        sWay: null,
        sProjectState: null,
        sDeadline: null
      },
      // 表单参数
      formData: {
        field101: null,
        field108: ''
      },
      // 表单校验
      rules: {
        field101: [],
        field108: []
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    query() {

    },
    resetForm() {

    },
    /** 查询招标项目列表 */
    getList() {
      this.loading = false
      // listTender(this.queryParams).then(response => {
      //   this.tenderList = response.rows;
      //   this.total = response.total;
      //   this.loading = false;
      // });
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        orderNum: 0,
        sCode: null,
        sName: null,
        sWay: null,
        sProjectState: null,
        sDeadline: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.sid)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    }
  }
}
</script>
