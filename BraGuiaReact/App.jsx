import * as React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import Home from './src/views/Home';
import PageUser from './src/views/user/PageUser'
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Ionicons from 'react-native-vector-icons/Ionicons';
import Settings from './src/views/settings/Settings';
import History from './src/views/history/History';
import NavBarStyle from './src/styles/sub-components/NavBar';
import { navBarColorNotSelected, navBarColorSelected } from './src/styles/Colors';
import { HeaderProps } from './src/components/sub-components/Header';
import TrailsStack from './src/views/trails/TrailsStack';

const Tab = createBottomTabNavigator()

const getIcon = (route) => {
  if (route.name.includes('Home')) {
    return 'home';
  } else if (route.name.includes('Settings')) {
    return 'settings';
  }
  else if (route.name.includes('Trails')) {
    return 'walk'
  }
  else if (route.name.includes('User')) {
    return 'person'
  }
  else if (route.name.includes('History')) {
    return 'timer-outline'
  }
}

const App = () => {
  return (
    <NavigationContainer>
      <Tab.Navigator 
        initialRouteName='Home'
        screenOptions={({ route }) => ({
          ...HeaderProps(),
          tabBarIcon:({ focused, color, size }) => {
            size = focused ? size + 5 : size
            return  <Ionicons name={getIcon(route)} size={size} color={color} />;
          },
          tabBarActiveTintColor: navBarColorSelected(),
          tabBarInactiveTintColor: navBarColorNotSelected(),
          tabBarStyle:NavBarStyle.navbar,
        } )}
        >
        <Tab.Screen name="User" component={PageUser} options={{ headerShown: false }}/>
        <Tab.Screen name="History" component={History} />
        <Tab.Screen name="Home" component={Home} />
        <Tab.Screen name="Trails" component={TrailsStack} options={{ headerShown: false }}/>
        <Tab.Screen name="Settings" component={Settings} />
      </Tab.Navigator>
    </NavigationContainer>
  );
};

export default App;