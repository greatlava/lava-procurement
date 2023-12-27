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
    <el-row :gutter="10" class="mb8">
      <right-toolbar @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="tenderList" @selection-change="handleSelectionChange">
      <el-table-column label="序号" align="center" prop="orderNum" width="80"/>
      <el-table-column label="项目编号" align="center" prop="sCode" width="250"/>
      <el-table-column label="项目名称" align="center" prop="sName" width="400"/>
      <el-table-column prop="sWay" label="招标方式" align="center" width="200">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.bid_tender_biddingmethod" :value="scope.row.sWay"/>
        </template>
      </el-table-column>
      <el-table-column label="项目负责人" align="center" prop="sLeader" width="200">
        <template slot-scope="scope">
          {{ scope.row.sLeader || '——' }}
        </template>
      </el-table-column>
      <el-table-column label="项目状态" align="center" prop="sProjectState" width="250">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.bid_tender_state" :value="scope.row.sProjectState"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <router-link :to="'/tender/update?type=update&sid='+scope.row.sid">
          <el-button v-if="scope.row.sProjectState === 1"
            size="mini"
            type="text"
            icon="el-icon-edit"
          >修改</el-button>
          </router-link>
          <el-button v-if="scope.row.sProjectState === 1"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
          <router-link :to="'/tender/bidding?type=bidding&sid='+scope.row.sid">
          <el-button v-if="scope.row.sProjectState != 1"
            size="mini"
            type="text"
            icon="el-icon-delete"
          >进入项目</el-button>
          </router-link>
          <router-link :to="'/tender/details?type=details&sid='+scope.row.sid">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-view"
              @click=""
            >查看</el-button>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column v-for="column in tableColumns" :key="column.prop" :label="column.label" :align="column.align" :prop="column.prop" :width="column.width"/>
    </el-table>
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
import { listTender, getTender, delTender, addTender, updateTender } from '@/api/system/tender/tender'

export default {
  dicts:["bid_tender_biddingmethod","bid_tender_state"],
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
        pageNum: 1,
        pageSize: 10,
        sCode: null,
        sName: null,
        sWay: null,
        sLeader: null,
        sProjectState: null,
        sDeadline: null,
        sid:null,
        orderNum:0,
        createTime:null,
        rangeStartTime:null,
        rangeEndTime:null
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
    this.getList();
  },
  methods: {
    query() {
      this.queryParams.sName = this.formData.field108;//项目名称
      let range = this.formData.field101;//查询时间范围
      console.log(range,"range");
     if(range){
       this.queryParams.rangeStartTime= range[0];//时间范围开始
       this.queryParams.rangeEndTime = range[1];//时间范围截至
     }
      this.queryParams.rangeStartTime= null;//时间范围开始
      this.queryParams.rangeEndTime = null;//时间范围截至
      this.handleQuery();//搜索按钮点击方法
      this.reset();//清空查询条件
    },
    /**
     * 重置
     */
    resetForm() {
      this.$refs.elForm.resetFields();
    },
    /** 查询招标项目列表 */
    getList() {
      this.loading = true;
      this.tenderList=[];
      listTender(this.queryParams).then(response => {
        response.rows.forEach((e,i)=>{
          e.orderNum = i+1;
          this.tenderList.push(e);
        })
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        orderNum: 0,
        sCode: null,
        sName: null,
        sWay: null,
        sLeader: null,
        sProjectState: null,
        sDeadline: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
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
    },
    /** 修改按钮操作 */
    // handleUpdate(row) {
    //   this.reset();
    //   const sid = row.sid || this.ids
    // },
    /** 删除按钮操作 */
    handleDelete(row) {
      const sids = row.sid || this.ids;
      this.$modal.confirm('是否确认删除招标项目编号为"' + sids + '"的数据项？').then(function() {
        return delTender(sids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
  }
}
</script>
