import * as React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import Login from './src/views/Login';
import Home from './src/views/Home';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import Register from './src/views/Register';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Ionicons from 'react-native-vector-icons/Ionicons';

import Settings from './src/views/Settings';
import NavBarStyle from './src/styles/NavBar';

const Stack = createNativeStackNavigator();
const Tab = createBottomTabNavigator()

function LoginStack() {
  return (
    <Stack.Navigator initialRouteName="Login">
      <Stack.Screen name="Login" component={Login} />
      <Stack.Screen name="Register" component={Register} />
    </Stack.Navigator>
  )
}

const getIcon = (route) => {
  if (route.name.includes('Principal')) {
    return 'home';
  } else if (route.name.includes('Settings')) {
    return 'settings';
  }
  else if (route.name.includes('Autenticação')) {
    return 'log-in'
  }
}

const App = () => {
  return (
    <NavigationContainer>
      <Tab.Navigator 
        initialRouteName='Principal'
        screenOptions={({ route }) => ({
          tabBarIcon:({ focused, color, size }) => {
            return <Ionicons name={getIcon(route)} size={size} color={color} />;
          },
          tabBarActiveTintColor: 'white',
          tabBarInactiveTintColor: 'white',
          tabBarStyle:NavBarStyle.navbar
        } )}
        >
        <Tab.Screen name="Autenticação" component={LoginStack} options={{ headerShown: false }}/>
        <Tab.Screen name="Principal" component={Home} />
        <Tab.Screen name="Settings" component={Settings} />
      </Tab.Navigator>
    </NavigationContainer>
  );
};

export default App;