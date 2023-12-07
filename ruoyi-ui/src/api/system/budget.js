import request from '@/utils/request'

// 查询预算列表
export function listBudget(query) {
  return request({
    url: '/ppm/budget/list',
    method: 'get',
    params: query
  })
}

// 查询预算详细
export function getBudget(duId) {
  return request({
    url: '/ppm/budget/' + duId,
    method: 'get'
  })
}

// 新增预算
export function addBudget(data) {
  return request({
    url: '/ppm/budget',
    method: 'post',
    data: data
  })
}

// 修改预算
export function updateBudget(data) {
  return request({
    url: '/ppm/budget',
    method: 'put',
    data: data
  })
}

// 删除预算
export function delBudget(duId) {
  return request({
    url: '/ppm/budget/' + duId,
    method: 'delete'
  })
}

export function selectPpmBudgetByAid(aid) {
  return request({
    url: '/ppm/budget/BudgetBy?aid=' + aid,
    method: 'post'
  })
}
