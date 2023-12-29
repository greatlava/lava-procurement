<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="合同名称" prop="eHname">
        <el-input
          v-model="queryParams.eHname"
          placeholder="请输入合同名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商名称" prop="hName" label-width="100px">
        <el-input
          v-model="queryParams.hName"
          placeholder="请输入供应商名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="风险项" prop="rmItem">
        <el-select v-model="queryParams.rmItem" placeholder="请选择风险项"
                   @keyup.enter.native="handleQuery">
          <el-option v-for="dict in rm_items"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="风险处理状态" prop="rmStatus" label-width="110px">
        <el-select v-model="queryParams.rmStatus" placeholder="请选择风险处理状态"
                   @keyup.enter.native="handleQuery">
          <el-option v-for="dict in rm_Status"
                     :key="dict.dictValue"
                     :label="dict.dictLabel"
                     :value="dict.dictValue">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasRole="['common','controller']"
        >新增
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="monitorList" stripe>
      <el-table-column type="index" label="序号" align="center"/>
      <el-table-column label="合同编号" align="center" prop="bsContract.eHcode"/>
      <el-table-column label="合同名称" align="center" prop="bsContract.eHname"/>
      <el-table-column label="供应商名称" align="center" prop="bsSupplier.hName"/>
      <el-table-column label="风险说明" align="center" prop="rmIllustrate"/>
      <el-table-column label="发生时间" align="center" prop="rmHappenDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.rmHappenDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="风险项" align="center" prop="rmItem">
        <template slot-scope="scope">
          <el-tag size="small" v-if="monitorList[scope.$index].rmItem == 1" type="danger">合同履约点逾期风险
          </el-tag>
          <el-tag size="small" v-if="monitorList[scope.$index].rmItem == 2" type="danger">合同变更风险
          </el-tag>
          <el-tag size="small" v-if="monitorList[scope.$index].rmItem == 3" type="danger">收款时间点逾期风险
          </el-tag>
          <el-tag size="small" v-if="monitorList[scope.$index].rmItem == 4" type="danger">故意逃避监控风险
          </el-tag>
          <el-tag size="small" v-if="monitorList[scope.$index].rmItem == 5" type="danger">外部签约方异常风险
          </el-tag>
          <el-tag size="small" v-if="monitorList[scope.$index].rmItem == 6" type="danger">合同履约率风险
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="风险处理状态" align="center" prop="rmStatus">
        <template slot-scope="scope">
          <el-tag size="small" v-if="monitorList[scope.$index].rmStatus == 1" type="danger">未处理
          </el-tag>
          <el-tag size="small" v-if="monitorList[scope.$index].rmStatus == 2" type="success">已处理
          </el-tag>
          <el-tag size="small" v-if="monitorList[scope.$index].rmStatus == 3" type="info">已忽略
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasRole="['common','controller']"
          >修改
          </el-button>
          <router-link :to="'riskHandling?rm_id='+scope.row.rmId">
            <el-button
              size="small"
              icon="el-icon-document-checked"
              style="margin-left: 10px"
              v-hasRole="['controller']"
            >风险处理
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

    <!-- 添加或修改供应商风险监控对话框 -->
    <el-dialog width="30%" :title="title" :visible.sync="open" append-to-body>
      <el-dialog
        title="选择合同"
        width="50%"
        :visible.sync="innerVisible"
        append-to-body @close="innerVisible = false">
        <el-table
          ref="singleTable"
          :data="contractData"
          highlight-current-row
          @current-change="handleCurrentChange"
          style="width: 100%">
          <el-table-column
            type="index"
            width="60">
          </el-table-column>
          <el-table-column
            property="eHcode"
            label="合同编号"
            width="185">
          </el-table-column>
          <el-table-column
            property="eHname"
            label="合同名称"
            width="185">
          </el-table-column>
          <el-table-column
            property="bsSupplier.hName"
            label="供应商名称">
          </el-table-column>
        </el-table>
        <pagination
          v-show="total1>0"
          :total="total1"
          :page.sync="contract.pageNum"
          :limit.sync="contract.pageSize"
          @pagination="innerShow"
        />
      </el-dialog>

      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="合同名称" prop="eHname">
          <el-input v-model="contractName" placeholder="请选择合同" @focus="innerShow"/>
        </el-form-item>
        <el-form-item label="风险说明" prop="rmIllustrate">
          <el-input type="textarea" v-model="form.rmIllustrate" placeholder="请输入风险说明"/>
        </el-form-item>
        <el-form-item label="发生日期" prop="rmHappenDate">
          <el-date-picker clearable
                          v-model="form.rmHappenDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择发生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="风险项" prop="rmItem">
          <el-select v-model="form.rmItem" placeholder="请选择风险项">
            <el-option v-for="dict in rm_items"
                       :key="dict.dictValue"
                       :label="dict.dictLabel"
                       :value="Number(dict.dictValue)">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listMonitor, getMonitor, delMonitor, addMonitor, updateMonitor} from "@/api/system/riskMonitor";
