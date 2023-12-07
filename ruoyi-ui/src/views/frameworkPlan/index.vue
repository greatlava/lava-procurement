<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="框架计划编码" prop="jhCode">
        <el-input
          v-model="queryParams.jhCode"
          placeholder="请输入框架计划编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计划名称" prop="jhName">
        <el-input
          v-model="queryParams.jhName"
          placeholder="请输入计划名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
<!--        <router-link to="frameworkPlan"></router-link>-->
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增
        </el-button>
      </el-form-item>
    </el-form>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="待提交" name="first">
        <el-table v-loading="loading" :data="framewokPlanList">
          <el-table-column label="框架计划编码" align="center" prop="jhCode"/>
          <el-table-column label="框架计划名称" align="center" prop="jhName"/>
          <el-table-column label="预算(含税)/元" align="center" prop="jhYu"/>
          <el-table-column label="创建部门" align="center" prop="dept"/>
          <el-table-column label="计划状态" align="center" prop="jhStatus"/>
          <el-table-column label="审批人" align="center" prop="jhPerson"/>
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
              <el-button
                size="mini"
                type="text"
                @click="handleClick(scope.row)"
                v-hasPermi="['system:plan:remove']"
              >查看
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="待审批" name="second">
        <el-table v-loading="loading" :data="framewokPlanList">
          <el-table-column label="框架计划编码" align="center" prop="jhCode"/>
          <el-table-column label="框架计划名称" align="center" prop="jhName"/>
          <el-table-column label="预算(含税)/元" align="center" prop="jhYu"/>
          <el-table-column label="创建部门" align="center" prop="dept"/>
          <el-table-column label="计划状态" align="center" prop="jhStatus"/>
          <el-table-column label="审批人" align="center" prop="jhPerson"/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['system:plan:edit']"
              >审批
              </el-button>
              <el-button
                size="mini"
                type="text"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:plan:remove']"
              >查看
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="已完成" name="third">
        <el-table v-loading="loading" :data="framewokPlanList">
          <el-table-column label="框架计划编码" align="center" prop="jhCode"/>
          <el-table-column label="框架计划名称" align="center" prop="jhName"/>
          <el-table-column label="预算(含税)/元" align="center" prop="jhYu"/>
          <el-table-column label="创建部门" align="center" prop="dept"/>
          <el-table-column label="计划状态" align="center" prop="jhStatus"/>
          <el-table-column label="审批人" align="center" prop="jhPerson"/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:plan:remove']"
              >查看
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 添加或修改框架计划对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form :model="form" ref="form"  :rules="rules" label-position="left" label-width="120px"
               size="medium" @submit.native.prevent>
        <div class="static-content-item" v-show="false">
          <el-divider direction="horizontal"></el-divider>
        </div>
        <div class="card-container">
            <el-row>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="计划编码" prop="计划编码" class="label-center-align">
                  <el-input v-model="form.jhCode" :disabled="true" type="text" placeholder="自动生成"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="计划名称" prop="jhName" class="required label-center-align">
                  <el-input v-model="form.jhName" type="text" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="业务类型" prop="BusinessType" class="required label-center-align">
                  <el-select  v-model="form.BusinessType" class="input full-width-input" clearable>
                    <el-option v-for="(item, index) in BusinessTypeOptions" :key="index" :label="item.label"
                               :value="item.value" :disabled="item.disabled"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="采购方式" prop="jhPmethod" class="required label-center-align">
                  <el-select v-model="form.jhPmethod" class="input full-width-input" clearable>
                    <el-option v-for="(item, index) in jhPmethodOptions" :key="index" :label="item.label"
                               :value="item.value" :disabled="item.disabled"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="计划预算" prop="jhYu" class="required label-center-align">
                  <el-select v-model="form.jhYu" class="input full-width-input" clearable>
                    <el-option v-for="(item, index) in jhYuOptions" :key="index" :label="item.label"
                               :value="item.value" :disabled="item.disabled"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="供应商" prop="supplier" class="required label-center-align">
                  <el-select v-model="form.supplier" class="input full-width-input" clearable>
                    <el-option v-for="(item, index) in supplierOptions" :key="index" :label="item.label"
                               :value="item.value" :disabled="item.disabled"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="创建人" prop="jhFounder" class="required label-center-align">
                  <el-input v-model="form.jhFounder" type="text" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12" class="grid-cell">
                <el-form-item label="创建部门" prop="dept" class="required label-center-align">
                  <el-input v-model="form.dept" type="text" clearable></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24" class="grid-cell">
                <el-form-item label="上传附件" prop="annex" class="label-center-align">
                  <template #label><span class="custom-label"><i class="el-icon-upload"></i>上传附件
                </span></template>
                  <el-upload :file-list="annexFileList" :headers="annexUploadHeaders" :data="annexUploadData"
                             list-type="picture-card" show-file-list :limit="3"> <template #default><i
                    class="el-icon-plus"></i></template></el-upload>
                </el-form-item>
              </el-col>
              <el-col :span="24" class="grid-cell">
                <el-form-item label="备注" prop="notes" class="label-center-align">
                  <el-input type="textarea" v-model="form.notes" rows="3"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
        </div>
        <div class="static-content-item" v-show="false">
          <el-divider direction="horizontal"></el-divider>
        </div>
        <div class="card-container">
            <div class="static-content-item">
              <el-button @click="insertItems" icon="el-icon-plus" type="primary" size="small">添加项目</el-button>
            </div>
          <el-table ref="elTable" max-height="250" v-loading="loading" :data="device" border style="margin-top: 20px">
            <el-table-column label="序号" align="center">
              <template slot-scope="scope">
                <span>{{ scope.$index + 1 }}</span>
              </template>
            </el-table-column>
            <el-table-column label="行项目编号" align="center" prop="vCode" width="150">
              <template slot-scope="scope">
                <span v-if="form.aid==null">自动生成</span>
                <el-input v-model="scope.row.vCode" class="borderNone" disabled></el-input>
              </template>
            </el-table-column>
            <el-table-column label="物料编号" align="center" prop="tid" width="150">
              <template slot-scope="scope">
                <span>{{ scope.row.tid }}</span>
                <i class="el-icon-search" @click="search(scope.$index)"></i>
              </template>
            </el-table-column>
            <el-table-column label="数量" align="center" prop="vCount" width="230">
              <template slot-scope="scope">
                <el-input-number :step="1" step-strictly v-model="scope.row.vCount"></el-input-number>
              </template>
            </el-table-column>
            <el-table-column label="采购人" align="center" prop="vPerson" width="120">
              <template slot-scope="scope">
                <el-input class="borderNone" v-model="scope.row.vPerson"></el-input>
              </template>
            </el-table-column>
            <el-table-column label="交付时间" align="center" prop="vDeliveryTime" width="200">
              <template slot-scope="scope">
                <el-input class="borderNone" v-model="scope.row.vDeliveryTime"></el-input>
              </template>
            </el-table-column>
            <el-table-column label="交付地点" align="center" prop="vDeliveryArea" width="180">
              <template slot-scope="scope">
                <el-input class="borderNone" v-model="scope.row.vDeliveryArea">
                  {{ parseTime(scope.row.tDate, '{y}-{m}-{d}') }}
                </el-input>
              </template>
            </el-table-column>
            <el-table-column label="需求说明" align="center" prop="vIllustrate" width="160">
              <template slot-scope="scope">
                <el-input class="borderNone" v-model="scope.row.vIllustrate"></el-input>
              </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row,scope.$index)"
                  v-hasPermi="['system:record:remove']"
                >删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import { listPlan, getPlan, delPlan, addPlan, updatePlan } from "@/api/system/plan";
