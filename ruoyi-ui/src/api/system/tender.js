import request from '@/utils/request'

// 查询招标项目列表
export function listTender(query) {
  return request({
    url: '/system/tender/list',
    method: 'get',
    params: query
  })
}

// 查询招标项目详细
export function getTender(sid) {
  return request({
    url: '/system/tender/' + sid,
    method: 'get'
  })
}

// 新增招标项目
export function addTender(data) {
  return request({
    url: '/system/tender',
    method: 'post',
    data: data
  })
}

// 修改招标项目
export function updateTender(data) {
  return request({
    url: '/system/tender',
    method: 'put',
    data: data
  })
}

// 删除招标项目
export function delTender(sid) {
  return request({
    url: '/system/tender/' + sid,
    method: 'delete'
  })
}
