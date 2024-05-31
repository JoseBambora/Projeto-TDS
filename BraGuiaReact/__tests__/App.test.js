
import { test, expect, afterAll, beforeAll } from '@jest/globals';
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

let data;

test('Test App Request', async () => {
  const data_or = await GetApp();
  data = JSON.parse(JSON.stringify(data_or))
  expect(data.app_name).toEqual('BraGuia');
  expect(data.app_desc).toEqual('BraGuia - Your virtual guide in Braga');
});

test('Test App BD', async () => {
  const data_or = await GetApp();
  const data_2 = JSON.parse(JSON.stringify(data_or))
  expect(data_2).toEqual(data)
});
