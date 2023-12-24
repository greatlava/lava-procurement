<template>
  <div>
    <div class="box">
      <div class="header" height="150px">
        <el-row justify="space-around" class="thear_row" type="flex">
          <el-col class="purchasing_department" :span="5">
            <div class="header_left">
              <div class="purchasing_department_icon"></div>
              <div class="purchasing_department_describe">
                <p>采购部</p>
                <p>The purchasing department</p>
              </div>
            </div>
            <el-divider class="header_divider" content-position="center" direction="vertical"></el-divider>
          </el-col>
          <el-col class="header_right" :span="3">
            <el-statistic
              group-separator=","
              :precision="2"
              decimal-separator="."
              :value="879757698"
              title="采购计划数量"
            ></el-statistic>
          </el-col>
          <el-col class="header_right" :span="3">
            <el-statistic
              group-separator=","
              :precision="2"
              decimal-separator="."
              :value="3453234"
              title="框架计划数量"
            ></el-statistic>
          </el-col>
          <el-col class="header_right" :span="3">
            <el-statistic
              group-separator=","
              :precision="2"
              decimal-separator="."
              :value="678"
              title="新增供应商"
            >
              <template slot="prefix">
                <i class="el-icon-s-flag" style="color: red"></i>
              </template>
              <template slot="suffix">
                <i class="el-icon-s-flag" style="color: blue"></i>
              </template>
            </el-statistic>
          </el-col>
          <el-col class="header_right" :span="3">
            <el-statistic
              group-separator=","
              :precision="2"
              decimal-separator="."
              :value="987543.8754"
              title="总采购金额"
            >
              <template slot="suffix">
                <i
                  class="el-icon-star-on"
                  style="color:red"
                ></i>
              </template>
            </el-statistic>
          </el-col>
          <el-col class="header_right" :span="3">
            <el-statistic
              group-separator=","
              :precision="2"
              decimal-separator="."
              :value="45679"
              title="招标项目数量"
            ></el-statistic>
          </el-col>
          <el-col class="header_right" :span="3">
            <el-statistic
              group-separator=","
              :precision="2"
              decimal-separator="."
              :value="98765"
              title="待审核计划"
            ></el-statistic>
          </el-col>
        </el-row>
      </div>
      <el-row style="height: 510px" :gutter="5" type="flex" class="content">
        <el-col class="main" :span="16">
          <div class="project_Kanban_title">
            <h3>项目看板
              <i @click="refresh_project_Kanban" class="el-icon-refresh-right"></i>
            </h3>
          </div>
          <ul class="conent_left">
            <li v-for="(item,i) in project_Kanban" :key="i">
              <div class="content_top">
                <img
                  :src="item.imgUrl"/>
                <p class="pt-2">{{ item.title }}</p>
              </div>
              <div class="cont">
                <p>
                  共计：<span>{{ item.count }}</span>个
                </p>
                <ul>
                  <a target="_blank" v-for="(e,j) in item.content" :key="j">
                    <li>
                      <h3>{{ e.title }}</h3>
                      <p>编号：{{ e.code }}</p>
                      <p>负责人：{{ e.responsible_person }}</p>
                    </li>
                  </a>
                </ul>
              </div>
            </li>
          </ul>
        </el-col>
        <el-col :span="1" style="width: 5px"></el-col>
        <el-col :span="8">
          <div class="content_rigth_top">
            <el-row>
              <el-col :span="6">
                <router-link :to="'purchase/purchase'">
                  <div class="content_rigth_top_center">
                    <img class="content_rigth_top_img"
                         src="https://enterprise.e-cology.com.cn/page/resource/userfile/image/1caigou001.png" alt="">
                    <div>采购计划</div>
                  </div>
                </router-link>
              </el-col>
              <el-col :span="6">
                <router-link :to="'purchase/procurementSourcing'">
                  <div class="content_rigth_top_center">
                    <img class="content_rigth_top_img"
                         src="https://enterprise.e-cology.com.cn/page/resource/userfile/image/1caigou003.png" alt="">
                    <div>采购寻源</div>
                  </div>
                </router-link>
              </el-col>
              <el-col :span="6">
                <router-link :to="'contract/cm'">
                  <div class="content_rigth_top_center">
                    <img class="content_rigth_top_img"
                         src="https://enterprise.e-cology.com.cn/page/resource/userfile/image/1caigou004.png" alt="">
                    <div>合同管理</div>
                  </div>
                </router-link>
              </el-col>
              <el-col :span="6">
                <router-link :to="'supplier/sqe'">
                  <div class="content_rigth_top_center">
                    <img class="content_rigth_top_img"
                         src="https://enterprise.e-cology.com.cn/page/resource/userfile/image/1caigou005.png" alt="">
                    <div>供应商管理</div>
                  </div>
                </router-link>
              </el-col>

            </el-row>
          </div>
          <div class="content_right_bottom">
            <div class="project_Kanban_title">
              <h3>采购订单
                <router-link :to="'purchase/purchase'">
                  <i style="vertical-align: middle;" class="el-icon-more"></i>
                  <span style="float: right;margin-right: 5px;cursor: pointer">more</span>
                </router-link>
              </h3>
            </div>
            <el-timeline class="content_right_purchase">
              <el-timeline-item timestamp="2018/4/12" placement="top">
                <el-card shadow="never">
                  <h4>张三新增了采购计划</h4>
                  <el-descriptions>
                    <el-descriptions-item label="用户名">kooriookami</el-descriptions-item>
                    <el-descriptions-item label="手机号">18100000000</el-descriptions-item>
                    <el-descriptions-item label="居住地">苏州市</el-descriptions-item>
                    <el-descriptions-item label="备注">
                      <el-tag size="small">学校</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
                  </el-descriptions>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="2018/4/3" placement="top">
                <el-card shadow="never">
                  <h4>张三新增了采购计划</h4>
                  <el-descriptions>
                    <el-descriptions-item label="用户名">kooriookami</el-descriptions-item>
                    <el-descriptions-item label="手机号">18100000000</el-descriptions-item>
                    <el-descriptions-item label="居住地">苏州市</el-descriptions-item>
                    <el-descriptions-item label="备注">
                      <el-tag size="small">学校</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
                  </el-descriptions>
                </el-card>
              </el-timeline-item>
              <el-timeline-item timestamp="2018/4/2" placement="top">
                <el-card shadow="never">
                  <h4>张三新增了采购计划</h4>
                  <el-descriptions>
                    <el-descriptions-item label="用户名">kooriookami</el-descriptions-item>
                    <el-descriptions-item label="手机号">18100000000</el-descriptions-item>
                    <el-descriptions-item label="居住地">苏州市</el-descriptions-item>
                    <el-descriptions-item label="备注">
                      <el-tag size="small">学校</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>
                  </el-descriptions>
                </el-card>
              </el-timeline-item>
            </el-timeline>
          </div>
        </el-col>
      </el-row>
      <el-row type="flex">
        <el-col :span="16">
          <div class="content_foot_left">
            <div class="project_Kanban_title">
              <h3>招标项目
                <router-link :to="'tender/tender1'">
                  <i style="vertical-align: middle;margin-right: 25px" class="el-icon-more"></i>
                  <span style="float: right;margin-right: 5px;cursor: pointer">more</span>
                </router-link>
              </h3>
            </div>
            <el-table max-height="250px"
                      :data="tableData"
                      style="width: 100%">
              <el-table-column
                align="center"
                prop="date"
                label="招标项目名称">
              </el-table-column>
              <el-table-column
                align="center"
                prop="name"
                label="创建时间">
              </el-table-column>
              <el-table-column
                align="center"
                prop="address"
                label="地址">
              </el-table-column>
            </el-table>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col class="supplier_box" :span="24">
          <div style="height: 100%" class="content_foot_left">
            <div class="project_Kanban_title">
              <h3 style="margin-left: 0px">供应商列表
                <router-link :to="'supplier/sqe'">
                  <i style="vertical-align: middle" class="el-icon-more"></i>
                  <span style="float: right;margin-right: 5px;cursor: pointer">more</span>
                </router-link>
              </h3>
            </div>
            <div style="display: flex;flex-wrap: wrap;justify-content: space-between">
              <el-card v-for="item in suppliers" :key="item.index" shadow="hover" class="supplier_list">
                <div class="supper_list_content">
                  <div>
                    <img
                      src="https://gd-hbimg.huaban.com/aae0dae4e98dbd565b5855f37243cfea50bf56461a143-68yMUX_fw658webp"
                      alt="">
                  </div>
                  <div class="supplier_describe div">
                    <p>法人：{{ item.hJuridical }}</p>
                    <h3>{{ item.hName }}</h3>
                    <span
                      class="brief_introduction">公司简介：{{ item.hDesc }}</span>
                  </div>
                  <div class="review_status">
                    <el-tag type="primary">已审核</el-tag>
                  </div>
                </div>
                <div class="supplier_list_foot">
                  <p>
                    <i class="el-icon-location-information"></i>
                    联系地址：{{ item.hAddress }}
                  </p>
                </div>
              </el-card>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import {listSupplier} from "@/api/system/supplier";

