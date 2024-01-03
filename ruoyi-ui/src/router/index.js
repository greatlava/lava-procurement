import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
import Details from '../views/tender/tender1/details.vue'
import Update from '@/views/tender/tender1/update.vue'
import Detail from '@/views/supplier/sqe/detail.vue'
import Bidding from '@/views/tender/tender1/bidding.vue'
import DrawExpert from '@/views/tender/tender1/drawExpert.vue'

/**
 * Note: 路由配置项
 *
 * hidden: true                     // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true                 // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                  // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                  // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                  // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect             // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'               // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * query: '{"id": 1, "name": "ry"}' // 访问路由的默认传递参数
 * roles: ['admin', 'common']       // 访问路由的角色权限
 * permissions: ['a:a:a', 'b:b:b']  // 访问路由的菜单权限
 * meta : {
 noCache: true                   // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
 title: 'title'                  // 设置该路由在侧边栏和面包屑中展示的名字
 icon: 'svg-name'                // 设置该路由的图标，对应路径src/assets/icons/svg
 breadcrumb: false               // 如果设置为false，则不会在breadcrumb面包屑中显示
 activeMenu: '/system/user'      // 当路由设置了该属性，则会高亮相对应的侧边栏。
 }
 */

// 公共路由
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/register'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error/401'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'index',
    children: [
      {
        path: 'index',
        component: () => import('@/views/index'),
        name: 'Index',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'profile',
        component: () => import('@/views/system/user/profile/index'),
        name: 'Profile',
        meta: { title: '个人中心', icon: 'user' }
      }
    ]
  }
]

