import request from '@/utils/request'

// 查询任职经历列表
export function listExperience(query) {
  return request({
    url: '/basic/experience/list',
    method: 'get',
    params: query
  })
}

// 查询任职经历详细
export function getExperience(qid) {
  return request({
    url: '/basic/experience/' + qid,
    method: 'get'
  })
}

// 新增任职经历
export function addExperience(data) {
  return request({
    url: '/basic/experience',
    method: 'post',
    data: data
  })
}

// 修改任职经历
export function updateExperience(data) {
  return request({
    url: '/basic/experience',
    method: 'put',
    data: data
  })
}

// 删除任职经历
export function delExperience(qid) {
  return request({
    url: '/basic/experience/' + qid,
    method: 'delete'
  })
}
