import request from '@/utils/request'

export function getapicasesdataList(params) {
  return request({
    url: '/dubboapi/casedata',
    method: 'get',
    params
  })
}

export function search(apicasesdataForm) {
  return request({
    url: '/dubboapi/casedata/search',
    method: 'post',
    data: apicasesdataForm
  })
}

export function updatedubbocasedata(apicasesdataForm) {
  return request({
    url: '/dubboapi/casedata/updatepropertydata',
    method: 'post',
    data: apicasesdataForm
  })
}

export function getcasedatabycaseid(params) {
  return request({
    url: '/dubboapi/casedata/casevalue',
    method: 'post',
    data: params
  })
}

export function casevalueforbody(params) {
  return request({
    url: '/dubboapi/casedata/casevalueforbody',
    method: 'post',
    data: params
  })
}
// export function deleparamvaluebycaseidandtype(params) {
//   return request({
//     url: '/dubboapi/casedata/delecasevalue',
//     method: 'delete',
//     data: params
//   })
// }

export function addapicasesdata(apicasesdataForm) {
  return request({
    url: '/dubboapi/casedata',
    method: 'post',
    data: apicasesdataForm
  })
}

export function updateapicasesdata(apicasesdataForm) {
  return request({
    url: '/dubboapi/casedata/detail',
    method: 'put',
    data: apicasesdataForm
  })
}

export function removeapicasesdata(apicasesdataId) {
  return request({
    url: '/dubboapi/casedata/' + apicasesdataId,
    method: 'delete'
  })
}
