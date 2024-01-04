<template>
  <div class="app-container">

    <div>
      <el-row :gutter="15">
        <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="70px" v-if="change===1">
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

        <el-form ref="elForm1" :model="formData1" :rules="rules" size="medium" label-width="70px" v-if="change===2">
          <el-col :span="23">
            <el-row type="flex" justify="start" align="middle" :gutter="30">
              <el-col :span="24">
                <el-form-item label="合同编号" prop="field101">
                  <el-input v-model="formData1.field101" placeholder="请输入合同编号" clearable
                            :style="{width: '100%'}"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="合同名称" prop="field102">
                  <el-input v-model="formData1.field102" placeholder="请输入合同名称" clearable
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
            <el-table-column label="项目编号" align="center" prop="sCode"/>
            <el-table-column label="项目名称" align="center" prop="sName"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <!--创建合同-->
                <router-link :to="'add?sid='+scope.row.sid">
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-folder-add"
                  >创建合同
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
                <el-tag v-else-if="scope.row.eStatus === 4" type="danger">未通过</el-tag>
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
                  @click="UploadHt(scope.row.eid)"
                >上传签订合同
                </el-button>
                <!--状态2-->
                <!--进入合同-->
                <router-link :to="'update?eid='+scope.row.eid">
                  <el-button
                    style="margin-right: 20px"
                    v-if="scope.row.eStatus === 2|| scope.row.eStatus === 4"
                    size="mini"
                    type="text"
                    icon="el-icon-edit"
                    @click=""
                  >编辑
                  </el-button>
                </router-link>
                <el-button
                  v-if="scope.row.eStatus === 2|| scope.row.eStatus === 4"
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="delectHt(scope.row.eid)"
                >删除
                </el-button>
                <!--状态3-->
                <router-link :to="'examine?eid='+scope.row.eid">
                  <el-button
                    v-if="scope.row.eStatus === 3"
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
              4
            </el-table-column>
            <el-table-column label="交付日期" align="center" prop="eDeliveryTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.eDeliveryTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="状态" align="center" prop="eCancel">
              <template slot-scope="scope">
                <el-tag v-if="scope.row.eCancel === 0">正常</el-tag>
                <el-tag v-else-if="scope.row.eCancel === 1" type="danger">作废</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <router-link :to="'details?eid='+scope.row.eid">
                  <el-button
                    style="margin-right: 20px"
                    size="mini"
                    type="text"
                    icon="el-icon-edit"
                  >查看
                  </el-button>
                </router-link>
                <el-button
                  size="mini"
                  v-if="scope.row.eCancel===0"
                  type="text"
                  icon="el-icon-delete"
                  @click="cancel(scope.row)"
                >作废
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
import { listContract, listNoContract, listNoContract2, listTender } from '../../../api/system/cm'
import { delContract, updateoHstatus, HtCancel } from '../../../api/system/addContract'
import { delContract1 } from '../../../api/system/noTender'

export default {
  name: 'Contract',
  data() {
    return {
      change: 1,
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
        sid: null,
        sCode: null,
        sName: null,
        sProjectState: 7
      },
      queryParams2: {
        pageNum: 1,
        pageSize: 10,
        hid: null,
        eHcode: null,
        eHname: null,
        eStatus: null,
        eDeliveryTime: null,
        oHstatus: 2,
        createBy: null,
        createTime: null,
        sProjectState: 7
      },
      queryParams3: {
        pageNum: 1,
        pageSize: 10,
        hid: null,
        eHcode: null,
        eHname: null,
        eStatus: null,
        eDeliveryTime: null,
        oHstatus: 3,
        createBy: null,
        createTime: null,
        eCancel: null,
        sProjectState: 7
      },
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
    //合同作废
    cancel(row) {
      this.$confirm('确定使该合同作废?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        HtCancel({ 'eid': row.eid, 'gid': row.gid }).then(response => {
          console.log(response)
          if (response.msg === '修改成功') {
            this.activeName = 'first'
            this.getList3()
            this.$message({ type: 'info', message: '已作废' })
          } else {
            this.$message({ type: 'info', message: '修改失败' })
          }
        })
      }).catch(() => {
        this.$message({
          type: 'warning',
          message: '已取消'
        })
      })
    },
    //删除合同
    delectHt(eid) {
      this.$confirm('确定删除该合同?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delContract(eid).then(response => {
          console.log(response)
          if (response.msg === '删除成功') {
            this.activeName = 'third'
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            this.activeName = 'first'
            this.getList1()
          } else {
            this.$message.error('删除异常')
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        })
      })
    },
    //上传签订合同
    UploadHt(eid) {
      updateoHstatus({ 'oHstatus': 3, 'eid': eid }).then(response => {
        console.log(response)
        if (response.data > 0) {
          this.activeName = 'third'
          this.$message({
            message: '上传成功',
            type: 'success'
          })
          this.getList3()
        } else {
          this.$message.error('上传异常')
        }
      })
    },
    handleClick(tab, event) {
      console.log('切换到标签页', tab.name)
      if (tab.name === 'first') {
        this.change = 1
        // 执行标签页first的查询操作
        this.getList1(),
          console.log('执行标签页1的查询操作')
      } else if (tab.name === 'second') {
        this.change = 2
        // 执行标签页second的查询操作
        this.getList2(),
          console.log('执行标签页2的查询操作')
      } else {
        // 执行标签页third的查询操作
        this.change = 2
        this.getList3(),
          console.log('执行标签页3的查询操作')
      }
    },
    query() {
      // 模糊查询按钮点击时的处理逻辑
      console.log('执行模糊查询')
      this.queryParams1.sCode = this.formData.field101
      this.queryParams1.sName = this.formData.field102
      this.queryParams1.pageNum = 1
      // 在这里执行模糊查询操作,
      this.getList1()
    },
    query1() {
      // 模糊查询按钮点击时的处理逻辑
      console.log('执行模糊查询')
      this.queryParams2.eHcode = this.formData1.field101
      this.queryParams2.eHname = this.formData1.field102
      this.queryParams3.eHcode = this.formData1.field101
      this.queryParams3.eHname = this.formData1.field102
      this.queryParams2.pageNum = 1
      this.queryParams3.pageNum = 1
      // 在这里执行模糊查询操作m,
      this.getList2()
      this.getList3()
    },
    /**
     * 重置
     */
    resetForm() {
      this.$refs.elForm.resetFields()
    },
    resetForm1() {
      this.$refs.elForm1.resetFields()
    },
    /** 查询待创建合同列表 */
    getList1() {
      this.loading = true
      listTender(this.queryParams1).then(response => {
        this.contractList1 = response.rows
        this.total1 = response.total
        this.loading = false
      })
    },
    /** 查询签订中合同列表 */
    getList2() {
      this.loading = true
      listNoContract(this.queryParams2).then(response => {
        console.log(response.rows)
        this.contractList2 = response.rows
        this.total2 = response.total
        this.loading = false
      })
    },
    /** 查询已签订合同列表 */
    getList3() {
      this.loading = true
      listNoContract(this.queryParams3).then(response => {
        console.log(response.rows)
        this.contractList3 = response.rows
        this.total3 = response.total
        this.loading = false
      })
    }
  }
}
</script>

