<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="专家姓名" prop="jName">
        <el-input
          v-model="queryParams.jName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="jShState" label-width="120px">
        <el-select v-model="queryParams.jShState" placeholder="请输入专家审核状态"
                   @keyup.enter.native="handleQuery">
          <el-option v-for="dict in statusList"
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

      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="expertList">
      <el-table-column label="序号" align="center" type="index"/>
      <el-table-column label="专家姓名" align="center" prop="jName"/>
      <el-table-column label="联系方式" align="center" prop="jPhone"/>
      <el-table-column label="工作单位" align="center" prop="jUnit"/>
      <el-table-column label="主评专业" align="center" prop="experience.specialized"/>
      <el-table-column label="所在区域" align="center" prop="jAddress"/>
      <el-table-column label="审核状态" align="center" prop="jShState">
        <template slot-scope="scope">
          <el-tag size="small" v-if="expertList[scope.$index].jShState == 0" type="primary">待审核
          </el-tag>
          <el-tag size="small" v-else-if="expertList[scope.$index].jShState == 1" type="success">已通过</el-tag>
          <el-tag size="small" v-else-if="expertList[scope.$index].jShState == 2" type="info">未通过</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <router-link :to="'auditOrView?jid='+scope.row.jid+'&param=0'">
            <el-button
              v-hasRole="['controller']"
              v-if="scope.row.jShState == 0"
            >审核
            </el-button>
          </router-link>
          <router-link :to="'auditOrView?jid='+scope.row.jid+'&param=1'">
            <el-button
              v-hasRole="['common','controller']"
              type="primary"
              v-if="scope.row.jShState == 2 || scope.row.jShState == 1"
            >查看
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

    <!-- 添加或修改专家对话框 -->
    <!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
    <!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
    <!--        <el-form-item label="姓名" prop="jName">-->
    <!--          <el-input v-model="form.jName" placeholder="请输入姓名"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="身份证号" prop="jIdentity">-->
    <!--          <el-input v-model="form.jIdentity" placeholder="请输入身份证号"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="手机号" prop="jPhone">-->
    <!--          <el-input v-model="form.jPhone" placeholder="请输入手机号"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="工作单位" prop="jUnit">-->
    <!--          <el-input v-model="form.jUnit" placeholder="请输入工作单位"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="通讯地址" prop="jAddress">-->
    <!--          <el-input v-model="form.jAddress" placeholder="请输入通讯地址"/>-->
    <!--        </el-form-item>-->
    <!--        <el-form-item label="银行卡号" prop="jCard">-->
    <!--          <el-input v-model="form.jCard" placeholder="请输入银行卡号"/>-->
    <!--        </el-form-item>-->
    <!--      </el-form>-->
    <!--      <div slot="footer" class="dialog-footer">-->
    <!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
    <!--        <el-button @click="cancel">取 消</el-button>-->
    <!--      </div>-->
    <!--    </el-dialog>-->
  </div>
</template>

<script>
import {listExpert, getExpert, delExpert, addExpert, updateExpert} from "@/api/system/expert";
import {getDicts} from "@/api/system/dict/data";

export default {
  name: "Expert",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 专家表格数据
      expertList: [],
      //审核状态字典
      statusList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        jName: null,
        jShState: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  beforeRouteLeave(to, from, next) {
    sessionStorage.setItem('b1', this.queryParams.pageNum)
    sessionStorage.setItem('b2', this.queryParams.pageSize)
    next(true)
  },
  created() {
    if (sessionStorage.getItem('b1') != null && sessionStorage.getItem('b2')) {
      this.queryParams.pageNum = JSON.parse(sessionStorage.getItem('b1'))
      this.queryParams.pageSize = JSON.parse(sessionStorage.getItem('b2'))
      sessionStorage.removeItem('b1')
      sessionStorage.removeItem('b2')
    }
    this.getList();
    this.getDicts('bs_expert_status').then(res => {
      this.statusList = res.data
    })
  },
  methods: {
    /** 查询专家列表 */
    getList() {
      this.loading = true;
      listExpert(this.queryParams).then(response => {
        this.expertList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    // cancel() {
    //   this.open = false;
    //   this.reset();
    // },
    // 表单重置
    // reset() {
    //   this.form = {
    //     jid: null,
    //     qid: null,
    //     pbId: null,
    //     jName: null,
    //     jSex: null,
    //     jIdentity: null,
    //     jPhone: null,
    //     email: null,
    //     jState: null,
    //     jUnit: null,
    //     jRetire: null,
    //     jAddress: null,
    //     jCard: null,
    //     jIdentityPhoto: null,
    //     jDocumentsPhoto: null,
    //     jBank: null,
    //     jShState: null,
    //     jOpinion: null
    //   };
    //   this.resetForm("form");
    // },
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
    /** 修改按钮操作 */
    // handleUpdate(row) {
    //   this.reset();
    //   const jid = row.jid
    //   getExpert(jid).then(response => {
    //     this.form = response.data;
    //     this.open = true;
    //     this.title = "修改专家";
    //   });
    // },
    /** 提交按钮 */
    // submitForm() {
    //   this.$refs["form"].validate(valid => {
    //     if (valid) {
    //       updateExpert(this.form).then(response => {
    //         this.$modal.msgSuccess("修改成功");
    //         this.open = false;
    //         this.getList();
    //       });
    //     }
    //   });
    // }
  }
};
</script>
