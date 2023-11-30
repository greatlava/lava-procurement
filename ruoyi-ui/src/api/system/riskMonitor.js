import request from '@/utils/request'

// 查询供应商风险监控列表
export function listMonitor(query) {
  return request({
    url: '/basic/monitor/list',
    method: 'get',
    params: query
  })
}

// 查询供应商风险监控详细
export function getMonitor(rmId) {
  return request({
    url: '/basic/monitor/' + rmId,
    method: 'get'
  })
}

// 新增供应商风险监控
export function addMonitor(data) {
  return request({
    url: '/basic/monitor',
    method: 'post',
    data: data
  })
}

// 修改供应商风险监控
export function updateMonitor(data) {
  return request({
    url: '/basic/monitor',
    method: 'put',
    data: data
  })
}

// 删除供应商风险监控
export function delMonitor(rmId) {
  return request({
    url: '/basic/monitor/' + rmId,
    method: 'delete'
  })
}
