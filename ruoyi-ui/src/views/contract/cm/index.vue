<template>
  <div class="app-container">

    <div>
      <el-row :gutter="15">
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="70px">
          <el-col :span="23">
            <el-row type="flex" justify="start" align="middle" :gutter="30">
              <el-col :span="24">
                <el-form-item label="项目编号" prop="field101">
                  <el-input v-model="formData.field101" placeholder="请输入项目编号" clearable
                            :style="{width: '100%'}"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="项目名称" prop="field102">
                  <el-input v-model="formData.field102" placeholder="请输入项目名称" clearable
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

    <div class="tcl">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="待创建" name="first">
          <el-table stripe v-loading="loading" :data="contractList1">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="项目编号" align="center" prop="eXcode"/>
            <el-table-column label="项目名称" align="center" prop="eXname"/>
            <el-table-column label="中标人" align="center" prop="eWinningPerson"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <!--创建合同-->
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-folder-add"
                  @click=""
                  v-hasPermi="['system:contract:add']"
                >创建合同
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <pagination
            v-show="total1>0"
            :total="total1"
            :page.sync="queryParams1.pageNum"
            :limit.sync="queryParams1.pageSize"
            @pagination="getList1"
          />
        </el-tab-pane>

        <el-tab-pane label="签订中" name="second">
          <el-table stripe v-loading="loading" :data="contractList2">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="合同编号" align="center" prop="eHcode"/>
            <el-table-column label="合同名称" align="center" prop="eHname"/>
            <el-table-column label="创建人" align="center" prop="createBy"/>
            <el-table-column label="创建日期" align="center" prop="createTime"/>
            <el-table-column label="合同状态" align="center" prop="eStatus">
              <template slot-scope="scope">
                <el-tag v-if="scope.row.eStatus === 1">已通过</el-tag>
                <el-tag v-else-if="scope.row.eStatus === 2" type="info">草稿</el-tag>
                <el-tag v-else-if="scope.row.eStatus === 3" type="warning">待审核</el-tag>
                <el-tag v-else-if="scope.row.eStatus === 4" type="info">未通过</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <!--状态1-->
                <el-button
                  v-if="scope.row.eStatus === 1"
                  size="mini"
                  type="text"
                  icon="el-icon-upload"
                  @click=""
                  v-hasPermi="['system:contract:upload']"
                >上传签订合同
                </el-button>
                <!--状态2-->
                <el-button
                  v-if="scope.row.eStatus === 2|| scope.row.eStatus === 4"
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click=""
                  v-hasPermi="['system:contract:edit']"
                >编辑
                </el-button>
                <el-button
                  v-if="scope.row.eStatus === 2|| scope.row.eStatus === 4"
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click=""
                  v-hasPermi="['system:contract:delete']"
                >删除
                </el-button>
                <!--状态3-->
                <el-button
                  v-if="scope.row.eStatus === 3"
                  size="mini"
                  type="text"
                  @click=""
                >--
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <pagination class="gg"
                      v-show="total2>0"
                      :total="total2"
                      :page.sync="queryParams2.pageNum"
                      :limit.sync="queryParams2.pageSize"
                      @pagination="getList2"
          />
        </el-tab-pane>

        <el-tab-pane label="已签订" name="third">
          <el-table stripe v-loading="loading" :data="contractList3">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="合同编号" align="center" prop="eHcode"/>
            <el-table-column label="合同名称" align="center" prop="eHname"/>
            <el-table-column label="创建人" align="center" prop="createBy"/>
            <el-table-column label="创建日期" align="center" prop="createTime">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="交付日期" align="center" prop="eDeliveryTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.eDeliveryTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  @click=""
                >变更
                </el-button>
                <el-button
                  size="mini"
                  type="text"
                  @click=""
                >补充
                </el-button>
                <el-button
                  size="mini"
                  type="text"
                  @click=""
                >验收
                </el-button>
                <el-button
                  size="mini"
                  type="text"
                  @click=""
                >付款
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <pagination
            v-show="total3>0"
            :total="total3"
            :page.sync="queryParams3.pageNum"
            :limit.sync="queryParams3.pageSize"
            @pagination="getList3"
          />
        </el-tab-pane>

      </el-tabs>
    </div>

  </div>
