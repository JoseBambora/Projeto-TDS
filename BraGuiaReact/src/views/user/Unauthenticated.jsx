import React from 'react';
import { View, ScrollView, StyleSheet } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import { OurHeaderCurve } from '../../components/ui/HeaderCurve';
import UnauthenticatedStyles from '../../styles/sub-components/Unauthenticated';
import { activityColorPrimary } from '../../styles/Colors';
import OurText from '../../components/ui/Text';
import OurButton from '../../components/ui/Button';
import { refreshIfDarkModeChanges } from '../utils/RefreshDarkMode';
import { pageColor } from '../../styles/Colors';
import PageStyle from '../../styles/ui/Pages';

const Unauthenticated = () => {
  refreshIfDarkModeChanges();
  const UnauthenticatedStyleVar = UnauthenticatedStyles()
  const navigation = useNavigation();

  const handleLogin = () => {
    navigation.navigate('User');
  };

  return (
    <ScrollView style={PageStyle(pageColor()).page}>
      <OurHeaderCurve icon="sad-sharp" />
      <View style={UnauthenticatedStyleVar.content}>
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
    </ScrollView>
  );
};

export default Unauthenticated;