import {getFrameworkPlan} from "@/api/system/frameworkPlan"

export default {
  name: "Plan",
  data() {
    return {
      activeName: "first",
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
      // 框架计划表格数据
      framewokPlanList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        oid: null,
        jhCode: null,
        jhName: null,
        jhYu: null,
        dept: null,
        jhStatus: null,
        jhPerson: null,
        jhPmethod: null
      },
      // 表单参数
      form: {},
      BusinessTypeOptions: [],
      jhPmethodOptions: [],
      jhYuOptions: [],
      supplierOptions: [],
      annexFileList: [],
      annexUploadHeaders: {},
      annexUploadData: {},
      items:[],
      // 表单校验
      rules: {
        jhName: [{
          required: true,
          message: '计划名称值不可为空',
        }],
        BusinessType: [{
          required: true,
          message: '业务类型值不可为空',
        }],
        jhPmethod: [{
          required: true,
          message: '采购方式值不可为空',
        }],
        jhYu: [{
          required: true,
          message: '计划预算值不可为空',
        }],
        supplier: [{
          required: true,
          message: '供应商值不可为空',
        }],
        jhFounder: [{
          required: true,
          message: '创建人不可为空',
        }],
        dept: [{
          required: true,
          message: '创建部门不可为空',
        }],
      },
    };
  },
  created() {
    this.queryParams.jhStatus = 0;
    this.getList();
  },
  methods: {
    /*查看框架计划*/
    handleClick(activeName, oldActiveName) {
      console.log(activeName, oldActiveName)
      switch (activeName.name) {
        case 'first':
          this.queryParams.jhStatus = 0;
          this.getList();
          break;
        case 'second':
          this.queryParams.jhStatus = 1;
          this.getList();
          break;
        case 'third':
          this.queryParams.jhStatus = 2;
          this.getList();
          break;
        default:
          //默认 待提交状态
          this.queryParams.jhStatus = 0;
          this.getList();
          break;
      }
    },
    /** 查询框架计划列表 */
    getList() {
      this.loading = true;
      getFrameworkPlan(this.queryParams).then(res => {
        this.loading=false;
        this.framewokPlanList = res.rows;
        this.total = res.total;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        jhId: null,
        oid: null,
        jhCode: null,
        jhName: null,
        jhYu: null,
        dept: null,
        jhStatus: null,
        jhPerson: null,
        jhPmethod: null
      };
      this.resetForm("form");
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
      this.reset();
      this.open = true;
      this.title = "添加框架计划";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const jhId = row.jhId || this.ids
      getPlan(jhId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改框架计划";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.jhId != null) {
            updatePlan(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPlan(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const jhIds = row.jhId || this.ids;
      this.$modal.confirm('是否确认删除框架计划编号为"' + jhIds + '"的数据项？').then(function () {
        return delPlan(jhIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /**
     * 添加行项目
     */
    insertItems(){

    }
  }
};
</script>


<style lang="scss">
.el-input-number.full-width-input,
.el-cascader.full-width-input {
  width: 100% !important;
}

.el-form-item--medium {
  .el-radio {
    line-height: 40px !important;
  }

}

.el-form-item--small {
  .el-radio {
    line-height: 32px !important;
  }

  .el-rate {
    margin-top: 6px;
  }
}

.el-form-item--mini {
  .el-radio {
    line-height: 28px !important;
  }

  .el-rate {
    margin-top: 4px;
  }
}

.clear-fix:before,
.clear-fix:after {
  display: table;
  content: "";
}

.clear-fix:after {
  clear: both;
}

.float-right {
  float: right;
}
.input{
  width: 360px;
}

</style>

<style lang="scss" scoped>
div.table-container {
  table.table-layout {
    width: 100%;
    table-layout: fixed;
    border-collapse: collapse;

    td.table-cell {
      display: table-cell;
      height: 36px;
      border: 1px solid #e1e2e3;
    }
  }
}

div.tab-container {}

.label-left-align ::v-deep .el-form-item__label {
  text-align: left;
}

.label-center-align ::v-deep .el-form-item__label {
  text-align: center;
}

.label-right-align ::v-deep .el-form-item__label {
  text-align: right;
}

.custom-label {}

.static-content-item {
  min-height: 20px;
  display: flex;
  align-items: center;

  ::v-deep .el-divider--horizontal {
    margin: 0;
  }
}

</style>
