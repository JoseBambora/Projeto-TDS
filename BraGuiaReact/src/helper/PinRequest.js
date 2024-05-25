import { pinroute} from '../constants/API';
import axios from 'axios';

export const PinRequest = (id) => {
    return axios.get(pinroute(id))
    .then(response => response.data)
    .catch(error => { throw error })
}