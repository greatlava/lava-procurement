import request from '@/utils/request'

// 查询相关附件列表
export function listAccessories(query) {
  return request({
    url: '/basic/accessories/list',
    method: 'get',
    params: query
  })
}

// 查询相关附件详细
export function getAccessories(fjId) {
  return request({
    url: '/system/accessories/' + fjId,
    method: 'get'
  })
}

// 新增相关附件
export function addAccessories(data) {
  return request({
    url: '/system/accessories',
    method: 'post',
    data: data
  })
}

// 修改相关附件
export function updateAccessories(data) {
  return request({
    url: '/system/accessories',
    method: 'put',
    data: data
  })
}

// 删除相关附件
export function delAccessories(fjId) {
  return request({
    url: '/system/accessories/' + fjId,
    method: 'delete'
  })
}
