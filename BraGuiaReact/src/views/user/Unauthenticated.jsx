import React from 'react';
import { View } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { OurHeaderCurve } from '../../components/ui/HeaderCurve';
import UnauthenticatedStyles from '../../styles/sub-components/Unauthenticated';
import { activityColorPrimary } from '../../styles/Colors';
import OurText from '../../components/ui/Text';
import OurButton from '../../components/ui/Button';

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
          content={`Para acessar a esta página, é necessário estar autenticado!`}
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
