import request from '@/utils/request'
import {download} from '@/utils/request'

// 查询采购计划列表
export function listPlan(query) {
  return request({
    url: '/ppm/plan/list',
    method: 'get',
    params: query
  })
}

// 查询采购计划详细
export function getPlan(aid) {
  return request({
    url: '/ppm/plan/' + aid,
    method: 'get'
  })
}

// 新增采购计划
export function addPlan(data) {
  return request({
    url: '/ppm/plan',
    method: 'post',
    data: data
  })
}

// 修改采购计划
export function updatePlan(data) {
  return request({
    url: '/ppm/plan',
    method: 'put',
    data: data
  })
}

// 删除采购计划
export function delPlan(aid) {
  return request({
    url: '/ppm/plan/' + aid,
    method: 'delete'
  })
}

export function fileDownload(fileName) {
  const url = "/ppm/plan/fileDownload";
  download(url, {fileName: fileName}, fileName)
}

export function selectProcurementPlanByIdForThreeTables(aid) {
  return request({
    url: '/ppm/plan/many?aid=' + aid,
    method: 'GET'
  })
}

export function generatePlanID() {
  return request({
    url: '/ppm/plan/generatePlanID',
    method: 'GET'
  })
}

export function ModifyPlanAndOtherInformation(data) {
  return request({
    url: "/ppm/plan/otherInformation",
    method: "post",
    data: data
  })
}

export function updateStateAndAddBidWinning(data, type) {
  return request({
    url: "/ppm/plan/updateStateAndAddBidWinning?type=" + type,
    method: "post",
    data: data
  })
}

export function FindProcurementPlanBy(data) {
  return request({
    url: "/ppm/plan/FindProcurementPlanBy",
    params: data,
    method: "post"
  })
}
