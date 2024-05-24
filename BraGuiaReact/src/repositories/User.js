import UserRequest from "../helper/UserRequest";
import { AddUserDB, GetUsersDB, DeleteUserDB } from "../redux/User";
import { DeleteCookies } from "../redux/SessionState";


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
    return users.length == 0 ? LoadAndSaveUser():  new Promise((resolve, _) => {resolve(users[0])})
}

export const Logout = () => {
    DeleteUserDB()
    return DeleteCookies()
}