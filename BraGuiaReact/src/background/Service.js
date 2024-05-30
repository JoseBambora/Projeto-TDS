import BackgroundTimer from 'react-native-background-timer';
import { requestLocationPermission, getLocation } from './Geolocation';
import { IsPremium } from '../repositories/User';
import { IsLocationOn, TimeoutLocation } from '../repositories/Settings';


const startService = async () => {
  console.log('A arrancar serviço')
  const hasLocationPermission = await requestLocationPermission();
  if (hasLocationPermission && IsLocationOn()) {
    BackgroundTimer.runBackgroundTimer(() => {
      getLocation();
    }, TimeoutLocation());
  }
}

export const startBackgroundTask = () => {
  IsPremium()
    .then(b => { if(b) startService() })
    .catch(e => {throw e})
};

export const stopBackgroundTask = () => {
  console.log('A parar serviço')
  BackgroundTimer.stopBackgroundTimer();
};

export const restartBackGroudTask = () => {
  stopBackgroundTask()
  startBackgroundTask()
}