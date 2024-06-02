import realm from "./DB";
import { deepCopy } from "./Utils";

const CreatePin = (pin) => {
  try {
    realm.write(() => {
      realm.create('Pin', pin)
      console.log(`Adicionou pin ${pin.id}`)
    })
  } catch (error) {
    console.error(error.message)
  }
}

const UpdatePin = (pin) => {
  realm.write(() => {
    realm.create('Pin', pin, 'modified')
    console.log(`Deu Update ao pin ${pin.id}`)
  })
}

export const AddPinDB = (pin) => {
  const pindb = GetPinDB(pin.id)
  if(pindb)
    UpdatePin(pin)
  else
    CreatePin(pin)
}

export const GetPinDB = (id) => deepCopy(realm.objectForPrimaryKey('Pin', id));
export const GetPinsDB = () => deepCopy(realm.objects('Pin'))