export default {
  name: "Index",
  data() {
    return {
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }],
      project_Kanban: [{
        title: "寻源阶段",
        imgUrl: "https://enterprise.e-cology.com.cn/cloudstore/release/3d14457595ef4785a1ee406c5650c01d/resources/iconsCg01.png",
        count: 2,
        content: [{
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }, {
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }]
      }, {
        title: "招标阶段",
        imgUrl: "https://enterprise.e-cology.com.cn/cloudstore/release/3d14457595ef4785a1ee406c5650c01d/resources/iconsCg02.png",
        count: 456,
        content: [{
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }, {
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }, {
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }]
      }, {
        title: "投标阶段",
        imgUrl: "https://enterprise.e-cology.com.cn/cloudstore/release/3d14457595ef4785a1ee406c5650c01d/resources/iconsCg03.png",
        count: 23,
        content: [{
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }, {
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }, {
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }]
      }, {
        title: "比价阶段",
        imgUrl: "https://enterprise.e-cology.com.cn/cloudstore/release/3d14457595ef4785a1ee406c5650c01d/resources/iconsCg04.png",
        count: 31, content: [{
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }, {
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }, {
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }]
      }, {
        title: "评审阶段",
        imgUrl: "https://enterprise.e-cology.com.cn/cloudstore/release/3d14457595ef4785a1ee406c5650c01d/resources/iconsCg05.png",
        count: 73, content: [{
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }, {
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }, {
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }]
      }, {
        title: "中标阶段",
        imgUrl: "https://enterprise.e-cology.com.cn/cloudstore/release/3d14457595ef4785a1ee406c5650c01d/resources/iconsCg06.png",
        count: 35, content: [{
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }, {
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }, {
          title: "2021年维森集团重点电脑采购项目", code: "CGXY2021120027", responsible_person: "张三"
        }]
      }],
      // 版本号
      version: "3.6.3",
      suppliers: []
    };
  },
  created() {
    this.listSupplier()
  },
  methods: {
    refresh_project_Kanban() {

    },
    //显示供应商列表
    listSupplier() {
      listSupplier({"pageNum": 1, "pageSize": 12}).then(res => {
        this.suppliers = res.rows
      })
    }
  },
};
</script>

