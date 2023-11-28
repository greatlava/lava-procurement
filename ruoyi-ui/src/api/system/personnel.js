import request from '@/utils/request'

// 查询核心技术人员列表
export function listPersonnel(query) {
  return request({
    url: '/basic/personnel/list',
    method: 'get',
    params: query
  })
}

// 查询核心技术人员详细
export function getPersonnel(jsId) {
  return request({
    url: '/basic/personnel/' + jsId,
    method: 'get'
  })
}

// 新增核心技术人员
export function addPersonnel(data) {
  return request({
    url: '/system/personnel',
    method: 'post',
    data: data
  })
}

// 修改核心技术人员
export function updatePersonnel(data) {
  return request({
    url: '/basic/personnel',
    method: 'put',
    data: data
  })
}

// 删除核心技术人员
export function delPersonnel(jsId) {
  return request({
    url: '/basic/personnel/' + jsId,
    method: 'delete'
  })
}
