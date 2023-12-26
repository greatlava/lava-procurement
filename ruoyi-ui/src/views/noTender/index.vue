<template>
  <div class="app-container">
    <div>
      <el-row :gutter="15">
        <el-form ref="elForm" :model="form" :rules="rules" size="medium" label-width="70px">
          <el-col :span="23">
            <el-row type="flex" justify="start" align="middle" :gutter="30">
              <el-col :span="24">
                <el-form-item label="时间" prop="field101">
                  <el-date-picker type="daterange" v-model="form.field101" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" :style="{width: '100%'}" start-placeholder="年/月/日"
                                  end-placeholder="年/月/日" range-separator="至" clearable
                  ></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="项目名称" prop="field102">
                  <el-input v-model="form.field102" placeholder="请输入项目名称" clearable
                            :style="{width: '100%'}"
                  ></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item size="medium">
                  <el-button type="primary" @click="query">查询</el-button>
                  <el-button @click="resetQuery">重置</el-button>
                </el-form-item>
              </el-col>
            </el-row>
          </el-col>
        </el-form>
      </el-row>
    </div>

    <!--非招标项目采购方式-->
    <div class="tcl">
      <el-tabs v-model="activeName" @tab-click="handleClick">

        <el-tab-pane label="询价" name="first">
          <el-table stripe v-loading="loading" :data="contractList1">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="项目编号" align="center" prop="gCode"/>
            <el-table-column label="项目名称" align="center" prop="gName"/>
            <el-table-column label="公开/邀请" align="center" prop="gIsPublic">
              <template slot-scope="scope">
                <span v-if="scope.row.gIsPublic===0">公开</span>
                <span v-else-if="scope.row.gIsPublic===1">邀请</span>
              </template>
            </el-table-column>
            <el-table-column label="生成时间" align="center" prop="gSpawnTime"/>
            <el-table-column label="报价截止时间" align="center" prop="gDeadline">
              <template slot-scope="scope">
                <span v-if="scope.row.gDeadline!=null">{{ scope.row.gDeadline }}</span>
                <span v-else>- -</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <router-link :to="'details?gid='+scope.row.gid">
                  <el-button
                      v-if="scope.row.gDeadline!=null"
                      size="mini"
                      type="text"
                  >进入项目
                  </el-button>
                </router-link>
                <router-link :to="'addRFQ?gid='+scope.row.gid">
                  <el-button
                      v-if="scope.row.gDeadline==null"
                      size="mini"
                      type="text"
                  >发布项目
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

        <el-tab-pane label="竞争性谈判" name="second">
          <el-table stripe v-loading="loading" :data="contractList2">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="项目编号" align="center" prop="gCode"/>
            <el-table-column label="项目名称" align="center" prop="gName"/>
            <el-table-column label="公开/邀请" align="center" prop="gIsPublic">
              <template slot-scope="scope">
                <span v-if="scope.row.gIsPublic===0">公开</span>
                <span v-else-if="scope.row.gIsPublic===1">邀请</span>
              </template>
            </el-table-column>
            <el-table-column label="生成时间" align="center" prop="gSpawnTime"/>
            <el-table-column label="报价截止时间" align="center" prop="gDeadline"/>
            <el-table-column label="报价数量" align="center" prop="gCount"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                    v-if="scope.row.gStatus === 1"
                    size="mini"
                    type="text"
                    @click=""
                >编辑
                </el-button>
                <el-button
                    v-if="scope.row.gStatus === 2||scope.row.gStatus === 3"
                    size="mini"
                    type="text"
                    @click=""
                >进入项目
                </el-button>
                <el-button
                    size="mini"
                    type="text"
                    @click=""
                >查看
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

        <el-tab-pane label="委托" name="third">
          <el-table stripe v-loading="loading" :data="contractList3">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="项目编号" align="center" prop="gCode"/>
            <el-table-column label="项目名称" align="center" prop="gName"/>
            <el-table-column label="公开/邀请" align="center" prop="gIsPublic">
              <template slot-scope="scope">
                <span v-if="scope.row.gIsPublic===0">公开</span>
                <span v-else-if="scope.row.gIsPublic===1">邀请</span>
              </template>
            </el-table-column>
            <el-table-column label="生成时间" align="center" prop="gSpawnTime"/>
            <el-table-column label="委托单位" align="center" prop="gUnit"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                    v-if="scope.row.gStatus === 1"
                    size="mini"
                    type="text"
                    @click=""
                >编辑
                </el-button>
                <el-button
                    v-if="scope.row.gStatus === 2||scope.row.gStatus === 3"
                    size="mini"
                    type="text"
                    @click=""
                >进入项目
                </el-button>
                <el-button
                    size="mini"
                    type="text"
                    @click=""
                >查看
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

        <el-tab-pane label="单一来源" name="fourth">
          <el-table stripe v-loading="loading" :data="contractList4">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="项目编号" align="center" prop="gCode"/>
            <el-table-column label="项目名称" align="center" prop="gName"/>
            <el-table-column label="公开/邀请" align="center" prop="gIsPublic">
              <template slot-scope="scope">
                <span v-if="scope.row.gIsPublic===0">公开</span>
                <span v-else-if="scope.row.gIsPublic===1">邀请</span>
              </template>
            </el-table-column>
            <el-table-column label="生成时间" align="center" prop="gSpawnTime"/>
            <el-table-column label="报价轮次" align="center" prop="gRounds"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                    v-if="scope.row.gStatus === 1"
                    size="mini"
                    type="text"
                    @click=""
                >编辑
                </el-button>
                <el-button
                    v-if="scope.row.gStatus === 2||scope.row.gStatus === 3"
                    size="mini"
                    type="text"
                    @click=""
                >进入项目
                </el-button>
                <el-button
                    size="mini"
                    type="text"
                    @click=""
                >查看
                </el-button>
              </template>
            </el-table-column>
          </el-table>
          <pagination
              v-show="total4>0"
              :total="total4"
              :page.sync="queryParams4.pageNum"
              :limit.sync="queryParams4.pageSize"
              @pagination="getList4"
          />
        </el-tab-pane>

      </el-tabs>
    </div>

  </div>
