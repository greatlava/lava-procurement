<template>
  <div class="app-container">
    <div class="tcl">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="合格供应商" name="first">
          <el-form ref="elForm1" :model="formData" :rules="rules" size="medium" label-width="100px">
            <el-row type="flex" justify="start" align="middle" :gutter="15">
              <el-form-item label="供应商名称" prop="field101">
                <el-input v-model="formData.field101" placeholder="请输入供应商名称" clearable
                          :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item label="机构类型" prop="field102">
                <el-input v-model="formData.field102" placeholder="请输入机构类型" clearable
                          :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item size="medium">
                <el-button type="primary" @click="query1">查询</el-button>
                <el-button @click="resetForm1">重置</el-button>
              </el-form-item>
            </el-row>
          </el-form>
          <el-table stripe v-loading="loading" :data="supplierList">
            <el-table-column type="index" label="序号" align="center" width="80"/>
            <el-table-column label="供应商名称" align="center" prop="hName"/>
            <el-table-column label="企业性质" align="center" prop="hQuality">
              <template slot-scope="scope">
                {{ scope.row.hQuality ? scope.row.hQuality : '---' }}
              </template>
            </el-table-column>
            <el-table-column label="机构类型" align="center" prop="hInstitution">
              <template slot-scope="scope">
                <el-tag
                  size="small"
                  disable-transitions v-if="scope.row.hInstitution">{{ scope.row.hInstitution }}
                </el-tag>
                <span v-else>---</span>
              </template>
            </el-table-column>
            <el-table-column label="统一社会信用代码" align="center" prop="hCreditCode"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <router-link :to="'detail?hid='+scope.row.hid+'&zr_id=0'">
                  <el-button
                    size="small"
                    type="primary"
                    v-hasRole="['common','controller']">查看
                  </el-button>
                </router-link>
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
          <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
            <el-row type="flex" justify="start" align="middle" :gutter="15">
              <el-form-item label="供应商名称" prop="field103">
                <el-input v-model="formData.field103" placeholder="请输入供应商名称" clearable
                          :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item label="机构类型" prop="field104">
                <el-input v-model="formData.field104" placeholder="请输入机构类型" clearable
                          :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item size="medium">
                <el-button type="primary" @click="query2">查询</el-button>
                <el-button @click="resetForm2">重置</el-button>
              </el-form-item>
            </el-row>
          </el-form>
          <el-table stripe v-loading="loading" :data="noSupplierList">
            <el-table-column type="index" label="序号" align="center" width="80"/>
            <el-table-column label="供应商名称" align="center" prop="hName"/>
            <el-table-column label="企业性质" align="center" prop="hQuality">
              <template slot-scope="scope">
                {{ scope.row.hQuality ? scope.row.hQuality : '---' }}
              </template>
            </el-table-column>
            <el-table-column label="机构类型" align="center" prop="hInstitution">
              <template slot-scope="scope">
                <el-tag
                  size="small"
                  disable-transitions v-if="scope.row.hInstitution">{{ scope.row.hInstitution }}
                </el-tag>
                <span v-else>---</span>
              </template>
            </el-table-column>
            <el-table-column label="统一社会信用代码" align="center" prop="hCreditCode"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <router-link :to="'detail?hid='+scope.row.hid+'&zr_id=0'">
                  <el-button
                    size="small"
                    type="primary"
                    v-hasRole="['common','controller']">查看
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

        <el-tab-pane label="供应商不良记录" name="third">
          <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
            <el-row type="flex" justify="start" align="middle" :gutter="15">
              <el-form-item label="供应商名称" prop="field105">
                <el-input v-model="formData.field105" placeholder="请输入供应商名称" clearable
                          :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item label="机构类型" prop="field106">
                <el-input v-model="formData.field106" placeholder="请输入机构类型" clearable
                          :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item size="medium">
                <el-button type="primary" @click="query3">查询</el-button>
                <el-button @click="resetForm3">重置</el-button>
              </el-form-item>
            </el-row>
          </el-form>
          <el-table stripe v-loading="loading" :data="supplierBadList">
            <el-table-column type="index" label="序号" align="center" width="80"/>
            <el-table-column label="供应商名称" align="center" prop="hName"/>
            <el-table-column label="企业性质" align="center" prop="hQuality">
              <template slot-scope="scope">
                {{ scope.row.hQuality ? scope.row.hQuality : '---'}}
              </template>
            </el-table-column>
            <el-table-column label="机构类型" align="center" prop="hInstitution">
              <template slot-scope="scope">
                <el-tag
                  size="small"
                  disable-transitions v-if="scope.row.hInstitution">{{ scope.row.hInstitution }}
                </el-tag>
                <span v-else>---</span>
              </template>
            </el-table-column>
            <el-table-column label="状态" align="center" prop="fState">
              <template slot-scope="scope">
                <el-tag size="small" v-if="supplierBadList[scope.$index].fState == 2" type="danger">不合格供应商
                </el-tag>
                <el-tag size="small" v-else type="info">黑名单</el-tag>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <router-link :to="'detail?hid='+scope.row.hid+'&zr_id=0'">
                  <el-button
                    size="small"
                    type="primary"
                    v-hasRole="['common','controller']">查看
                  </el-button>
                </router-link>
              </template>
            </el-table-column>
          </el-table>
          <pagination
            v-show="total2>0"
            :total="total2"
            :page.sync="queryParams2.pageNum"
            :limit.sync="queryParams2.pageSize"
            @pagination="getList2"
          />
        </el-tab-pane>

        <el-tab-pane label="供应商准入" name="fourth">
          <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
            <el-row type="flex" justify="start" align="top" :gutter="15">
              <el-form-item label="提交时间" prop="field107">
                <el-date-picker v-model="formData.field107" format="yyyy-MM-dd" value-format="yyyy-MM-dd"
                                :style="{width: '100%'}" placeholder="年/月/日" clearable
                ></el-date-picker>
              </el-form-item>
              <el-form-item label="发起人" prop="field108">
                <el-input v-model="formData.field108" placeholder="请输入发起人" clearable :style="{width: '100%'}">
                </el-input>
              </el-form-item>
              <el-form-item size="medium">
                <el-button type="primary" @click="query4">查询</el-button>
                <el-button @click="resetForm4">重置</el-button>
              </el-form-item>
            </el-row>
          </el-form>
          <el-table stripe v-loading="loading" :data="supplierAccessList">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="业务编号" align="center" prop="zrBnumber">
              <template slot-scope="scope">
                {{ scope.row.zrBnumber }}
              </template>
            </el-table-column>
            <el-table-column label="供应商名称" align="center" prop="hName">
              <template slot-scope="scope">
                {{ scope.row.bsSupplier.hName }}
                <el-tag size="small" style="margin-left: 20px" v-if="scope.row.bsSupplier.fStatus == 2"
                        type="danger">
                  已驳回
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column label="发起人" align="center" prop="zrPromoter"/>
            <el-table-column label="提交时间" align="center" prop="zrTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.zrTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <router-link :to="'process?zr_id='+scope.row.zrId">
                  <el-button
                    size="small"
                    v-if="scope.row.bsSupplier.fStatus == 0"
                    v-hasRole="['controller']">审核
                  </el-button>
                </router-link>
                <router-link :to="'detail?zr_id='+scope.row.zrId+'&hid=0'">
                  <el-button
                    style="margin-left: 10px"
                    size="small"
                    type="primary"
                    v-hasRole="['common','controller']">查看
                  </el-button>
                </router-link>
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
import {
  listSupplier2,
  listSupplier1,
  listSupplier,
  getSupplier,
  delSupplier,
  addSupplier,
  updateSupplier
} from '@/api/system/supplier';
import {listAccess, getAccess, delAccess, addAccess, updateAccess} from "@/api/system/access";
import {parseTime} from "@/utils/ruoyi";
import moment from "moment";

