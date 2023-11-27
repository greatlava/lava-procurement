import request from '@/utils/request'

// 查询业绩列表
export function listAchievement(query) {
  return request({
    url: '/basic/achievement/list',
    method: 'get',
    params: query
  })
}

// 查询业绩详细
export function getAchievement(yjId) {
  return request({
    url: '/system/achievement/' + yjId,
    method: 'get'
  })
}

// 新增业绩
export function addAchievement(data) {
  return request({
    url: '/system/achievement',
    method: 'post',
    data: data
  })
}

// 修改业绩
export function updateAchievement(data) {
  return request({
    url: '/system/achievement',
    method: 'put',
    data: data
  })
}

// 删除业绩
export function delAchievement(yjId) {
  return request({
    url: '/system/achievement/' + yjId,
    method: 'delete'
  })
}