<style scoped>
ul, li, p {
  padding: 0;
  margin: 0;
}

li {
  list-style: none;
}

.items-center {
  align-items: center;
}

.justify-center {
  justify-content: center;
}

.flex {
  display: flex;
}

.header {
  padding: 0;
  background: #fff;
}

.purchasing_department {
  height: 150px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0px 10px;
}

.project_Kanban_title h3 {
  margin-left: 20px;
  padding: 0;
  color: #556cc5;
  font-size: 14px;
}

.project_Kanban_title i {
  float: right;
  margin-right: 10px;
  cursor: pointer;
}

.box {
  width: 100%;
  height: 100%;
  background-color: #f5f5f5;
}

.header_divider {
  height: 100px;
  margin-top: 15px;
  width: 1px;
}

.purchasing_department_icon {
  width: 100px;
  height: 100px;
  background: url("http://localhost:9610/static/2023/12/13/icon_cg.png") center center no-repeat;
  background-size: 60px;
  display: inline-block;
}

.purchasing_department_describe {
  height: 100px;
  line-height: 100px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.header_left {
  display: flex;
}

.purchasing_department_describe p {
  padding: 0;
  margin: 0;
  height: 20px;
  line-height: 20px;
  font-size: 12px;
}

.thear_row {
  align-items: center;
}


.header_right {
  text-align: center;
}


.main {
  background: #fff;
  margin-top: 10px;
  height: 500px;
}

.cont > p {
  text-align: center;
  background: #F4F6FB;
  padding: 5px 0 7px;
  color: #2B4BAB;
  font-size: 12px;
}

.cont {
  margin-top: 10px;
  border: 1px solid #eee;
  border-radius: 2px;
  height: 100%;
}

.content_top {
  background: #2B4BAB;
  color: #fff;
  text-align: center;
  border-radius: 2px;
  height: 50px;
  line-height: 50px;
}

.conent_left {
  margin-top: 30px;
  display: flex;
  justify-content: space-evenly
}

.conent_left li {
  width: 15%;
  display: inline-block;
  vertical-align: top;
}

.conent_left .cont span {
  font-size: 16px;
  display: inline-block;
  margin-right: 5px;
}

.conent_left li .content_top img {
  width: 20px;
  display: inline-block;
  vertical-align: middle;
  margin-right: 10px;
}


.pt-2 {
  display: inline-block;
  font-size: 14px;
}

.cont ul {
  height: 245px;
  margin: 5px 0 10px;
}

.cont ul li h3 {
  font-size: 14px;
  font-weight: normal;
  margin-bottom: 6px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.cont ul li {
  display: block;
  padding: 10px;
  border-bottom: 1px solid #eee;
  cursor: pointer;
  width: 100%;
}

.cont ul a {
  display: block;
}

.cont ul li p {
  color: #888;
  font-size: 11px;
  margin-bottom: 5px;
}

.content_rigth_top {
  background: #fff;
  margin-top: 10px;
  padding: 40px 0px
}


.content_rigth_top_img {
  width: 38px;
  height: 40px;
  vertical-align: middle;
  margin-bottom: 7px;
}

.content_rigth_top_center {
  cursor: pointer;
  text-align: center;
}

.content_right_bottom {
  margin-top: 10px;
  background: #fff;
  padding: 7px 20px 20px 0px;
}

.content_right_purchase {
  margin-left: 20px;
}


.content_foot_left {
  background: #fff;
  height: 318px;
  margin-top: 10px;
  padding: 10px 0px;
}

.supplier_box {
  background: #fff;
  margin-top: 10px;
  margin-bottom: 50px;
  padding: 20px;
}

.supplier_list {
  width: 500px;
  height: 180px;
  cursor: pointer;
  position: relative;
  margin-top: 15px;
}

.supper_list_content {
  display: flex;
}

.supplier_list img {
  width: 80px;
  height: 80px;
  vertical-align: middle;
}

.supper_list_content > .div {
  display: flex;
  flex-direction: column;
  justify-content: center

}


.supplier_list_foot {
  height: 40px;
  border-top: 1px solid #dadada;
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  line-height: 40px;
  padding-left: 10px;
  display: flex;
}

.supplier_list_foot p i {
  margin-right: 7px;
}

.supplier_list_foot p {
  font-size: 14px;
  color: #9d9d9d;
  display: inline-block;
}

.supplier_describe {
  margin-left: 20px;
}

.supplier_describe p {
  margin: 5px 0px 10px 0px;
  padding: 0;
  font-size: 14px;
  color: #a1a1a1;
}

.supplier_describe h3 {
  margin: 0;
  padding: 0;
  font-size: 16px;
  color: #5e69b6;
  font-weight: bold;
}

.supplier_list_button {
  position: absolute;
  right: 130px;
  top: 15px;
}

.brief_introduction {
  font-size: 14px;
  color: #a1a1a1;
  margin-top: 10px;
}

.review_status {
  position: absolute;
  right: 0;
  top: 0;
}
</style>

