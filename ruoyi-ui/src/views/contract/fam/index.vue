<template>
  <div class="app-container">

    <div>
      <el-row :gutter="15">
        <el-form ref="elForm" :model="formData" :rules="formRules" size="medium" label-width="70px" v-if="change===1">
          <el-col :span="23">
            <el-row type="flex" justify="start" align="middle" :gutter="30">
              <el-col :span="24">
                <el-form-item label="框架编号" prop="field101">
                  <el-input v-model="formData.field101" placeholder="请输入框架编号" clearable
                            :style="{width: '100%'}"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="框架名称" prop="field102">
                  <el-input v-model="formData.field102" placeholder="请输入框架名称" clearable
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
        <el-form ref="elForm" :model="formData1" :rules="formRules1" size="medium" label-width="70px" v-if="change===2">
          <el-col :span="23">
            <el-row type="flex" justify="start" align="middle" :gutter="30">
              <el-col :span="24">
                <el-form-item label="协议编号" prop="field101">
                  <el-input v-model="formData1.field101" placeholder="请输入协议编号" clearable
                            :style="{width: '100%'}"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="协议名称" prop="field102">
                  <el-input v-model="formData1.field102" placeholder="请输入协议名称" clearable
                            :style="{width: '100%'}"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item size="medium">
                  <el-button type="primary" @click="query1">查询</el-button>
                  <el-button @click="resetForm1">重置</el-button>
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
            <el-table-column label="框架计划编号" align="center" prop="jhCode"/>
            <el-table-column label="框架计划名称" align="center" prop="jhName"/>
            <el-table-column label="采购方式" align="center" prop="dept"/>
            <el-table-column label="供应商" align="center" prop="bsSupplier.hName"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <!--创建框架协议-->
                <router-link :to="'faAdd?jhId='+scope.row.jhId">
                  <el-button
                      size="mini"
                      type="text"
                  >下单
                  </el-button>
                </router-link>
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

        <el-tab-pane label="已创建" name="second">
          <el-table stripe v-loading="loading" :data="contractList2">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="框架协议编号" align="center" prop="oCode"/>
            <el-table-column label="框架协议名称" align="center" prop="oName"/>
            <el-table-column label="创建人" align="center" prop="createBy"/>
            <el-table-column label="创建日期" align="center" prop="createTime"/>
            <el-table-column label="协议状态" align="center" prop="oHstatus">
              <template slot-scope="scope">
                <el-tag v-if="scope.row.oHstatus === 1" type="info">草稿</el-tag>
                <el-tag v-else-if="scope.row.oHstatus === 2" type="warning">待审核</el-tag>
                <el-tag v-else-if="scope.row.oHstatus === 3" type="success">已通过</el-tag>
                <el-tag v-else-if="scope.row.oHstatus === 4" type="danger">未通过</el-tag>
                <el-tag v-else-if="scope.row.oHstatus === 5">已作废</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <router-link :to="'examineFa?oid='+scope.row.oid">
                  <el-button
                      style="margin-right: 20px"
                      v-if="scope.row.oHstatus !==2"
                      size="mini"
                      type="text"
                      @click=""
                      icon="el-icon-view"
                  >查看
                  </el-button>
                </router-link>
                <!--状态3-->
                <el-button
                    v-if="scope.row.oHstatus === 3"
                    size="mini"
                    type="text"
                    icon="el-icon-upload"
                    @click="cancel1(scope.row.oid)"
                    v-hasPermi="['system:contract:upload']"
                >作废
                </el-button>
                <!--状态2|4-->
                <router-link :to="'updateFa?oid='+scope.row.oid">
                  <el-button
                      style="margin-right: 20px"
                      v-if="scope.row.oHstatus === 1|| scope.row.oHstatus === 4"
                      size="mini"
                      type="text"
                      icon="el-icon-edit"
                      v-hasPermi="['system:contract:edit']"
                  >编辑
                  </el-button>
                </router-link>
                <el-button
                    v-if="scope.row.oHstatus === 1|| scope.row.oHstatus === 4"
                    size="mini"
                    type="text"
                    icon="el-icon-delete"
                    @click="deleteXy(scope.row.oid)"
                    v-hasPermi="['system:contract:delete']"
                >删除
                </el-button>
                <!--状态2-->
                <router-link :to="'examineFa?oid='+scope.row.oid">
                  <el-button
                      v-if="scope.row.oHstatus === 2"
                      size="mini"
                      type="text"
                      @click=""
                  >审核
                  </el-button>
                </router-link>
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
      </el-tabs>
    </div>
  </div>
