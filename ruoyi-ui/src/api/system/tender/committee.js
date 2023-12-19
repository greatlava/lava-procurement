import request from '@/utils/request'

// 查询评标委员会列表
export function listCommittee(query) {
  return request({
    url: '/bidding/committee/list',
    method: 'get',
    params: query
  })
}

// 查询评标委员会详细
export function getCommittee(pbId) {
  return request({
    url: '/bidding/committee/' + pbId,
    method: 'get'
  })
}

// 新增评标委员会
export function addCommittee(data) {
  return request({
    url: '/bidding/committee',
    method: 'post',
    data: data
  })
}

// 修改评标委员会
export function updateCommittee(data) {
  return request({
    url: '/bidding/committee',
    method: 'put',
    data: data
  })
}

// 删除评标委员会
export function delCommittee(pbId) {
  return request({
    url: '/bidding/committee/' + pbId,
    method: 'delete'
  })
}
