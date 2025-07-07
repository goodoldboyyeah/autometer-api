import request from '@/utils/request'

export function getappkeyList(params) {
  return request({
    url: '/appkey',
    method: 'get',
    params
  })
}

export function getappkeyLists(params) {
  return request({
    url: '/appkey/getappkey',
    method: 'get',
    params
  })
}

export function getappkeynum(params) {
  return request({
    url: '/appkey/getappkeynum',
    method: 'get',
    params
  })
}

export function search(appkeyForm) {
  return request({
    url: '/appkey/search',
    method: 'post',
    data: appkeyForm
  })
}

export function addappkey(appkeyForm) {
  return request({
    url: '/appkey',
    method: 'post',
    data: appkeyForm
  })
}

export function updateappkey(appkeyForm) {
  return request({
    url: '/appkey/detail',
    method: 'put',
    data: appkeyForm
  })
}

export function removeappkey(appkeyId) {
  return request({
    url: '/appkey/' + appkeyId,
    method: 'delete'
  })
}
