import React, {useCallback, useState} from 'react';
import { useFocusEffect } from '@react-navigation/native';
import OurText from '../../components/Text';
import { IsAuthenticated } from '../../redux/SessionState';
import User from './User';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import Login from './Login';
import Register from './Register';


const Stack = createNativeStackNavigator();

function StackNavigator(logged) {
  initialRouteName = logged == 1 ? "UserPage" : "Login"
  return (
    <Stack.Navigator initialRouteName={initialRouteName}>
      <Stack.Screen name="Login" component={Login} />
      <Stack.Screen name="Register" component={Register} />
      <Stack.Screen name="UserPage" component={User} />
    </Stack.Navigator>
  )
}

function isLogged(setLogged) {
  return useCallback(() => {
    IsAuthenticated()
    .then(v => v ? setLogged(1) : setLogged(2))
    .catch(_ => setLogged(2))
  }, [])
}

function PageUser() {
  const [logged, setLogged] = useState(0)
  useFocusEffect(isLogged(setLogged));
  return logged == 0 ? (<OurText content={'A verificar se está autenticado'}/>) : StackNavigator(logged)
};

export default PageUser;
