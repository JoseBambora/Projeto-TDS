import React, { useState, useEffect } from 'react';
import { View } from 'react-native';
import SwitchButtonStyle from '../styles/SwitchButton';
import SwitchButton from '../components/SwitchButton';
import SettingsStyles from '../styles/Settings';
import { OurHeaderCurve } from '../components/HeaderCurve';
import { GetSettings, SaveDarkMode, SaveLocationOn, SaveNotificationOn, SaveAccuracyLocation, SaveTimeoutLocation } from '../repositories/Settings';
import PrecisionOption from '../components/PrecisionOption';
import OurText from '../components/Text';
import OurSlider from '../components/SliderComponent';

function Settings() {
  const [isLocationEnabled, setIsLocationEnabled] = useState(false);
  const [areNotificationsEnabled, setAreNotificationsEnabled] = useState(false);
  const [isDarkModeEnabled, setIsDarkModeEnabled] = useState(false);
  const [delay, setDelay] = useState(1);
  const [accuracy, setAccuracy] = useState(false);

  useEffect(() => {
    const settings = GetSettings();
    setIsLocationEnabled(settings.location_on);
    setAreNotificationsEnabled(settings.notification_on);
    setIsDarkModeEnabled(settings.dark_mode);
    setAccuracy(settings.accuracy);
    setDelay(settings.timeout / 1000);
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

  const handleAccuracyChange = (isHighAccuracy) => {
    setAccuracy(isHighAccuracy);
    SaveAccuracyLocation(isHighAccuracy);
  };

  const handleDelayChange = (newDelay) => {
    setDelay(newDelay);
    SaveTimeoutLocation(newDelay * 1000); 
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
      <View style={SettingsStyles.container}>
        <OurText content="Precisão da localização" fontSize={18} fontWeight="bold" />
        <View style={SettingsStyles.optionContainer}>
          <PrecisionOption
            label="Baixa"
            isSelected={!accuracy}
            onPress={() => handleAccuracyChange(false)}
          />
          <PrecisionOption
            label="Alta"
            isSelected={accuracy}
            onPress={() => handleAccuracyChange(true)}
          />
        </View>
      </View>
      <View style={SettingsStyles.sliderContainer}>
        <OurSlider
          value={delay}
          onValueChange={handleDelayChange}
          min={1}
          max={50}
          step={1}
          title="Intervalo entre medição da localização do utilizador"
          valueLabel={(value) => `${value} segundos`}
        />
      </View>
    </View>
  );
}

export default Settings;
