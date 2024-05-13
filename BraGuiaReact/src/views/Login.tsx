import React from 'react';
import { View, Text, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';

function Login(): React.JSX.Element {
  const navigation = useNavigation();
  const redirectToHomePage = () => {
    navigation.navigate('Home' as never);
  };

  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <Text>Welcome to Login</Text>
      <Button title="Go to Home" onPress={redirectToHomePage} />
    </View>
  );
};

export default Login;
