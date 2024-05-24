import { View } from "react-native"
import OurButton from "../../components/Button"
import OurText from "../../components/Text"
import { Logout } from "../../redux/SessionState"

function User({ navigation }) {
  const logout = () => {
    Logout()
      .then(_ => navigation.replace('Login'))
      .catch(error => alert(error.message))
  }
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <OurText content={'Já está autenticado'} />
      <OurButton title={'Logout'} icon={'log-out-outline'} onPress={logout} />
    </View>
  )
}


export default User