<template>
  <div class="app-container">
    <div class="tcl">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="合格供应商" name="first">
          <SupplierQuery></SupplierQuery>
          <el-table stripe v-loading="loading" :data="supplierList" @selection-change="handleSelectionChange">
            <el-table-column label="序号" align="center" prop="hid" width="80"/>
            <el-table-column label="供应商名称" align="center" prop="hName"/>
            <el-table-column label="企业性质" align="center" prop="hQuality"/>
            <el-table-column label="机构类型" align="center" prop="hInstitution"/>
            <el-table-column label="统一社会信用代码" align="center" prop="hCreditCode"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                  size="small"
                  type="primary"
                  @click="handleEdit(scope.$index, scope.row)">查看
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
          />
        </el-tab-pane>

        <el-tab-pane label="不合格供应商" name="second">
          <SupplierQuery></SupplierQuery>
          <el-table v-loading="loading" :data="supplierList" @selection-change="handleSelectionChange">
            <el-table-column label="序号" align="center" prop="orderNum" width="80"/>
            <el-table-column label="供应商名称" align="center" prop="hName"/>
            <el-table-column label="企业性质" align="center" prop="hQuality"/>
            <el-table-column label="机构类型" align="center" prop="hInstitution"/>
            <el-table-column label="统一社会信用代码" align="center" prop="hCreditCode"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            </el-table-column>
          </el-table>
          <pagination class="gg"
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
          />
        </el-tab-pane>

        <el-tab-pane label="供应商不良记录" name="third">
          <SupplierQuery></SupplierQuery>
          <el-table v-loading="loading" :data="supplierList" @selection-change="handleSelectionChange">
            <el-table-column label="序号" align="center" prop="orderNum" width="80"/>
            <el-table-column label="供应商名称" align="center" prop="hName"/>
            <el-table-column label="企业性质" align="center" prop="hQuality"/>
            <el-table-column label="机构类型" align="center" prop="hInstitution"/>
            <el-table-column label="统一社会信用代码" align="center" prop="hCreditCode"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            </el-table-column>
          </el-table>
          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
          />
        </el-tab-pane>

        <el-tab-pane label="供应商准入" name="fourth">
          <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
            <el-row type="flex" justify="start" align="top" :gutter="15">
              <el-form-item label="提交时间" prop="field102">
                <el-date-picker v-model="formData.field102" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                                :style="{width: '100%'}" placeholder="年/月/日" clearable
                ></el-date-picker>
              </el-form-item>
              <el-form-item label="发起人" prop="field103">
                <el-input v-model="formData.field103" placeholder="请输入发起人" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item size="medium">
                <el-button type="primary" @click="query">查询</el-button>
                <el-button @click="resetForm">重置</el-button>
              </el-form-item>
            </el-row>
          </el-form>
          <el-table v-loading="loading" :data="supplierList" @selection-change="handleSelectionChange">
            <el-table-column label="序号" align="center" prop="orderNum" width="80"/>
            <el-table-column label="供应商名称" align="center" prop="hName"/>
            <el-table-column label="企业性质" align="center" prop="hQuality"/>
            <el-table-column label="机构类型" align="center" prop="hInstitution"/>
            <el-table-column label="统一社会信用代码" align="center" prop="hCreditCode"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            </el-table-column>
          </el-table>
          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize"
            @pagination="getList"
          />
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import {listSupplier, getSupplier, delSupplier, addSupplier, updateSupplier} from '@/api/system/supplier'
import SupplierQuery from '@/components/SupplierQuery/index.vue';

export default {
  components: {
    SupplierQuery
  },
  name: 'Supplier',
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
      total: 0,
      // 供应商表格数据
      supplierList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        zrId: null,
        hName: null,
        hCreditCode: null,
        hIncorporation: null,
        hInstitution: null,
        hQuality: null,
        hStartTime: null,
        hJuridical: null,
        hJuridicalIdentity: null,
        hAddress: null,
        hRange: null,
        hDesc: null,
        hCopies: null,
        hJuridicalCopies: null,
        hExpiration: null,
        hBank: null,
        hAccount: null,
        hBankAddress: null,
        hSignPhone: null,
        hSignAddress: null,
        hCapital: null,
        hActualCapital: null,
        hProve: null,
        fStatus: null,
        fOpinion: null,
        fState: null,
        fClassify: null
      },
      // 表单参数
      form: {},
      // 表单校验
      formData: {
        field102: null,
        field103: undefined
      },
      rules: {
        field102: [],
        field103: []
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    query() {
      this.$nextTick(() => {
        this.$refs['elForm'].validate(valid => {
          if (!valid) return
        })
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
    handleClick(tab, event) {
      console.log(tab, event)
    },
    /** 查询供应商列表 */
    getList() {
      this.loading = true
      listSupplier(this.queryParams).then(response => {
        this.supplierList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        zrId: null,
        hName: null,
        hCreditCode: null,
        hIncorporation: null,
        hInstitution: null,
        hQuality: null,
        hStartTime: null,
        hJuridical: null,
        hJuridicalIdentity: null,
        hAddress: null,
        hRange: null,
        hDesc: null,
        hCopies: null,
        hJuridicalCopies: null,
        hExpiration: null,
        hBank: null,
        hAccount: null,
        hBankAddress: null,
        hSignPhone: null,
        hSignAddress: null,
        hCapital: null,
        hActualCapital: null,
        hProve: null,
        fStatus: null,
        fOpinion: null,
        fState: null,
        fClassify: null
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
      this.ids = selection.map(item => item.hid)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = '添加供应商'
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const hid = row.hid || this.ids
      getSupplier(hid).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改供应商'
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {
          if (this.form.hid != null) {
            updateSupplier(this.form).then(response => {
              this.$modal.msgSuccess('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addSupplier(this.form).then(response => {
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
      const hids = row.hid || this.ids
      this.$modal.confirm('是否确认删除供应商编号为"' + hids + '"的数据项？').then(function () {
        return delSupplier(hids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess('删除成功')
      }).catch(() => {
      })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/supplier/export', {
        ...this.queryParams
      }, `supplier_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
