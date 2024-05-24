import * as React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import Home from './src/views/Home';
import PageUser from './src/views/user/PageUser'
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Ionicons from 'react-native-vector-icons/Ionicons';
import Settings from './src/views/Settings';
import NavBarStyle from './src/styles/NavBar';

const Tab = createBottomTabNavigator()


const getIcon = (route) => {
  if (route.name.includes('Home')) {
    return 'home';
  } else if (route.name.includes('Settings')) {
    return 'settings';
  }
  else if (route.name.includes('PageUser')) {
    return 'person'
  }
}

const App = () => {
  return (
    <NavigationContainer>
      <Tab.Navigator 
        initialRouteName='Home'
        screenOptions={({ route }) => ({
          tabBarIcon:({ focused, color, size }) => {
            return <Ionicons name={getIcon(route)} size={size} color={color} />;
          },
          tabBarActiveTintColor: 'white',
          tabBarInactiveTintColor: 'white',
          tabBarStyle:NavBarStyle.navbar
        } )}
        >
        <Tab.Screen name="PageUser" component={PageUser} options={{ headerShown: false }}/>
        <Tab.Screen name="Home" component={Home} />
        <Tab.Screen name="Settings" component={Settings} />
      </Tab.Navigator>
    </NavigationContainer>
  );
};

export default App;