</template>

<script>
import { listPlan, listPro } from '../../api/system/noTender'

export default {
  data() {
    return {
      dicts: ['ppm_procurement_plan'],
      // 遮罩层
      loading: true,
      contractList1: [],
      contractList2: [],
      contractList3: [],
      contractList4: [],
      // 总条数
      total1: 0,
      total2: 0,
      total3: 0,
      total4: 0,
      form: {},
      form1: {},
      //表单参数
      queryParams1: {
        pageNum: 1,//页数
        pageSize: 10,//每页10条
        xyId: null,//采购计划序号
        gCode: null,//非招标项目编号
        gName: null,//非招标项目名称
        gIsPublic: null,//公开/邀请
        gSpawnTime: null,//生成时间
        gDeadline: null,//报价截至时间
        gStatus: null,//单据状态
        gTendertype: 1//业务类型
      },
      queryParams2: {
        pageNum: 1,//页数
        pageSize: 10,//每页10条
        xyId: null,//采购计划序号
        gCode: null,//非招标项目编号
        gName: null,//非招标项目名称
        gIsPublic: null,//公开/邀请
        gSpawnTime: null,//生成时间
        gDeadline: null,//报价截至时间
        gStatus: null,//单据状态
        gCount: null,//报价数量
        gTendertype: 2//业务类型
      },
      queryParams3: {
        pageNum: 1,//页数
        pageSize: 10,//每页10条
        xyId: null,//采购计划序号
        gCode: null,//非招标项目编号
        gName: null,//非招标项目名称
        gIsPublic: null,//公开/邀请
        gSpawnTime: null,//生成时间
        gDeadline: null,//报价截至时间
        gStatus: null,//单据状态
        gUnit: null,//委托单位
        gTendertype: 3//业务类型
      },
      queryParams4: {
        pageNum: 1,//页数
        pageSize: 10,//每页10条
        xyId: null,//采购计划序号
        gCode: null,//非招标项目编号
        gName: null,//非招标项目名称
        gIsPublic: null,//公开/邀请
        gSpawnTime: null,//生成时间
        gDeadline: null,//报价截至时间
        gStatus: null,//单据状态
        gRounds: null,//报价轮次
        gTendertype: 4//业务类型
      },
      // 表单校验
      rules: {},
      rules1: {},
      //采购方式
      activeName: 'first'
    }
  },
  created() {
    this.getList1()
  },
  methods: {
    //查询
    query() {
      // 模糊查询按钮点击时的处理逻辑
      console.log('执行模糊查询')
      if (this.form.field101 != null) {
        this.queryParams1.gSpawnTime = this.form.field101[0]
        this.queryParams1.gDeadline = this.form.field101[1]
        this.queryParams2.gSpawnTime = this.form.field101[0]
        this.queryParams2.gDeadline = this.form.field101[1]
        this.queryParams3.gSpawnTime = this.form.field101[0]
        this.queryParams3.gDeadline = this.form.field101[1]
        this.queryParams4.gSpawnTime = this.form.field101[0]
        this.queryParams4.gDeadline = this.form.field101[1]
      } else {
        this.queryParams1.gSpawnTime = null
        this.queryParams1.gDeadline = null
        this.queryParams2.gSpawnTime = null
        this.queryParams2.gDeadline = null
        this.queryParams3.gSpawnTime = null
        this.queryParams3.gDeadline = null
        this.queryParams4.gSpawnTime = null
        this.queryParams4.gDeadline = null
      }
      this.queryParams1.gName = this.form.field102
      this.queryParams1.pageNum = 1
      this.getList1()
      this.queryParams2.gName = this.form.field102
      this.queryParams2.pageNum = 1
      this.getList2()
      this.queryParams3.gName = this.form.field102
      this.queryParams3.pageNum = 1
      this.getList3()
      this.queryParams4.gName = this.form.field102
      this.queryParams4.pageNum = 1
      this.getList4()
    },
    //重置
    resetQuery() {
      this.$refs.elForm.resetFields()
      this.query()
    },
    //切换标签页
    handleClick(tab, event) {
      console.log('切换到标签页', tab.name)
      if (tab.name === 'first') {
        this.getList1()// 执行标签页first的查询操作
      } else if (tab.name === 'second') {
        this.getList2()// 执行标签页second的查询操作
      } else if (tab.name === 'third') {
        this.getList3()// 执行标签页third的查询操作
      } else if (tab.name === 'fourth') {
        this.getList4()// 执行标签页fourth的查询操作
      }
    },
    //查询询价信息
    getList1() {
      this.loading = true
      listPro(this.queryParams1).then(response => {
        this.contractList1 = response.rows
        this.total1 = response.total
        this.loading = false
      })
    }
    ,
    getList2() {
      this.loading = true
      listPro(this.queryParams2).then(response => {
        this.contractList2 = response.rows
        this.total2 = response.total
        this.loading = false
      })
    }
    ,
    getList3() {
      this.loading = true
      listPro(this.queryParams3).then(response => {
        this.contractList3 = response.rows
        this.total3 = response.total
        this.loading = false
      })
    }
    ,
    getList4() {
      this.loading = true
      listPro(this.queryParams4).then(response => {
        this.contractList4 = response.rows
        this.total4 = response.total
        this.loading = false
      })
    }
  }
}
</script>
