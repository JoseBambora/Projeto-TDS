import CookieManager from '@react-native-cookies/cookies';
import { loginroute } from '../constants/API';

export const IsAuthenticated = () => {
    return CookieManager.get(loginroute())
    .then(data => data !== null && Object.keys(data).length > 0)
    .catch(error => { throw error })
}

export const Logout = () => {
    return CookieManager.clearAll()
    .then(_ => 0)
    .catch(error => { throw error })
}


