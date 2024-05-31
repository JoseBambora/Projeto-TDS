import BackgroundTimer from 'react-native-background-timer';
import { requestLocationPermission, getLocation } from './Geolocation';
import { IsPremium } from '../repositories/User';
import { IsLocationOn, TimeoutLocation } from '../repositories/Settings';

let isRunning = false

const startService = async () => {
  console.log('A arrancar serviço')
  const hasLocationPermission = await requestLocationPermission();
  if (hasLocationPermission && IsLocationOn()) {
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
    BackgroundTimer.stopBackgroundTimer();
  }
};

export const restartBackGroudTask = () => {
  if(!isRunning) {
    stopBackgroundTask()
    startBackgroundTask()
  }
}