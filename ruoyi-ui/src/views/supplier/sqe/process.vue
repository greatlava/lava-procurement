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
                <el-input type="textarea" v-model="this.opinion"/>
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
                {{ this.hName }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  身份证号
                </template>
                {{ this.hCreditCode }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  手机号
                </template>
                {{ this.hIncorporation }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  邮箱
                </template>
                <el-tag size="small">{{ this.hInstitution }}</el-tag>
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  身份证扫描件
                </template>
                {{ this.hQuality }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  授权书扫描件
                </template>
                {{ this.hStartTime }}
              </el-descriptions-item>
              <el-descriptions-item>
                <template slot="label">
                  座机
                </template>
                {{ this.hJuridical }}
              </el-descriptions-item>
            </el-descriptions>
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="pass" type="primary" v-if="this.fStatus == 0">通过</el-button>
              <el-button @click="overrule" type="danger" v-if="this.fStatus == 0">驳回</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-row>
          </div>
        </el-tab-pane>
        <el-tab-pane label="核心技术人员" name="third">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="personnelList">
              <el-table-column type="index" label="序号" align="center" width="80"/>
              <el-table-column label="姓名" align="center" prop="hName"/>
              <el-table-column label="职位" align="center" prop="hQuality"/>
              <el-table-column label="学历" align="center" prop="hInstitution"/>
              <el-table-column label="职称" align="center" prop="fState"/>
              <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                  <el-button
                    size="small"
                    type="text">编辑
                  </el-button>
                  <el-button
                    size="small"
                    type="text">删除
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="pass" type="primary" v-if="this.fStatus == 0">通过</el-button>
              <el-button @click="overrule" type="danger" v-if="this.fStatus == 0">驳回</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-row>
          </div>
        </el-tab-pane>
        <el-tab-pane label="企业资质" name="fourth">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="personnelList">
              <el-table-column type="index" label="序号" align="center" width="80"/>
              <el-table-column label="证书名称" align="center" prop="hName"/>
              <el-table-column label="证书编号" align="center" prop="hQuality"/>
              <el-table-column label="发证单位" align="center" prop="hInstitution"/>
              <el-table-column label="发证日期" align="center" prop="fState"/>
              <el-table-column label="有效期至" align="center" prop="fState"/>
              <el-table-column label="扫描件" align="center" prop="fState"/>
            </el-table>
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="pass" type="primary" v-if="this.fStatus == 0">通过</el-button>
              <el-button @click="overrule" type="danger" v-if="this.fStatus == 0">驳回</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-row>
          </div>
        </el-tab-pane>
        <el-tab-pane label="业绩" name="fifth">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="personnelList">
              <el-table-column type="index" label="序号" align="center" width="80"/>
              <el-table-column label="采购单位" align="center" prop="hName"/>
              <el-table-column label="合作方" align="center" prop="hQuality"/>
              <el-table-column label="签订日期" align="center" prop="hInstitution"/>
              <el-table-column label="合同金额（万元）" align="center" prop="fState"/>
              <el-table-column label="买方业务代表" align="center" prop="fState"/>
              <el-table-column label="买方业务代表联系电话" align="center" prop="fState"/>
              <el-table-column label="合同扫描件" align="center" prop="fState"/>
            </el-table>
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="pass" type="primary" v-if="this.fStatus == 0">通过</el-button>
              <el-button @click="overrule" type="danger" v-if="this.fStatus == 0">驳回</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-row>
          </div>
        </el-tab-pane>
        <el-tab-pane label="财务状态" name="sixth">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="personnelList">
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
              <el-button @click="pass" type="primary" v-if="this.fStatus == 0">通过</el-button>
              <el-button @click="overrule" type="danger" v-if="this.fStatus == 0">驳回</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-row>
          </div>
        </el-tab-pane>
        <el-tab-pane label="相关附件" name="seventh">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="personnelList">
              <el-table-column type="index" label="序号" align="center" width="80"/>
              <el-table-column label="名称" align="center" prop="hName"/>
              <el-table-column label="附件" align="center" prop="hQuality"/>
            </el-table>
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="pass" type="primary" v-if="this.fStatus == 0">通过</el-button>
              <el-button @click="overrule" type="danger" v-if="this.fStatus == 0">驳回</el-button>
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
  getSupplierByZrId,
  updateSupplier
} from '@/api/system/supplier';
import {delAccess, updateAccess} from "@/api/system/access";

export default {
  components: {},
  name: "detail",
  data() {
    return {
      // 遮罩层
      loading: true,
      zr_id: this.$route.query.zr_id,
      activeName: 'first',
      opinion: '',
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
      fStatus: null,
      fState: null
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
                message: '操作成功！',
                type: 'success'
              });
              this.cancel()
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
            message: '操作成功！',
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
        this.fStatus = response.data.fStatus
      });
      this.loading = false
    }
  }
}
</script>
