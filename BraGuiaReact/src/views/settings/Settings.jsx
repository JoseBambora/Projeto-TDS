import React, { useState, useEffect } from 'react';
import { ScrollView, View } from 'react-native';
import SwitchButtonStyle from '../../styles/ui/SwitchButton';
import SwitchButton from '../../components/ui/SwitchButton';
import SettingsStyles from '../../styles/sub-components/Settings';
import { OurHeaderCurve } from '../../components/ui/HeaderCurve';
import { GetSettings, SaveDarkMode, SaveLocationOn, SaveNotificationOn, SaveAccuracyLocation, SaveTimeoutLocation } from '../../repositories/Settings';
import PrecisionOption from '../../components/sub-components/PrecisionOption';
import OurText from '../../components/ui/Text';
import OurSlider from '../../components/ui/SliderComponent';
import PageStyle from '../../styles/ui/Pages';
import { refreshIfDarkModeChanges } from '../utils/RefreshDarkMode';
import { pageColor } from '../../styles/Colors';

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
  refreshIfDarkModeChanges()

  return (
    <ScrollView style={PageStyle(pageColor()).page}>
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
          max={240}
          step={1}
          title="Intervalo entre medição da localização do utilizador"
          valueLabel={(value) => `${value} segundos`}
        />
      </View>
    </ScrollView>
  );
}

export default Settings;
