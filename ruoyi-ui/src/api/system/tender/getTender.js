import request from '@/utils/request'

// 查询获取标书列表
export function listTender(query) {
  return request({
    url: '/system/tender/list',
    method: 'get',
    params: query
  })
}

// 查询获取标书详细
export function getTender(yid) {
  return request({
    url: '/system/tender/' + yid,
    method: 'get'
  })
}

// 新增获取标书
export function addTender(data) {
  return request({
    url: '/system/tender',
    method: 'post',
    data: data
  })
}

// 修改获取标书
export function updateTender(data) {
  return request({
    url: '/system/tender',
    method: 'put',
    data: data
  })
}

// 删除获取标书
export function delTender(yid) {
  return request({
    url: '/system/tender/' + yid,
    method: 'delete'
  })
}

export function operatorList(sid) {
  return request({
    url: '/bidding/tenderFile/operatorList/' + sid,
    method: 'get',
  })
}


