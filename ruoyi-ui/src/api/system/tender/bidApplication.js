import request from '@/utils/request'

// 查询抽取申请列表
export function listApplications(query) {
  return request({
    url: '/bidding/applications/list',
    method: 'get',
    params: query
  })
}

// 查询抽取申请详细
export function getApplications(xid) {
  return request({
    url: '/bidding/applications/' + xid,
    method: 'get'
  })
}

// 新增抽取申请
export function addApplications(data) {
  return request({
    url: '/bidding/applications',
    method: 'post',
    data: data
  })
}

// 修改抽取申请
export function updateApplications(data) {
  return request({
    url: '/bidding/applications',
    method: 'put',
    data: data
  })
}

// 删除抽取申请
export function delApplications(xid) {
  return request({
    url: '/bidding/applications/' + xid,
    method: 'delete'
  })
}

// 查询最大ID
export function getmaxApp(sid) {
  return request({
    url: '/bidding/applications/maxApp/' + sid,
    method: 'get'
  })
}
