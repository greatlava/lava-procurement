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

// 查询签署执行状态列表
export function SelectSign(query) {
  return request({
    url: '/basic/sign/list1',
    method: 'get',
    params: query
  })
}

// 查询框架协议管理详细
export function getManagement(oid) {
  return request({
    url: '/basic/management/' + oid,
    method: 'get'
  })
}

// 修改框架协议管理
export function updateManagement(data) {
  return request({
    url: '/basic/management',
    method: 'put',
    data: data
  })
}

// 修改合同
export function updateContract(data) {
  return request({
    url: '/basic/contract',
    method: 'put',
    data: data
  })
}

// 查询公共附件详细eid
export function getAttachments(eid) {
  return request({
    url: '/basic/contract/selectCom',
    method: 'get',
    params: {
      eid: eid
    }
  })
}

// 修改合同
export function updateEStatus(data) {
  return request({
    url: '/basic/contract/upEStatus',
    method: 'put',
    data: data
  })
}

// 修改合同管理状态
export function updateoHstatus(data) {
  return request({
    url: '/basic/contract/updateoHstatus',
    method: 'put',
    data: data
  })
}

// 删除合同
export function delContract(eid) {
  return request({
    url: '/basic/contract/del',
    method: 'get',
    params: {
      eid: eid
    }
  })
}

// 合同作废
export function HtCancel(data) {
  return request({
    url: '/basic/contract/HtCancel',
    method: 'put',
    data: data
  })
}

// 协议作废
export function XyCancel(oid) {
  return request({
    url: '/basic/management/XyCancel',
    method: 'get',
    params: {
      oid: oid
    }
  })
}

// 协议作废(修改ppm里的oid)
export function XyCancelByOid(oid) {
  return request({
    url: '/ppm/framework/XyCancel',
    method: 'get',
    params: {
      oid: oid
    }
  })
}

// 删除协议
export function delXy(oid) {
  return request({
    url: '/basic/management/delXy',
    method: 'get',
    params: {
      oid: oid
    }
  })
}

// 修改框架计划oid
export function upOidbyOid(data) {
  return request({
    url: '/ppm/framework/upOidbyOid',
    method: 'put',
    data: data
  })
}

// 新增非招标合同
export function addNoContract(data) {
  return request({
    url: '/basic/contract/noTender',
    method: 'post',
    data: data
  })
}

//查询中标供应商信息
export function selectSuppHid(sid) {
  return request({
    url: '/bidding/candidate/getSuppHid',
    method: 'get',
    params: {
      sid: sid
    }
  })
}
