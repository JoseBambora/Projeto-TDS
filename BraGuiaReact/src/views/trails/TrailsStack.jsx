import React, { useCallback, useState } from 'react';
import { useFocusEffect } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Trails from './TrailsList';
import TrailDetail from './TrailDetail';
import { IsAuthenticated } from '../../repositories/User';
import { HeaderProps } from '../../components/sub-components/Header';
import LoadingIndicator from '../../components/ui/Indicator';
import Unauthenticated from '../user/Unauthenticated';
import PinDetail from './PinDetail';

const Stack = createNativeStackNavigator();


function StackNavigator(logged) {
  const initialRouteName = logged === 1 ? "TrailsList" : "Unauthenticated";
  return (
    <Stack.Navigator initialRouteName={initialRouteName}>
      {logged === 2 ? (<Stack.Screen name="Unauthenticated" component={Unauthenticated} options={HeaderProps()} />) :
        (<>
          <Stack.Screen name="TrailsList" component={Trails} options={HeaderProps()} />
          <Stack.Screen name="TrailDetail" component={TrailDetail} options={HeaderProps()} />
          <Stack.Screen name="PinDetail" component={PinDetail} options={HeaderProps()} />
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
