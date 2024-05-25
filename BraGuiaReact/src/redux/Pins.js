import realm from "./DB";

const CreatePin = (pin) => {
  try {
    realm.write(() => {
      realm.create('Pin', pin)
      console.log(`Adicionou pin ${pin.id}`)
    })
  } catch (error) {}
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

export const GetPinDB = (id) => realm.objectForPrimaryKey('Pin', id);
