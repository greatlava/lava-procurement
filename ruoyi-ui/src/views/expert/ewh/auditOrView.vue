<template>
  <div style="padding: 50px;">
    <span style="font-size: 18px;font-weight: bold;">基本资料</span>
    <el-divider></el-divider>
    <el-descriptions :column="2" border style="margin-bottom: 20px">
      <el-descriptions-item label="姓名">
        {{ basic.jName }}
        <el-tag size="small" style="margin-left: 10px" v-if="basic.jShState == 1"
                type="success">已通过
        </el-tag>
        <el-tag size="small" style="margin-left: 10px" v-if="basic.jShState == 2"
                type="info">未通过
        </el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="性别">{{ basic.jSex == '0' ? '男' : '女' }}</el-descriptions-item>
      <el-descriptions-item label="身份证号">{{ basic.jIdentity }}</el-descriptions-item>
      <el-descriptions-item label="手机号">
        {{ basic.jPhone }}
      </el-descriptions-item>
      <el-descriptions-item label="邮箱">{{ basic.email }}</el-descriptions-item>
      <el-descriptions-item label="健康状况">{{ basic.jState }}</el-descriptions-item>
      <el-descriptions-item label="工作单位">{{ basic.jUnit }}</el-descriptions-item>
      <el-descriptions-item label="是否退休">{{ basic.jRetire == 0 ? '否' : '是' }}</el-descriptions-item>
      <el-descriptions-item label="通讯地址" :span="2">{{ basic.jAddress }}</el-descriptions-item>
      <el-descriptions-item label="开户行">{{ basic.jBank }}</el-descriptions-item>
      <el-descriptions-item label="银行卡号">{{ basic.jCard }}</el-descriptions-item>

      <el-descriptions-item>
        <template slot="label">
          身份证照
        </template>
        <div class="demo-image__preview">
          <el-image
            style="width: 100px; height: 100px"
            :src="jIdentityCopy"
            fit="contain"
            :preview-src-list="jIdentityCopyList">
          </el-image>
        </div>
      </el-descriptions-item>
      <el-descriptions-item>
        <template slot="label">
          证件照
        </template>
        <div class="demo-image__preview">
          <el-image
            style="width: 100px; height: 100px"
            :src="jDocumentsCopy"
            fit="contain"
            :preview-src-list="jDocumentsCopyList">
          </el-image>
        </div>
      </el-descriptions-item>
    </el-descriptions>

    <span style="font-size: 18px;font-weight: bold;">任职经历</span>
    <el-divider></el-divider>
    <el-table v-loading="loading" :data="experienceList" stripe style="margin-bottom: 20px">
      <el-table-column type="index" label="序号" align="center"/>
      <el-table-column label="单位名称" align="center" prop="unit"/>
      <el-table-column label="入职时间" align="center" prop="time"/>
      <el-table-column label="职务" align="center" prop="qOffice"/>
      <el-table-column label="专业" align="center" prop="specialized"/>
    </el-table>

    <span style="font-size: 18px;font-weight: bold;">教育培训</span>
    <el-divider></el-divider>
    <el-table v-loading="loading" :data="educateList" stripe style="margin-bottom: 20px">
      <el-table-column type="index" label="序号" align="center"/>
      <el-table-column label="毕业院校" align="center" prop="rSchool"/>
      <el-table-column label="专业" align="center" prop="specialized"/>
      <el-table-column label="学历" align="center" prop="rDegree"/>
      <el-table-column label="入学时间" align="center" prop="rEnrollmentTime"/>
      <el-table-column label="毕业时间" align="center" prop="rGraduationTime"/>
    </el-table>

    <el-table v-loading="loading" :data="trainingList" stripe style="margin-bottom: 20px">
      <el-table-column type="index" label="序号" align="center"/>
      <el-table-column label="培训机构" align="center" prop="lTraining"/>
      <el-table-column label="培训专业" align="center" prop="lSpecialized"/>
      <el-table-column label="培训开始时间" align="center" prop="lStartDate"/>
      <el-table-column label="培训结束时间" align="center" prop="lEndDate"/>
    </el-table>

    <span style="font-size: 18px;font-weight: bold;">职业职称</span>
    <el-divider></el-divider>
    <el-table v-loading="loading" :data="occupationList" stripe style="margin-bottom: 20px">
      <el-table-column type="index" label="序号" align="center"/>
      <el-table-column label="职业资格证书" align="center" prop="nCertificate"/>
      <el-table-column label="取得时间" align="center" prop="nAcquireDate"/>
      <el-table-column label="证书编号" align="center" prop="nCode"/>
      <el-table-column label="颁发机构" align="center" prop="nInstitution"/>
      <el-table-column label="有效期" align="center" prop="nExpirationDate"/>
    </el-table>

    <el-table v-loading="loading" :data="jobList" stripe style="margin-bottom: 20px">
      <el-table-column type="index" label="序号" align="center"/>
      <el-table-column label="证书名称" align="center" prop="mName"/>
      <el-table-column label="证书编号" align="center" prop="mCode"/>
      <el-table-column label="职称" align="center" prop="mJob"/>
      <el-table-column label="颁发机构" align="center" prop="mInstitution"/>
      <el-table-column label="获得时间" align="center" prop="mGetDate"/>
    </el-table>

    <el-form style="margin-top: 20px" label-width="80px">
      <el-form-item label="审核意见">
        <el-input
          type="textarea"
          placeholder="请输入审核意见"
          v-model="basic.jOpinion"
          :readonly="is_readonly">
        </el-input>
      </el-form-item>
    </el-form>
    <el-row style="text-align: center">
      <el-button @click="pass" type="primary" v-if="param == 0 && basic.jShState == 0">通过</el-button>
      <el-button @click="overrule" type="danger" v-if="param == 0 && basic.jShState == 0">驳回</el-button>
      <el-button @click="cancel">返回</el-button>
    </el-row>
  </div>
