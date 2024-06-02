import { loginroute } from '../constants/API';
import axios from 'axios';

const LoginRequest = (username, password) => {
  return axios.post(loginroute(), data = { username: username, password: password })
    .then(_ => 0)
    .catch(error => { throw error })
}

export default LoginRequest