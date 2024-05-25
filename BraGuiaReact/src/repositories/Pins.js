import { PinRequest } from "../helper/PinRequest"
import { AddPinDB, GetPinDB } from "../redux/Pins"
import { CreatePromise } from "./Util"


function LoadAndSavePin(id) {
    return PinRequest(id)
    .then(data => {
        AddPinDB(data)
        return data
    })
    .catch(err => { throw err})
}

export const GetPin = (id) => {
    const pin = GetPinDB(id)
    return pin ? CreatePromise(pin) : LoadAndSavePin(id)
}