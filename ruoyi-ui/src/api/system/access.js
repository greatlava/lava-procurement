import request from '@/utils/request'

// 查询供应商准入列表
export function listAccess(query) {
  return request({
    url: '/basic/access/list',
    method: 'get',
    params: query
  })
}

// 查询供应商准入详细
export function getAccess(zrId) {
  return request({
    url: '/system/access/' + zrId,
    method: 'get'
  })
}

// 新增供应商准入
export function addAccess(data) {
  return request({
    url: '/system/access',
    method: 'post',
    data: data
  })
}

// 修改供应商准入
export function updateAccess(data) {
  return request({
    url: '/basic/access',
    method: 'put',
    data: data
  })
}

// 删除供应商准入
export function delAccess(zrId) {
  return request({
    url: '/basic/access/' + zrId,
    method: 'delete'
  })
}
