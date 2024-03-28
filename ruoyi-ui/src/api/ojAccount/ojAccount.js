import request from '@/utils/request'

// 查询ojAccount列表
export function listOjAccount(query) {
  return request({
    url: '/ojAccount/ojAccount/list',
    method: 'get',
    params: query
  })
}

// 查询ojAccount详细
export function getOjAccount(userscore) {
  return request({
    url: '/ojAccount/ojAccount/' + userscore,
    method: 'get'
  })
}

// 新增ojAccount
export function addOjAccount(data) {
  return request({
    url: '/ojAccount/ojAccount',
    method: 'post',
    data: data
  })
}

// 修改ojAccount
export function updateOjAccount(data) {
  return request({
    url: '/ojAccount/ojAccount',
    method: 'put',
    data: data
  })
}

// 删除ojAccount
export function delOjAccount(userscore) {
  return request({
    url: '/ojAccount/ojAccount/' + userscore,
    method: 'delete'
  })
}
