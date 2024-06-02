import { approute } from '../constants/API';
import axios from 'axios';

const AppRequest = () => {
  return axios.get(approute())
    .then(response => response.data[0])
    .catch(error => { throw error })
}

export default AppRequest