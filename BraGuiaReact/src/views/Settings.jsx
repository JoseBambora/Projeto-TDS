import React, { useState, useEffect } from 'react';
import { View } from 'react-native';
import SwitchButtonStyle from '../styles/SwitchButton';
import SwitchButton from '../components/SwitchButton';
import { OurHeaderCurve } from '../components/HeaderCurve';
import { GetSettings, SaveDarkMode, SaveLocationOn, SaveNotificationOn } from '../repositories/Settings';

function Settings() {
  const [isLocationEnabled, setIsLocationEnabled] = useState(false);
  const [areNotificationsEnabled, setAreNotificationsEnabled] = useState(false);
  const [isDarkModeEnabled, setIsDarkModeEnabled] = useState(false);

  useEffect(() => {
    const settings = GetSettings();
    setIsLocationEnabled(settings.location_on);
    setAreNotificationsEnabled(settings.notification_on);
    setIsDarkModeEnabled(settings.dark_mode);
  }, []);

  const toggleLocation = () => {
    const newValue = !isLocationEnabled;
    setIsLocationEnabled(newValue);
    SaveLocationOn(newValue);
  };

  const toggleNotifications = () => {
    const newValue = !areNotificationsEnabled;
    setAreNotificationsEnabled(newValue);
    SaveNotificationOn(newValue);
  };

  const toggleDarkMode = () => {
    const newValue = !isDarkModeEnabled;
    setIsDarkModeEnabled(newValue);
    SaveDarkMode(newValue);
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
