import React, { useState } from 'react';
import { View } from 'react-native';
import SwitchButtonStyle from '../styles/SwitchButton';
import SwitchButton from '../components/SwitchButton';
import { OurHeaderCurve } from '../components/HeaderCurve';

function Settings() {
  const [isLocationEnabled, setIsLocationEnabled] = useState(false);
  const [areNotificationsEnabled, setAreNotificationsEnabled] = useState(false);
  const [isDarkModeEnabled, setisDarkModeEnabled] = useState(false);

  const toggleLocation = () => {
    setIsLocationEnabled(previousState => !previousState);
  };

  const toggleNotifications = () => {
    setAreNotificationsEnabled(previousState => !previousState);
  };
  const toggleDarkMode = () => {
    setisDarkModeEnabled(previousState => !previousState);
  };

  return (
    <View style={{ flex: 1 }}>
      <OurHeaderCurve icon="settings" />
      <View style={SwitchButtonStyle.container}>
        <SwitchButton
          label="Notificações"
          value={areNotificationsEnabled}
          onValueChange={toggleNotifications}
          iconName="notifications"
        />
        <SwitchButton
          label="Localização"
          value={isLocationEnabled}
          onValueChange={toggleLocation}
          iconName="location-sharp"
        />
        <SwitchButton
          label="Dark Mode"
          value={isDarkModeEnabled}
          onValueChange={toggleDarkMode}
          iconName="moon"
        />
      </View>
    </View>
  );
}

export default Settings;
