import request from '@/utils/request'

// 查询采购计划列表
export function listPlan(query) {
  return request({
    url: '/system/plan/list',
    method: 'get',
    params: query
  })
}

// 查询采购计划详细
export function getPlan(aid) {
  return request({
    url: '/system/plan/' + aid,
    method: 'get'
  })
}

// 新增采购计划
export function addPlan(data) {
  return request({
    url: '/system/plan',
    method: 'post',
    data: data
  })
}

// 修改采购计划
export function updatePlan(data) {
  return request({
    url: '/system/plan',
    method: 'put',
    data: data
  })
}

// 删除采购计划
export function delPlan(aid) {
  return request({
    url: '/system/plan/' + aid,
    method: 'delete'
  })
}
