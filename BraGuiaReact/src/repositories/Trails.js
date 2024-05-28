import { TrailRequest, TrailsRequest } from "../helper/TrailsRequest";
import { AddTrailDB, AddTrailsDB, GetTrailDB, GetTrailsDB } from "../redux/Trails";
import { CreatePromise } from "./Util";


function LoadAndSaveTrails() {
    return TrailsRequest()
    .then(data => AddTrailsDB(data))
    .then(_ => GetTrailsDB())
    .catch(err => { throw err})
}

function LoadAndSaveTrail(id) {
    return TrailRequest(id)
    .then(data => AddTrailDB(data))
    .then(_ => GetTrailDB(id))
    .catch(err => { throw err})
}

export const GetTrails = () => {
    const trails = GetTrailsDB()
    return trails.length > 0 ? CreatePromise(trails) : LoadAndSaveTrails()
}

export const GetTrail = (id) => {
    const trail = GetTrailDB(id)
    return trail ? CreatePromise(trail) : LoadAndSaveTrail(id)
}