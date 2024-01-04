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
              :value="count.procurementCount"
              title="采购计划数量"
            ></el-statistic>
          </el-col>
          <el-col class="header_right" :span="3">
            <el-statistic
              group-separator=","
              :value="count.farmeworkPlanCount"
              title="框架计划数量"
            ></el-statistic>
          </el-col>
          <el-col class="header_right" :span="3">
            <el-statistic
              group-separator=","
              :value="count.contractCount"
              title="合同数量"
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
              :value="count.totalPurchaseAmount"
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
              :value="count.tenderCount"
              title="招标项目数量"
            ></el-statistic>
          </el-col>
          <el-col class="header_right" :span="3">
            <el-statistic
              group-separator=","
              :value="count.waitingReviewCount"
              title="待审核计划"
            >
              <template slot="prefix">
                <i class="el-icon-s-check"></i>
              </template>
              <template slot="suffix">
                <i class="el-icon-s-check"></i>
              </template>
            </el-statistic>
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
                  共计：<span style="font-weight: bold">{{ item.count }}</span>个
                </p>
                <ul>
                  <el-empty v-if="item.content.length==0" :image-size="100"
                            :description="item.title+'数据是空的'"></el-empty>
                  <a v-else @click.prevent="jumpPage(i,e.id)" href="#" target="_blank" v-for="(e,j) in item.content"
                     :key="j">
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
              <h3>采购计划
                <router-link :to="'purchase/purchase'">
                  <i style="vertical-align: middle;" class="el-icon-more"></i>
                  <span style="float: right;margin-right: 5px;cursor: pointer">more</span>
                </router-link>
              </h3>
            </div>
            <el-timeline class="content_right_purchase">
              <el-timeline-item style="cursor: pointer" @click="clickPlan(item)" v-for="item in listPlan"
                                :timestamp="item.createTime"
                                placement="top">
                <el-card :body-style="{ padding: '12px 20px' }" shadow="never">
                  <h4>{{ item.createBy }}新增了采购计划</h4>
                  <el-descriptions :column="2">
                    <el-descriptions-item label="编号">{{ item.aCode }}</el-descriptions-item>
                    <el-descriptions-item label="创建部门">{{ item.aCreateDept }}</el-descriptions-item>
                    <el-descriptions-item label="计划名称">{{ item.aName }}</el-descriptions-item>
                    <el-descriptions-item label="创建人"> {{ item.createBy }}</el-descriptions-item>
                    <el-descriptions-item label="审核状态">
                      <el-tag size="mini" v-if="item.aAstate == 0" type="info">待提交</el-tag>
                      <el-tag size="mini" v-if="item.aAstate == 1" type="small">待审核</el-tag>
                      <el-tag size="mini" v-if="item.aAstate == 2" type="success">待寻源</el-tag>
                      <el-tag size="mini" v-if="item.aAstate == 3" type="danger">已寻源</el-tag>
                    </el-descriptions-item>
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
            <el-table max-height="250px" :data="listTender" style="width: 100%">
              <el-table-column
                align="center"
                prop="sCode"
                label="项目编号">
              </el-table-column>
              <el-table-column
                align="center"
                prop="sName"
                show-overflow-tooltip
                label="招标项目名称">
              </el-table-column>
              <el-table-column
                align="center"
                prop="sLeader"
                label="负责人">
                <template slot-scope="scope">
                  <el-tooltip v-if="!scope.row.sLeader" content="文本是空的奥，可以前去编辑" placement="top">
                    <span>——</span>
                  </el-tooltip>
                  <span v-else>{{ scope.row.sLeader }}</span>
                </template>
              </el-table-column>
              <el-table-column
                align="center"
                prop="sAddress"
                show-overflow-tooltip
                label="地址">
                <template slot-scope="scope">
                  <el-tooltip v-if="!scope.row.sAddress" content="文本是空的奥，可以前去编辑" placement="top">
                    <span>——</span>
                  </el-tooltip>
                  <span v-else>{{ scope.row.sAddress }}</span>
                </template>
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
            <el-empty description="供应商列表是空的奥！！" v-if="suppliers.length==0" :image-size="200"></el-empty>
            <div style="display: flex;flex-wrap: wrap;justify-content: flex-start;">
              <el-card style="margin-right: 25px" v-for="item in suppliers" shadow="hover" class="supplier_list">
                <div class="supper_list_content">
                  <div>
                    <img
                      src="https://gd-hbimg.huaban.com/aae0dae4e98dbd565b5855f37243cfea50bf56461a143-68yMUX_fw658webp"
                      alt="">
                  </div>
                  <div class="supplier_describe div">
                    <p>法人：{{ item.hJuridical }}</p>
                    <h3>{{ item.hName }}</h3>
                    <textOverflowHiding style="margin-top: 10px"
                                        :text="'公司简介：'+ item.hDesc"
                                        :width="300" v-if="item.hDesc"></textOverflowHiding>
                    <textOverflowHiding style="margin-top: 10px"
                                        :text="'公司简介：待填写~'"
                                        :width="300" v-else></textOverflowHiding>
                  </div>
                  <div class="review_status">
                    <el-tag type="primary">已审核</el-tag>
                  </div>
                </div>
                <div class="supplier_list_foot">
                  <p>
                    <i class="el-icon-location-information"></i>
                    <text-overflow-hiding style="display: inline-block;height: 100%" :text="'联系地址：'+ item.hAddress"
                                          :width="410" v-if="item.hAddress"></text-overflow-hiding>
                    <text-overflow-hiding style="display: inline-block;height: 100%" :text="'联系地址：待填写~'"
                                          :width="410" v-else></text-overflow-hiding>
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
import textOverflowHiding from "@/components/Text/textOverflowHiding.vue";
import {listSupplier} from "@/api/system/supplier";

