<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="框架计划编码" prop="jhCode">
        <el-input
          v-model="queryParams.jhCode"
          placeholder="请输入框架计划编码"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计划名称" prop="jhName">
        <el-input
          v-model="queryParams.jhName"
          placeholder="请输入计划名称"
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
          <el-table-column label="计划状态" align="center" prop="jhStatus">
            <template>
              <el-tag>待提交</el-tag>
            </template>
          </el-table-column>
          <!--          <el-table-column label="审批人" align="center" prop="jhPerson"/>-->
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
              >删除
              </el-button>
              <el-button
                size="mini"
                type="text"
                @click="showFrameworkDetails(scope.row)"
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
          <el-table-column label="计划状态" align="center" prop="jhStatus">
            <template>
              <el-tag type="danger">待审批</el-tag>
            </template>
          </el-table-column>
          <!--          <el-table-column label="审批人" align="center" prop="jhPerson"/>-->
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                @click="showFrameworkDetails(scope.row)"
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
          <el-table-column label="计划状态" align="center" prop="jhStatus">
            <template>
              <el-tag type="success">已完成</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="审批人" align="center" prop="jhPerson"/>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                @click="showFrameworkDetails(scope.row)"
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
    <el-dialog @close="OpenClose" :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form :model="form" ref="form" :rules="rules" label-position="left" label-width="120px"
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
                <el-input v-model="form.jhName" type="text"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" class="grid-cell">
              <el-form-item label="业务类型" prop="businessType" class="required label-center-align">
                <el-select class="input" v-model="form.businessType" placeholder="请选择业务类型">
                  <el-option
                    v-for="dict in dict.type.ppm_procurement_plan"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value*1"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" class="grid-cell">
              <el-form-item label="计划预算" prop="jhYu" class="required label-center-align">
                <el-input v-model.number="form.jhYu"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" class="grid-cell">
              <el-form-item label="供应商" prop="hid" class="required label-center-align">
                <el-select filterable v-model="form.hid" class="input full-width-input">
                  <el-option v-for="(item, index) in supplierOptions" :key="index" :label="item.hName"
                             :value="item.hid"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12" class="grid-cell">
              <el-form-item label="创建人" class="required label-center-align">
                <el-input disabled :value="form.jhFounder || '系统自动生成' " type="text"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12" class="grid-cell">
              <el-form-item label="创建部门" prop="dept" class="required label-center-align">
                <el-input :value="form.dept || '系统自动生成' " disabled type="text"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12" class="grid-cell">
              <el-form-item label="上传附件" prop="annex" class="label-center-align">
                <el-upload
                  ref="upload"
                  :action="upload.url"
                  :headers="upload.headers"
                  :file-list="upload.fileList"
                  :multiple="true"
                  :on-progress="handleFileUploadProgress"
                  :on-success="handleFileSuccess"
                  :limit="5"
                  :on-exceed="exceedingMaximumLimit"
                  :on-error="uploadError"
                  :before-upload="beforeUpload"
                  :before-remove="beforeRemove"
                  :on-remove="removeFile"
                  :auto-upload="false">
                  <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                  <el-button style="margin-left: 10px;" size="small" type="success" :loading="upload.isUploading"
                             @click="submitUpload">上传到服务器
                  </el-button>
                </el-upload>
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
          <el-table ref="elTable" max-height="250" v-loading="loading" :data="items" border style="margin-top: 20px">
            <el-table-column label="序号" align="center">
              <template slot-scope="scope">
                <span>{{ scope.$index + 1 }}</span>
              </template>
            </el-table-column>
            <el-table-column label="行项目编号" align="center" prop="vCode" width="150">
              <template slot-scope="scope">
                <span v-if="form.aid==null">自动生成</span>
                <el-input v-else v-model="scope.row.vCode" class="borderNone" disabled></el-input>
              </template>
            </el-table-column>
            <el-table-column label="物料编号" align="center" prop="tid" width="150">
              <template slot-scope="scope">
                <span>{{ scope.row.tid }}</span>
                <i class="el-icon-search" @click="search(scope.row,scope.$index)"></i>
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
                <el-date-picker style="width: 150px" type="date" placeholder="选择日期"
                                v-model="scope.row.vDeliveryTime"></el-date-picker>
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
                  @click="deleteItems(scope.row,scope.$index)"
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
    <!--  搜索设备弹窗  -->
    <el-dialog title="收货地址" :visible.sync="deviceOpen" width="42%">
      <el-form :model="form" ref="queryForm" size="small" :inline="true" label-width="68px">
        <el-form-item label="产品编码" prop="tid">
          <el-input
            v-model="queryParams.tid"
            placeholder="请输入产品编码"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="产品名称" prop="tName">
          <el-input
            v-model="queryParams.tName"
            placeholder="请输入产品名称"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
      <el-table @cell-dblclick="selectedMateria" max-height="300" border :data="device"
                v-loading="deviceLoding">
        <el-table-column label="产品编码" align="center" prop="tid"/>
        <el-table-column label="产品名称" align="center" prop="tName"/>
      </el-table>
      <div :class="{'hidden':hidden}" class="pagination-container">
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getMaterialList"
        />
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelDevice">关 闭</el-button>
      </div>
    </el-dialog>
    <!--    查看框架计划   -->
    <el-dialog :visible.sync="openFrameworkDetails">
      <el-descriptions direction="vertical" class="margin-top" title="查看框架详情" :column="3" size="medium" border>
        <el-descriptions-item>
          <template slot="label">
            框架计划名称
          </template>
          {{ form.jhName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            框架计划预算
          </template>
          {{ form.jhYu }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            部门
          </template>
          {{ form.dept }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            框架计划状态
          </template>
          <el-tag v-if="form.jhStatus == 0">待提交</el-tag>
          <el-tag v-if="form.jhStatus == 1">待审核</el-tag>
          <el-tag v-if="form.jhStatus == 2">已生效</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            审批人
          </template>
          <span v-if="form.jhPerson != null && form.jhPerson != ''">
            {{ form.jhPerson }}
          </span>
          <span style="color: #cccccc" v-else>
            未审核
          </span>
        </el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            创建人
          </template>
          {{ form.jhFounder }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            附件
          </template>
          <el-button @click="downloadFile" type="primary" icon="el-icon-download">下载附件</el-button>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            附件名称
          </template>
          <p v-for="i in file.fileUrls.length" :key="i">
            <a :href="file.fileUrls[i-1]">{{ file.fileName[i - 1] }}</a>
          </p>
        </el-descriptions-item>
      </el-descriptions>
      <el-table border @cell-click="click" max-height="250" v-loading="loading" :data="form.items"
                style="margin-top: 20px">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-descriptions border>
              <el-descriptions-item label="产品名称">{{ props.row.ppmDevice.tName }}</el-descriptions-item>
              <el-descriptions-item label="计量单位">{{ props.row.ppmDevice.tUnit }}</el-descriptions-item>
              <el-descriptions-item label="税率">{{ props.row.ppmDevice.shui }}</el-descriptions-item>
              <el-descriptions-item label="类别">
                <el-tag size="small">{{ props.row.ppmDevice.category }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item label="需求说明">{{ props.row.ppmDevice.tIllustrate }} 1188 号
              </el-descriptions-item>
              <el-descriptions-item label="规格型号">{{ props.row.ppmDevice.tModel }}</el-descriptions-item>
            </el-descriptions>
          </template>
        </el-table-column>
        <el-table-column label="行项目编号" align="center" prop="vCode" width="160"/>
        <el-table-column label="数量" align="center" prop="vCount"/>
        <el-table-column label="采购人" align="center" prop="vPerson"/>
        <el-table-column label="交付时间" align="center" prop="vDeliveryTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.vDeliveryTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="交付地点" align="center" prop="vDeliveryArea" width="150"/>
        <el-table-column label="需求说明" align="center" prop="vIllustrate" width="120"/>
        <!--        <el-table-column label="采购方式" align="center" prop="procurementMethod"/>-->
      </el-table>
      <div slot="footer" class="dialog-footer">
        <!--        <div style="display: inline-block;margin-right: 10px" v-has-permi="['system:procurement:allow']">-->
        <!--          <el-button  @click="allowFarmeworkPlan" v-if="form.jhStatus == 1" type="success">审 核</el-button>-->
        <!--          <el-button @click="rejectFarmeworkPlan" v-if="form.jhStatus == 1" type="danger">驳 回</el-button>-->
        <!--        </div>-->
        <div style="display: inline-block;margin-right: 10px" v-has-permi="['system:procurement:allow']">
          <el-button @click="allowFarmeworkPlan" v-if="form.jhStatus == 1" type="success">审 核</el-button>
          <el-button @click="rejectFarmeworkPlan" v-if="form.jhStatus == 1" type="danger">驳 回</el-button>
        </div>
        <el-button @click="sumbitFarmeworkPlan" v-if="form.jhStatus == 0" type="primary">提 交</el-button>
        <el-button @click="cancelFramework">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import { listPlan, getPlan, delPlan, addPlan, updatePlan } from "@/api/system/plan";
import {
  getFrameworkPlan,
  AddPlanAndOther,
  queryFrameworkPlanAndRelatedInformation,
  deleteByJhId,
  updateFarmeworkPlanStatus,
  updatePlan
} from "@/api/system/frameworkPlan";
import {listDevice} from "@/api/device/device";
import {listSupplier} from "@/api/system/supplier";
import {updateComPubAttamentsByAid} from "@/api/file/attachments";
import {getToken} from "@/utils/auth";

export default {
  dicts: ['ppm_procurement_plan', "procurement_method"],
  name: "Plan",
  data() {
    return {
      activeName: "first",
      fullscreenLoading: false,
      openFrameworkDetails: false,
      hidden: false,
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
      //搜索设备显示
      deviceOpen: false,
      //当前选择的下标
      selectedIndex: null,
      // 总条数
      total: 0,
      // 框架计划表格数据
      framewokPlanList: [],
      //设备集合
      device: [],
      //设备加载
      deviceLoding: false,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //文件
      file: {
        fileUrls: [],
        fileName: []
      },
      //上传参数
      upload: {
        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: {Authorization: "Bearer " + getToken()},
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/ppm/file/upload",
        // 上传的文件列表
        fileList: [],
        //上传成功列表
        fileSecuss: [],
      },
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
      form: {
        hid: ''
      },
      supplierOptions: [],
      annexFileList: [],
      annexUploadHeaders: {},
      annexUploadData: {},
      items: [],
      // 表单校验
      rules: {
        jhName: [{
          required: true,
          message: '计划名称值不可为空',
        }],
        businessType: [{
          required: true,
          message: '业务类型值不可为空',
        }],
        jhYu: [
          {
            required: true,
            message: '计划预算值不可为空',
          },
          {
            type: "number",
            message: "计划预算只能是数字"
          }
        ],
        hid: [{
          required: true,
          message: '供应商值不可为空',
        }],
        jhFounder: [{
          required: true,
          message: '创建人不可为空',
        }],
      },
    };
  },
  created() {
    this.queryParams.jhStatus = 0;
    this.getList();
  },
  methods: {
    //弹窗关闭事件
    OpenClose() {
      this.file.fileUrls = [];
      this.file.fileName = [];
      this.form = {};
      this.upload.fileList = [];
      this.upload.fileSecuss = [];
      this.device = [];
      this.items = [];
    },
    //文件移除钩子
    removeFile(file, fileList) {
      this.$modal.loading("文件删除中");
      if (this.form.jhId) {
        if (file.status == "success") {
          let obj = file;
          obj["jhid"] = this.form.jhId;
          obj["anName"] = obj["name"];
          obj["anUrl"] = obj["url"];
          updateComPubAttamentsByAid(obj, "success").then(res => {
            this.$modal.closeLoading();
            this.$modal.msgSuccess("删除成功！！")
          }).catch(err => {
            this.$modal.closeLoading();
            this.$modal.msgError("服务器出错，请联系管理员！！！");
          })
        }
      } else {
        this.upload.fileSecuss = this.upload.fileSecuss.filter(item => {
          return !item["name"].includes(file["name"].substring(0, file["name"].indexOf(".")))
        })
      }
    },
    //文件删除之前的钩子
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    //上传文件之前
    beforeUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$modal.msgError("文件大小不可超过2MB")
      }
      return isLt2M;
    },
    //文件上传失败
    uploadError(err, file, fileList) {
      this.upload.isUploading = false;
      this.$modal.msgError(err);
    },
    exceedingMaximumLimit(file, fileList) {
      this.$modal.msgError("最多上传文件5个！！")
    },
    // 文件提交处理
    submitUpload() {
      this.$refs.upload.submit();
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      if (response.code == 200) {
        if (this.form.jhId) {
          let obj = response.data.data;
          obj["jhid"] = this.form.jhId;
          obj["anName"] = obj["name"];
          obj["anUrl"] = obj["url"];
          updateComPubAttamentsByAid(obj, "insert").then(res => {
            if (res.code == 200) {
              this.$modal.msgSuccess(res.msg);
              return 1;
            } else {
              this.$modal.msgSuccess("文件上传失败");
            }
          }).catch(err => {
            this.$modal.msgError("服务器出错，请联系管理员！！！");
          })
        } else {
          this.upload.fileSecuss.push(response.data.data);
          this.$modal.msgSuccess("文件上成功，需要点击提交按钮才可生效！！");
        }
      }
      this.upload.isUploading = false;

    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    //下载文件
    downloadFile() {
      if (this.file.fileUrls.length == 0 || this.file.fileName.length == 0) {
        this.$modal.msgError("没有附件可下载，请上传附件！！")
        return;
      }
    },
    deleteItems(row, index) {
      this.items.splice(index, 1)
    },
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
    allowFarmeworkPlan(row) {
      let jhid = this.form.jhId || row.jhId;
      this.$modal.confirm("确定要通过编号为" + jhid + "的审核吗").then(() => {
        this.$modal.loading("审核中.....")
        updateFarmeworkPlanStatus(jhid, 2).then(res => {
          this.$modal.closeLoading();
          this.$modal.msgSuccess("审核成功！！");
          this.openFrameworkDetails = false;
          this.getList();
        }).catch(err => {
          this.$modal.closeLoading();
          this.$modal.msgError("服务器出错，请联系管理员！！！");
        })
      })
    },
    rejectFarmeworkPlan() {
      this.$modal.confirm("确定要驳回编号为" + this.form.jhId + "的审核吗").then(() => {
        this.$modal.loading("驳回中.......")
        updateFarmeworkPlanStatus(this.form.jhId, 0).then(res => {
          this.$modal.closeLoading();
          this.$modal.msgSuccess("驳回成功！！");
          this.openFrameworkDetails = false;
          this.getList();
        }).catch(err => {
          this.$modal.closeLoading();
          this.$modal.msgError("服务器出错，请联系管理员！！！");
        })
      })
    },
    sumbitFarmeworkPlan() {
      this.$modal.confirm("确定要提交编号为" + this.form.jhId + "的框架计划吗").then(() => {
        this.$modal.loading("提交中.......")
        updateFarmeworkPlanStatus(this.form.jhId, 1).then(res => {
          this.$modal.closeLoading();
          this.$modal.msgSuccess("提交成功！！");
          this.openFrameworkDetails = false;
          this.getList();
        }).catch(err => {
          this.$modal.closeLoading();
          this.$modal.msgError("服务器出错，请联系管理员！！！");
        })
      })
    },
    click() {

    },
    //查看框架计划详情
    showFrameworkDetails(row) {
      const loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      queryFrameworkPlanAndRelatedInformation(row.jhId).then(res => {
        this.form = res.data;
        if (res.data.comPubAttachments.anUrl && res.data.comPubAttachments.anName) {
          this.file.fileUrls = res.data.comPubAttachments.anUrl.split(",");
          this.file.fileName = res.data.comPubAttachments.anName.split(",");
        }
        loading.close();
        this.openFrameworkDetails = true;
      }).catch(err => {
        loading.close();
        this.$modal.msgError("服务器出错，请联系管理员！！！");
      })
    },
    /** 查询框架计划列表 */
    getList() {
      this.loading = true;
      getFrameworkPlan(this.queryParams).then(res => {
        this.loading = false;
        this.framewokPlanList = res.rows;
        this.total = res.total;
      }).catch(err => {
        this.$modal.msgError("服务器出错，请联系管理员！！！");
      })
    },
    // 取消按钮
    cancel() {

      this.open = false;
      this.items = [];
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
      this.file.fileName = [];
      this.file.fileUrls = [];
      this.upload.fileList = [];
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
      this.getSupplier();
      this.title = "添加框架计划";
    },
    getSupplier() {
      listSupplier(null).then(res => {
        this.supplierOptions = res.rows;
      }).catch(err => {
        this.$modal.msgError("服务器出错，请联系管理员！！！");
      })
    },
    cancelFramework() {
      this.reset();
      this.openFrameworkDetails = false;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();

      const jhId = row.jhId || this.ids
      this.$modal.loading("加载中.......");
      queryFrameworkPlanAndRelatedInformation(jhId).then(response => {
        this.$modal.closeLoading();
        this.form = response.data;
        this.items = response.data.items;
        if (response.data.comPubAttachments.anUrl && response.data.comPubAttachments.anName) {
          this.file.fileUrls = response.data.comPubAttachments.anUrl.split(",");
          this.file.fileName = response.data.comPubAttachments.anName.split(",");
          this.file.fileUrls.forEach((e, i) => {
            this.upload.fileList.push({
              name: this.file.fileName[i],
              url: this.file.fileUrls[i]
            })
          })
        }
        this.open = true;
        this.title = "修改框架计划";
      }).catch(err => {
        this.$modal.closeLoading();
        this.$modal.msgError("服务器出错，请联系管理员！！！");
      })
      this.getSupplier();
    },
    //设备关闭按钮
    cancelDevice() {
      this.deviceOpen = false;
      this.reset();
    },
    search(row, index) {
      this.selectedIndex = index;
      this.deviceOpen = true;
      if (this.device.length == 0)
        this.getMaterialList();
    },
    getMaterialList() {
      this.deviceLoding = true;
      listDevice(this.queryParams).then((res => {
        this.device = res.rows;
        this.total = res.total;
        this.deviceLoding = false;
      })).catch(err => {
        this.deviceLoding = false;
        this.$modal.msgError("服务器出错，请联系管理员！！！");
      })
    },
    // 双击选择设备
    selectedMateria(row) {
      let obj = this.items[this.selectedIndex];
      obj = {...obj, tid: row.tid}
      this.items.splice(this.selectedIndex, 1, obj);
      this.deviceOpen = false;
    },
    /** 提交按钮 */
    submitForm() {
      console.log("hid", this.form.hid)
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.jhId != null) {
            this.$modal.loading("修改中.....")
            updatePlan(this.form).then(response => {
              this.$modal.closeLoading();
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).catch(err => {
              this.$modal.closeLoading();
              this.$modal.msgError("修改失败，请联系管理员！！！");
            })
          } else {
            if (this.items.length == 0) {
              this.$modal.msgError("请至少添加一条项目信息");
              return false;
            }
            if (this.items.some(e => !e.tid)) {
              this.$modal.msgError("所有项目设备不能为空");
              return;
            }
            this.$modal.loading("添加中.......")
            this.form['items'] = this.items;
            AddPlanAndOther(this.form).then(res => {
              this.$modal.msgSuccess("添加成功");
              this.open = false;
              this.$modal.closeLoading();

              setTimeout(() => {
                this.getList();
              }, 1000)
            }).catch(err => {
              this.$modal.msgError("服务器发生错误请联系管理员!!");
              this.$modal.closeLoading();
            })
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const jhIds = row.jhId || this.ids;
      this.$modal.confirm('删除框架计划将删除所有行项目，是否继续删除框架计划编号为"' + jhIds + '"的数据项？').then(() => {
        this.$modal.loading("删除中.......");
        return deleteByJhId(jhIds).then(() => {
          this.getList();
          this.$modal.closeLoading();
          this.$modal.msgSuccess("删除成功");
        }).catch(err => {
          this.$modal.closeLoading();
          this.$modal.msgError("删除失败，请联系管理员！！！");
        })
      })
    },
    /**
     * 添加行项目
     */
    insertItems() {
      this.items.push({})
    }
  }
};
</script>


<style lang="scss" scoped>
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

.input {
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

div.tab-container {
}

.label-left-align ::v-deep .el-form-item__label {
  text-align: left;
}

.label-center-align ::v-deep .el-form-item__label {
  text-align: center;
}

.label-right-align ::v-deep .el-form-item__label {
  text-align: right;
}

.custom-label {
}

.static-content-item {
  min-height: 20px;
  display: flex;
  align-items: center;

  ::v-deep .el-divider--horizontal {
    margin: 0;
  }
}

</style>

<style scoped>
.borderNone >>> .el-input__inner {
  border: none;
  text-align: center;
//box-shadow: none;
}
</style>
