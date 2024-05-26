import React from 'react';
import { View } from 'react-native';
import OurText from '../../components/Text';

function History({navigation}) {
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <OurText content={'Welcome to History Page'}/>
    </View>
  );
};

export default History;