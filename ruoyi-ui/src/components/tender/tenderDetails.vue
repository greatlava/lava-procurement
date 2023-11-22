<template>
  <div class="app-container">
    <el-descriptions  class="margin-top" title="招标项目/查看" :column="2" :size="size" border>
      <el-descriptions-item >
        <template slot="label">
          招标项目编号
        </template>
        自动生成
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          招标项目名称
        </template>
        招标项目名称
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          招标方式
        </template>
        <template slot="default">
          <el-radio v-model="radio1" label="1">公开招标</el-radio>
          <el-radio v-model="radio1" label="2">邀请招标</el-radio>
        </template>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          是否招标
        </template>
        <template slot="default">
          <el-radio v-model="radio2" label="1">依法必招</el-radio>
          <el-radio v-model="radio2" label="2">自主招标</el-radio>
        </template>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          资格审查方式
        </template>
        <template slot="default">
          <el-radio v-model="radio3" label="1">资格预审</el-radio>
          <el-radio v-model="radio3" label="2">资格后审</el-radio>
        </template>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          业务类型
        </template>
        <template slot="default">
          <el-select v-model="ywValue" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </template>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          项目预算
        </template>
        100000
      </el-descriptions-item>
      <el-descriptions-item/>

      <el-descriptions-item label="招标人信息"/>
      <el-descriptions-item/>
      <el-descriptions-item>
        <template slot="label">
          招标单位
        </template>
        XXXX
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          联系人
        </template>
        张三
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          电话
        </template>
        1236217312
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          邮箱
        </template>
        12312@qq.com
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          地址
        </template>
        XXXXX
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label"/>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          相关附件
        </template>
        <el-upload
          class="upload-demo"
          action="https://jsonplaceholder.typicode.com/posts/"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList"
        >
          <el-button size="small" type="primary">点击上传</el-button>
          <span slot="tip" class="el-upload__tip"> 支持文件格式：pdf.xis......</span>
        </el-upload>
      </el-descriptions-item>
      <el-descriptions-item/>
      <el-descriptions-item label="标的清单"/>
      <el-descriptions-item/>
    </el-descriptions>

    <!--    <el-table v-loading="loading" :data="itemsList">-->
    <!--    </el-table>-->

  </div>
</template>

<script>

export default {
  name: 'Tender',
  data() {
    return {
      fileList: [],
      //招标方式
      radio1: '1',
      //是否必招
      radio2: '1',
      //资格审查方式
      radio3: '1',
      //业务类型
      options: [{
        value: '1',
        label: '黄金糕'
      }, {
        value: '2',
        label: '双皮奶'
      }],
      ywValue: '1',
      size: '',
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
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      // 总条数
      total: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        xyId: null,
        sCode: null,
        sName: null,
        sWay: null,
        sMust: null,
        sSway: null,
        sType: null,
        sBudget: null,
        sUnit: null,
        sPerson: null,
        sPhone: null,
        email: null,
        sAddress: null,
        sStartTime: null,
        sEndTime: null,
        sProjectState: null,
        sDeadline: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    }
  },
  created() {
  },
  methods: {
    /** 查询行项目列表 */
    getList() {
      this.loading = false
      // listItems(this.queryParams).then(response => {
      //   this.itemsList = response.rows;
      //   this.total = response.total;
      //   this.loading = false;
      // });
    },
    //上传附件
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    // 表单重置
    reset() {
      this.form = {
        sid: null,
        xyId: null,
        sCode: null,
        sName: null,
        sWay: null,
        sMust: null,
        sSway: null,
        sType: null,
        sBudget: null,
        sUnit: null,
        sPerson: null,
        sPhone: null,
        email: null,
        sAddress: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        sStartTime: null,
        sEndTime: null,
        sProjectState: null,
        sDeadline: null,
      }
      this.resetForm('form')
    }
  }
}
</script>

<style>
.my-label {
  color: black;
  font-weight: bold;
}

</style>

