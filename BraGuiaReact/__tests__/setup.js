/* eslint-disable no-undef */
jest.mock('@react-native-cookies/cookies', () => {
  const cookies = {'https://39b6-193-137-92-72.ngrok-free.app/login' : {'cookies':true}}
  return {
    getAll: jest.fn(() => new Promise((resolve) => resolve(Object.values(cookies)))),
    get: jest.fn(str => new Promise((resolve) => { resolve( cookies[str] != undefined ? cookies[str] : null) })),
    clearAll : jest.fn(() => { delete cookies['https://39b6-193-137-92-72.ngrok-free.app/login']; return Promise.resolve()}),
    set: jest.fn((url,cookie) => {cookies[url] = cookie; return new Promise((resolve) => resolve(true))})
  }
})
/* eslint-enable no-undef */