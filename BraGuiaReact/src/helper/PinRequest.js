import { pinroute, pinsroute } from '../constants/API';
import axios from 'axios';

export const PinRequest = (id) => {
  return axios.get(pinroute(id))
    .then(response => response.data)
    .catch(error => { throw error })
}

export const PinsRequest = () => {
  return axios.get(pinsroute())
    .then(response => response.data)
    .catch(error => { throw error })
}