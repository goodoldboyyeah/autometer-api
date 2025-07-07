import request from '@/utils/request'

export function getregistercenterinterfaceList(params) {
  return request({
    url: '/registercenterinterface',
    method: 'get',
    params
  })
}

export function getregistercenterinterfaceListbydeploy(params) {
  return request({
    url: 'registercenterinterface/apibydeploy',
    method: 'get',
    params
  })
}

export function exportfromregister(deployunitForm) {
  return request({
    url: '/registercenterinterface/exportfromregister',
    method: 'post',
    data: deployunitForm
  })
}

export function getapisbydeployunitid(apiForm) {
  return request({
    url: '/registercenterinterface/getapibydeployunitid',
    method: 'post',
    data: apiForm
  })
}

export function copyapi(apiForm) {
  return request({
    url: '/registercenterinterface/copyapi',
    method: 'post',
    data: apiForm
  })
}

export function exportapi(apiForm) {
  return request({
    url: '/registercenterinterface/exportapi',
    method: 'post',
    data: apiForm
  })
}

export function removebatchregistercenterinterface(executeplanForm) {
  return request({
    url: '/registercenterinterface/removebatchapi',
    method: 'post',
    data: executeplanForm
  })
}

export function getstaticsdeployapi(params) {
  return request({
    url: '/registercenterinterface/getstaticsdeployapi',
    method: 'get',
    params
  })
}

export function getapinum(params) {
  return request({
    url: '/registercenterinterface/getapinum',
    method: 'get',
    params
  })
}

export function getapiListbydeploy(params) {
  return request({
    url: 'api/registercenterinterfacebydeploy',
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
    url: '/registercenterinterface/search',
    method: 'post',
    data: apiForm
  })
}

export function addregistercenterinterface(apiForm) {
  return request({
    url: '/registercenterinterface',
    method: 'post',
    data: apiForm
  })
}

export function updateregistercenterinterface(apiForm) {
  return request({
    url: '/registercenterinterface/detail',
    method: 'put',
    data: apiForm
  })
}

export function removeregistercenterinterface(apiId) {
  return request({
    url: '/registercenterinterface/' + apiId,
    method: 'delete'
  })
}

export function getapi(apiId) {
  return request({
    url: '/registercenterinterface/' + apiId,
    method: 'get'
  })
}
