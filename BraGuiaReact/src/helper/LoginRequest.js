/* eslint-disable no-undef */
import { loginroute } from '../constants/API';
import axios from 'axios';

const LoginRequest = (username, password) => {
  return axios.post(loginroute(), data={ username: username, password: password })
    .then(() => 0)
    .catch(error => { throw error })
}

export default LoginRequest
/* eslint-enable no-undef */