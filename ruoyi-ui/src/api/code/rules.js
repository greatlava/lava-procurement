import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listRules(query) {
  return request({
    url: '/ppm/rules/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getRules(id) {
  return request({
    url: '/ppm/rules/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addRules(data) {
  return request({
    url: '/ppm/rules',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateRules(data) {
  return request({
    url: '/ppm/rules',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delRules(id) {
  return request({
    url: '/ppm/rules/' + id,
    method: 'delete'
  })
}
