import React, {useCallback, useState} from 'react';
import { useFocusEffect } from '@react-navigation/native';
import { View } from 'react-native';
import OurButton from '../components/Button';
import OurText from '../components/Text';
import OurTextInput from '../components/TextInput';
import OurClickable from '../components/Clickable';
import LoginRequest from '../helper/LoginRequest';
import { IsAuthenticated, Logout } from '../helper/SessionState';


function Login({navigation}) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [logged, setLogged] = useState(0)
  const redirectToRegister = () => {
    navigation.navigate('Register');
  };
  const postLogin = () => {
    LoginRequest(username,password)
    .then(_ => navigation.navigate('Principal'))
    .catch(error => alert(error.message))
  }
  const logout = () => {
    Logout()
    .then(_ => navigation.navigate('Principal'))
    .catch(error => alert(error.message))
  }
  
  useFocusEffect(
    useCallback(() => {
      IsAuthenticated()
      .then(v => v ? setLogged(1) : setLogged(2))
      .catch(_ => setLogged(2))
    }, [])
  );
  
  return logged == 0 ? (<OurText content={'A verificar se está autenticado'}/>) :
        logged == 1 ? (
          <View style={{flex:1, justifyContent: 'center', alignItems: 'center' }}>
            <OurText content={'Já está autenticado'}/>
            <OurButton title={'Logout'} icon={'log-out-outline'} onPress={logout}/>
          </View>
        ) :
        (
            <View style={{flex:1, justifyContent: 'center', alignItems: 'center' }}>
              <OurText content={'Página de Login'} color={'red'} fontSize={30}/>
              <OurTextInput placeholder={'Nome de Utilizador'} password={false} icon={'user'} onChangeText={text => setUsername(text)}/>
              <OurTextInput placeholder={'Palavra passe'} password={true} icon={'lock'} onChangeText={text => setPassword(text)} />
              <OurButton title='Login' onPress={postLogin} icon={'log-in-outline'}/>
              <OurClickable text={'Ainda não criou conta?'} title='Registe-se' onPress={redirectToRegister}/>
            </View>
        )
};

export default Login;
