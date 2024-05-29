
import { test, afterAll, beforeAll, expect} from '@jest/globals';
import realm from '../src/redux/DB';
import Realm from 'realm';
import LoginRequest from '../src/helper/LoginRequest';
import { True, False } from './Utils';
import { GetUser, IsAuthenticated, Logout } from '../src/repositories/User';

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
  Realm.deleteFile({ path: 'default.realm' })
})


test('Test False Login', async () => {
  try {
    await LoginRequest('premium_user', 'wrong_password')
    False()
  }
  catch {
    True()
  }
});

test('Test True Login', async () => {
  try {
    await LoginRequest('premium_user', 'paiduser')
    True()
    const a1 = await IsAuthenticated()
    expect(a1).toBeTruthy()
  }
  catch(e) {
    console.log(e)
    False()
  }
});

test('Test Logout', async () => {
  try {
    await LoginRequest('premium_user', 'paiduser')
    True()
    await Logout()
    True()
    const a2 = await IsAuthenticated()
    expect(a2).toBeFalsy()
  }
  catch(e) {
    console.log(e)
    False()
  }
});