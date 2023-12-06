import request from '@/utils/request'

// 查询行项目列表
export function listItems(query) {
  return request({
    url: '/ppm/items/list',
    method: 'get',
    params: query
  })
}

// 查询行项目详细
export function getItems(vid) {
  return request({
    url: '/ppm/items/' + vid,
    method: 'get'
  })
}

// 新增行项目
export function addItems(data) {
  return request({
    url: '/ppm/items',
    method: 'post',
    data: data
  })
}

// 修改行项目
export function updateItems(data) {
  return request({
    url: '/ppm/items',
    method: 'put',
    data: data
  })
}

// 删除行项目
export function delItems(vid) {
  return request({
    url: '/ppm/items/' + vid,
    method: 'delete'
  })
}
