import request from '@/utils/request'

// 查询公共附件列表
export function listAttachments(query) {
  return request({
    url: '/ppm/attachments/list',
    method: 'get',
    params: query
  })
}

// 查询公共附件详细
export function getAttachments(anId) {
  return request({
    url: '/ppm/attachments/' + anId,
    method: 'get'
  })
}

// 新增公共附件
export function addAttachments(data) {
  return request({
    url: '/ppm/attachments',
    method: 'post',
    data: data
  })
}

// 修改公共附件
export function updateAttachments(data) {
  return request({
    url: '/ppm/attachments',
    method: 'put',
    data: data
  })
}

// 删除公共附件
export function delAttachments(anId) {
  return request({
    url: '/ppm/attachments/' + anId,
    method: 'delete'
  })
}


export function selectedComPubAttamentsByAid(aid) {
  return request({
    url: "/ppm/attachments/selectedComPubAttamentsByAid",
    method: "post",
    data: {aid: aid}
  })
}

export function updateComPubAttamentsByAid(data, status) {
  return request({
    url: "/ppm/attachments/updateComPubAttamentsByAid?status=" + status,
    method: "post",
    data: data
  })
}
