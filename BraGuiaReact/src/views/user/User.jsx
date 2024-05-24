import React, {useEffect, useState}  from "react"
import { ScrollView, View } from "react-native"
import OurButton from "../../components/Button"
import OurCardView from "../../components/CardView"
import { GetUser } from "../../repositories/User"
import OurText from "../../components/Text"
import { Logout } from "../../repositories/User"
import { OurHeaderCurve } from "../../components/HeaderCurve"
import PageStyle from "../../styles/Pages"

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
  const content = {
    'Primeiro Nome': data.first_name,
    'Último Nome': data.last_name,
    'Tipo de Utilizador': data.user_type,
    'Email': data.email
  }
  return isLoading ? (
    <View>
      <OurText content={'Loading user info'}/>
    </View>
  ) : (
    <ScrollView>
      <OurHeaderCurve icon={'person'} content={data.username} />
      <OurCardView data={content} />
      <View style={PageStyle.bottomleft}>
        <OurButton title={'Logout'} icon={'log-out-outline'} onPress={logout} />
      </View>
    </ScrollView>
  )
}


export default User