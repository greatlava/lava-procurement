import request from '@/utils/request'

// 查询职业列表
export function listOccupation(query) {
  return request({
    url: '/basic/occupation/list',
    method: 'get',
    params: query
  })
}

// 查询职业详细
export function getOccupation(nid) {
  return request({
    url: '/basic/occupation/' + nid,
    method: 'get'
  })
}

// 新增职业
export function addOccupation(data) {
  return request({
    url: '/basic/occupation',
    method: 'post',
    data: data
  })
}

// 修改职业
export function updateOccupation(data) {
  return request({
    url: '/basic/occupation',
    method: 'put',
    data: data
  })
}

// 删除职业
export function delOccupation(nid) {
  return request({
    url: '/basic/occupation/' + nid,
    method: 'delete'
  })
}