</template>

<script>
import { listContract, getContract, delContract, addContract, updateContract } from '@/api/system/cm'

export default {
  name: 'Contract',
  data() {
    return {
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
      total1: 0,
      total2: 0,
      total3: 0,
      // 合同表格数据
      contractList1: [],
      contractList2: [],
      contractList3: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams1: {
        pageNum: 1,
        pageSize: 10,
        hid: null,
        eXcode: null,
        eXname: null,
        eWinningPerson: null,
        eHcode: null,
        eHname: null,
        eStatus: null,
        eDeliveryTime: null,
        oHstatus: 1,
        createBy: null,
        createTime: null
      },
      queryParams2: {
        pageNum: 1,
        pageSize: 10,
        hid: null,
        eXcode: null,
        eXname: null,
        eWinningPerson: null,
        eHcode: null,
        eHname: null,
        eStatus: null,
        eDeliveryTime: null,
        oHstatus: 2,
        createBy: null,
        createTime: null
      },
      queryParams3: {
        pageNum: 1,
        pageSize: 10,
        hid: null,
        eXcode: null,
        eXname: null,
        eWinningPerson: null,
        eHcode: null,
        eHname: null,
        eStatus: null,
        eDeliveryTime: null,
        oHstatus: 3,
        createBy: null,
        createTime: null
      },
      // 表单参数
      formData: {
        field101: null,
        field102: undefined
      },
      // 表单校验
      rules: {
        field101: [],
        field102: []
      }
    }
  },
  created() {
    this.getList1()
  },
  methods: {
    handleClick(tab, event) {
      console.log('切换到标签页', tab.name)
      if (tab.name === 'first') {
        // 执行标签页first的查询操作
        this.getList1(),
          console.log('执行标签页1的查询操作')
      } else if (tab.name === 'second') {
        // 执行标签页second的查询操作
        this.getList2(),
          console.log('执行标签页2的查询操作')
      } else {
        // 执行标签页third的查询操作
        this.getList3(),
          console.log('执行标签页3的查询操作')
      }
    },
    query() {
      // 模糊查询按钮点击时的处理逻辑
      console.log('执行模糊查询')
      this.queryParams1.eXcode = this.formData.field101
      this.queryParams1.eXname = this.formData.field102
      this.queryParams2.eXcode = this.formData.field101
      this.queryParams2.eXname = this.formData.field102
      this.queryParams3.eXcode = this.formData.field101
      this.queryParams3.eXname = this.formData.field102
      this.queryParams1.pageNum = 1
      this.queryParams2.pageNum = 1
      this.queryParams3.pageNum = 1
      // 在这里执行模糊查询操作m,
      this.getList1()
      this.getList2()
      this.getList3()
    },
    /**
     * 重置
     */
    resetForm() {
      this.$refs.elForm.resetFields()
    },
    /** 查询待创建合同列表 */
    getList1() {
      this.loading = true
      listContract(this.queryParams1).then(response => {
        this.contractList1 = response.rows
        this.total1 = response.total
        this.loading = false
      })
    },
    /** 查询签订中3合同列表 */
    getList2() {
      this.loading = true
      listContract(this.queryParams2).then(response => {
        console.log(response.row)
        this.contractList2 = response.rows
        this.total1 = response.total
        this.loading = false
      })
    },
    /** 查询已签订合同列表 */
    getList3() {
      this.loading = true
      listContract(this.queryParams3).then(response => {
        console.log(response.row)
        this.contractList3 = response.rows
        this.total1 = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        hid: null,
        eid: null,
        eXcode: null,
        eXname: null,
        eWinningPerson: null,
        eHcode: null,
        eHname: null,
        eStatus: null,
        createBy: null,
        createTime: null,
        eDeliveryTime: null,
        updateBy: null,
        updateTime: null,
        oHstatus: null
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加合同'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const eid = row.eid || this.ids
      getContract(eid).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改合同'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.eid != null) {
            updateContract(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addContract(this.form).then(response => {
              this.$modal.msgSuccess('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const eids = row.eid || this.ids
      this.$modal.confirm('是否确认删除合同编号为"' + eids + '"的数据项？').then(function() {
        return delContract(eids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    }
  }
}
</script>

