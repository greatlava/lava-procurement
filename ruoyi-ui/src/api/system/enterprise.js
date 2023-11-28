import request from '@/utils/request'

// 查询企业资质列表
export function listEnterprise(query) {
  return request({
    url: '/basic/enterprise/list',
    method: 'get',
    params: query
  })
}

// 查询企业资质详细
export function getEnterprise(zzId) {
  return request({
    url: '/system/enterprise/' + zzId,
    method: 'get'
  })
}

// 新增企业资质
export function addEnterprise(data) {
  return request({
    url: '/system/enterprise',
    method: 'post',
    data: data
  })
}

// 修改企业资质
export function updateEnterprise(data) {
  return request({
    url: '/system/enterprise',
    method: 'put',
    data: data
  })
}

// 删除企业资质
export function delEnterprise(zzId) {
  return request({
    url: '/system/enterprise/' + zzId,
    method: 'delete'
  })
}
