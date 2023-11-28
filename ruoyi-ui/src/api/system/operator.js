import request from '@/utils/request'

// 查询业务经办人信息列表
export function listOperator(query) {
  return request({
    url: '/system/operator/list',
    method: 'get',
    params: query
  })
}

// 查询业务经办人信息详细
export function getOperator(hid) {
  return request({
    url: '/basic/operator/getInfoByHid/' + hid,
    method: 'get'
  })
}

// 新增业务经办人信息
export function addOperator(data) {
  return request({
    url: '/system/operator',
    method: 'post',
    data: data
  })
}

// 修改业务经办人信息
export function updateOperator(data) {
  return request({
    url: '/system/operator',
    method: 'put',
    data: data
  })
}

// 删除业务经办人信息
export function delOperator(ywId) {
  return request({
    url: '/system/operator/' + ywId,
    method: 'delete'
  })
}
