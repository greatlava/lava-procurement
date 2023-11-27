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
                <el-tag size="small" style="margin-left: 10px" v-if="this.fState == 1 && this.zr_id == 0"
                        type="success">合格
                </el-tag>
                <el-tag size="small" style="margin-left: 10px" v-if="this.fState == 2 && this.zr_id == 0" type="danger">
                  不合格
                </el-tag>
                <el-tag size="small" style="margin-left: 10px" v-if="this.fState == 3 && this.zr_id == 0" type="info">
                  黑名单
                </el-tag>
                <el-tag size="small" style="margin-left: 10px" v-if="this.fStatus == 2 && this.zr_id != 0"
                        type="danger">
                  已驳回
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
                <el-tag size="small">{{ this.hInstitution }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  企业性质
                </template>
                {{ this.hQuality }}
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
                {{ this.hAddress }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  经营范围
                </template>
                {{ this.hRange }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  公司简介
                </template>
                {{ this.hDesc }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  营业执照扫描件
                </template>
                江苏省苏州市吴中区吴中大道 1188 号
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  营业执照有效期
                </template>
                {{ this.hExpiration }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  法人/负责人身份证扫描件
                </template>
                江苏省苏州市吴中区吴中大道 1188 号
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
                {{ this.hBank }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  银行账号
                </template>
                {{ this.hAccount }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  开户行地址
                </template>
                {{ this.hBankAddress }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  单位注册电话
                </template>
                {{ this.hSignPhone }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  单位注册地址
                </template>
                {{ this.hSignAddress }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  注册资本（万元）
                </template>
                {{ this.hCapital }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  实缴资本（万元）
                </template>
                {{ this.hActualCapital }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  开户许可证/基本户存款信息证明
                </template>
                江苏省苏州市吴中区吴中大道 1188 号
              </el-descriptions-item>
            </el-descriptions>
            <el-form style="margin-top: 20px" label-width="80px">
              <el-form-item label="审核意见">
                <el-input type="textarea" v-model="this.opinion" disabled/>
              </el-form-item>
            </el-form>
            <el-row>
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
                {{ this.operator.ywScanIdcard }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  授权书扫描件
                </template>
                {{ this.operator.ywScanEmpower }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  座机
                </template>
                {{ this.operator.ywLandline }}
              </el-descriptions-item>
            </el-descriptions>
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="cancel">取消</el-button>
            </el-row>
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
              <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                  <el-button
                    size="small"
                    type="text"
                    @click="handleUpdate(scope.row)">编辑
                  </el-button>
                  <el-button
                    size="small"
                    type="text"
                    @click="handleDelete(scope.row)">删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
            <pagination
              v-show="total>0"
              :total="total"
              :page.sync="personnel.pageNum"
              :limit.sync="personnel.pageSize"
              @pagination="personnelList"
            />
            <!-- 添加或修改核心技术人员对话框 -->
            <el-dialog title="编辑" :visible.sync="open" width="500px" append-to-body>
              <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="姓名" prop="jsName">
                  <el-input v-model="form.jsName" placeholder="请输入姓名"/>
                </el-form-item>
                <el-form-item label="职位" prop="jsPosition">
                  <el-input v-model="form.jsPosition" placeholder="请输入职位"/>
                </el-form-item>
                <el-form-item label="学历" prop="jsEducation">
                  <el-input v-model="form.jsEducation" placeholder="请输入学历"/>
                </el-form-item>
                <el-form-item label="职称" prop="jsTitle">
                  <el-input v-model="form.jsTitle" placeholder="请输入职称"/>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="qx">取 消</el-button>
              </div>
            </el-dialog>
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="cancel">取消</el-button>
            </el-row>
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
              <el-table-column label="扫描件" align="center" prop="zzScan"/>
            </el-table>
            <pagination
              v-show="total1>0"
              :total="total1"
              :page.sync="enterprise.pageNum"
              :limit.sync="enterprise.pageSize"
              @pagination="enterpriseList"
            />
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="cancel">取消</el-button>
            </el-row>
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
              <el-table-column label="合同扫描件" align="center" prop="yjScanContract"/>
            </el-table>
            <pagination
              v-show="total2>0"
              :total="total2"
              :page.sync="achievement.pageNum"
              :limit.sync="achievement.pageSize"
              @pagination="achievementList"
            />
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="cancel">取消</el-button>
            </el-row>
          </div>
        </el-tab-pane>
        <el-tab-pane label="财务状态" name="sixth">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="List">
              <el-table-column type="index" label="序号" align="center" width="80"/>
              <el-table-column label="年度" align="center" prop="hName"/>
              <el-table-column label="净利润（万元）" align="center" prop="hQuality"/>
              <el-table-column label="资产负债率（%）" align="center" prop="hInstitution"/>
              <el-table-column label="财务审计报告扫描件" align="center" prop="fState"/>
              <el-table-column label="附件审计报告" align="center" prop="fState"/>
              <el-table-column label="资产负债表扫描件" align="center" prop="fState"/>
              <el-table-column label="利润表扫描件" align="center" prop="fState"/>
              <el-table-column label="现金流量表扫描件" align="center" prop="fState"/>
            </el-table>
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="cancel">取消</el-button>
            </el-row>
          </div>
        </el-tab-pane>
        <el-tab-pane label="相关附件" name="seventh">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="List">
              <el-table-column type="index" label="序号" align="center" width="80"/>
              <el-table-column label="名称" align="center" prop="hName"/>
              <el-table-column label="附件" align="center" prop="hQuality"/>
            </el-table>
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="cancel">取消</el-button>
            </el-row>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import {
  getSupplier,
  getSupplierByZrId
} from '@/api/system/supplier';
import {getOperator} from "@/api/system/operator";
import {delPersonnel, getPersonnel, listPersonnel, updatePersonnel} from "@/api/system/personnel";
import {listEnterprise} from "@/api/system/enterprise";
import {listAchievement} from "@/api/system/achievement";

export default {
  components: {},
  name: "detail",
  data() {
    return {
      // 是否显示弹出层
      open: false,
      // 遮罩层
      loading: true,
      hid: this.$route.query.hid,
      zr_id: this.$route.query.zr_id,
      activeName: 'first',
      opinion: '',
      zrId: null,
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
      hCopies: null,
      hJuridicalCopies: null,
      hExpiration: null,
      hBank: null,
      hAccount: null,
      hBankAddress: null,
      hSignPhone: null,
      hSignAddress: null,
      hCapital: null,
      hActualCapital: null,
      hProve: null,
      fState: null,
      fStatus: null,
      //业务经办人
      operator: {
        ywName: null,
        ywPhone: null,
        ywIdcrad: null,
        ywMailbox: null,
        ywScanIdcard: null,
        ywScanEmpower: null,
        ywLandline: null
      },
      //核心技术人员
      personnelList: [],
      //企业资质列表
      enterpriseList: [],
      //业绩
      achievementList: [],
      //核心技术人员
      total: 0,
      personnel: {
        pageNum: 1,
        pageSize: 10,
        hid: this.$route.query.hid,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      //企业资质
      total1: 0,
      enterprise: {
        pageNum: 1,
        pageSize: 10,
        hid: this.$route.query.hid,
      },
      //业绩
      total2: 0,
      achievement: {
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
    /** 删除按钮操作 */
    handleDelete(row) {
      const jsIds = row.jsId
      this.$modal.confirm('是否确认删除该核心技术人员的数据项？').then(function () {
        return delPersonnel(jsIds);
      }).then(() => {
        this.query();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          updatePersonnel(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.query();
          });
        }
      });
    },
    // 表单重置
    reset() {
      this.form = {
        jsId: null,
        jsName: null,
        jsPosition: null,
        jsEducation: null,
        jsTitle: null
      };
      this.resetForm("form");
    },
    // 取消按钮
    qx() {
      this.open = false;
      this.reset();
    },
    handleUpdate(row) {
      this.reset()
      getPersonnel(row.jsId).then(response => {
        this.form = response.data
        this.open = true
      })
    },
    query() {
      if (this.zr_id == 0) {
        getSupplier(this.hid).then(response => {
          this.opinion = response.data.fOpinion
          this.hName = response.data.hName
          this.hCreditCode = response.data.hCreditCode
          this.hIncorporation = response.data.hIncorporation
          this.hInstitution = response.data.hInstitution
          this.hQuality = response.data.hQuality
          this.hStartTime = response.data.hStartTime
          this.hJuridical = response.data.hJuridical
          this.hJuridicalIdentity = response.data.hJuridicalIdentity
          this.hAddress = response.data.hAddress
          this.hRange = response.data.hRange
          this.hDesc = response.data.hDesc
          this.hExpiration = response.data.hExpiration
          this.hBank = response.data.hBank
          this.hBankAddress = response.data.hBankAddress
          this.hSignPhone = response.data.hSignPhone
          this.hSignAddress = response.data.hSignAddress
          this.hCapital = response.data.hCapital
          this.hActualCapital = response.data.hActualCapital
          this.fState = response.data.fState
          this.hAccount = response.data.hAccount
        });
      } else {
        getSupplierByZrId(this.zr_id).then(response => {
          this.opinion = response.data.fOpinion
          this.hName = response.data.hName
          this.hCreditCode = response.data.hCreditCode
          this.hIncorporation = response.data.hIncorporation
          this.hInstitution = response.data.hInstitution
          this.hQuality = response.data.hQuality
          this.hStartTime = response.data.hStartTime
          this.hJuridical = response.data.hJuridical
          this.hJuridicalIdentity = response.data.hJuridicalIdentity
          this.hAddress = response.data.hAddress
          this.hRange = response.data.hRange
          this.hDesc = response.data.hDesc
          this.hExpiration = response.data.hExpiration
          this.hBank = response.data.hBank
          this.hBankAddress = response.data.hBankAddress
          this.hSignPhone = response.data.hSignPhone
          this.hSignAddress = response.data.hSignAddress
          this.hCapital = response.data.hCapital
          this.hActualCapital = response.data.hActualCapital
          this.fState = response.data.fState
          this.hAccount = response.data.hAccount
          this.fStatus = response.data.fStatus
        });
      }
      //业务经办人
      getOperator(this.hid).then(response => {
        this.operator.ywName = response.data.ywName
        this.operator.ywPhone = response.data.ywPhone
        this.operator.ywIdcrad = response.data.ywIdcrad
        this.operator.ywMailbox = response.data.ywMailbox
        this.operator.ywScanIdcard = response.data.ywScanIdcard
        this.operator.ywScanEmpower = response.data.ywScanEmpower
        this.operator.ywLandline = response.data.ywLandline
      })
      //核心技术人员
      listPersonnel(this.personnel).then(response => {
        console.log(response)
        this.personnelList = response.rows;
        this.total = response.total;
      })
      //企业资质
      listEnterprise(this.enterprise).then(response => {
        console.log(response)
        this.enterpriseList = response.rows;
        this.total1 = response.total;
      })
      //业绩
      listAchievement(this.achievement).then(response => {
        console.log(response)
        this.achievementList = response.rows;
        this.total2 = response.total;
      })
      this.loading = false
    }
  }
}
</script>
