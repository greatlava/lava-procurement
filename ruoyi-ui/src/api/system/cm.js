import request from '@/utils/request'

// 查询合同列表
export function listContract(query) {
  return request({
    url: '/basic/contract/list',
    method: 'get',
    params: query
  })
}

// 查询没有合同的招标项目列表
export function listTender(query) {
  return request({
    url: '/bidding/tender/NoEidTenderList',
    method: 'get',
    params: query
  })
}

// 查询完成的框架计划
export function listComPlan(query) {
  return request({
    url: '/bidding/tender/NoEidTenderList',
    method: 'get',
    params: query
  })
}

// 查询合同详细
export function getContract(eid) {
  return request({
    url: '/system/contract/' + eid,
    method: 'get'
  })
}

// 新增合同
export function addContract(data) {
  return request({
    url: '/system/contract',
    method: 'post',
    data: data
  })
}

// 修改合同
export function updateContract(data) {
  return request({
    url: '/system/contract',
    method: 'put',
    data: data
  })
}

// 删除合同
export function delContract(eid) {
  return request({
    url: '/system/contract/' + eid,
    method: 'delete'
  })
}


// 查询招标签订中合同列表
export function listNoContract(query) {
  return request({
    url: '/basic/contract/list2',
    method: 'get',
    params: query
  })
}
