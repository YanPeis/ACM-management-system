import request from '@/utils/request'

// 查询submission列表
export function listSubmission(query) {
  return request({
    url: '/submission/submission/list',
    method: 'get',
    params: query
  })
}

// 查询submission详细
export function getSubmission(submitid) {
  return request({
    url: '/submission/submission/' + submitid,
    method: 'get'
  })
}

// 新增submission
export function addSubmission(data) {
  return request({
    url: '/submission/submission',
    method: 'post',
    data: data
  })
}

// 修改submission
export function updateSubmission(data) {
  return request({
    url: '/submission/submission',
    method: 'put',
    data: data
  })
}

// 删除submission
export function delSubmission(submitid) {
  return request({
    url: '/submission/submission/' + submitid,
    method: 'delete'
  })
}
