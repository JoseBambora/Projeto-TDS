import realm from "./DB";

const CreateUser = (user) => {
  try {
    user.groups = undefined
    user.user_permissions = undefined
    realm.write(() => {
      realm.create('User', user)
    })
  } catch (error) {
    console.error(error.message)
  }
}

export const DeleteUserDB = () => {
  return new Promise((resolve, reject) => {
    try {
      realm.write(() => {
        let users = realm.objects('User');
        realm.delete(users);
        resolve(0);
      });
    } catch (error) {
      reject(error);
    }
  });
};

export const AddUserDB = (user) => {
  return DeleteUserDB()
  .then(() => CreateUser(user))
  .catch(e => console.log(e.message))
}

export const GetUsersDB = () => Array.from(realm.objects('User'));