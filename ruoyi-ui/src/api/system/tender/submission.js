import request from '@/utils/request'

// 查询投递标书列表
export function listSubmission(query) {
  return request({
    url: '/bidding/submission/list',
    method: 'get',
    params: query
  })
}

// 查询投递标书详细
export function getSubmission(tdId) {
  return request({
    url: '/bidding/submission/' + tdId,
    method: 'get'
  })
}

// 新增投递标书
export function addSubmission(data) {
  return request({
    url: '/bidding/submission',
    method: 'post',
    data: data
  })
}

// 修改投递标书
export function updateSubmission(data) {
  return request({
    url: '/bidding/submission',
    method: 'put',
    data: data
  })
}

// 删除投递标书
export function delSubmission(tdId) {
  return request({
    url: '/bidding/submission/' + tdId,
    method: 'delete'
  })
}

export function findSubmission(sid) {
  return request({
    url: '/bidding/submission/submissionList/' + sid,
    method: 'get'
  })
}
