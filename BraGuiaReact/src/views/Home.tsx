import React from 'react';
import { View, Text, Button } from 'react-native';
import { useNavigation } from '@react-navigation/native';

function Home(): React.JSX.Element {
  const navigation = useNavigation();
  const redirectToLoginPage = () => {
    navigation.navigate('Login' as never);
  };
  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <Text>Welcome to Home Page</Text>
      <Button title="Go to Login" onPress={redirectToLoginPage} />
    </View>
  );
};

export default Home;