import {
  selectPpmpProcurementCount,
  selectFarmeworkPlanCount,
  selectContractCount,
  queryTotalPurchaseAmount,
  selectTenderCount,
  listPlan,
  selectTenderByState,
  selectTenderByStateCount
} from '@/api/system/plan'

export default {
  name: "Index",
  components: {
    textOverflowHiding,
  },
  data() {
    return {
      listPlan: [],
      listTender: [],
      count: {
        //采购计划数量
        procurementCount: 0,
        //框架计划数量
        farmeworkPlanCount: 0,
        //合同数量
        contractCount: 0,
        //采购计划总金额
        totalPurchaseAmount: 0,
        //招标项目数量
        tenderCount: 0,
        //待审核采购计划数量
        waitingReviewCount: 0,
      },
      //项目看板
      project_Kanban: [{
        title: "寻源阶段",
        imgUrl: "https://enterprise.e-cology.com.cn/cloudstore/release/3d14457595ef4785a1ee406c5650c01d/resources/iconsCg01.png",
        count: 0,
        content: []
      }, {
        title: "招标阶段",
        imgUrl: "https://enterprise.e-cology.com.cn/cloudstore/release/3d14457595ef4785a1ee406c5650c01d/resources/iconsCg02.png",
        count: 0,
        content: []
      }, {
        title: "投标阶段",
        imgUrl: "https://enterprise.e-cology.com.cn/cloudstore/release/3d14457595ef4785a1ee406c5650c01d/resources/iconsCg03.png",
        count: 0,
        content: []
      }, {
        title: "评审阶段",
        imgUrl: "https://enterprise.e-cology.com.cn/cloudstore/release/3d14457595ef4785a1ee406c5650c01d/resources/iconsCg05.png",
        count: 0, content: []
      }, {
        title: "中标阶段",
        imgUrl: "https://enterprise.e-cology.com.cn/cloudstore/release/3d14457595ef4785a1ee406c5650c01d/resources/iconsCg06.png",
        count: 0, content: []
      }],
      // 版本号
      version: "3.6.3",
      suppliers: []
    };
  },
  created() {
    this.init();
  },
  methods: {
    jumpPage(index, id) {
      switch (index) {
        case 0:
          this.$router.push({path: '/purchase/procurementSourcing', query: {aid: id}})
          break;
        case 1:
          this.$router.push({path: '/tender/bidding', query: {type: 'bidding', sid: id}})
          break;
        case 2:
          this.$router.push({path: '/tender/bidding', query: {type: 'bidding', sid: id}})
          break;
        case 3:
          this.$router.push({path: '/tender/bidding', query: {type: 'bidding', sid: id}})
          break;
        case 4:
          this.$router.push({path: '/tender/bidding', query: {type: 'bidding', sid: id}})
          break;
      }
    },
    init() {
      //查询采购计划数量
      selectPpmpProcurementCount(null).then(res => {
        this.count.procurementCount = res;
      })
      //查询框架计划数量
      selectFarmeworkPlanCount().then(res => {
        this.count.farmeworkPlanCount = res.data;
      })
      //查询合同数量
      selectContractCount().then(res => {
        this.count.contractCount = res.data;
      })
      //查询采购计划总价格
      queryTotalPurchaseAmount().then(res => {
        this.count.totalPurchaseAmount = res.data;
      })
      //查询招标项目数量
      selectTenderCount().then(res => {
        this.count.tenderCount = res.data;
      })
      //查询待审核采购计划数量
      selectPpmpProcurementCount(2).then(res => {
        this.count.waitingReviewCount = res;
      })

      //项目看板
      this.selectPoject_kanban();
      //采购订单
      this.selectNewPlan();
      //招标项目
      this.selectListTender();
      //供应商列表
      this.listSupplier()
    },
    //项目看板
    selectPoject_kanban() {
      //查询寻源阶段数量
      selectPpmpProcurementCount(2).then(res => {
        this.project_Kanban[0].count = res;
      })
      //查询项目看板寻源阶段
      listPlan({pageSize: 3, aAstate: 2}).then(res => {
        res.rows.forEach((e, i) => {
          let obj = {
            title: e.aName,
            code: e.aCode,
            responsible_person: e.createBy,
            id: e.aid
          };
          this.project_Kanban[0].content.push(obj);
        })
      })


      //查询招标项目数量
      selectTenderByStateCount({sProjectState: 2}).then(res => {
        this.project_Kanban[1].count = res.data;
      })
      //查询招标项目阶段项目看板
      selectTenderByState({sProjectState: 2}).then(res => {
        res.forEach((e, i) => {
          let obj = {
            title: e.sName,
            code: e.sCode,
            responsible_person: e.sLeader,
            id: e.sid
          };
          this.project_Kanban[1].content.push(obj);
        })
      })


      //查询投标项目数量
      selectTenderByStateCount({sProjectState: 3}).then(res => {
        this.project_Kanban[2].count = res.data;
      })
      //查询投标项目阶段项目看板
      selectTenderByState({sProjectState: 3}).then(res => {
        res.forEach((e, i) => {
          let obj = {
            title: e.sName,
            code: e.sCode,
            responsible_person: e.sLeader,
            id: e.sid
          };
          this.project_Kanban[2].content.push(obj);
        })
      })


      //查询评审项目数量
      selectTenderByStateCount({sProjectState: 5}).then(res => {
        this.project_Kanban[3].count = res.data;
      })
      //查询评审阶段项目看板
      selectTenderByState({sProjectState: 5}).then(res => {
        res.forEach((e, i) => {
          let obj = {
            title: e.sName,
            code: e.sCode,
            responsible_person: e.sLeader,
            id: e.sid
          };
          this.project_Kanban[3].content.push(obj);
        })
      })

      selectTenderByStateCount({sProjectState: 6}).then(res => {
        this.project_Kanban[4].count = res.data;
      })
      //查询定标阶段项目看板
      selectTenderByState({sProjectState: 6}).then(res => {
        res.forEach((e, i) => {
          let obj = {
            title: e.sName,
            code: e.sCode,
            responsible_person: e.sLeader,
            id: e.sid
          };
          this.project_Kanban[4].content.push(obj);
        })
      })
    },
    //查询最新的采购计划
    selectNewPlan() {
      listPlan({pageSize: 3}).then(res => {
        this.listPlan = res.rows;
      })
    },
    //刷新项目看板
    refresh_project_Kanban() {
      this.project_Kanban[0].content = [];
      this.project_Kanban[1].content = [];
      this.project_Kanban[2].content = [];
      this.project_Kanban[3].content = [];
      this.project_Kanban[4].content = [];

      this.project_Kanban[0].count = 0;
      this.project_Kanban[1].count = 0;
      this.project_Kanban[2].count = 0;
      this.project_Kanban[3].count = 0;
      this.project_Kanban[4].count = 0;
      this.selectPoject_kanban();
    },
    //招标项目
    selectListTender() {
      selectTenderByState({}).then(res => {
        this.listTender = res;
        console.log("listTender", res)
      })
    },
    //显示供应商列表
    listSupplier() {
      listSupplier({"pageNum": 1, "pageSize": 9}).then(res => {
        this.suppliers = res.rows
      })
    },
    clickPlan(item) {
      console.log("item", item)
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
  width: 17%;
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
  height: 150px;
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
  display: flex;
  align-items: center;
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
  color: #091044;
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

