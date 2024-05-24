import { trailroute, trailsroute } from '../constants/API';
import axios from 'axios';

export const TrailsRequest = () => {
    return axios.get(trailsroute())
    .then(response => response.data)
    .catch(error => { throw error })
}

export const TrailRequest = (id) => {
    return axios.get(trailroute(id))
    .then(response => response.data)
    .catch(error => { throw error })
}