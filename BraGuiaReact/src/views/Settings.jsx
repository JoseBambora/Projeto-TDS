import React from 'react';
import { View } from 'react-native';
import OurText from '../components/Text';

function Settings({navigation}) {
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <OurText content={'Welcome to Settings Page'}/>
    </View>
  );
};

export default Settings;
