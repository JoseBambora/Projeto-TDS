import CookieManager from '@react-native-cookies/cookies';
import { loginroute } from '../constants/API';

export const HasCookies = () => {
    return CookieManager.get(loginroute())
    .then(data => data !== null && Object.keys(data).length > 0)
    .catch(error => { throw error })
}

export const DeleteCookies = () => {
    return CookieManager.clearAll()
    .then(_ => 0)
    .catch(error => { throw error })
}


