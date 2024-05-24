import React, {useEffect, useState}  from "react"
import { ScrollView, View } from "react-native"
import OurButton from "../../components/Button"
import OurCardView from "../../components/CardView"
import { GetUser } from "../../repositories/User"
import OurText from "../../components/Text"
import { Logout } from "../../repositories/User"

function userInfo(setData,setLoading,navigation) {
  useEffect(() => {
    GetUser()
    .then(json => {setData(json); navigation.setOptions({title: 'User ' + json.username})})
    .catch(err => alert(err.message))
    .finally(() => setLoading(false))
  },[])
}

function User({ navigation }) {
  const [isLoading, setLoading] = useState(true);
  const [data, setData] = useState([]);
  const logout = () => {
    Logout()
      .then(_ => navigation.replace('Login'))
      .catch(error => alert(error.message))
  }
  userInfo(setData,setLoading,navigation)
  return isLoading ? (
    <View>
      <OurText content={'Loading user info'}/>
    </View>
  ) : (
    <ScrollView>
      <View style={{ padding: 10, paddingBottom: 30 }}>
        <OurCardView title={'Nome de Utilizador'} description={data.username} />
        <OurCardView title={'Primeiro Nome'} description={data.first_name} />
        <OurCardView title={'Último Nome'} description={data.last_name} />
        <OurCardView title={'Email'} description={data.email} />
        <OurCardView title={'Tipo de Utilizador'} description={data.user_type} />
      </View>
      <View style={{ padding: 5, paddingBottom: 10, paddingRight: 20, alignItems: 'flex-end' }}>
        <OurButton title={'Logout'} icon={'log-out-outline'} onPress={logout} />
      </View>
    </ScrollView>
  )
}


export default User