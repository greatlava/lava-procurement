<template>
  <div class="app-container">
    <div>
      <el-form ref="elForm" :model="form" :rules="rules" size="medium" label-width="150px">
        <el-row type="flex" justify="start" align="middle" :gutter="15">
          <el-form-item label="采购计划编码" prop="field107">
            <el-input v-model="form.field107" placeholder="请输入采购计划编码" clearable :style="{width: '100%'}">
            </el-input>
          </el-form-item>
          <el-form-item label="采购业务类型" prop="field106">
            <el-select v-model="form.field106" placeholder="请选择采购业务类型" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in field106Options" :key="index" :label="item.label"
                         :value="item.value" :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item size="medium">
            <el-button type="primary" @click="query">查询</el-button>
            <el-button type="primary" @click="add">新建</el-button>
            <el-button @click="resetForm">重置</el-button>
          </el-form-item>
        </el-row>
      </el-form>
    </div>

    <div class="tcl">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="待提交" name="first">
          <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
            <el-table-column label="序号" align="center" prop="orderNum" width="80"/>
            <el-table-column label="采购计划编号" align="center" prop="aCode"/>
            <el-table-column label="采购业务类型" align="center" prop="aBtype"/>
            <el-table-column label="采购计划名称" align="center" prop="aName"/>
            <el-table-column label="行项目数量" align="center" prop="aProjectCount"/>
            <el-table-column label="创建人" align="center" prop=""/>
            <el-table-column label="创建日期" align="center" prop=""/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['system:plan:view']"
                >查看
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="待审批" name="second">
          <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
            <el-table-column label="序号" align="center" prop="orderNum" width="80"/>
            <el-table-column label="采购计划编号" align="center" prop="aCode"/>
            <el-table-column label="采购业务类型" align="center" prop="aBtype"/>
            <el-table-column label="采购计划名称" align="center" prop="aName"/>
            <el-table-column label="行项目数量" align="center" prop="aProjectCount"/>
            <el-table-column label="创建人" align="center" prop=""/>
            <el-table-column label="创建日期" align="center" prop=""/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['system:plan:view']"
                >查看
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>

        <el-tab-pane label="已生效" name="third">
          <el-table v-loading="loading" :data="planList" @selection-change="handleSelectionChange">
            <el-table-column label="序号" align="center" prop="orderNum" width="80"/>
            <el-table-column label="采购计划编号" align="center" prop="aCode"/>
            <el-table-column label="采购业务类型" align="center" prop="aBtype"/>
            <el-table-column label="采购计划名称" align="center" prop="aName"/>
            <el-table-column label="行项目数量" align="center" prop="aProjectCount"/>
            <el-table-column label="创建人" align="center" prop=""/>
            <el-table-column label="创建日期" align="center" prop=""/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                  v-hasPermi="['system:plan:edit']"
                >修改
                </el-button>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['system:plan:remove']"
                >删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>

        </el-tab-pane>
      </el-tabs>
    </div>


    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>
</template>

<script>
import { listPlan, getPlan, delPlan, addPlan, updatePlan } from '@/api/system/plan'

export default {
  name: 'Plan',
  components: {},
  props: [],
  data() {
    return {
      tableData: [/* 表格数据 */],
      tableColumns: [/* 表格列配置 */],
      activeName: 'first',
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
      // 采购计划表格数据
      planList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        aCode: null,
        aName: null,
        aCreateDept: null,
        fjAnnex: null,
        aOpinion: null,
        aProjectCount: null,
        aBtype: null,
        aAstate: null
      },
      // 表单参数
      form: {
        field107: undefined,
        field106: undefined
      },
      // 表单校验
      rules: {
        field107: [],
        field106: []
      },
      field106Options: [{
        'label': '选项一',
        'value': 1
      }, {
        'label': '',
        'value': ''
      }]
    }
  },
  created() {
    this.getList()
  },
  methods: {
    add() {

    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    query() {
      this.$nextTick(() => {
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
        })
      })
    },
    resetForm() {
      this.$nextTick(() => {
        this.$refs['elForm'].resetFields()
      })
    },
    /** 查询采购计划列表 */
    getList() {
      this.loading = false
      // listPlan(this.queryParams).then(response => {
      //   this.planList = response.rows;
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
        aid: null,
        aCode: null,
        aName: null,
        createBy: null,
        aCreateDept: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        fjAnnex: null,
        aOpinion: null,
        aProjectCount: null,
        aBtype: null,
        aAstate: null
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
      this.ids = selection.map(item => item.aid)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加采购计划'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const aid = row.aid || this.ids
      getPlan(aid).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改采购计划'
      })
    },
    /** 提交按钮 */
    submitForm() {

    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const aids = row.aid || this.ids
      this.$modal.confirm('是否确认删除采购计划编号为"' + aids + '"的数据项？').then(function() {
        return delPlan(aids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
  }
}
</script>

