import React, { useCallback, useState } from 'react';
import { useFocusEffect } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Trails from './TrailsList';
import TrailDetail from './TrailDetail';
import { IsAuthenticated } from '../../repositories/User';
import { EmergencyCall } from '../../components/Emergency';
import LoadingIndicator from '../../components/Indicator';
import Unauthenticated from '../Unauthenticated';
import PinDetail from './PinDetail';

const Stack = createNativeStackNavigator();


function StackNavigator(logged) {
  const initialRouteName = logged === 1 ? "TrailsList" : "Unauthenticated";
  return (
    <Stack.Navigator initialRouteName={initialRouteName}>
      {logged === 2 ? (<Stack.Screen name="Unauthenticated" component={Unauthenticated} options={{ ...EmergencyCall(), headerShown: true }} />) :
        (<>
          <Stack.Screen name="TrailsList" component={Trails} options={{ ...EmergencyCall(), headerShown: true }} />
          <Stack.Screen name="TrailDetail" component={TrailDetail} options={{ ...EmergencyCall(), headerShown: true }} />
          <Stack.Screen name="PinDetail" component={PinDetail} options={{ ...EmergencyCall(), headerShown: true }} />
        </>)}
    </Stack.Navigator>
  );
}

function isLogged(setLogged) {
  return useCallback(() => {
    IsAuthenticated()
      .then(v => setLogged(v ? 1 : 2))
      .catch(_ => setLogged(2));
  }, [setLogged]);
}


function TrailsStack() {
  const [logged, setLogged] = useState(0);

  useFocusEffect(isLogged(setLogged));

  return logged === 0 ? <LoadingIndicator /> : StackNavigator(logged);
}

export default TrailsStack;
