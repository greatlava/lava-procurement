import request from '@/utils/request'

// 查询招标项目列表
export function listTender(query) {
  return request({
    url: '/bidding/tender/list',
    method: 'get',
    params: query
  })
}

// 查询招标项目详细
export function getTender(sid) {
  return request({
    url: '/bidding/tender/' + sid,
    method: 'get'
  })
}

// 新增招标项目
export function addTender(data) {
  return request({
    url: '/bidding/tender/addTender',
    method: 'post',
    data: data
  })
}

// 修改招标项目
export function updateTender(data) {
  return request({
    url: '/bidding/tender',
    method: 'put',
    data: data
  })
}

// 删除招标项目
export function delTender(sid) {
  return request({
    url: '/bidding/tender/' + sid,
    method: 'delete'
  })
}

// 查询招标项目与公告详细
export function findTenderNotice(sid) {
  return request({
    url: '/bidding/tender/findTenderAndNotice/' + sid,
    method: 'get'
  })
}
