import request from '@/utils/request'

// 查询财务状态列表
export function listStatus(query) {
  return request({
    url: '/basic/status/list',
    method: 'get',
    params: query
  })
}

// 查询财务状态详细
export function getStatus(cId) {
  return request({
    url: '/system/status/' + cId,
    method: 'get'
  })
}

// 新增财务状态
export function addStatus(data) {
  return request({
    url: '/system/status',
    method: 'post',
    data: data
  })
}

// 修改财务状态
export function updateStatus(data) {
  return request({
    url: '/system/status',
    method: 'put',
    data: data
  })
}

// 删除财务状态
export function delStatus(cId) {
  return request({
    url: '/system/status/' + cId,
    method: 'delete'
  })
}
