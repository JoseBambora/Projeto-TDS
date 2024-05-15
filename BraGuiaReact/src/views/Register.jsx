import React from 'react';
import { View } from 'react-native';
import OurButton from '../components/Button';
import OurText from '../components/Text';


function Register({ navigation }) {
  const redirectToLogin = () => {
    navigation.navigate('Login');
  };
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <OurText content={'Welcome Register Page'} />
      <OurButton title='Login' onPress={redirectToLogin} icon={'log-in'} />
    </View>
  );
};

export default Register;