import {listContract} from "@/api/system/contract";
import {selectDictLabel, selectDictLabels} from "@/utils/ruoyi";

export default {
  name: "Monitor",
  data() {
    return {
      contract: {
        eCancel: 0,
        oHstatus: 3,
        pageNum: 1,
        pageSize: 10
      },
      //合同总数
      total1: 0,
      //合同数据
      contractData: [],
      //内嵌对话框状态
      innerVisible: false,
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 供应商风险监控表格数据
      monitorList: [],
      //风险项字典数据
      rm_items: [],
      //风险处理状态字典数据
      rm_Status: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        eHname: null,
        hName: null,
        rmItem: null,
        rmStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      contractName: null
    };
  },
  created() {
    if (sessionStorage.getItem('a1') != null && sessionStorage.getItem('a2')) {
      this.queryParams.pageNum = JSON.parse(sessionStorage.getItem('a1'))
      this.queryParams.pageSize = JSON.parse(sessionStorage.getItem('a2'))
      sessionStorage.removeItem('a1')
      sessionStorage.removeItem('a2')
    }
    this.getList();
    //查询风险项字典
    this.getDicts("bs_risk_monitoring_item").then(res => {
      this.rm_items = res.data;
    });
    //查询风险处理状态字典
    this.getDicts("bs_risk_monitoring_status").then(res => {
      this.rm_Status = res.data;
    });
  },
  beforeRouteLeave(to, from, next) {
    sessionStorage.setItem('a1', this.queryParams.pageNum)
    sessionStorage.setItem('a2', this.queryParams.pageSize)
    next(true)
  },
  methods: {
    /** 查询供应商风险监控列表 */
    getList() {
      this.loading = true;
      console.log(this.queryParams);
      listMonitor(this.queryParams).then(response => {
        console.log(response)
        this.monitorList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    handleCurrentChange(val) {
      console.log(val)
      this.contractName = val.eHname
      this.form.eid = val.eid
      this.innerVisible = false
    },
    // 表单重置
    reset() {
      this.form = {
        rmId: null,
        eid: null,
        rmIllustrate: null,
        rmHappenDate: null,
        rmItem: null,
        rmStatus: 1,
      };
      this.resetForm("form");
    },
    innerShow() {
      listContract(this.contract).then(res => {
        console.log(res)
        this.contractData = res.rows;
        this.total1 = res.total;
      });
      this.innerVisible = true
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.contractName = null
      this.reset();
      this.open = true;
      this.title = "添加供应商风险监控";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const rmId = row.rmId
      getMonitor(rmId).then(response => {
        console.log(response)
        this.form = response.data;
        this.contractName = row.bsContract.eHname;
        this.open = true;
        this.title = "修改供应商风险监控";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.contractName == null || this.contractName == '' || this.form.rmIllustrate == null || this.form.rmIllustrate == '' || this.form.rmHappenDate == null || this.form.rmHappenDate == '' || this.form.rmItem == null || this.form.rmItem == '') {
            this.$message.error("信息不能为空")
          } else {
            if (this.form.rmId != null) {
              updateMonitor(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addMonitor(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        }
      });
    }
  }
};
</script>
