import { AddPinHistory, AddTrailHistory, GetPinsHistory, GetTrailsHistory } from "../src/repositories/History";
import { test, expect, afterAll, beforeAll } from '@jest/globals';
import Realm from "realm";
import realm from "../src/redux/DB";


beforeAll(() => {
  realm.write(() => {
    realm.deleteAll()
  })
})

afterAll(() => {
  realm.write(() => {
    realm.deleteAll()
  })
  realm.close()
  Realm.deleteFile({ path: 'default.realm' })
})

test('Get Pins', () => {
  const pins = GetPinsHistory()
  expect(pins.length).toEqual(0)
});

test('Get Trails', () => {
  const trails = GetTrailsHistory()
  expect(trails.length).toEqual(0)
});


test('Add Pins', () => {
  const pins = [
    {
      id:1,
      pin_name:'Braga'
    },
    {
      id:2,
      pin_name:'Portugal',
    },
    {
      id:3,
      pin_name:'Dume',
    }
  ]
  pins.forEach(p => AddPinHistory(p))
  const pinsHistory = GetPinsHistory()
  expect(pinsHistory.length).toEqual(3)
  const pins2 = pins.map(p => ({id:p.id, pointOfInterestName:p.pin_name}))
  expect(pinsHistory).toEqual(pins2)
})


test('Add Trails', () => {
  const trails = [
    {
      id:1,
      trail_name:'Braga'
    },
    {
      id:2,
      trail_name:'Portugal',
    },
    {
      id:3,
      trail_name:'Dume',
    }
  ]
  trails.forEach(t => AddTrailHistory(t))
  const trailsHistory = GetTrailsHistory()
  expect(trailsHistory.length).toEqual(3)
  const trails2 = trails.map(t=> ({id:t.id, trailName:t.trail_name}))
  expect(trailsHistory).toEqual(trails2)

})

