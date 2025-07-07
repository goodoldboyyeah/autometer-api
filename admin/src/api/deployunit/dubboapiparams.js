import request from '@/utils/request'

export function getapiparamsList(params) {
  return request({
    url: '/dubboapi/params',
    method: 'get',
    params
  })
}

export function addapiallparams(apiparamsForm) {
  return request({
    url: '/dubboapi/params/addapiallparams',
    method: 'post',
    data: apiparamsForm
  })
}

export function getcaseparatype(params) {
  return request({
    url: '/dubboapi/params/searchid',
    method: 'post',
    data: params
  })
}

export function searchbyid(params) {
  return request({
    url: '/dubboapi/params/searchbyid',
    method: 'post',
    data: params
  })
}

export function searchbyidandproperty(params) {
  return request({
    url: '/dubboapi/params/searchbyidandproperty',
    method: 'post',
    data: params
  })
}

export function getBodyNoFormbyapiid(params) {
  return request({
    url: '/dubboapi/params/getBodyNoFormbyapiid',
    method: 'post',
    data: params
  })
}

export function search(apiparamsForm) {
  return request({
    url: '/dubboapi/params/search',
    method: 'post',
    data: apiparamsForm
  })
}

export function addapiparams(apiparamsForm) {
  return request({
    url: '/dubboapi/params',
    method: 'post',
    data: apiparamsForm
  })
}

export function updateapiparams(apiparamsForm) {
  return request({
    url: '/dubboapi/params/detail',
    method: 'put',
    data: apiparamsForm
  })
}

export function removeapiparams(apiparamsId) {
  return request({
    url: '/dubboapi/params/' + apiparamsId,
    method: 'delete'
  })
}
