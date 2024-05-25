import React from 'react';
import { View, Text, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { OurHeaderCurve } from '../components/HeaderCurve';
import UnauthenticatedStyles from '../styles/Unauthenticated';

const Unauthenticated = () => {
  const navigation = useNavigation();

  const handleLogin = () => {
    navigation.navigate('User');
  };

  return (
    <View style={UnauthenticatedStyles.container}>
      <OurHeaderCurve icon="sad-sharp" />
      <View style={UnauthenticatedStyles.content}>
        <Text style={UnauthenticatedStyles.text}>
          Para acessar aos trilhos, é necessário estar autenticado!
        </Text>
        <Button title="Autentique-se" onPress={handleLogin} color="red" />
      </View>
    </View>
  );
};

export default Unauthenticated;
