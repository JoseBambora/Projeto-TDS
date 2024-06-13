import { AddPinHistory, AddTrailHistory, CleanHistory, GetPinsHistory, GetTrailsHistory } from "../src/repositories/History";
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


function sameTrails(trails) {
  const trailsHistory = GetTrailsHistory().map(t => ({id:t.id, trailName:t.trailName}))
  expect(trailsHistory.length).toEqual(3)
  const trails2 = trails.reverse().map(t=> ({id:t.id, trailName:t.trail_name}))
  expect(trailsHistory).toEqual(trails2)
}

function samePins(pins) {
  const pinsHistory = GetPinsHistory().map(p => ({id:p.id, pointOfInterestName:p.pointOfInterestName}))
  expect(pinsHistory.length).toEqual(3)
  const pins2 = pins.reverse().map(p => ({id:p.id, pointOfInterestName:p.pin_name}))
  expect(pinsHistory).toEqual(pins2)
}

test('Add Pins', () => {
  const pins = [
    {
      id:1,
      pin_name:'Braga'
    },
    {
      id:2,
      pin_name:'Portugal'
    },
    {
      id:3,
      pin_name:'Dume'
    }
  ]
  pins.map(p => AddPinHistory(p)).forEach(n => expect(n).toEqual(0))
  samePins(pins)
})

test('Add Trails', () => {
  const trails = [
    {
      id:1,
      trail_name:'Braga'
    },
    {
      id:2,
      trail_name:'Portugal'
    },
    {
      id:3,
      trail_name:'Dume'
    }
  ]
  trails.map(t => AddTrailHistory(t)).forEach(n => expect(n).toEqual(0))
  sameTrails(trails)
})

test('Update Pin', () => {
  const pins = [
    {
      id:1,
      pin_name:'Braga'
    },
    {
      id:2,
      pin_name:'Portugal'
    },
    {
      id:3,
      pin_name:'Dume'
    }
  ]
  pins.map(p => AddPinHistory(p)).forEach(n => expect(n).toEqual(1))
  samePins(pins)
})

test('Update Trail', () => {
  const trails = [
    {
      id:1,
      trail_name:'Braga'
    },
    {
      id:2,
      trail_name:'Portugal'
    },
    {
      id:3,
      trail_name:'Dume'
    }
  ]
  trails.map(t => AddTrailHistory(t)).forEach(n => expect(n).toEqual(1))
  sameTrails(trails)
})

test('Sort', () => {
  const trails = [
    {
      id:4,
      trail_name:'Braga'
    },
    {
      id:5,
      trail_name:'Portugal'
    },
    {
      id:6,
      trail_name:'Dume'
    }
  ]
  trails.map(t => AddTrailHistory(t)).forEach(n => expect(n).toEqual(0))
  const trailsDB = GetTrailsHistory().map(t => t.id)
  expect(trailsDB[0]).toEqual(6)
  expect(trailsDB[1]).toEqual(5)
  expect(trailsDB[2]).toEqual(4)
  expect(trailsDB[3]).toEqual(3)
  expect(trailsDB[4]).toEqual(2)
  expect(trailsDB[5]).toEqual(1)
})


test('Clean History', () => {
  CleanHistory()
  expect(GetTrailsHistory().length).toEqual(0)
  expect(GetPinsHistory().length).toEqual(0)
})

