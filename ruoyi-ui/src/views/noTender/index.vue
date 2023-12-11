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
                <el-form-item label="项目名称" prop="field108">
                  <el-input v-model="form.field108" placeholder="请输入项目名称" clearable
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

    <!--非招标项目采购方式-->
    <div class="tcl">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="询价" name="first">
          <el-table stripe v-loading="loading" :data="contractList1">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="项目编号" align="center" prop="sCode"/>
            <el-table-column label="项目名称" align="center" prop="sName"/>
            <el-table-column label="公开/邀请" align="center" prop="sName"/>
            <el-table-column label="生成时间" align="center" prop="sName"/>
            <el-table-column label="报价截止时间" align="center" prop="sName"/>
            <el-table-column label="单据状态" align="center" prop="sName"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <!--创建合同-->
                <router-link :to="'add?sid='+scope.row.sid">
                  <el-button
                    size="mini"
                    type="text"
                    @click=""
                  >编辑
                  </el-button>
                  <el-button
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
            <el-table-column label="项目编号" align="center" prop="eHcode"/>
            <el-table-column label="项目名称" align="center" prop="eHname"/>
            <el-table-column label="公开/邀请" align="center" prop="createBy"/>
            <el-table-column label="生成时间" align="center" prop="createTime"/>
            <el-table-column label="报价截止时间" align="center" prop="eStatus"/>
            <el-table-column label="单据状态" align="center" prop="eStatus"/>
            <el-table-column label="报价数量" align="center" prop="eStatus"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <!--创建合同-->
                <router-link :to="'add?sid='+scope.row.sid">
                  <el-button
                    size="mini"
                    type="text"
                    @click=""
                  >编辑
                  </el-button>
                  <el-button
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

        <el-tab-pane label="委托" name="third">
          <el-table stripe v-loading="loading" :data="contractList3">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="项目编号" align="center" prop="eHcode"/>
            <el-table-column label="项目名称" align="center" prop="eHname"/>
            <el-table-column label="公开/邀请" align="center" prop="createBy"/>
            <el-table-column label="生成时间" align="center" prop="createTime"/>
            <el-table-column label="委托单位" align="center" prop="eStatus"/>
            <el-table-column label="单据状态" align="center" prop="eStatus"/>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <!--创建合同-->
                <router-link :to="'add?sid='+scope.row.sid">
                  <el-button
                    size="mini"
                    type="text"
                    @click=""
                  >编辑
                  </el-button>
                  <el-button
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

        <el-tab-pane label="单一来源" name="fourth">
          <el-table stripe v-loading="loading" :data="contractList4">
            <el-table-column type="index" label="序号" align="center"/>
            <el-table-column label="项目编号" align="center" prop="eHcode"/>
            <el-table-column label="项目名称" align="center" prop="eHname"/>
            <el-table-column label="公开/邀请" align="center" prop="createBy"/>
            <el-table-column label="生成时间" align="center" prop="createTime"/>
            <el-table-column label="委托单位" align="center" prop="eStatus"/>
            <el-table-column label="报价轮次" align="center" prop="eStatus"/>
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
export default {
  data() {
    return {
      // 总条数
      total1: 0,
      total2: 0,
      total3: 0,
      total4: 0,
      form: {},
      //表单参数
      queryParams1: {
        pageNum: 1,
        pageSize: 10
      },
      queryParams2: {
        pageNum: 1,
        pageSize: 10
      },
      queryParams3: {
        pageNum: 1,
        pageSize: 10
      },
      queryParams4: {
        pageNum: 1,
        pageSize: 10
      },
      // 表单校验
      rules: {},
      //采购方式
      activeName: 'first'
    }
  },
  methods: {
    //查询
    query() {
    },
    //重置
    resetForm() {
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
      } else {
        this.getList4()// 执行标签页fourth的查询操作
      }
    },
    getList1() {
    },
    getList2() {
    },
    getList3() {
    },
    getList4() {
    }
  }
}
</script>
