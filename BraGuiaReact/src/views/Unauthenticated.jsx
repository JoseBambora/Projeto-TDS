import React from 'react';
import { View } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { OurHeaderCurve } from '../components/HeaderCurve';
import UnauthenticatedStyles from '../styles/Unauthenticated';
import { activityColorPrimary } from '../styles/Colors';
import OurText from '../components/Text';
import OurButton from '../components/Button';

const Unauthenticated = () => {
  const navigation = useNavigation();

  const handleLogin = () => {
    navigation.navigate('User');
  };

  return (
    <View style={UnauthenticatedStyles.container}>
      <OurHeaderCurve icon="sad-sharp" />
      <View style={UnauthenticatedStyles.content}>
      <OurText
          content="Para acessar aos trilhos, é necessário estar autenticado!"
          fontSize={18}
          color={activityColorPrimary()}
          textAlign="center"
        />
        <OurButton
          title="Autentique-se"
          onPress={handleLogin}
          color={activityColorPrimary()}
        />
      </View>
    </View>
  );
};

export default Unauthenticated;
