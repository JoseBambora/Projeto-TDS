import { test, expect} from '@jest/globals';
import { IsDarkMode, IsLocationOn, IsNotificationOn, SaveDarkMode } from '../src/repositories/Settings';

test('Settings', () => {
  const s1 = IsDarkMode()
  const s2 = IsLocationOn()
  const s3 = IsNotificationOn()
  expect(s1).toBe(false)
  expect(s2).toBe(true)
  expect(s3).toBe(false)
  SaveDarkMode(true)
  const s4 = IsDarkMode()
  const s5 = IsLocationOn()
  const s6 = IsNotificationOn()
  expect(s4).toBe(true)
  expect(s5).toBe(true)
  expect(s6).toBe(false)
});