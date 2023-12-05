import request from '@/utils/request'

// 查询合同列表
export function listContract(query) {
  return request({
    url: '/basic/contract/list',
    method: 'get',
    params: query
  })
}

// 查询合同详细
export function getContract(eid) {
  return request({
    url: '/system/contract/' + eid,
    method: 'get'
  })
}

// 新增合同
export function addContract(data) {
  return request({
    url: '/system/contract',
    method: 'post',
    data: data
  })
}

// 修改合同
export function updateContract(data) {
  return request({
    url: '/system/contract',
    method: 'put',
    data: data
  })
}

// 删除合同
export function delContract(eid) {
  return request({
    url: '/system/contract/' + eid,
    method: 'delete'
  })
}
