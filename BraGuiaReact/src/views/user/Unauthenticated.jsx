import React, { useContext } from 'react';
import { View } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { OurHeaderCurve } from '../../components/ui/HeaderCurve';
import UnauthenticatedStyles from '../../styles/sub-components/Unauthenticated';
import OurText from '../../components/ui/Text';
import OurButton from '../../components/ui/Button';
import { ThemeContext } from '../../controler/ThemeControler';
import { backgroundColor, activityColorPrimary } from '../../styles/Colors';

const Unauthenticated = () => {
  const { isDarkMode } = useContext(ThemeContext); 
  const navigation = useNavigation();

  const handleLogin = () => {
    navigation.navigate('User');
  };

  return (
    <View style={[UnauthenticatedStyles.container, { backgroundColor: backgroundColor(isDarkMode) }]}>
      <OurHeaderCurve icon="sad-sharp" />
      <View style={UnauthenticatedStyles.content}>
        <OurText
          content={`Para acessar esta página, é necessário estar autenticado!`}
          fontSize={18}
          color={activityColorPrimary(isDarkMode)}
          textAlign="center"
        />
        <OurButton
          title="Autenticar-se"
          onPress={handleLogin}
          color={activityColorPrimary(isDarkMode)}
        />
      </View>
    </View>
  );
};

export default Unauthenticated;
