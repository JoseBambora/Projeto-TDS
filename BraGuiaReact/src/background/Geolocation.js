
import { PermissionsAndroid, Platform } from 'react-native';
import Geolocation from 'react-native-geolocation-service';
import { GetPins } from '../repositories/Pins';
import { IsHighAccuracyLocation } from '../repositories/Settings';

export const requestLocationPermission = async () => {
    if (Platform.OS === 'android') {
        const granted = await PermissionsAndroid.request(
            PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
            {
                title: 'Location Access Required',
                message: 'This app needs to access your location',
                buttonPositive: 'OK'
            }
        );
        return granted === PermissionsAndroid.RESULTS.GRANTED;
    }
    return true;
};

function calculateDistance(x1, y1, x2, y2) {
    const dx = x2 - x1;
    const dy = y2 - y1;
    return Math.sqrt(dx * dx + dy * dy);
  }

export const getLocation = () => {
    Geolocation.getCurrentPosition(
        (position) => {
            const lng = position.coords.longitude
            const lat = position.coords.latitude
            GetPins()
            .then(data => data.map(p => ({id:p.id,name:p.pin_name,lng:p.pin_lng,lat:p.pin_lat})))
            .then(data => data.filter(p => calculateDistance(p.lng,p.lat,lng,lat) < 10))
            .then(data => console.log(data))
            .catch(e => console.log(e))
        },
        (error) => {
            console.error('Geolocation Error:', error);
        },
        {
            enableHighAccuracy: IsHighAccuracyLocation(),
            timeout: 15000,
            maximumAge: 10000
        }
    );
};