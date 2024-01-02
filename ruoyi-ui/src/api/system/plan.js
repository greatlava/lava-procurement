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

export function updateStateAndAddBidWinning(data, type, noBidType) {
  return request({
    url: "/ppm/plan/updateStateAndAddBidWinning?type=" + type + "&noBidType=" + noBidType,
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

export function selectePpmProcurementPlanAndComPubAttamentByAid(data) {
  return request({
    url: "/ppm/plan/PpmProcurementPlanAndComPubAttament",
    method: "GET",
    params: data
  })
}

export function selectPpmpProcurementCount(state) {
  return request({
    url: "/ppm/plan/selectPpmpProcurementCount",
    method: "post",
    data: {aAstate: state},
    headers: {
      "repeatSubmit": false
    }
  })
}


export function selectFarmeworkPlanCount() {
  return request({
    url: "/ppm/framework/selectFarmeworkPlanCount",
    method: "post"
  })
}

export function selectContractCount() {
  return request({
    url: "/ppm/plan/selectContractCount",
    method: "post"
  })
}

export function queryTotalPurchaseAmount() {
  return request({
    url: "/ppm/plan/queryTotalPurchaseAmount",
    method: "post"
  })
}

export function selectTenderCount() {
  return request({
    url: "/ppm/plan/selectTenderCount",
    method: "post"
  })
}

export function selectTenderByState(data) {
  return request({
    url: "/ppm/plan/selectTenderByState",
    method: "post",
    data: data
  })
}

export function selectTenderByStateCount(data) {
  return request({
    url: "/ppm/plan/selectTenderByStateCount",
    method: "post",
    data: data
  })
}