</template>

<script>
import { getFrameworkPlan1 } from '../../../api/system/frameworkPlan'
import { delXy, HtCancel, listManagement, XyCancel, xYCancel, XyCancelByOid } from '../../../api/system/addContract'

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
      Pmethod: [],
      // 总条数
      total1: 0,
      total2: 0,
      // 合同表格数据
      contractList1: [],
      contractList2: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams1: {
        pageNum: 1,
        pageSize: 10,
        oid: null,
        jhCode: null,
        jhName: null,
        jhYu: null,
        dept: null,
        jhStatus: 2,
        jhPerson: null,
        jhPmethod: null,
        jhFounder: null,
        hid: null,
        businessType: null
      },
      queryParams2: {
        pageNum: 1,
        pageSize: 10,
        oCode: null,
        oName: null
      },
      //根据不同的表单显示不同的查询对象
      change: 1,
      // 表单参数
      formData: {
        field101: null,
        field102: undefined
      },
      // 表单参数
      formData1: {
        field101: null,
        field102: undefined
      },
      // 表单校验
      formRules: {
        field101: null,
        field102: undefined
      },
      // 表单校验
      formRules1: {
        field101: null,
        field102: undefined
      }
    }
  },
  created() {
    this.getList1()
  },
  methods: {
    //协议作废
    cancel1(oid) {
      this.$confirm('确定使该协议作废?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let k1 = null
        let k2 = null
        Promise.all([XyCancel(oid), XyCancelByOid(oid)]).then(responses => {
          console.log(responses[0])
          k1 = responses[0].msg
          console.log(responses[1])
          k2 = responses[1].msg
          // console.log(k1, k2)
          if (k1 === '修改成功' && k2 === '修改成功') {
            this.$nextTick(() => {
              this.activeName = 'second'
              this.getList2()
              this.$message({ type: 'success', message: '已作废' })
            })
          } else {
            this.$message({ type: 'error', message: '修改失败' })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        })
      })
    },
    //删除协议
    deleteXy(oid) {
      this.$confirm('确定删除该协议?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let k1 = null
        let k2 = null
        Promise.all([delXy(oid), XyCancelByOid(oid)]).then(responses => {
          console.log(responses[0])
          k1 = responses[0].msg
          console.log(responses[1])
          k2 = responses[1].msg
          console.log(k1, k2)
          if (k1 === '删除成功' && k2 === '修改成功') {
            this.$nextTick(() => {
              this.activeName = 'second'
              this.getList2()
              this.$message({ type: 'success', message: '已删除' })
            })
          } else {
            this.$message({ type: 'error', message: '删除失败' })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        })
      })
    },
    handleClick(tab) {
      console.log('切换到标签页', tab.name)
      if (tab.name === 'first') {
        this.change = 1
        this.getList1()// 执行标签页first的查询操作
      } else {
        this.change = 2
        this.getList2()// 执行标签页second的查询操作
      }
    },
    query() {
      // 模糊查询按钮点击时的处理逻辑
      console.log('执行模糊查询')
      this.queryParams1.jhCode = this.formData.field101
      this.queryParams1.jhName = this.formData.field102
      this.queryParams1.pageNum = 1
      this.getList1()
    },
    query1() {
      this.queryParams2.oCode = this.formData1.field101
      this.queryParams2.oName = this.formData1.field102
      this.queryParams2.pageNum = 1
      this.getList2()
    },
    /**
     * 重置
     */
    resetForm() {
      this.$refs.elForm.resetFields()
      this.query()
    },
    resetForm1() {
      this.$refs.elForm.resetFields()
      this.query1()
    },
    /** 查询完成的框架计划 */
    getList1() {
      this.loading = true
      getFrameworkPlan1(this.queryParams1).then(response => {
        // console.log(response)
        this.contractList1 = response.rows
        this.total1 = response.total
        this.loading = false
      })
    },
    /** 查询已签订的框架协议 */
    getList2() {
      this.loading = true
      listManagement(this.queryParams2).then(response => {
        // console.log(response)
        this.contractList2 = response.rows
        this.total2 = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    }
  }
}
</script>
