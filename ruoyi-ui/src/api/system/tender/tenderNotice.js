import request from '@/utils/request'

// 查询招标公告列表
export function listNotice(query) {
  return request({
    url: '/bidding/notice/list',
    method: 'get',
    params: query
  })
}

// 查询招标公告详细
export function getNotice(uid) {
  return request({
    url: '/bidding/notice/' + uid,
    method: 'get'
  })
}

// 新增招标公告
export function addNotice(data) {
  return request({
    url: '/bidding/notice',
    method: 'post',
    data: data
  })
}

// 修改招标公告
export function updateNotice(data) {
  return request({
    url: '/bidding/notice',
    method: 'put',
    data: data
  })
}

// 删除招标公告
export function delNotice(uid) {
  return request({
    url: '/bidding/notice/' + uid,
    method: 'delete'
  })
}

// 已发布公告
export function findStatus(sid) {
    return request({
      url: '/bidding/notice/findStatus',
      method: 'post',
      data: sid
    })
}

// 删除招标公告
export function delYfb(sid) {
  return request({
    url: '/bidding/notice/sc/' + sid,
    method: 'delete'
  })
}

export function selMax() {
  return request({
    url: '/bidding/notice/selMax',
    method: 'get'
  })
}

// 根据sid修改公告公示时间
export function updateStatus(sid) {
  return request({
    url: '/bidding/notice/updateStatus/' + sid,
    method: 'put'
  })
}
