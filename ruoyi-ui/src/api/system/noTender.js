import request from '@/utils/request'

// 查询非招标项目列表
export function listPro(query) {
  return request({
    url: '/nonbidding/pro/list',
    method: 'get',
    params: query
  })
}
