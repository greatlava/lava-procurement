import request from '@/utils/request'

// 查询教育列表
export function listEducate(query) {
  return request({
    url: '/basic/educate/list',
    method: 'get',
    params: query
  })
}

// 查询教育详细
export function getEducate(rid) {
  return request({
    url: '/basic/educate/' + rid,
    method: 'get'
  })
}

// 新增教育
export function addEducate(data) {
  return request({
    url: '/basic/educate',
    method: 'post',
    data: data
  })
}

// 修改教育
export function updateEducate(data) {
  return request({
    url: '/basic/educate',
    method: 'put',
    data: data
  })
}

// 删除教育
export function delEducate(rid) {
  return request({
    url: '/basic/educate/' + rid,
    method: 'delete'
  })
}
