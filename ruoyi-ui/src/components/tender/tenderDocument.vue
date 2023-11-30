<template>
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:documents:add']"
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
          v-hasPermi="['system:documents:edit']"
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
          v-hasPermi="['system:documents:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:documents:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="documentsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="wid" />
      <el-table-column label="文件标题" align="center" prop="wTitle"/>
      <el-table-column label="文件大小" align="center" prop="wSize" />
      <el-table-column label="上传时间" align="center" prop="wUploadTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.wUploadTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
          >预览</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
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

    <!-- 添加或修改招标文件对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="招标项目ID" prop="sid">
          <el-input v-model="form.sid" placeholder="请输入招标项目ID" :disabled="true"/>
        </el-form-item>
        <el-form-item label="文件标题" prop="wTitle">
          <el-input v-model="form.wTitle" placeholder="请输入文件标题" />
        </el-form-item>
        <el-form-item label="文件大小" prop="wSize">
          <el-input v-model="form.wSize" placeholder="请输入文件大小" />
        </el-form-item>
        <el-form-item label="上传时间" prop="wUploadTime">
          <el-date-picker clearable
                          v-model="form.wUploadTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择上传时间">
          </el-date-picker>
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
import { listDocuments, getDocuments, delDocuments, addDocuments, updateDocuments } from "@/api/system/document";
import { getTender,updateTender } from '@/api/system/tender';

export default {
  name: "Documents",
  data() {
    return {
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
      // 招标文件表格数据
      documentsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      sCode:null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        sid: null,
        wTitle: null,
        wSize: null,
        wUploadTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询招标文件列表 */
    getList() {
      this.loading = true;
      listDocuments(this.queryParams).then(response => {
        this.documentsList = response.rows;
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
        wid: null,
        wTitle: null,
        sid:this.$route.query.sid,
        wSize: null,
        wUploadTime: null
      };
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.wid)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加招标文件";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const wid = row.wid || this.ids
      getDocuments(wid).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改招标文件";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.wid != null) {
            updateDocuments(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDocuments(this.form).then(response => {
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
      const wids = row.wid || this.ids;
      this.$modal.confirm('是否确认删除招标文件编号为"' + wids + '"的数据项？').then(function() {
        return delDocuments(wids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/documents/export', {
        ...this.queryParams
      }, `documents_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
