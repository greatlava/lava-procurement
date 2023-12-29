<template>
    <div class="app-container">
      <el-table v-loading="loading" :data="tenderList" border style="margin: 0 auto 60px">
        <el-table-column label="项目编号" align="center" prop="sCode" width="200"/>
         <el-table-column label="项目名称" align="center" prop="sName"/>
        <el-table-column label="招标单位" align="center" prop="sUnit" />
        <el-table-column label="负责人" align="center" prop="sLeader" />
        <el-table-column label="标书获取截止时间" align="center" prop="uEndTime" width="200">
          <template slot-scope="scope">
            <span>{{ scope.row.bidNotices[0].uEndTime || '——'}}</span>
          </template>
        </el-table-column>
      </el-table>
      <el-tabs v-model="activeName">
        <el-tab-pane label="获取详细信息" name="info">
          <el-row :gutter="10" class="mb8">
            <right-toolbar  @queryTable="getOperator(queryParams.sid)"></right-toolbar>
          </el-row>
          <el-table v-loading="loading" :data="operatorList">
            <el-table-column
              label="序号"
              type="index"
              align="center"
              width="50">
            </el-table-column>
            <el-table-column label="投标单位名称" align="center" prop="bsSuppliers">
              <template slot-scope="scope">
                <span>{{ scope.row.bsSuppliers[0].hName}}</span>
              </template>
            </el-table-column>
            <el-table-column label="联系人" align="center" prop="tdPerson">
              <template slot-scope="scope">
                <span>{{ scope.row.bsSuppliers[0].bsOperator.ywName}}</span>
              </template>
            </el-table-column>
            <el-table-column label="联系方式" align="center" prop="tdPhone">
              <template slot-scope="scope">
                <span>{{ scope.row.bsSuppliers[0].bsOperator.ywPhone}}</span>
              </template>
            </el-table-column>
            <el-table-column label="邮箱" align="center" prop="email">
              <template slot-scope="scope">
                <span>{{ scope.row.bsSuppliers[0].bsOperator.ywMailbox}}</span>
              </template>
            </el-table-column>
            <el-table-column label="下载时间" align="center" prop="yDownloadTime" width="180">
              <template slot-scope="scope">
                <span>{{ scope.row.yDownloadTime }}</span>
              </template>
            </el-table-column>
<!--            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--              <template slot-scope="scope">-->
<!--                <el-button-->
<!--                  size="mini"-->
<!--                  type="text"-->
<!--                  icon="el-icon-zoom-in"-->
<!--                  @click="handleDetail"-->
<!--                >详情</el-button>-->
<!--              </template>-->
<!--            </el-table-column>-->
          </el-table>
        </el-tab-pane>
      </el-tabs>

      <!-- 添加或修改获取标书对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="招标项目ID" prop="sid">
            <el-input v-model="form.sid" placeholder="请输入招标项目ID" />
          </el-form-item>
          <el-form-item label="投标人名称" prop="tdName">
            <el-input v-model="form.tdName" placeholder="请输入投标人名称" />
          </el-form-item>
          <el-form-item label="联系人" prop="tdPerson">
            <el-input v-model="form.tdPerson" placeholder="请输入联系人" />
          </el-form-item>
          <el-form-item label="联系方式" prop="tdPhone">
            <el-input v-model="form.tdPhone" placeholder="请输入联系方式" />
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="form.email" placeholder="请输入邮箱" />
          </el-form-item>
          <el-form-item label="下载时间" prop="yDownloadTime">
            <el-date-picker clearable
                            v-model="form.yDownloadTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择下载时间">
            </el-date-picker>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </template>

  <script>
    import { listTender, getTender, delTender, addTender, updateTender ,findTenderNotice} from "@/api/system/tender/tender";
    import {operatorList} from "@/api/system/tender/getTender"

    export default {
      name: "Tender",
      data() {
        return {
          activeName:'info',
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
          // 获取项目和公告数据
          tenderList: [],
          //获取下载标书供应商信息
          operatorList:[],
          //项目数据
          tenderInfo:null,
          // 弹出层标题
          title: "",
          // 是否显示弹出层
          open: false,
          // 查询参数
          queryParams: {
            pageNum: 1,
            pageSize: 10,
            sid: null,
            tdName: null,
            tdPerson: null,
            tdPhone: null,
            email: null,
            yDownloadTime: null,
            tdStatus: null
          },
          // 表单参数
          form: {},
          // 表单校验
          rules: {
          }
        };
      },
      created() {
        this.queryParams.sid = this.$route.query.sid;
        this.getList(this.queryParams.sid);
        this.getOperator(this.queryParams.sid);
      },
      methods: {
        /** 查询获取标书列表 */
        getList(sid) {
          this.loading = true;
          findTenderNotice(sid).then(response => {
            console.log(response,"res");
            this.tenderList.push(response.data);
            console.log(this.tenderList,"tenderList");
            this.loading = false;
          });
        },
        getOperator(sid){
           operatorList(sid).then(res=>{
             this.operatorList = res.data;
             console.log(res,"operator");
             console.log(this.operatorList,"list");
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
            yid: null,
            sid: null,
            tdName: null,
            tdPerson: null,
            tdPhone: null,
            email: null,
            yDownloadTime: null,
            tdStatus: null
          };
        },
        handleDetail(){
          this.open = true;
        }
      }
    };
  </script>
