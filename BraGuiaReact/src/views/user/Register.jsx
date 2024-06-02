import React, {useState} from 'react';
import { View } from 'react-native';
import OurButton from '../../components/ui/Button';
import OurText from '../../components/ui/Text';
import OurTextInput from '../../components/ui/TextInput';
import OurClickable from '../../components/ui/Clickable';
import PageStyle from '../../styles/ui/Pages';
import LoadingIndicator from '../../components/ui/Indicator';
import { Alert } from 'react-native';


function Register({ navigation }) {
  const [username, setUsername] = useState('');
  const [fstPassword, setPassword1] = useState('');
  const [sndPassword, setPassword2] = useState('');
  const [register, setRegister] = useState(false);
  const redirectToLogin = () => {
    navigation.navigate('Login');
  };
  const postRegister = () => {
    setRegister(true)
    Alert.alert(`Funcionalidade não implementada`);
    setRegister(false)
  }
  return register ? (<LoadingIndicator />):(
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
      <OurButton title='Registar' onPress={postRegister} icon={'person-add'}/>
      <OurClickable text={'Já criou conta?'} title='Faça login' onPress={redirectToLogin}/>
    </View>
  );
};

export default Register;