export default {
  components: {},
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
      total1: 0,
      total2: 0,
      total3: 0,
      // 合格供应商表格数据
      supplierList: [],
      // 不合格供应商表格数据
      noSupplierList: [],
      // 供应商不良记录表格数据
      supplierBadList: [],
      // 供应商准入表格数据
      supplierAccessList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        hName: '',
        hInstitution: '',
      },
      queryParams1: {
        pageNum: 1,
        pageSize: 10,
        hName: '',
        hInstitution: '',
      },
      queryParams2: {
        pageNum: 1,
        pageSize: 10,
        hName: '',
        hInstitution: '',
      },
      queryParams3: {
        pageNum: 1,
        pageSize: 10,
        zrPromoter: '',
        zrTime: ''
      },
      // 表单参数
      form: {},
      // 表单校验
      formData: {
        field101: undefined,
        field102: null,
        field103: undefined,
        field104: null,
        field105: undefined,
        field106: null,
        field107: undefined,
        field108: null
      },
      rules: {
        field102: [],
        field103: [],
        field101: [],
        field104: [],
        field105: [],
        field106: [],
        field107: [],
        field108: []
      }
    }
  },
  created() {
    if (sessionStorage.getItem('tag_item') != null) {
      //标签页
      this.activeName = sessionStorage.getItem('tag_item')
      sessionStorage.removeItem('tag_item')

      if (sessionStorage.getItem('num') != null && sessionStorage.getItem('size') != null) {
        this.queryParams.pageNum = JSON.parse(sessionStorage.getItem('num'))
        this.queryParams.pageSize = JSON.parse(sessionStorage.getItem('size'))

        sessionStorage.removeItem('num')
        sessionStorage.removeItem('size')
      }
      if (sessionStorage.getItem('num1') != null && sessionStorage.getItem('size1') != null) {
        this.queryParams1.pageNum = JSON.parse(sessionStorage.getItem('num1'))
        this.queryParams1.pageSize = JSON.parse(sessionStorage.getItem('size1'))

        sessionStorage.removeItem('num1')
        sessionStorage.removeItem('size1')
      }
      if (sessionStorage.getItem('num2') != null && sessionStorage.getItem('size2') != null) {
        this.queryParams2.pageNum = JSON.parse(sessionStorage.getItem('num2'))
        this.queryParams2.pageSize = JSON.parse(sessionStorage.getItem('size2'))

        sessionStorage.removeItem('num2')
        sessionStorage.removeItem('size2')
      }
      if (sessionStorage.getItem('num3') != null && sessionStorage.getItem('size3') != null) {
        this.queryParams3.pageNum = JSON.parse(sessionStorage.getItem('num3'))
        this.queryParams3.pageSize = JSON.parse(sessionStorage.getItem('size3'))

        sessionStorage.removeItem('num3')
        sessionStorage.removeItem('size3')
      }
    }
    this.getList()
    this.getList1()
    this.getList2()
    this.getList3()
  },
  beforeRouteLeave(to, from, next) {
    sessionStorage.setItem('tag_item', this.activeName)

    if (this.activeName == 'first') {
      //存第一个标签页参数数据
      sessionStorage.setItem('num', this.queryParams.pageNum)
      sessionStorage.setItem('size', this.queryParams.pageSize)
    } else if (this.activeName == 'second') {
      //存第二个标签页参数数据
      sessionStorage.setItem('num1', this.queryParams1.pageNum)
      sessionStorage.setItem('size1', this.queryParams1.pageSize)
    } else if (this.activeName == 'third') {
      //存第一个标签页参数数据
      sessionStorage.setItem('num2', this.queryParams2.pageNum)
      sessionStorage.setItem('size2', this.queryParams2.pageSize)
    } else if (this.activeName == 'fourth') {
      //存第一个标签页参数数据
      sessionStorage.setItem('num3', this.queryParams3.pageNum)
      sessionStorage.setItem('size3', this.queryParams3.pageSize)
    }
    next(true)
  },
  methods: {
    parseTime,
    dateFormat(row, column) {
      let date = row[column.property];
      if (date === undefined) {
        return ''
      }
      return moment(date).format("YYYY-MM-DD HH:mm:ss")
    },
    query1() {
      this.loading = true
      this.queryParams.hName = this.formData.field101
      this.queryParams.hInstitution = this.formData.field102
      this.queryParams.pageNum = 1
      listSupplier(this.queryParams).then(response => {
        this.supplierList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    query2() {
      this.loading = true
      this.queryParams1.hName = this.formData.field103
      this.queryParams1.hInstitution = this.formData.field104
      this.queryParams1.pageNum = 1
      listSupplier1(this.queryParams1).then(response => {
        this.noSupplierList = response.rows;
        this.total1 = response.total;
        this.loading = false;
      });
    },
    query3() {
      this.loading = true
      this.queryParams2.hName = this.formData.field105
      this.queryParams2.hInstitution = this.formData.field106
      this.queryParams2.pageNum = 1
      listSupplier2(this.queryParams2).then(response => {
        this.supplierBadList = response.rows;
        this.total2 = response.total;
        this.loading = false;
      });
    },
    query4() {
      this.loading = true
      if (this.formData.field107 == '' || this.formData.field107 == undefined) {
        this.queryParams3.zrTime = null
      } else {
        this.queryParams3.zrTime = this.formData.field107
      }
      this.queryParams3.zrPromoter = this.formData.field108
      this.queryParams3.pageNum = 1
      listAccess(this.queryParams3).then(response => {
        console.log(response)
        this.supplierAccessList = response.rows;
        this.total3 = response.total;
        this.loading = false;
      });
    },
    resetForm1() {
      this.formData.field101 = undefined;
      this.formData.field102 = null
    },
    resetForm2() {
      this.formData.field103 = undefined;
      this.formData.field104 = null
    },
    resetForm3() {
      this.formData.field105 = undefined;
      this.formData.field106 = null
    },
    resetForm4() {
      this.formData.field107 = undefined;
      this.formData.field108 = null
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
    getList1() {
      this.loading = true
      listSupplier1(this.queryParams1).then(response => {
        this.noSupplierList = response.rows;
        this.total1 = response.total;
        this.loading = false;
      });
    },
    getList2() {
      this.loading = true
      listSupplier2(this.queryParams2).then(response => {
        this.supplierBadList = response.rows;
        this.total2 = response.total;
        this.loading = false;
      });
    },
    getList3() {
      this.loading = true
      listAccess(this.queryParams3).then(response => {
        this.supplierAccessList = response.rows;
        this.total3 = response.total;
        this.loading = false;
      });
    }
  }
}
</script>
