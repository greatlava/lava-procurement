import request from '@/utils/request'

// 查询招标文件列表
export function listDocuments(query) {
  return request({
    url: '/bidding/documents/list',
    method: 'get',
    params: query
  })
}

// 查询招标文件详细
export function getDocuments(wid) {
  return request({
    url: '/bidding/documents/' + wid,
    method: 'get'
  })
}

// 新增招标文件
export function addDocuments(data) {
  return request({
    url: '/bidding/documents',
    method: 'post',
    data: data
  })
}

// 修改招标文件
export function updateDocuments(data) {
  return request({
    url: '/bidding/documents',
    method: 'put',
    data: data
  })
}

// 删除招标文件
export function delDocuments(wid) {
  return request({
    url: '/bidding/documents/' + wid,
    method: 'delete'
  })
}

// // 查询招标文件列表(两表联查)
// export function findTwoDocInfo(query) {
//   return request({
//     url: '/bidding/documents/findTwoDocInfo',
//     method: 'get',
//     params: query
//   })
// }


