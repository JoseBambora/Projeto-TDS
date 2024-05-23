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
  const pinOriginal = realm.objectForPrimaryKey('Pin', id)
  realm.write(() => {
    pinOriginal = pin
    console.log(`Deu Update ao pin ${pin.id}`)
  })
}

export const AddPinDB = (pin) => {
  GetPinDB(pin.id)
  .then(_ => UpdatePin(pin))
  .catch(_ => CreatePin(pin))
}

export const GetPinDB = (id) => {
  return new Promise((resolve, reject) => {
    try {
      const pin = realm.objectForPrimaryKey('Pin', id);
      if (pin) {
        resolve(pin);
      } else {
        console.log('NotFound here')
        reject(new Error(`Pin with id ${id} not found`));
      }
    } catch (error) {
      console.log('Erro: ' + error.message)
      reject(error);
    }
  });
};