</template>

<script>
import {getExpert, updateExpert} from "@/api/system/expert";
import {listExperience} from "@/api/system/experience";
import {listEducate} from "@/api/system/educate";
import {listTraining} from "@/api/system/training";
import {listOccupation} from "@/api/system/occupation";
import {listJob} from "@/api/system/job";

export default {
  data() {
    return {
      // 遮罩层
      loading: true,
      jid: this.$route.query.jid,
      param: this.$route.query.param,
      //任职经历
      experienceList: [],
      //教育
      educateList: [],
      //培训
      trainingList: [],
      //职业
      occupationList: [],
      //职称
      jobList: [],
      //是否只读
      is_readonly: false,
      basic: {
        jName: null,
        jSex: null,
        jIdentity: null,
        jPhone: null,
        email: null,
        jState: null,
        jUnit: null,
        jRetire: null,
        jAddress: null,
        jCard: null,
        jIdentityPhoto: null,
        jDocumentsPhoto: null,
        jBank: null,
        jShState: null,
        jOpinion: null
      },
      jIdentityCopy: '',
      jIdentityCopyList: [],
      jDocumentsCopy: '',
      jDocumentsCopyList: []
    }
  }, created() {
    if (this.param == 0) {
      //审核
      this.is_readonly = false
    } else {
      //查看
      this.is_readonly = true
    }
    this.getInfo();
  },
  methods: {
    getInfo() {
      this.loading = true;
      getExpert(this.jid).then(response => {
        this.basic = response.data;
        console.log(JSON.parse(response.data.jIdentityPhoto)[0].url)
        console.log(JSON.parse(response.data.jDocumentsPhoto)[0].url)
        this.jIdentityCopy = JSON.parse(response.data.jIdentityPhoto)[0].url
        for (let i = 0; i < JSON.parse(response.data.jIdentityPhoto).length; i++) {
          this.jIdentityCopyList.push(JSON.parse(response.data.jIdentityPhoto)[i].url)
        }
        this.jDocumentsCopy = JSON.parse(response.data.jDocumentsPhoto)[0].url
        this.jDocumentsCopyList.push(JSON.parse(response.data.jDocumentsPhoto)[0].url)
      });
      listExperience({"jid": this.jid}).then(res => {
        this.experienceList = res.rows;
      });
      listEducate({"jid": this.jid}).then(res => {
        this.educateList = res.rows;
      });
      listTraining({"jid": this.jid}).then(res => {
        this.trainingList = res.rows;
      });
      listOccupation({"jid": this.jid}).then(res => {
        this.occupationList = res.rows;
      });
      listJob({"jid": this.jid}).then(res => {
        this.jobList = res.rows;
      });
      this.loading = false;
    },
    cancel() {
      this.$router.back()
    },
    //通过
    pass() {
      updateExpert({"jid": this.jid, "jShState": 1, "jOpinion": this.basic.jOpinion}).then(res => {
        this.$modal.msgSuccess("通过成功");
        this.basic.jShState = 1
      })
    },
    //驳回
    overrule() {
      updateExpert({"jid": this.jid, "jShState": 2, "jOpinion": this.basic.jOpinion}).then(res => {
        this.$modal.msgSuccess("驳回成功");
        this.basic.jShState = 2
      })
    }
  }
}
</script>
