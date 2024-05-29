
import { test, expect, afterAll, beforeAll } from '@jest/globals';
import { GetTrail, GetTrails } from '../src/repositories/Trails';
import realm from '../src/redux/DB';
import Realm from 'realm';
import { GetPin } from '../src/repositories/Pins';

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
  Realm.deleteFile({path:'default.realm'})
})

console.log = function() {}
let data;

test('Test Trails Request', async () => {
  const data_or = await GetTrails();
  data = JSON.parse(JSON.stringify(data_or))
  expect(data.length).toEqual(6);
});

test('Test Trails BD', async () => {
  const data_or = await GetTrails();
  const data_2 = JSON.parse(JSON.stringify(data_or))
  expect(data_2).toEqual(data)
});

test('Test Trail', async () => {
  const data_or = await GetTrail(1)
  const data_2 = JSON.parse(JSON.stringify(data_or))
  expect(data_2).toEqual(data[0])
})

const Pin2 = () => ( {
    "id": 2,
    "rel_pin": [
        {
            "id": 1,
            "value": "yes",
            "attrib": "Wheelchair accessible",
            "pin": 2
        },
        {
            "id": 2,
            "value": "Monument",
            "attrib": "Type",
            "pin": 2
        },
        {
            "id": 3,
            "value": "Catedral",
            "attrib": "SubType",
            "pin": 2
        }
    ],
    "media": [
        {
            "id": 1,
            "media_file": "http://39b6-193-137-92-72.ngrok-free.app/media/se_de_braga_m0C5XV9.jpg",
            "media_type": "I",
            "media_pin": 2
        },
        {
            "id": 2,
            "media_file": "http://39b6-193-137-92-72.ngrok-free.app/media/A_Alma_Gente_Se_de_Braga_short.mp3",
            "media_type": "R",
            "media_pin": 2
        }
    ],
    "pin_name": "Sé de Braga",
    "pin_desc": "The Sé of Braga, also known as the Braga Cathedral, is a magnificent example of Baroque architecture located in the historic city of Braga, Portugal. This grand cathedral was built in the 11th century, and has undergone numerous renovations and additions over the centuries, resulting in a stunning blend of architectural styles.\r\n\r\nThe exterior of the cathedral is impressive, with intricate stone carvings, towering bell towers, and a grandiose façade that features a large rose window and statues of saints. The cathedral's interior is just as awe-inspiring, with a nave and transept that are richly adorned with paintings, sculptures, and gold leaf accents.",
    "pin_alt": 450.0
})

test('Test Pin',  async () => {
    const data_or = await GetPin(2)
    const data_2 = JSON.parse(JSON.stringify(data_or))
    data_2.pin_lat = undefined
    data_2.pin_lng = undefined
    expect(data_2).toEqual(Pin2())
})
