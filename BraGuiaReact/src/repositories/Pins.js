import { PinRequest, PinsRequest } from "../helper/PinRequest"
import { AddPinDB, GetPinDB, GetPinsDB } from "../redux/Pins"
import { CreatePromise } from "./Util"

function LoadAndSavePin(id) {
  return PinRequest(id)
    .then(data => {
      AddPinDB(data)
      return data
    })
    .catch(err => { throw err })
}

function LoadAndSavePins() {
  return PinsRequest()
    .then(data => {
      data.forEach(element => AddPinDB(element));
      return data
    })
    .catch(err => { throw err })
}

export const GetPin = (id) => {
  const pin = GetPinDB(id)
  return pin ? CreatePromise(pin) : LoadAndSavePin(id)
}

export const GetPins = () => {
  const pins = GetPinsDB()
  return pins.length > 0 ? CreatePromise(pins) : LoadAndSavePins()
}