import request from '@/utils/request'

export function getdubbomodelmethodsList(params) {
  return request({
    url: '/dubbomodelmethods',
    method: 'get',
    params
  })
}

export function getdubbomodelmethodsListbydeploy(params) {
  return request({
    url: 'dubbomodelmethods/apibydeploy',
    method: 'get',
    params
  })
}

export function getapisbydeployunitid(apiForm) {
  return request({
    url: '/dubbomodelmethods/getapibydeployunitid',
    method: 'post',
    data: apiForm
  })
}

export function copyapi(apiForm) {
  return request({
    url: '/dubbomodelmethods/copyapi',
    method: 'post',
    data: apiForm
  })
}

export function exportapi(apiForm) {
  return request({
    url: '/dubbomodelmethods/exportapi',
    method: 'post',
    data: apiForm
  })
}

export function removebatchdubbomodelmethods(executeplanForm) {
  return request({
    url: '/dubbomodelmethods/removebatchapi',
    method: 'post',
    data: executeplanForm
  })
}

export function getstaticsdeployapi(params) {
  return request({
    url: '/dubbomodelmethods/getstaticsdeployapi',
    method: 'get',
    params
  })
}

export function getapinum(params) {
  return request({
    url: '/dubbomodelmethods/getapinum',
    method: 'get',
    params
  })
}

export function getapiListbydeploy(params) {
  return request({
    url: 'api/dubbomodelmethodsbydeploy',
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
    url: '/dubbomodelmethods/search',
    method: 'post',
    data: apiForm
  })
}

export function adddubbomodelmethods(apiForm) {
  return request({
    url: '/dubbomodelmethods',
    method: 'post',
    data: apiForm
  })
}

export function updatedubbomodelmethods(apiForm) {
  return request({
    url: '/dubbomodelmethods/detail',
    method: 'put',
    data: apiForm
  })
}

export function removedubbomodelmethods(apiId) {
  return request({
    url: '/dubbomodelmethods/' + apiId,
    method: 'delete'
  })
}

export function getapi(apiId) {
  return request({
    url: '/dubbomodelmethods/' + apiId,
    method: 'get'
  })
}
