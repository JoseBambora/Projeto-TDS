
import realm from "../redux/DB"

export const AddPinHistoryDB = (pin) => {
  const pin_save = {
    id: pin.id,
    pointOfInterestName: pin.pin_name,
    date: new Date()
  }
  if (realm.objectForPrimaryKey('PinHistory', pin_save.id)) {
    realm.write(() => {
      realm.create('PinHistory', pin_save, 'modified')
      console.log(`Deu Update ao pinhistory ao pin ${pin_save.id}`)
    })
    return 1;
  }
  else {
    realm.write(() => {
      realm.create('PinHistory', pin_save)
      console.log(`Adicionou a pinhistory o pin ${pin_save.id}`)
    })
    return 0;
  }
}

export const AddTrailHistoryDB = (trail) => {
  const trail_save = {
    id: trail.id,
    trailName: trail.trail_name,
    date: new Date()
  }
  if (realm.objectForPrimaryKey('TrailHistory', trail_save.id)) {
    realm.write(() => {
      realm.create('TrailHistory', trail_save, 'modified')
      console.log(`Deu Update ao TrailHistory ao pin ${trail_save.id}`)
    })
    return 1;
  }
  else {
    realm.write(() => {
      realm.create('TrailHistory', trail_save)
    })
    return 0;
  }
}

export const GetTrailsHistoryDB = () => Array.from(realm.objects('TrailHistory')).sort((a, b) => b.date - a.date)
export const GetPinsHistoryDB = () => Array.from(realm.objects('PinHistory')).sort((a, b) => b.date - a.date)
export const CleanHistoryDB = () => {
  realm.delete(realm.objects('TrailHistory'))
  realm.delete(realm.objects('PinHistory'))
}