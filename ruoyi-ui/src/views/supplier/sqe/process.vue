<template>
  <div class="app-container">
    <div class="tcl">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="基本信息" name="first">
          <div style="padding: 20px 100px">
            <el-descriptions class="margin-top" size="medium" :column="2" border>
              <el-descriptions-item>
                <template slot="label">
                  供应商名称
                </template>
                {{ this.hName }}
                <el-tag size="small" style="margin-left: 10px" v-if="this.fStatus == 2"
                        type="danger">已驳回
                </el-tag>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  统一社会信用代码
                </template>
                {{ this.hCreditCode }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  单位注册地
                </template>
                {{ this.hIncorporation }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  机构类型
                </template>
                <span v-if="this.hInstitution == null" style="color: #cccccc">待填写</span>
                <el-tag size="small" v-else>{{ this.hInstitution }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  企业性质
                </template>
                <span v-if="this.hQuality == null" style="color: #cccccc">待填写</span>
                <span v-else>{{ this.hQuality }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  成立日期
                </template>
                {{ this.hStartTime }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  法人/负责人
                </template>
                {{ this.hJuridical }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  法人/负责人身份证号
                </template>
                {{ this.hJuridicalIdentity }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  单位联系地址
                </template>
                <span v-if="this.hAddress == null" style="color: #cccccc">待填写</span>
                <span v-else>{{ this.hAddress }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  公司简介
                </template>
                <span v-if="this.hDesc == null" style="color: #cccccc">待填写</span>
                <span v-else>{{ this.hDesc }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  营业执照扫描件
                </template>
                <div class="demo-image__preview">
                  <el-image
                    style="width: 100px; height: 100px"
                    fit="contain"
                    :src="hCopies"
                    :preview-src-list="hCopiesList">
                  </el-image>
                </div>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  法人/负责人身份证扫描件
                </template>
                <div class="demo-image__preview">
                  <el-image
                    style="width: 100px; height: 100px"
                    :src="idCardCopy"
                    fit="contain"
                    :preview-src-list="idCardCopyList">
                  </el-image>
                </div>
              </el-descriptions-item>
            </el-descriptions>
          </div>
          <div style="padding:0 100px;text-align: center">
            <p style="color: red;font-size: 12px;text-align: left">注: 开票信息专用</p>
            <el-descriptions class="margin-top" size="medium" :column="2" border>
              <el-descriptions-item>
                <template slot="label">
                  开户行
                </template>
                <span v-if="this.hBank == null" style="color: #cccccc">待填写</span>
                <span v-else>{{ this.hBank }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  银行账号
                </template>
                <span v-if="this.hAccount == null" style="color: #cccccc">待填写</span>
                <span v-else>{{ this.hAccount }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  开户行地址
                </template>
                <span v-if="this.hBankAddress == null" style="color: #cccccc">待填写</span>
                <span v-else>{{ this.hBankAddress }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  单位注册电话
                </template>
                <span v-if="this.hSignPhone == null" style="color: #cccccc">待填写</span>
                <span v-else>{{ this.hSignPhone }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  注册资本（万元）
                </template>
                <span v-if="this.hCapital == null" style="color: #cccccc">待填写</span>
                <span v-else>{{ this.hCapital }}</span>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  实缴资本（万元）
                </template>
                <span v-if="this.hActualCapital == null" style="color: #cccccc">待填写</span>
                <span v-else>{{ this.hActualCapital }}</span>
              </el-descriptions-item>
            </el-descriptions>
            <el-form style="margin-top: 20px" label-width="80px">
              <el-form-item label="审核意见">
                <el-input
                  type="textarea"
                  placeholder="请输入审核意见"
                  v-model="opinion">
                </el-input>
              </el-form-item>
            </el-form>
            <el-row>
              <el-button @click="pass" type="primary" v-if="this.fStatus == 0">通过</el-button>
              <el-button @click="overrule" type="danger" v-if="this.fStatus == 0">驳回</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-row>
          </div>
        </el-tab-pane>
        <el-tab-pane label="业务经办人信息" name="second">
          <div style="padding: 20px 100px">
            <el-descriptions class="margin-top" size="medium" :column="2" border>
              <el-descriptions-item>
                <template slot="label">
                  姓名
                </template>
                {{ this.operator.ywName }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  身份证号
                </template>
                {{ this.operator.ywIdcrad }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  手机号
                </template>
                {{ this.operator.ywPhone }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  邮箱
                </template>
                {{ this.operator.ywMailbox }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  身份证扫描件
                </template>
                <div class="demo-image__preview">
                  <el-image
                    style="width: 100px; height: 100px"
                    :src="ywIdCardCopy"
                    fit="contain"
                    :preview-src-list="ywIdCardCopyList">
                  </el-image>
                </div>
              </el-descriptions-item>
            </el-descriptions>
          </div>
        </el-tab-pane>
        <el-tab-pane label="核心技术人员" name="third">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="personnelList">
              <el-table-column type="index" label="序号" align="center" width="80"/>
              <el-table-column label="姓名" align="center" prop="jsName"/>
              <el-table-column label="职位" align="center" prop="jsPosition"/>
              <el-table-column label="学历" align="center" prop="jsEducation"/>
              <el-table-column label="职称" align="center" prop="jsTitle"/>
            </el-table>
            <pagination
              v-show="total>0"
              :total="total"
              :page.sync="personnel.pageNum"
              :limit.sync="personnel.pageSize"
              @pagination="personnelList"
            />
          </div>
        </el-tab-pane>
        <el-tab-pane label="企业资质" name="fourth">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="enterpriseList">
              <el-table-column type="index" label="序号" align="center" width="80"/>
              <el-table-column label="证书名称" align="center" prop="zzCertName"/>
              <el-table-column label="证书编号" align="center" prop="zzCertNo"/>
              <el-table-column label="发证单位" align="center" prop="zzUnit"/>
              <el-table-column label="发证日期" align="center" prop="zzDate"/>
              <el-table-column label="有效期至" align="center" prop="zzExpirationDate"/>
            </el-table>
            <pagination
              v-show="total1>0"
              :total="total1"
              :page.sync="enterprise.pageNum"
              :limit.sync="enterprise.pageSize"
              @pagination="enterpriseList"
            />
          </div>
        </el-tab-pane>
        <el-tab-pane label="业绩" name="fifth">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="achievementList">
              <el-table-column type="index" label="序号" align="center" width="80"/>
              <el-table-column label="采购单位" align="center" prop="yjUnit"/>
              <el-table-column label="合作方" align="center" prop="yjPartner"/>
              <el-table-column label="签订日期" align="center" prop="yjDate"/>
              <el-table-column label="合同金额（万元）" align="center" prop="yjRmb"/>
              <el-table-column label="买方业务代表" align="center" prop="yjBbr"/>
              <el-table-column label="买方业务代表联系电话" align="center" prop="yjPhone"/>
            </el-table>
            <pagination
              v-show="total2>0"
              :total="total2"
              :page.sync="achievement.pageNum"
              :limit.sync="achievement.pageSize"
              @pagination="achievementList"
            />
          </div>
        </el-tab-pane>
        <el-tab-pane label="财务状态" name="sixth">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="financialStatusList">
              <el-table-column type="index" label="序号" align="center" width="80"/>
              <el-table-column label="年度" align="center" prop="cAnnual"/>
              <el-table-column label="净利润（万元）" align="center" prop="cNetProfit"/>
              <el-table-column label="资产负债率（%）" align="center" prop="cLev"/>
            </el-table>
            <pagination
              v-show="total3>0"
              :total="total3"
              :page.sync="financialStatus.pageNum"
              :limit.sync="financialStatus.pageSize"
              @pagination="financialStatusList"
            />
          </div>
        </el-tab-pane>
        <el-tab-pane label="相关附件" name="seventh">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="accessoriesList">
              <el-table-column type="index" label="序号" align="center" width="80"/>
              <el-table-column label="名称" align="center" prop="fjName"/>
              <el-table-column label="附件" align="center" prop="fjAnnex"/>
            </el-table>
            <pagination
              v-show="total4>0"
              :total="total4"
              :page.sync="accessories.pageNum"
              :limit.sync="accessories.pageSize"
              @pagination="accessoriesList"
            />
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import {
  getSupplierByZrId,
  updateSupplier
} from '@/api/system/supplier';
import {delAccess, updateAccess} from "@/api/system/access";
import {getOperator} from "@/api/system/operator";
import {listPersonnel} from "@/api/system/personnel";
import {listEnterprise} from "@/api/system/enterprise";
import {listAchievement} from "@/api/system/achievement";
import {listStatus} from "@/api/system/financialStatus";
import {listAccessories} from "@/api/system/accessories";

export default {
  components: {},
  name: "detail",
  data() {
    return {
      // 遮罩层
      loading: true,
      zr_id: this.$route.query.zr_id,
      activeName: 'first',
      opinion: null,
      hid: 0,
      zrId: 0,
      hName: null,
      hCreditCode: null,
      hIncorporation: null,
      hInstitution: null,
      hQuality: null,
      hStartTime: null,
      hJuridical: null,
      hJuridicalIdentity: null,
      hAddress: null,
      hRange: null,
      hDesc: null,
      hJuridicalCopies: null,
      hExpiration: null,
      hBank: null,
      hAccount: null,
      hBankAddress: null,
      hSignPhone: null,
      hCapital: null,
      hActualCapital: null,
      hProve: null,
      fStatus: null,
      fState: null,
      //核心技术人员
      personnelList: [],
      //企业资质列表
      enterpriseList: [],
      //业绩
      achievementList: [],
      //财务状态
      financialStatusList: [],
      //相关附件
      accessoriesList: [],
      //营业执照
      hCopies: '',
      hCopiesList: [],
      //法人身份证
      idCardCopy: '',
      idCardCopyList: [],
      //业务经办人身份证
      ywIdCardCopy: '',
      ywIdCardCopyList: [],
      //业务经办人
      operator: {
        ywName: null,
        ywPhone: null,
        ywIdcrad: null,
        ywMailbox: null,
        ywScanIdcard: null,
      },
      //核心技术人员
      total: 0,
      personnel: {
        pageNum: 1,
        pageSize: 10,
        hid: 0,
      },
      //企业资质
      total1: 0,
      enterprise: {
        pageNum: 1,
        pageSize: 10,
        hid: 0,
      },
      //业绩
      total2: 0,
      achievement: {
        pageNum: 1,
        pageSize: 10,
        hid: this.$route.query.hid,
      },
      //财务状态
      total3: 0,
      financialStatus: {
        pageNum: 1,
        pageSize: 10,
        hid: this.$route.query.hid,
      },
      //相关附件
      total4: 0,
      accessories: {
        pageNum: 1,
        pageSize: 10,
        hid: this.$route.query.hid,
      }
    }
  },
  created() {
    this.query()
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    cancel() {
      this.$router.back()
    },
    pass() {
      this.fStatus = 1;
      updateSupplier({
        "hid": this.hid,
        "fStatus": this.fStatus,
        "zrId": this.zrId,
        "fOpinion": this.opinion
      }).then(response => {
        this.loading = true
        console.log(response);
        if (response.code == 200) {
          delAccess(this.zr_id).then(res => {
            if (res.code == 200) {
              this.$message({
                message: '通过成功！',
                type: 'success'
              });
              window.close()
            }
          })
        }
      });
      this.loading = false
    },
    overrule() {
      this.fStatus = 2;
      updateSupplier({"hid": this.hid, "fStatus": this.fStatus, "fOpinion": this.opinion}).then(response => {
        this.loading = true
        if (response.code == 200) {
          this.$message({
            message: '驳回成功！',
            type: 'success'
          });
        }
      });
      this.loading = false
    },
    query() {
      getSupplierByZrId(this.zr_id).then(response => {
        console.log(response)
        this.hid = response.data.hid
        this.hName = response.data.hName
        this.hCreditCode = response.data.hCreditCode
        this.hIncorporation = response.data.hIncorporation
        this.hInstitution = response.data.hInstitution
        this.hQuality = response.data.hQuality
        this.hStartTime = response.data.hStartTime
        this.hJuridical = response.data.hJuridical
        this.hJuridicalIdentity = response.data.hJuridicalIdentity
        this.hAddress = response.data.hAddress
        this.hDesc = response.data.hDesc
        this.hBank = response.data.hBank
        this.hBankAddress = response.data.hBankAddress
        this.hSignPhone = response.data.hSignPhone
        this.hCapital = response.data.hCapital
        this.hActualCapital = response.data.hActualCapital
        this.fState = response.data.fState
        this.fStatus = response.data.fStatus
        this.hCopies = JSON.parse(response.data.hCopies)[0].url
        this.hCopiesList.push(JSON.parse(response.data.hCopies)[0].url)
        this.idCardCopy = JSON.parse(response.data.hJuridicalCopies)[0].url
        for (let i = 0; i < JSON.parse(response.data.hJuridicalCopies).length; i++) {
          this.idCardCopyList.push(JSON.parse(response.data.hJuridicalCopies)[i].url)
        }
        //业务经办人
        getOperator(this.hid).then(res => {
          this.operator.ywName = res.data.ywName
          this.operator.ywPhone = res.data.ywPhone
          this.operator.ywIdcrad = res.data.ywIdcrad
          this.operator.ywMailbox = res.data.ywMailbox
          this.operator.ywScanIdcard = res.data.ywScanIdcard
          this.ywIdCardCopy = JSON.parse(res.data.ywScanIdcard)[0].url
          for (let i = 0; i < JSON.parse(res.data.ywScanIdcard).length; i++) {
            this.ywIdCardCopyList.push(JSON.parse(res.data.ywScanIdcard)[i].url)
          }
        })
        //核心技术人员
        this.personnel.hid = response.data.hid
        listPersonnel(this.personnel).then(res => {
          console.log(res)
          this.personnelList = res.rows;
          this.total = res.total;
        })
        //企业资质
        this.enterprise.hid = response.data.hid
        listEnterprise(this.enterprise).then(res => {
          console.log(res)
          this.enterpriseList = res.rows;
          this.total1 = res.total;
        })
        //业绩
        this.achievement.hid = response.data.hid
        listAchievement(this.achievement).then(res => {
          console.log(res)
          this.achievementList = res.rows;
          this.total2 = res.total;
        })
        //财务状态
        this.financialStatus.hid = response.data.hid
        listStatus(this.financialStatus).then(res => {
          console.log(res)
          this.financialStatusList = res.rows;
          this.total3 = res.total;
        })
        //相关附件
        this.accessories.hid = response.data.hid
        listAccessories(this.accessories).then(res => {
          console.log(res)
          this.accessoriesList = res.rows;
          this.total4 = res.total;
        })
      });
      this.loading = false
    }
  }
}
</script>
