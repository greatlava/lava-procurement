import request from '@/utils/request'

// 查询框架计划列表
export function getFrameworkPlan(query) {
  return request({
    url: '/ppm/framework/list',
    method: 'get',
    params: query
  })
}

// 查询框架计划详细
export function getPlan(jhId) {
  return request({
    url: '/ppm/framework/' + jhId,
    method: 'get'
  })
}

// 新增框架计划
export function addPlan(data) {
  return request({
    url: '/ppm/framework',
    method: 'post',
    data: data
  })
}

// 修改框架计划
export function updatePlan(data) {
  return request({
    url: '/ppm/framework',
    method: 'put',
    data: data
  })
}

// 删除框架计划
export function delPlan(jhId) {
  return request({
    url: '/ppm/framework/' + jhId,
    method: 'delete'
  })
}

export function AddPlanAndOther(data) {
  return request({
    url: "/ppm/framework/AddPlanAndOther",
    method: "post",
    data: data,
  })
}

export function queryFrameworkPlanAndRelatedInformation(jhId) {
  return request({
    url: "/ppm/framework/PlanAndRelatedInformation?jhId=" + jhId,
    method: "post"
  })
}

export function deleteByJhId(jhid) {
  return request({
    url: '/ppm/framework/deleteByJhId?jhid=' + jhid,
    method: "post"
  })
}

export function updateFarmeworkPlanStatus(jhid, state) {
  return request({
    url: "/ppm/framework/updateFarmeworkPlanStatus?jhId=" + jhid + "&jhStatus=" + state,
    method: 'post'
  })
}

// 查询框架计划列表
export function getFrameworkPlan1(query) {
  return request({
    url: '/ppm/framework/list1',
    method: 'get',
    params: query
  })
}
