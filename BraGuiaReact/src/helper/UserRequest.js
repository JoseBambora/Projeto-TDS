import { userroute } from '../constants/API';
import axios from 'axios';

const UserRequest = () => {
    return axios.get(userroute())
    .then(response => response.data)
    .catch(error => { throw error })
}

export default UserRequest