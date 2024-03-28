import request from '@/utils/request'

// 查询certificates列表
export function listCertificates(query) {
  return request({
    url: '/certificates/certificates/list',
    method: 'get',
    params: query
  })
}

export function listCertificatesUser() {
  return request({
    url: '/certificates/certificates/listUser',
    method: 'get',
  })
}

// 查询certificates详细
export function getCertificates(certificatesId) {
  return request({
    url: '/certificates/certificates/' + certificatesId,
    method: 'get'
  })
}

// 新增certificates
export function addCertificates(data) {
  return request({
    url: '/certificates/certificates',
    method: 'post',
    data: data
  })
}

// 修改certificates
export function updateCertificates(data) {
  return request({
    url: '/certificates/certificates',
    method: 'put',
    data: data
  })
}

// 删除certificates
export function delCertificates(certificatesId) {
  return request({
    url: '/certificates/certificates/' + certificatesId,
    method: 'delete'
  })
}
