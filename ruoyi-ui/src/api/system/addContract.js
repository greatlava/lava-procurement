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
