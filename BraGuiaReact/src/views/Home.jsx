import React from 'react';
import { View, Text, Button } from 'react-native';
import OurText from '../components/Text';

function Home({navigation}) {
  const redirectToLoginPage = () => {
    navigation.navigate('Login');
  };
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <OurText content={'Welcome to Home Page'}/>
    </View>
  );
};

export default Home;
