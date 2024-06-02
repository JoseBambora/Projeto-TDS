import realm from "./DB";

export const AddAppDB = (app) => {
  try {
    realm.write(() => {
      realm.create('App', app)
    })
  } catch (error) {
    console.error(error.message)
  }
}

export const GetAppDB = () => Array.from(realm.objects('App'))[0]