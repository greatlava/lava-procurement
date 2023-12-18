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
    url: '/ppm/management',
    method: 'post',
    data: data
  })
}
