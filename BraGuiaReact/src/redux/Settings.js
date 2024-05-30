import { MMKV } from 'react-native-mmkv'

const storage = new MMKV()

export const saveDarkModeDB = (b) => storage.set('dark_mode',b)
export const saveLocationOnDB = (b) => storage.set('location_on',b)
export const saveNotificationOnDB = (b) => storage.set('notification_on',b)
export const saveAccuracyLocationDB = (b) => storage.set('accuracy',b)
export const saveTimeoutLocationDB = (v) => storage.set('timeout',v)

export const loadSettingsDB = () => (
  {
    dark_mode:isDarkModeDB(),
    location_on:locationOnDB(),
    notification_on:notificationOnDB(),
    accuracy:isHighAccuracyDB(),
    timeout:timeoutDB()
  }) 

export const isDarkModeDB = () => storage.contains('dark_mode') ? storage.getBoolean('dark_mode') : false
export const locationOnDB = () => storage.contains('location_on') ? storage.getBoolean('location_on') : true
export const notificationOnDB = () => storage.contains('notification_on') ? storage.getBoolean('notification_on') : false
export const isHighAccuracyDB = () => storage.contains('accuracy') ? storage.getBoolean('accuracy') : true
export const timeoutDB = () => storage.contains('timeout') ? storage.getNumber('timeout') : 1000