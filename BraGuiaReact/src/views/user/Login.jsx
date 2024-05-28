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
  const [errorMsg, setErrorMessage] = useState('');
  const redirectToRegister = () => {
    navigation.navigate('Register');
  };
  const errorFun = (error) => {
    setErrorMessage(error)
    setUsername('')
    setPassword('')
    
  }
  const postLogin = () => {
    if(username.length === 0)
      setErrorMessage('Insira um nome de utilizador')
    else if (password.length === 0)
      setErrorMessage('Insira uma palavra passe')
    else {
      setLogin(true)
      LoginRequest(username, password)
        .then(_ => navigation.replace('UserPage'))
        .catch(_ => {
          errorFun('Credenciais Erradas')
          setLogin(false)
        })
    }
  }
  color = errorMsg.length > 0 ? 'red' : 'gray'
  return login ? (<LoadingIndicator />) : (
    <View style={PageStyle.center}>
      <OurText content={'Página de Login'} color={'red'} fontSize={30} />
      {errorMsg && <OurText content={errorMsg} color={'red'} fontSize={15} />}
      <OurTextInput borderColor={color} placeholder={'Nome de Utilizador'} password={false} icon={'user'} onChangeText={text => setUsername(text)} />
      <OurTextInput borderColor={color} placeholder={'Palavra passe'} password={true} icon={'lock'} onChangeText={text => setPassword(text)} />
      <OurButton title='Login' onPress={postLogin} icon={'log-in-outline'} />
      <OurClickable text={'Ainda não criou conta?'} title='Registe-se' onPress={redirectToRegister} />
    </View>
    )
};

export default Login;
