import React, { useState, useContext } from 'react';
import { View } from 'react-native';
import OurButton from '../../components/ui/Button';
import OurText from '../../components/ui/Text';
import OurTextInput from '../../components/ui/TextInput';
import OurClickable from '../../components/ui/Clickable';
import LoginRequest from '../../helper/LoginRequest';
import PageStyle from '../../styles/ui/Pages'
import LoadingIndicator from '../../components/ui/Indicator';
import { ThemeContext } from '../../controler/ThemeControler';
import { backgroundColor } from '../../styles/Colors';

function Login({ navigation }) {
  const { isDarkMode } = useContext(ThemeContext);
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
  const color = errorMsg.length > 0 ? 'red' : 'gray'
  return login ? (<LoadingIndicator />) : (
    <View style={[PageStyle.center, { backgroundColor: backgroundColor(isDarkMode) }]}>
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
