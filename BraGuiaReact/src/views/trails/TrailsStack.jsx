import React, { useCallback, useState } from 'react';
import { useFocusEffect } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Trails from './TrailsList';
import TrailDetail from './TrailDetail';
import { IsAuthenticated } from '../../repositories/User';
import { EmergencyCall } from '../../components/Emergency';

const Stack = createNativeStackNavigator();

function TrailsStack() {
  const [authenticated, setAuthenticated] = useState(false);

  const checkAuthentication = useCallback(() => {
    IsAuthenticated()
      .then(isAuthenticated => setAuthenticated(isAuthenticated))
      .catch(error => console.error('Error checking authentication:', error));
  }, []);

  useFocusEffect(checkAuthentication);

  return (
    <Stack.Navigator>
      <Stack.Screen name="TrailsList" component={Trails} options={{...EmergencyCall(), headerShown: true}} />
      <Stack.Screen name="TrailDetail" component={TrailDetail} options={{...EmergencyCall(), headerShown: true}} />
    </Stack.Navigator>
  );
}

export default TrailsStack;
