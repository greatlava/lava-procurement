<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品编码" prop="tid">
        <el-input
          v-model="queryParams.tid"
          placeholder="请输入产品编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="tName">
        <el-input
          v-model="queryParams.tName"
          placeholder="请输入产品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类别" prop="category">
        <el-input
          v-model="queryParams.category"
          placeholder="请输入类别"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:device:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:device:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:device:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:device:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="产品编码" align="center" prop="tid" width="120" />
      <el-table-column label="产品名称" align="center" prop="tName" width="120" />
      <el-table-column label="类别" align="center" prop="category" width="100" />
      <el-table-column label="计量单位" align="center" prop="tUnit" width="80" />
      <el-table-column label="税率" align="center" prop="shui" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.shui ? scope.row.shui + '%' : '-' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="规格型号" align="center" prop="tModel" width="120" />
      <el-table-column label="单价" align="center" prop="tPrice" width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.tPrice ? '¥' + scope.row.tPrice : '-' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="需求说明" align="center" prop="tIllustrate" show-overflow-tooltip />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:device:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:device:remove']"
          >删除</el-button>
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

    <!-- 添加或修改物料对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="产品编码" prop="tid">
              <el-input v-model="form.tid" placeholder="请输入产品编码" :disabled="isEdit" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="产品名称" prop="tName">
              <el-input v-model="form.tName" placeholder="请输入产品名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="类别" prop="category">
              <el-input v-model="form.category" placeholder="请输入类别" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="计量单位" prop="tUnit">
              <el-input v-model="form.tUnit" placeholder="请输入计量单位" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="税率" prop="shui">
              <el-input-number v-model="form.shui" :min="0" :max="100" :precision="2" placeholder="请输入税率" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="规格型号" prop="tModel">
              <el-input v-model="form.tModel" placeholder="请输入规格型号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="单价" prop="tPrice">
              <el-input-number v-model="form.tPrice" :min="0" :precision="2" placeholder="请输入单价" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="预算科目编号" prop="duCode">
              <el-input v-model="form.duCode" placeholder="请输入预算科目编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="需求说明" prop="tIllustrate">
          <el-input v-model="form.tIllustrate" type="textarea" :rows="2" placeholder="请输入需求说明" />
        </el-form-item>
        <el-form-item label="备注" prop="tNotes">
          <el-input v-model="form.tNotes" type="textarea" :rows="2" placeholder="请输入备注" />
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
import { listDevice, getDevice, delDevice, addDevice, updateDevice } from "@/api/device/device";

export default {
  name: "Device",
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      deviceList: [],
      title: "",
      open: false,
      isEdit: false,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tid: undefined,
        tName: undefined,
        category: undefined
      },
      form: {},
      rules: {
        tid: [
          { required: true, message: "产品编码不能为空", trigger: "blur" }
        ],
        tName: [
          { required: true, message: "产品名称不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      listDevice(this.queryParams).then(response => {
        this.deviceList = response.rows;
        this.total = response.total;
        this.loading = false;
      }).catch(err => {
        this.loading = false;
        this.$modal.msgError("获取物料列表失败");
      });
    },
    cancel() {
      this.open = false;
      this.reset();
    },
    reset() {
      this.form = {
        tid: undefined,
        tName: undefined,
        category: undefined,
        tUnit: undefined,
        shui: 0,
        tIllustrate: undefined,
        duCode: undefined,
        tNotes: undefined,
        tModel: undefined,
        tPrice: undefined
      };
      this.resetForm("form");
    },
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.tid)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    handleAdd() {
      this.reset();
      this.isEdit = false;
      this.open = true;
      this.title = "添加物料";
    },
    handleUpdate(row) {
      this.reset();
      this.isEdit = true;
      const tid = row.tid || this.ids
      getDevice(tid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物料";
      }).catch(err => {
        this.$modal.msgError("获取物料信息失败");
      });
    },
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.isEdit) {
            updateDevice(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).catch(err => {
              this.$modal.msgError("修改失败");
            });
          } else {
            addDevice(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }).catch(err => {
              this.$modal.msgError("新增失败");
            });
          }
        }
      });
    },
    handleDelete(row) {
      const tids = row.tid || this.ids;
      this.$modal.confirm('是否确认删除产品编码为"' + tids + '"的数据项？').then(function() {
        return delDevice(tids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    handleExport() {
      this.download('ppm/device/export', {
        ...this.queryParams
      }, `device_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
