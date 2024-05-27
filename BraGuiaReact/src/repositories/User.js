import UserRequest from "../helper/UserRequest";
import { AddUserDB, GetUsersDB, DeleteUserDB } from "../redux/User";
import { DeleteCookies, HasCookies } from "../redux/CookiesManager";
import { CreatePromise } from "./Util";


function LoadAndSaveUser() {
    return UserRequest()
    .then(data => {
        AddUserDB(data)
        return data
    })
    .catch(err => { throw err})
}

export const GetUser = () => {
    const users = GetUsersDB()
    return users.length > 0 ? CreatePromise(users[0]) : LoadAndSaveUser()
}

export const Logout = () => {
    DeleteUserDB()
    return DeleteCookies()
}

export const IsAuthenticated = () => {
    return HasCookies()
}

export const IsPremium = () => {
    return IsAuthenticated()
    .then(b => {
        if(b) {
           return GetUser()
           .then(u => u.user_type === 'Premium')
           .catch(_ => false)
        }
    })
    .catch(_ => false)
}