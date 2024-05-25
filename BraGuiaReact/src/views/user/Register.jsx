import React, {useState} from 'react';
import { View } from 'react-native';
import OurButton from '../../components/Button';
import OurText from '../../components/Text';
import OurTextInput from '../../components/TextInput';
import OurClickable from '../../components/Clickable';
import PageStyle from '../../styles/Pages';


function Register({ navigation }) {
  const [username, setUsername] = useState('');
  const [fstPassword, setPassword1] = useState('');
  const [sndPassword, setPassword2] = useState('');
  const redirectToLogin = () => {
    navigation.navigate('Login');
  };
  const posRegister = () => {

  }
  return (
    <View style={PageStyle.center}>
      <OurText content={'Página de Registo'} color={'red'} fontSize={30}/>
      <OurTextInput 
        placeholder={'Nome de Utilizador'} 
        password={false} icon={'user'} 
        onChangeText={text => setUsername(text)}/>
      <OurTextInput 
      placeholder={'Palavra passe'} 
        password={true} 
        icon={'lock'} 
        onChangeText={text => setPassword1(text)}/>
      <OurTextInput 
        placeholder={'Confirmar Palavra Passe'} 
        password={true} icon={'lock'} 
        onChangeText={text => setPassword2(text)}/>
      <OurButton title='Registar' onPress={posRegister} icon={'person-add'}/>
      <OurClickable text={'Já criou conta?'} title='Faça login' onPress={redirectToLogin}/>
    </View>
  );
};

export default Register;
