import request from '@/utils/request'

// 查询职称列表
export function listJob(query) {
  return request({
    url: '/basic/job/list',
    method: 'get',
    params: query
  })
}

// 查询职称详细
export function getJob(mid) {
  return request({
    url: '/basic/job/' + mid,
    method: 'get'
  })
}

// 新增职称
export function addJob(data) {
  return request({
    url: '/basic/job',
    method: 'post',
    data: data
  })
}

// 修改职称
export function updateJob(data) {
  return request({
    url: '/basic/job',
    method: 'put',
    data: data
  })
}

// 删除职称
export function delJob(mid) {
  return request({
    url: '/basic/job/' + mid,
    method: 'delete'
  })
}
