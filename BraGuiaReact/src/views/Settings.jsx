import React, { useState } from 'react';
import { View } from 'react-native';
import SwitchButtonStyle from '../styles/SwitchButton';
import SwitchButton from '../components/SwitchButton';

function Settings() {
  const [isLocationEnabled, setIsLocationEnabled] = useState(false);
  const [areNotificationsEnabled, setAreNotificationsEnabled] = useState(false);

  const toggleLocation = () => {
    setIsLocationEnabled(previousState => !previousState);
  };

  const toggleNotifications = () => {
    setAreNotificationsEnabled(previousState => !previousState);
  };

  return (
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
    </View>
  );
}

export default Settings;
