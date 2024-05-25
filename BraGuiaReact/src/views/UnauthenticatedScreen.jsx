import React from 'react';
import { View, Text, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { OurHeaderCurve } from '../components/HeaderCurve';
import UnauthenticatedScreenStyles from '../styles/UnauthenticatedScreen'; // Import styles from separate file

const UnauthenticatedScreen = () => {
  const navigation = useNavigation();

  const handleLogin = () => {
    navigation.navigate('User');
  };

  return (
    <View style={UnauthenticatedScreenStyles.container}>
      <OurHeaderCurve icon="sad-sharp" />
      <View style={UnauthenticatedScreenStyles.content}>
        <Text style={UnauthenticatedScreenStyles.text}>
          Para acessar aos trilhos, é necessário estar autenticado!
        </Text>
        <Button title="Autentique-se" onPress={handleLogin} color="red" />
      </View>
    </View>
  );
};

export default UnauthenticatedScreen;
