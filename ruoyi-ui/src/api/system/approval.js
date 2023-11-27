import request from '@/utils/request'

// 查询审批记录列表
export function listRecord(query) {
  return request({
    url: '/ppm/record/list',
    method: 'get',
    params: query
  })
}

// 查询审批记录详细
export function getRecord(rid) {
  return request({
    url: '/ppm/record/' + rid,
    method: 'get'
  })
}

// 新增审批记录
export function addRecord(data) {
  return request({
    url: '/ppm/record',
    method: 'post',
    data: data
  })
}

// 修改审批记录
export function updateRecord(data) {
  return request({
    url: '/ppm/record',
    method: 'put',
    data: data
  })
}

// 删除审批记录
export function delRecord(rid) {
  return request({
    url: '/ppm/record/' + rid,
    method: 'delete'
  })
}
