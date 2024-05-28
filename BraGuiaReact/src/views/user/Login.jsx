import React, { useCallback, useState } from 'react';
import { useFocusEffect } from '@react-navigation/native';
import { View } from 'react-native';
import OurButton from '../../components/Button';
import OurText from '../../components/Text';
import OurTextInput from '../../components/TextInput';
import OurClickable from '../../components/Clickable';
import LoginRequest from '../../helper/LoginRequest';
import PageStyle from '../../styles/Pages'
import LoadingIndicator from '../../components/Indicator';


function Login({ navigation }) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [login, setLogin] = useState(false);
  const redirectToRegister = () => {
    navigation.navigate('Register');
  };
  const postLogin = () => {
    setLogin(true)
    LoginRequest(username, password)
      .then(_ => navigation.replace('UserPage'))
      .catch(error => alert(error.message))
  }

  return login ? (<LoadingIndicator />) : (
    <View style={PageStyle.center}>
      <OurText content={'Página de Login'} color={'red'} fontSize={30} />
      <OurTextInput placeholder={'Nome de Utilizador'} password={false} icon={'user'} onChangeText={text => setUsername(text)} />
      <OurTextInput placeholder={'Palavra passe'} password={true} icon={'lock'} onChangeText={text => setPassword(text)} />
      <OurButton title='Login' onPress={postLogin} icon={'log-in-outline'} />
      <OurClickable text={'Ainda não criou conta?'} title='Registe-se' onPress={redirectToRegister} />
    </View>
    )
};

export default Login;
