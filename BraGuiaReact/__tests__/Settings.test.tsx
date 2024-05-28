
// Note: import explicitly to use the types shipped with jest.
import { test, expect, afterAll, beforeAll } from '@jest/globals';

// Note: test renderer must be required after react-native.
import { IsDarkMode, IsLocationOn, IsNotificationOn, SaveDarkMode } from '../src/repositories/Settings';
import { GetApp } from '../src/repositories/App';
import realm from '../src/redux/DB';
import Realm from 'realm';

beforeAll(() => {
  realm.write(() => {
    realm.deleteAll()
  })
})

afterAll(() => {
  realm.write(() => {
    realm.deleteAll()
  })
  realm.close()
  Realm.deleteFile({path:'default.realm'})
})

test('Settings', () => {
  const s1 = IsDarkMode()
  const s2 = IsLocationOn()
  const s3 = IsNotificationOn()
  expect(s1).toBe(false)
  expect(s2).toBe(false)
  expect(s3).toBe(false)
  SaveDarkMode(true)
  const s4 = IsDarkMode()
  const s5 = IsLocationOn()
  const s6 = IsNotificationOn()
  expect(s4).toBe(true)
  expect(s5).toBe(false)
  expect(s6).toBe(false)
});

test('Test App Request', async () => {
  const data_or = await GetApp();
  const data = JSON.parse(JSON.stringify(data_or))
  expect(data.app_name).toEqual('BraGuia');
  expect(data.app_desc).toEqual('BraGuia - Your virtual guide in Braga');
});

test('Test App BD', async () => {
  const data_or = await GetApp();
  const data = JSON.parse(JSON.stringify(data_or))
  expect(data.app_name).toEqual('BraGuia');
  expect(data.app_desc).toEqual('BraGuia - Your virtual guide in Braga');
});


