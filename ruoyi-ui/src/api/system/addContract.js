import request from '@/utils/request'

// 查询项目相关信息
export function selectTenderBySid(sid) {
  return request({
    url: '/bidding/tender/selectTenderBySid',
    method: 'get',
    params: sid
  })
}

// 查询设备信息列表
export function listDevice(query) {
  return request({
    url: '/ppm/device/list',
    method: 'get',
    params: query
  })
}

// 查询框架计划内的设备信息
export function selectItemsDevice(jhId) {
  return request({
    url: '/ppm/items/selectItemsDevice',
    method: 'get',
    params: jhId
  })
}

// 新增框架协议管理
export function addManagement(data) {
  return request({
    url: '/basic/management',
    method: 'post',
    data: data
  })
}

// 查询框架协议管理列表
export function listManagement(query) {
  return request({
    url: '/basic/management/list',
    method: 'get',
    params: query
  })
}

// 新增合同
export function addContract(data) {
  return request({
    url: '/basic/contract',
    method: 'post',
    data: data
  })
}

// 查询合同详细
export function getContract(eid) {
  return request({
    url: '/basic/contract/' + eid,
    method: 'get'
  })
}

// 查询合同内的产品信息
export function listInventory(query) {
  return request({
    url: '/basic/inventory/list',
    method: 'get',
    params: query
  })
}

// 查询支付约定列表
export function listPayment(query) {
  return request({
    url: '/basic/payment/list',
    method: 'get',
    params: query
  })
}
