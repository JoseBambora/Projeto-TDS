import React from 'react';
import { View } from 'react-native';
import OurButton from '../components/Button';
import OurText from '../components/Text';


function Login({navigation}) {
  const redirectToRegister = () => {
    navigation.navigate('Register');
  };
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <OurText content={'Welcome Login Page'}/>
      <OurButton title='Registar' onPress={redirectToRegister} icon={'person-add'}/>
    </View>
  );
};

export default Login;
