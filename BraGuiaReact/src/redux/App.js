import realm from "./DB";


export const AddAppDB = (app) => {
  try {
    realm.write(() => {
      realm.create('App', app)
    })
  } catch (error) { }
}


export const GetAppDB = () => Array.from(realm.objects('App'))[0]