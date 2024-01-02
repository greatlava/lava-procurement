import request from '@/utils/request'

// 查询非招标项目列表
export function listPro(query) {
  return request({
    url: '/nonbidding/pro/list',
    method: 'get',
    params: query
  })
}

// 查询产品表列表
export function listInventory(query) {
  return request({
    url: '/nonbidding/inventory/list',
    method: 'get',
    params: query
  })
}

// 查询产品表详细
export function getInventory(inId) {
  return request({
    url: '/nonbidding/inventory/' + inId,
    method: 'get'
  })
}

// 新增产品表
export function addInventory(data) {
  return request({
    url: '/nonbidding/inventory',
    method: 'post',
    data: data
  })
}

// 修改产品表
export function updateInventory(data) {
  return request({
    url: '/nonbidding/inventory',
    method: 'put',
    data: data
  })
}

// 删除产品表
export function delInventory(inId) {
  return request({
    url: '/nonbidding/inventory/' + inId,
    method: 'delete'
  })
}

// 查询采购计划列表
export function listPlan(query) {
  return request({
    url: '/nonbidding/plan/list',
    method: 'get',
    params: query
  })
}

// 查询采购计划详细
export function getPlan(aid) {
  return request({
    url: '/nonbidding/plan/' + aid,
    method: 'get'
  })
}

// 查询非招标项目详细
export function getPro(gid) {
  return request({
    url: '/nonbidding/pro/' + gid,
    method: 'get'
  })
}

// 修改非招标项目
export function updatePro(data) {
  return request({
    url: '/nonbidding/pro',
    method: 'put',
    data: data
  })
}

// 查询框架计划内的设备信息
export function getItemsDevice(aid) {
  return request({
    url: '/nonbidding/items/getItemsDevice',
    method: 'get',
    params: aid
  })
}

// 查询项目报价
export function getQuotation(gfId) {
  return request({
    url: '/nonbidding/pro/selectQuotation',
    method: 'get',
    params: gfId
  })
}

// 查询公共附件详细aid
export function getAttachmentsByAid(aid) {
  return request({
    url: '/nonbidding/pro/selectCom',
    method: 'get',
    params: {
      aid: aid
    }
  })
}

// 修改非招标项目
export function upePro(data) {
  return request({
    url: '/nonbidding/pro',
    method: 'put',
    data: data
  })
}

// 查询非招标项目列表
export function listPro1(query) {
  return request({
    url: '/nonbidding/pro/list1',
    method: 'get',
    params: query
  })
}

// 获取附件详细信息
export function getBidCandidate(sid) {
  return request({
    url: '/basic/contract/selectCom',
    method: 'get',
    params: {
      sid: sid
    }
  })
}

// 查询框架计划内的设备信息
export function getItemsDevice1(aid) {
  return request({
    url: '/nonbidding/items/getItemsDevice',
    method: 'get',
    params: aid
  })
}

// 新增报价单
export function addQuotation(data) {
  return request({
    url: '/nonbidding/pro/addCom',
    method: 'post',
    data: data,
    headers: {
      isRepeatSubmit: true
    }
  })
}

// 查询没有合同的非招标项目列表
export function listNoTender(query) {
  return request({
    url: '/bidding/tender/NoEidTenderList',
    method: 'get',
    params: query
  })
}

// 查询非招标签订中合同列表
export function listNoContract(query) {
  return request({
    url: '/basic/contract/list1',
    method: 'get',
    params: query
  })
}

// 删除非招标合同
export function delContract1(eid) {
  return request({
    url: '/basic/contract/del1',
    method: 'get',
    params: {
      eid: eid
    }
  })
}

// 删除非招标合同
export function getBjCount(gfId) {
  return request({
    url: '/basic/quotation/getBjCount',
    method: 'get',
    params: {
      gfId: gfId
    }
  })
}

// 删除报价单
export function delQuotation(gfId) {
  return request({
    url: '/nonbidding/pro/delByGfId',
    method: 'delete',
    params: {
      gfId: gfId
    }
  })
}
