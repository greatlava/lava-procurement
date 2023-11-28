import request from '@/utils/request'

// 查询设备信息列表
export function listDevice(query) {
  return request({
    url: '/ppm/device/list',
    method: 'get',
    params: query
  })
}

// 查询设备信息详细
export function getDevice(tid) {
  return request({
    url: '/ppm/device/' + tid,
    method: 'get'
  })
}

// 新增设备信息
export function addDevice(data) {
  return request({
    url: '/ppm/device',
    method: 'post',
    data: data
  })
}

// 修改设备信息
export function updateDevice(data) {
  return request({
    url: '/ppm/device',
    method: 'put',
    data: data
  })
}

// 删除设备信息
export function delDevice(tid) {
  return request({
    url: '/ppm/device/' + tid,
    method: 'delete'
  })
}
