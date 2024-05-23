import { TrailsRequest } from "../helper/TrailsRequest";
import { AddTrails, GetTrailsDB } from "../redux/Trails";


function LoadAndSaveTrails() {
    console.log('No Trails')
    return TrailsRequest()
    .then(data => {
        AddTrails(data)
        return data
    })
    .catch(err => { throw err})
}

export const GetTrails = () => {
    const trails = GetTrailsDB()
    if(trails.length > 0)
        console.log('Tem trails')
    return trails.length == 0 ? LoadAndSaveTrails():  new Promise((resolve, _) => {resolve(trails)})
}