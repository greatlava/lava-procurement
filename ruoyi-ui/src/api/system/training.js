import request from '@/utils/request'

// 查询培训列表
export function listTraining(query) {
  return request({
    url: '/basic/training/list',
    method: 'get',
    params: query
  })
}

// 查询培训详细
export function getTraining(lid) {
  return request({
    url: '/basic/training/' + lid,
    method: 'get'
  })
}

// 新增培训
export function addTraining(data) {
  return request({
    url: '/basic/training',
    method: 'post',
    data: data
  })
}

// 修改培训
export function updateTraining(data) {
  return request({
    url: '/basic/training',
    method: 'put',
    data: data
  })
}

// 删除培训
export function delTraining(lid) {
  return request({
    url: '/basic/training/' + lid,
    method: 'delete'
  })
}
