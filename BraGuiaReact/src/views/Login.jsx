import React, {useState} from 'react';
import { View } from 'react-native';
import OurButton from '../components/Button';
import OurText from '../components/Text';
import OurTextInput from '../components/TextInput';
import OurClickable from '../components/Clickable';


function Login({navigation}) {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const redirectToRegister = () => {
    navigation.navigate('Register');
  };
  const posLogin = () => {
    console.log(`username: ${username}`)
    console.log(`password: ${password}`)

  }
  return (
    <View style={{flex:1, justifyContent: 'center', alignItems: 'center' }}>
      <OurText content={'Página de Login'} color={'red'} fontSize={30}/>
      <OurTextInput placeholder={'Nome de Utilizador'} password={false} icon={'user'} onChangeText={text => setUsername(text)}/>
      <OurTextInput placeholder={'Palavra passe'} password={true} icon={'lock'} onChangeText={text => setPassword(text)} />
      <OurButton title='Login' onPress={posLogin} icon={'enter-outline'}/>
      <OurClickable text={'Ainda não criou conta?'} title='Registe-se' onPress={redirectToRegister}/>
    </View>
  );
};

export default Login;
