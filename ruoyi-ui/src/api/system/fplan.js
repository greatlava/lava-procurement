import request from '@/utils/request'

// 查询框架计划列表
export function listPlan(query) {
  return request({
    url: '/ppm/procurePlan/list',
    method: 'get',
    params: query
  })
}

// 查询框架计划详细
export function getPlan(jhId) {
  return request({
    url: '/ppm/procurePlan/' + jhId,
    method: 'get'
  })
}

// 新增框架计划
export function addPlan(data) {
  return request({
    url: '/ppm/procurePlan',
    method: 'post',
    data: data
  })
}

// 修改框架计划
export function updatePlan(data) {
  return request({
    url: '/ppm/procurePlan',
    method: 'put',
    data: data
  })
}

// 删除框架计划
export function delPlan(jhId) {
  return request({
    url: '/ppm/procurePlan/' + jhId,
    method: 'delete'
  })
}
