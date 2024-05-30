import { loadSettingsDB, isDarkModeDB, isHighAccuracyDB, timeoutDB, locationOnDB, notificationOnDB, saveDarkModeDB, saveLocationOnDB, saveNotificationOnDB } from "../redux/Settings"

export const SaveDarkMode = (b) => saveDarkModeDB(b)
export const SaveLocationOn = (b) => saveLocationOnDB(b)
export const SaveNotificationOn = (b) => saveNotificationOnDB(b)
export const GetSettings = () => loadSettingsDB()
export const IsDarkMode = () => isDarkModeDB()
export const IsLocationOn = () => locationOnDB()
export const IsNotificationOn = () => notificationOnDB()
export const IsHighAccuracyLocation = () => isHighAccuracyDB()
export const TimeoutLocation = () => timeoutDB()
