import BackgroundTimer from 'react-native-background-timer';
import { requestLocationPermission, getLocation } from './Geolocation';
import { IsPremium } from '../repositories/User';
import { IsLocationOn, TimeoutLocation } from '../repositories/Settings';

let isRunning = false

const startService = async () => {
  const hasLocationPermission = await requestLocationPermission();
  if (hasLocationPermission && IsLocationOn()) {
    console.log('A arrancar serviço')
    isRunning = true
    BackgroundTimer.runBackgroundTimer(() => {
      getLocation();
    }, TimeoutLocation());
  }
}

export const startBackgroundTask = () => {
  if(!isRunning) {
    IsPremium()
    .then(b => { if(b) startService() })
    .catch(e => {throw e})
  }
};

export const stopBackgroundTask = () => {
  if(isRunning) {
    console.log('A parar serviço')
    isRunning = false
    BackgroundTimer.stopBackgroundTimer();
  }
};

export const restartBackGroudTask = () => {
  if(!isRunning) {
    stopBackgroundTask()
    startBackgroundTask()
  }
}