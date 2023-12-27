import request from '@/utils/request'

// 查询中标结果公示列表
export function listResults(query) {
  return request({
    url: '/bidding/results/list',
    method: 'get',
    params: query
  })
}

// 查询中标结果公示详细
export function getResults(gsId) {
  return request({
    url: '/bidding/results/' + gsId,
    method: 'get'
  })
}

// 新增中标结果公示
export function addResults(data) {
  return request({
    url: '/bidding/results',
    method: 'post',
    data: data
  })
}

// 修改中标结果公示
export function updateResults(data) {
  return request({
    url: '/bidding/results',
    method: 'put',
    data: data
  })
}

// 删除中标结果公示
export function delResults(gsId) {
  return request({
    url: '/bidding/results/' + gsId,
    method: 'delete'
  })
}

export function selectResultSupp(sid) {
  return request({
    url: '/bidding/results/selectResultSupp/'+sid,
    method: 'get',
  })
}
