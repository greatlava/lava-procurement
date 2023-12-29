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
                <el-input type="textarea" v-model="this.opinion" :readonly="true"/>
              </el-form-item>
            </el-form>
            <el-row>
              <el-button @click="cancel">返回</el-button>
            </el-row>
            <!--            <el-upload ref="upload" class="upload-demo" :limit="4" accept=".doc, .docx, .rar, .txt, .png, .jpg"-->
            <!--                       multiple-->
            <!--                       :action="upload.url"-->
            <!--                       :headers="upload.headers" :file-list="upload.fileList" :before-remove="beforeRemove"-->
            <!--                       :on-progress="handleFileUploadProgress"-->
            <!--                       :on-success="handleFileSuccess" :auto-upload="false">-->
            <!--              <el-button slot="trigger" size="small" type="primary">选取文件</el-button>-->
            <!--              <el-button style="margin-left: 10px;" size="small" type="success" :loading="upload.isUploading"-->
            <!--                         @click="submitUpload">上传到服务器-->
            <!--              </el-button>-->
            <!--              <div slot="tip" class="el-upload__tip">只能上传.doc, .docx, .rar, .txt, .png, .jpg文件，且不超过5MB</div>-->
            <!--            </el-upload>-->
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
              <!--              <el-descriptions-item>-->
              <!--                <template slot="label">-->
              <!--                  授权书扫描件-->
              <!--                </template>-->
              <!--                {{ this.operator.ywScanEmpower }}-->
              <!--              </el-descriptions-item>-->
              <!--              <el-descriptions-item>-->
              <!--                <template slot="label">-->
              <!--                  座机-->
              <!--                </template>-->
              <!--                {{ this.operator.ywLandline }}-->
              <!--              </el-descriptions-item>-->
            </el-descriptions>
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="cancel">返回</el-button>
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
              <el-button @click="cancel">返回</el-button>
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
              <el-button @click="cancel">返回</el-button>
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
              <el-button @click="cancel">返回</el-button>
            </el-row>
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
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="cancel">返回</el-button>
            </el-row>
          </div>
        </el-tab-pane>
        <el-tab-pane label="相关附件" name="seventh">
          <div style="padding: 20px 100px">
            <el-table stripe v-loading="loading" :data="accessoriesList">
              <el-table-column type="index" label="序号" align="center" width="80"/>
              <el-table-column label="名称" align="center" prop="fjName"/>
              <el-table-column
                label="附件" align="center">
                <template slot-scope="scope">
                  <p v-for="i in JSON.parse(scope.row.fjAnnex)" v-if="scope.row.fjAnnex != null">
                    <a :href="i.url" target="_blank">{{ i.name }}</a>
                  </p>
                  <p v-if="scope.row.fjAnnex == null" style="color: #409EFF">---</p>
                </template>
              </el-table-column>
              <el-table-column
                label="操作" align="center">
                <template slot-scope="scope">
                  <el-button type="primary" v-if="scope.row.fjAnnex != null"
                             @click="download(JSON.parse(scope.row.fjAnnex))">
                    下载附件<i class="el-icon-download"></i>
                  </el-button>
                  <p v-if="scope.row.fjAnnex == null" style="color: #409EFF">---</p>
                </template>
              </el-table-column>
            </el-table>
            <pagination
              v-show="total4>0"
              :total="total4"
              :page.sync="accessories.pageNum"
              :limit.sync="accessories.pageSize"
              @pagination="accessoriesList"
            />
          </div>
          <div style="text-align: center;padding: 0 100px">
            <el-row>
              <el-button @click="cancel">返回</el-button>
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
import {listStatus} from "@/api/system/financialStatus";
import {listAccessories} from "@/api/system/accessories";
import {
  getToken
} from "@/utils/auth";

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
      hJuridicalCopies: null,
      hExpiration: null,
      hBank: null,
      hAccount: null,
      hBankAddress: null,
      hSignPhone: null,
      hCapital: null,
      hActualCapital: null,
      hProve: null,
      fState: null,
      fStatus: null,
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
        ywScanIdcard: null
      },
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
      },

      // 上传参数
      upload: {
        // 上传的文件列表
        fileList: [],

        // 是否禁用上传
        isUploading: false,
        // 设置上传的请求头部
        headers: {
          Authorization: "Bearer " + getToken()
        },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/basic/supplier/upload1",
      }
    }
  },
  created() {
    this.query()
  },
  watch: {
    '$route.query.hid': function (newHid, oldHid) {
      if (newHid != oldHid) {
        this.hid = newHid;
        this.personnel.hid = newHid
        this.enterprise.hid = newHid
        this.achievement.hid = newHid
        this.financialStatus.hid = newHid
        this.accessories.hid = newHid
        this.query()
      }
    }
  },
  methods: {
    download(fileUrls) {
      if (fileUrls.length == 0) {
        this.$message.error("没有附件可下载，请上传附件！！")
        return;
      }
      let files = fileUrls.map(function (obj) {
        return obj.url
      })
      let urls = files.join(',')
      let name = encodeURIComponent(urls);
      var url = `http://localhost:8080/basic/supplier/downloadZip?url=${name}`;
      const a = document.createElement('a')
      a.setAttribute('target', '_blank')
      a.setAttribute('href', url)
      a.click()
    },
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
    yang(response) {
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
      this.hDesc = response.data.hDesc
      this.hBank = response.data.hBank
      this.hBankAddress = response.data.hBankAddress
      this.hSignPhone = response.data.hSignPhone
      this.hCapital = response.data.hCapital
      this.hActualCapital = response.data.hActualCapital
      this.fState = response.data.fState
      this.hAccount = response.data.hAccount
      this.fStatus = response.data.fStatus
      this.hCopies = JSON.parse(response.data.hCopies)[0].url
      this.hCopiesList.push(JSON.parse(response.data.hCopies)[0].url)
      this.idCardCopy = JSON.parse(response.data.hJuridicalCopies)[0].url
      for (let i = 0; i < JSON.parse(response.data.hJuridicalCopies).length; i++) {
        this.idCardCopyList.push(JSON.parse(response.data.hJuridicalCopies)[i].url)
      }
    },
    query() {
      if (this.zr_id == 0) {
        getSupplier(this.hid).then(response => {
          this.yang(response)
          this.infos()
        });
      } else {
        getSupplierByZrId(this.zr_id).then(response => {
          this.$router.replace({query: {...this.$route.query, hid: response.data.hid}});
          this.yang(response)
          this.infos()
        });
      }
    },
    infos() {
      //业务经办人
      getOperator(this.hid).then(response => {
        this.operator.ywName = response.data.ywName
        this.operator.ywPhone = response.data.ywPhone
        this.operator.ywIdcrad = response.data.ywIdcrad
        this.operator.ywMailbox = response.data.ywMailbox
        this.ywIdCardCopy = JSON.parse(response.data.ywScanIdcard)[0].url
        for (let i = 0; i < JSON.parse(response.data.ywScanIdcard).length; i++) {
          this.ywIdCardCopyList.push(JSON.parse(response.data.ywScanIdcard)[i].url)
        }
      })
      //核心技术人员
      listPersonnel(this.personnel).then(response => {
        this.personnelList = response.rows;
        this.total = response.total;
      })
      //企业资质
      listEnterprise(this.enterprise).then(response => {
        this.enterpriseList = response.rows;
        this.total1 = response.total;
      })
      //业绩
      listAchievement(this.achievement).then(response => {
        this.achievementList = response.rows;
        this.total2 = response.total;
      })
      //财务状态
      listStatus(this.financialStatus).then(response => {
        this.financialStatusList = response.rows;
        this.total3 = response.total;
      })
      //相关附件
      listAccessories(this.accessories).then(response => {
        this.accessoriesList = response.rows;
        this.total4 = response.total;
      })
      this.loading = false
    },
    // 文件提交处理
    submitUpload() {
      this.$refs.upload.submit();
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      console.log(response)
      this.upload.isUploading = false;
      //this.form.filePath = response.url;
      this.$message.success(response.msg);

    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    beforeUpload(file) {
      const isLt5M = file.size / 1024 / 1024 < 5;

      if (!isLt5M) {
        this.$message.error('文件大小不能超过5MB');
      }
      return isLt5M;
    }
  }
}
</script>
