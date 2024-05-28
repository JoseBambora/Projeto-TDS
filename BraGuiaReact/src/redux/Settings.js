import { MMKV } from 'react-native-mmkv'

const storage = new MMKV()

export const saveDarkModeDB = (b) => storage.set('dark_mode',b)
export const saveLocationOnDB = (b) => storage.set('location_on',b)
export const saveNotificationOnDB = (b) => storage.set('notification_on',b)

export const loadSettingsDB = () => storage.contains('dark_mode') ?
  {
    dark_mode:storage.getBoolean('dark_mode'),
    location_on:storage.getBoolean('location_on'),
    notification_on:storage.getBoolean('notification_on')
  } : 
  {
    dark_mode:false,
    location_on:false,
    notification_on:false
  }

export const isDarkModeDB = () => storage.contains('dark_mode') ? storage.getBoolean('dark_mode') : false
export const locationOnDB = () => storage.contains('location_on') ? storage.getBoolean('location_on') : false
export const notificationOnDB = () => storage.contains('notification_on') ? storage.getBoolean('notification_on') : false