/* eslint-disable no-undef */
jest.mock('@react-native-cookies/cookies', () => {
  const cookies = {'http://192.168.85.186/login' : {'cookies':true}}
  return {
    getAll: jest.fn(() => new Promise((resolve) => resolve(Object.values(cookies)))),
    get: jest.fn(str => new Promise((resolve) => { resolve( cookies[str] != undefined ? cookies[str] : null) })),
    clearAll : jest.fn(() => { delete cookies['http://192.168.85.186/login']; return Promise.resolve()}),
    set: jest.fn((url,cookie) => {cookies[url] = cookie; return new Promise((resolve) => resolve(true))})
  }
})
/* eslint-enable no-undef */