import request from '@/utils/request'

// 查询中标候选人列表
export function listCandidate(query) {
  return request({
    url: '/bidding/candidate/list',
    method: 'get',
    params: query
  })
}

// 查询中标候选人详细
export function getCandidate(zid) {
  return request({
    url: '/bidding/candidate/' + zid,
    method: 'get'
  })
}

// 新增中标候选人
export function addCandidate(data) {
  return request({
    url: '/bidding/candidate',
    method: 'post',
    data: data
  })
}

// 修改中标候选人
export function updateCandidate(data) {
  return request({
    url: '/bidding/candidate',
    method: 'put',
    data: data
  })
}

// 删除中标候选人
export function delCandidate(zid) {
  return request({
    url: '/bidding/candidate/' + zid,
    method: 'delete'
  })
}
