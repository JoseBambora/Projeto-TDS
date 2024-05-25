import AppRequest from "../helper/AppRequest";
import { AddAppDB, GetAppDB } from "../redux/App";
import { CreatePromise } from "./Util";

function LoadAndSaveApp() {
  return AppRequest()
  .then(data => {
    data.socials = data.socials.map((e) => { e.social_name = e.social_name.toLowerCase(); return e })
    AddAppDB(data)
    return data
  })
  .catch(err => { throw err})
}

export const GetApp = () => {
  const app = GetAppDB()
  return app ? CreatePromise(app) : LoadAndSaveApp()
}