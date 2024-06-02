import React, { useEffect, useState, useContext } from "react"
import { ScrollView, View } from "react-native"
import OurButton from "../../components/ui/Button"
import OurCardView from "../../components/ui/CardView"
import { GetUser } from "../../repositories/User"
import { Logout } from "../../repositories/User"
import { OurHeaderCurve } from "../../components/ui/HeaderCurve"
import PageStyle from "../../styles/ui/Pages"
import LoadingIndicator from "../../components/ui/Indicator"
import { stopBackgroundTask } from "../../background/Service"
import { ThemeContext } from "../../controler/ThemeControler"
import { backgroundColor } from '../../styles/Colors';

function userInfo(setData, setLoading, navigation) {
  useEffect(() => {
    GetUser()
      .then(json => { setData(json); navigation.setOptions({ title: 'User ' + json.username }) })
      .catch(err => alert(err.message))
      .finally(() => setLoading(false))
  }, [])
}

function User({ navigation }) {
  const { isDarkMode } = useContext(ThemeContext); 
  const [isLoading, setLoading] = useState(true);
  const [data, setData] = useState([]);
  const logout = () => {
    Logout()
      .then(_ => navigation.replace('Login'))
      .catch(error => alert(error.message))
    stopBackgroundTask()
  }
  userInfo(setData, setLoading, navigation)
  const content = {
    'Primeiro Nome': data.first_name,
    'Último Nome': data.last_name,
    'Tipo de Utilizador': data.user_type,
    'Email': data.email
  }
  return isLoading ? (<LoadingIndicator />) : (
    <ScrollView style={{ backgroundColor: backgroundColor(isDarkMode) }}>
      <OurHeaderCurve icon={'person'} content={data.username} />
      <OurCardView data={content} />
      <View style={PageStyle.bottomleft}>
        <OurButton title={'Logout'} icon={'log-out-outline'} onPress={logout} />
      </View>
    </ScrollView>
  )
}


export default User