// 动态路由，基于用户权限动态去加载
export const dynamicRoutes = [
  {
    path: '/system/user-auth',
    component: Layout,
    hidden: true,
    permissions: ['system:user:edit'],
    children: [
      {
        path: 'role/:userId(\\d+)',
        component: () => import('@/views/system/user/authRole'),
        name: 'AuthRole',
        meta: { title: '分配角色', activeMenu: '/system/user' }
      }
    ]
  },
  {
    path: '/system/role-auth',
    component: Layout,
    hidden: true,
    permissions: ['system:role:edit'],
    children: [
      {
        path: 'user/:roleId(\\d+)',
        component: () => import('@/views/system/role/authUser'),
        name: 'AuthUser',
        meta: { title: '分配用户', activeMenu: '/system/role' }
      }
    ]
  },
  {
    path: '/system/dict-data',
    component: Layout,
    hidden: true,
    permissions: ['system:dict:list'],
    children: [
      {
        path: 'index/:dictId(\\d+)',
        component: () => import('@/views/system/dict/data'),
        name: 'Data',
        meta: { title: '字典数据', activeMenu: '/system/dict' }
      }
    ]
  },
  {
    path: '/monitor/job-log',
    component: Layout,
    hidden: true,
    permissions: ['monitor:job:list'],
    children: [
      {
        path: 'index/:jobId(\\d+)',
        component: () => import('@/views/monitor/job/log'),
        name: 'JobLog',
        meta: { title: '调度日志', activeMenu: '/monitor/job' }
      }
    ]
  },
  {
    path: '/tool/gen-edit',
    component: Layout,
    hidden: true,
    permissions: ['tool:gen:edit'],
    children: [
      {
        path: 'index/:tableId(\\d+)',
        component: () => import('@/views/tool/gen/editTable'),
        name: 'GenEdit',
        meta: { title: '修改生成配置', activeMenu: '/tool/gen' }
      }
    ]
  },
  {
    path: '/tender/details?type=details&sid=:sid(\\d+)',
    component: Details,
    hidden: true,
    children: [
      {
        component: () => import('@/views/tender/tender1/details'),
        name: 'Details',
        meta: { title: '招标项目-查看', activeMenu: '/tender/tender1' }
      }
    ]
  },
  {
    path: '/tender/drawExpert?sid=:sid(\\d+)',
    component: Details,
    hidden: true,
    children: [
      {
        path: 'drawExpert',
        component: () => import('@/views/tender/tender1/drawExpert'),
        name: 'DrawExpert',
        meta: { title: '抽取专家', activeMenu: '/tender/tender1' }
      }
    ]
  },
  {
    path: '/tender/details?type=update&sid=:sid(\\d+)',
    component: Update,
    hidden: true,
    children: [
      {
        component: () => import('@/views/tender/tender1/update'),
        name: 'Update',
        meta: { title: '招标项目-编辑', activeMenu: '/tender/tender1' }
      }
    ]
  },
  {
    path: '/tender/bidding?type=bidding&sid=:sid(\\d+)',
    component: Bidding,
    hidden: true,
    children: [
      {
        component: () => import('@/views/tender/tender1/bidding'),
        name: 'Bidding',
        meta: { title: '进入项目', activeMenu: '/tender/tender1' }
      }
    ]
  },
  {
    path: '/supplier/detail',
    component: Details,
    hidden: true,
    children: [
      {
        path: 'detail?hid=:hid(\\d+)&zr_id=:zr_id(\\d+)',
        component: () => import('@/views/supplier/sqe/detail'),
        name: 'Detail',
        meta: { title: '查看', activeMenu: '/supplier' }
      }
    ]
  },
  {
    path: '/contract/add',
    component: Details,
    children: [
      {
        path: 'add?sid=:sid(\\d+)&gid=:gid(\\d+)',
        component: () => import('@/views/contract/cm/add'),
        name: 'Add',
        meta: { title: '创建合同', activeMenu: '/contract' }
      }
    ]
  },
  {
    path: '/supplier/process',
    component: Details,
    hidden: true,
    children: [
      {
        path: 'process?zr_id=:zr_id(\\d+)',
        component: () => import('@/views/supplier/sqe/process'),
        name: 'Process',
        meta: { title: '审核', activeMenu: '/supplier' }
      }
    ]
  },
  {
    path: '/supplier/riskHandling',
    component: Details,
    children: [
      {
        path: 'riskHandling?rm_id=:rm_id(\\d+)',
        component: () => import('@/views/supplier/risk/riskHandling'),
        name: 'RiskHandling',
        meta: { title: '供应商风险处理', activeMenu: '/supplier' }
      }
    ]
  },
  {
    path: '/expert/expertWare',
    component: Details,
    children: [
      {
        path: 'expertWare',
        component: () => import('@/views/expert/ewh/expertWare'),
        name: 'ExpertWare',
        meta: { title: '专家', activeMenu: '/expert' }
      }
    ]
  },
  {
    path: '/expert/auditOrView',
    component: Details,
    children: [
      {
        path: 'auditOrView?jid=:jid(\\d+)&param=:param(\\d+)',
        component: () => import('@/views/expert/ewh/auditOrView'),
        name: 'AuditOrView',
        meta: { title: '查看或审核', activeMenu: '/expert' }
      }
    ]
  },
  {
    path: '/purchase/insert',
    component: Details,
    children: [
      {
        path: 'insert',
        component: () => import('@/views/purchase/otb/insert'),
        name: 'InsertOtb',
        meta: { title: '新增采购计划', activeMenu: '/purchase' }
      }
    ]
  }, {
    path: '/frameworkPlan/FrameworkPlanInsert',
    component: Details,
    children: [
      {
        path: 'FrameworkPlanInsert',
        component: () => import('@/views/frameworkPlan/FrameworkPlanInsert'),
        name: 'FrameworkPlanInsert',
        meta: { title: '新增框架计划', activeMenu: '/frameworkPlan' }
      }
    ]
  },
  {
    path: '/contract/faAdd',
    component: Details,
    children: [
      {
        path: 'faAdd?jhId=:jhId(\\d+)',
        component: () => import('@/views/contract/fam/faAdd'),
        name: 'FaAdd',
        meta: { title: '创建框架协议', activeMenu: '/contract' }
      }
    ]
  },
  {
    path: '/contract/update',
    component: Details,
    children: [
      {
        path: 'update?eid=:eid(\\d+)',
        component: () => import('@/views/contract/cm/update'),
        name: 'Update',
        meta: { title: '修改合同', activeMenu: '/contract' }
      }
    ]
  },
  {
    path: '/contract/updateFa',
    component: Details,
    children: [
      {
        path: 'updateFa?eid=:eid(\\d+)',
        component: () => import('@/views/contract/fam/updateFa'),
        name: 'UpdateFa',
        meta: { title: '修改合同', activeMenu: '/contract' }
      }
    ]
  },
  {
    path: '/contract/examineFa',
    component: Details,
    children: [
      {
        path: 'examineFa?oid=:oid(\\d+)',
        component: () => import('@/views/contract/fam/examineFa'),
        name: 'examineFa',
        meta: { title: '框架协议审核', activeMenu: '/contract' }
      }
    ]
  },
  {
    path: '/contract/details',
    component: Details,
    children: [
      {
        path: 'details?eid=:eid(\\d+)',
        component: () => import('@/views/contract/cm/details'),
        name: 'Details',
        meta: { title: '合同详情', activeMenu: '/contract' }
      }
    ]
  },
  {
    path: '/contract/examine',
    component: Details,
    children: [
      {
        path: 'examine?eid=:eid(\\d+)',
        component: () => import('@/views/contract/cm/examine'),
        name: 'Examine',
        meta: { title: '合同详情', activeMenu: '/contract' }
      }
    ]
  },
  {
    path: '/noTender/details1',
    component: Details,
    children: [
      {
        path: 'details1?gid=:gid(\\d+)',
        component: () => import('@/views/noTender/RFQ/details1'),
        name: 'Details1',
        meta: { title: '查看询价', activeMenu: '/noTender/RFQ' }
      }
    ]
  },
  {
    path: '/noTender/details2',
    component: Details,
    children: [
      {
        path: 'details2?gid=:gid(\\d+)',
        component: () => import('@/views/noTender/RFQ/details2'),
        name: 'Details2',
        meta: { title: '查看竞争性谈判', activeMenu: '/noTender/RFQ' }
      }
    ]
  },
  {
    path: '/noTender/details3',
    component: Details,
    children: [
      {
        path: 'details3?gid=:gid(\\d+)',
        component: () => import('@/views/noTender/RFQ/details3'),
        name: 'Details3',
        meta: { title: '查看委托', activeMenu: '/noTender/RFQ' }
      }
    ]
  },
  {
    path: '/noTender/details4',
    component: Details,
    children: [
      {
        path: 'details4?gid=:gid(\\d+)',
        component: () => import('@/views/noTender/RFQ/details4'),
        name: 'Details4',
        meta: { title: '查看单一来源', activeMenu: '/noTender/RFQ' }
      }
    ]
  },
  {
    path: '/noTender/details5',
    component: Details,
    children: [
      {
        path: 'details5?gid=:gid(\\d+)',
        component: () => import('@/views/noTender/RFQ/details5'),
        name: 'Details5',
        meta: { title: '单一来源详情', activeMenu: '/noTender/RFQ' }
      }
    ]
  },
  {
    path: '/noTender/noContract',
    component: Details,
    children: [
      {
        path: 'noContract',
        component: () => import('@/views/noTender/noContract'),
        name: 'NoContract',
        meta: { title: '非招标合同管理', activeMenu: '/noTender' }
      }
    ]
  },
  {
    path: '/noTender/addNoTender',
    component: Details,
    children: [
      {
        path: 'addNoTender?gid=:gid(\\d+)',
        component: () => import('@/views/noTender/addNoTender'),
        name: 'AddNoTender',
        meta: { title: '非招标采购合同新增', activeMenu: '/noTender' }
      }
    ]
  },
  {
    path: '/noTender/upNoTender',
    component: Details,
    children: [
      {
        path: 'upNoTender?eid=:eid(\\d+)',
        component: () => import('@/views/noTender/upNoTender'),
        name: 'UpNoTender',
        meta: { title: '非招标采购合同修改', activeMenu: '/noTender' }
      }
    ]
  },
  {
    path: '/noTender/examineNo',
    component: Details,
    children: [
      {
        path: 'examineNo?eid=:eid(\\d+)',
        component: () => import('@/views/noTender/examineNo'),
        name: 'ExamineNo',
        meta: { title: '非招标采购合同审核', activeMenu: '/noTender' }
      }
    ]
  },
  {
    path: '/noTender/viewNo',
    component: Details,
    children: [
      {
        path: 'viewNo?eid=:eid(\\d+)',
        component: () => import('@/views/noTender/viewNo'),
        name: 'ViewNo',
        meta: { title: '非招标采购合同查看', activeMenu: '/noTender' }
      }
    ]
  }
]

// 防止连续点击多次路由报错
let routerPush = Router.prototype.push
let routerReplace = Router.prototype.replace
// push
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(err => err)
}
// replace
Router.prototype.replace = function push(location) {
  return routerReplace.call(this, location).catch(err => err)
}

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
