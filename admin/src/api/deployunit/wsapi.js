import request from '@/utils/request'

export function getapiList(params) {
  return request({
    url: '/wsapi',
    method: 'get',
    params
  })
}

export function getapisbydeployunitid(apiForm) {
  return request({
    url: '/wsapi/getapibydeployunitid',
    method: 'post',
    data: apiForm
  })
}

export function copyapi(apiForm) {
  return request({
    url: '/wsapi/copyapi',
    method: 'post',
    data: apiForm
  })
}

export function removebatchapi(executeplanForm) {
  return request({
    url: '/wsapi/removebatchapi',
    method: 'post',
    data: executeplanForm
  })
}

export function getstaticsdeployapi(params) {
  return request({
    url: '/wsapi/getstaticsdeployapi',
    method: 'get',
    params
  })
}

export function getapinum(params) {
  return request({
    url: '/wsapi/getapinum',
    method: 'get',
    params
  })
}

export function getapiListbydeploy(params) {
  return request({
    url: 'wsapi/apibydeploy',
    method: 'get',
    params
  })
}

export function getresponetypebydeployandapiname(params) {
  return request({
    url: 'api/getresponetypebydeployandapiname',
    method: 'get',
    params
  })
}

export function search(apiForm) {
  return request({
    url: '/wsapi/search',
    method: 'post',
    data: apiForm
  })
}

export function addapi(apiForm) {
  return request({
    url: '/wsapi',
    method: 'post',
    data: apiForm
  })
}

export function updateapi(apiForm) {
  return request({
    url: '/wsapi/detail',
    method: 'put',
    data: apiForm
  })
}

export function removeapi(apiId) {
  return request({
    url: '/wsapi/' + apiId,
    method: 'delete'
  })
}

export function getapi(apiId) {
  return request({
    url: '/wsapi/' + apiId,
    method: 'get'
  })
}
