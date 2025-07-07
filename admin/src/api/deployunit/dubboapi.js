import request from '@/utils/request'

export function getdubboapiList(params) {
  return request({
    url: '/dubboapi',
    method: 'get',
    params
  })
}

export function getdubboapiListbydeploy(params) {
  return request({
    url: 'dubboapi/apibydeploy',
    method: 'get',
    params
  })
}

export function getapisbydeployunitid(apiForm) {
  return request({
    url: '/dubboapi/getapibydeployunitid',
    method: 'post',
    data: apiForm
  })
}

export function copyapi(apiForm) {
  return request({
    url: '/dubboapi/copyapi',
    method: 'post',
    data: apiForm
  })
}

export function exportapi(apiForm) {
  return request({
    url: '/dubboapi/exportapi',
    method: 'post',
    data: apiForm
  })
}

export function removebatchdubboapi(executeplanForm) {
  return request({
    url: '/dubboapi/removebatchapi',
    method: 'post',
    data: executeplanForm
  })
}

export function getstaticsdeployapi(params) {
  return request({
    url: '/dubboapi/getstaticsdeployapi',
    method: 'get',
    params
  })
}

export function getapinum(params) {
  return request({
    url: '/dubboapi/getapinum',
    method: 'get',
    params
  })
}

export function getapiListbydeploy(params) {
  return request({
    url: 'api/dubboapibydeploy',
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
    url: '/dubboapi/search',
    method: 'post',
    data: apiForm
  })
}

export function adddubboapi(apiForm) {
  return request({
    url: '/dubboapi',
    method: 'post',
    data: apiForm
  })
}

export function updatedubboapi(apiForm) {
  return request({
    url: '/dubboapi/detail',
    method: 'put',
    data: apiForm
  })
}

export function removedubboapi(apiId) {
  return request({
    url: '/dubboapi/' + apiId,
    method: 'delete'
  })
}

export function getapi(apiId) {
  return request({
    url: '/dubboapi/' + apiId,
    method: 'get'
  })
}
