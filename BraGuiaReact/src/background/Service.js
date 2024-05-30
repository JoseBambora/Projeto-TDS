import BackgroundTimer from 'react-native-background-timer';
import { requestLocationPermission, getLocation } from './Geolocation';
import { IsPremium } from '../repositories/User';
import { IsLocationOn } from '../repositories/Settings';


const startService = async () => {
  const hasLocationPermission = await requestLocationPermission();
  if (hasLocationPermission && IsLocationOn()) {
    BackgroundTimer.runBackgroundTimer(() => {
      getLocation();
    }, 1000);
  }
}

export const startBackgroundTask = () => {
  return IsPremium()
    .then(b => { if(b) startService() })
    .catch(e => {throw e})
};

export const stopBackgroundTask = () => {
  BackgroundTimer.stopBackgroundTimer();
};