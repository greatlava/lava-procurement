<script setup>

</script>

<template>
  <div class="box">
    <div class="top">
      <h1 class="title">{{ title }}</h1>
      <span class="describe">基本信息</span>
      <el-form class="form" label-position="top" :inline="true" size="medium" ref="form" :model="form"
               :rules="rules" label-width="140px">
        <el-row>
          <el-col :span="6">
            <el-form-item label="采购计划编号" prop="aCode">
              <!--            <el-input v-model="form.aCode" disabled placeholder="请输入采购计划编号"/>-->
              <el-select style="width: 202px" v-model="checkedRuleForm" placeholder="请选择">
                <el-option
                  v-for="item in codeRuleForm"
                  :key="item.targetForm"
                  :label="item.targetForm"
                  :value="item.targetForm">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="计划名称" prop="aName">
              <el-input v-model="form.aName" placeholder="请输入计划名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="创建人" prop="createBy">
              <el-input v-model="form.createBy" :disabled="form.aid!=null"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="创建部门" prop="aCreateDept">
              <el-input v-model="form.aCreateDept" :disabled="form.aid!=null"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="附件" prop="fjAnnex">
              <div style="width: 202px">
                <el-button type="primary">
                  上传附件<i class="el-icon-upload"></i>
                </el-button>
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="采购计划审核意见" prop="aOpinion">
              <el-input style="width: 202px" v-model="form.aOpinion" type="textarea" placeholder="请输入内容"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="行项目数量" prop="aProjectCount">
              <el-input v-model="form.aProjectCount" placeholder="请输入行项目数量"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="采购业务类型名称" prop="aBtype">
              <el-select style="width: 202px" v-model="form.aBtype" placeholder="请选择采购业务类型名称">
                <el-option
                  v-for="dict in dict.type.ppm_procurement_plan"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <!--      <div slot="footer" class="dialog-footer">-->
      <!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
      <!--        <el-button @click="cancelInsert">取 消</el-button>-->
      <!--      </div>-->
    </div>
    <div class="content top">
      <h1 class="title">物料信息</h1>
      <span class="describe">物料信息</span>
      <div class="material_information">
        <div class="botton">
          <el-button type="primary" icon="el-icon-plus" @click="addMaterial" size="mini"></el-button>
          <el-button type="primary" icon="el-icon-minus" @click="delMaterial" size="mini"></el-button>
          <el-button type="primary" icon="el-icon-copy-document" size="mini"></el-button>
        </div>
        <el-table ref="elTable" max-height="250" v-loading="loading" :data="device" border style="margin-top: 20px">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="产品编码" align="center">
            <template slot-scope="scope">
              <span>{{ scope.$index + 1 }}</span>
            </template>
          </el-table-column>
          <el-table-column label="产品编号" align="center" prop="tid" width="150">
            <template slot-scope="scope">
              <span style="margin-right: 10px">{{ scope.row.tid }}</span>
              <i class="el-icon-search" @click="search(scope.$index)"></i>
            </template>
          </el-table-column>
          <el-table-column label="产品名称" align="center" prop="tName"/>
          <el-table-column label="数量" align="center" prop="tAmount" width="220">
            <template slot-scope="scope">
              <el-input-number :step="1" step-strictly v-model="scope.row.tAmount"></el-input-number>
            </template>
          </el-table-column>
          <el-table-column label="税率" align="center" prop="shui"/>
          <el-table-column label="预算总价" align="center" prop="tUnit"/>
          <el-table-column label="预算单价" align="center" prop="tPrice"/>
          <el-table-column label="预算总价" align="center" prop="tTotalPrice"/>
          <el-table-column label="交付时间" align="center" prop="tDate" width="180">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.tDate, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="交付地点" align="center" prop="tAddress"/>
          <el-table-column label="需求说明" align="center" prop="tIllustrate"/>
          <el-table-column label="采购人" align="center" prop="tPurchaser"/>
          <el-table-column label="备注" align="center" prop="tNotes"/>
          <el-table-column label="采购计划状态" align="center" prop="aState" width="120"/>
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
    </div>
    <!--    搜索设备/物料    -->
    <el-dialog title="收货地址" :visible.sync="show.open" width="42%">
      <el-form :model="form" ref="queryForm" size="small" :inline="true" label-width="68px">
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
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
      <el-table @cell-dblclick="selectedMateria" max-height="300" border :data="newDevice" v-loading="loading">
        <el-table-column label="产品编码" align="center" prop="tid"/>
        <el-table-column label="产品名称" align="center" prop="tName"/>
      </el-table>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getMaterialList"
      />
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {listRules} from "@/api/code/rules";
import {listDevice} from "@/api/device/device";

export default {
  dicts: ['ppm_procurement_plan'],
  data() {
    return {
      total: 1,
      //所有设备列表
      newDevice: [],
      //编号规则目标表单
      codeRuleForm: [],
      //编号规则选择的值
      checkedRuleForm: '',
      //选中设备信息
      device: [],
      //标题
      title: "新增采购计划",
      //form表单
      form: {},
      loading: false,
      //表单验证
      rules: {},
      //显示弹窗
      show: {
        open: false
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10
      },
      selectedIndex: 0
    }
  },
  created() {
    listRules(null).then(res => {
      console.log("res", res.rows)
      this.codeRuleForm = res.rows;
    })
  },
  methods: {
    handleDelete(row, index) {
      this.device.splice(index, 1)
    },
    addMaterial() {
      this.device.push({})
    },
    delMaterial() {
      let table = this.$refs.elTable.selection;
      console.log("t", table)
      console.log("index", table[0])
    },
    //搜索icon 显示搜索窗口
    search(index) {
      console.log(index, "index")
      this.selectedIndex = index;
      this.show.open = true;
      if (this.newDevice.length == 0)
        this.getMaterialList();
    },
    //搜索按钮
    handleQuery() {

    },
    //重置按钮
    resetQuery() {

    },
    //获取物料列表
    getMaterialList() {
      this.loading = true;
      listDevice(null).then((res => {
        this.newDevice = res.rows;
        this.total = res.total;
        this.loading = false;
      }))
    },
    //关闭按钮
    cancel() {
      this.show.open = false;
    },
    //双击选中物料
    selectedMateria(row) {
      let list = this.device.filter(item => item.tid == row.tid);
      this.device.splice(this.selectedIndex, 1, row);
      this.show.open = false;
    }
  }
}
</script>
<style scoped lang="scss">
.top {
  //background: #fafafa;
  padding: 20px 40px;
  box-shadow: -2px 2px 2px 2px #dadada;
  width: 1300px;
  margin: 50px auto;
  //background: #f5f5f5;
  height: 500px;
}

.title {
  text-align: center;
  padding-bottom: 30px;
  border-bottom: 1px #eeeeee solid;
}

.describe {
  display: inline-block;
  margin-bottom: 15px;
}

.form {
  padding: 20px 20px;
  padding-left: 80px;
  border: 1px #d2d2d2 solid
}

.material_information {
  padding: 10px;
  border: 1px #d2d2d2 solid
}

.botton {
  text-align: right;
  padding: 10px;
}
</style>
