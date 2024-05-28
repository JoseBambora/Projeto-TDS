
import realm from "../redux/DB"

export const AddPinHistoryDB = (pin) => {
  const pin_save = {
    id:pin.id,
    pointOfInterestName: pin.pin_name
  }
  try {
    realm.write(() => {
      realm.create('PinHistory', pin_save)
    })
  } catch (error) { }
}

export const AddTrailHistoryDB = (trail) => {
  const trail_save = {
    id:trail.id,
    trailName: trail.trail_name
  }
  try {
    realm.write(() => {
      realm.create('TrailHistory', trail_save)
    })
  } catch (error) { }
}

export const GetTrailsHistoryDB = () => Array.from(realm.objects('TrailHistory'))
export const GetPinsHistoryDB = () => Array.from(realm.objects('PinHistory'))