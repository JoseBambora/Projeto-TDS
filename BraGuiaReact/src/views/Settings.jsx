import React, { useState, useEffect } from 'react';
import { View } from 'react-native';
import SwitchButtonStyle from '../styles/SwitchButton';
import SwitchButton from '../components/SwitchButton';
import { OurHeaderCurve } from '../components/HeaderCurve';
import { GetSettings, SaveDarkMode, SaveLocationOn, SaveNotificationOn } from '../repositories/Settings';
import PrecisionOption from '../components/PrecisionOption';
import OurText from '../components/Text';
import OurSlider from '../components/SliderComponent';

function Settings() {
  const [isLocationEnabled, setIsLocationEnabled] = useState(false);
  const [areNotificationsEnabled, setAreNotificationsEnabled] = useState(false);
  const [isDarkModeEnabled, setIsDarkModeEnabled] = useState(false);
  const [delay, setDelay] = useState(1);
  const [accuracy, setAccuracy] = useState('Baixa'); 

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

  const handleAccuracyChange = (newAccuracy) => {
    setAccuracy(newAccuracy);
  };

  const handleDelayChange = (newDelay) => {
    setDelay(newDelay);
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
      <View style={{ alignItems: 'center', marginTop: 20, width: '100%' }}>
        <OurText content="Precisão da localização" fontSize={18} fontWeight="bold" />
        <View style={{ flexDirection: 'row', justifyContent: 'space-between', width: '50%' }}>
          <PrecisionOption
            label="Baixa"
            isSelected={accuracy === 'Baixa'}
            onPress={() => handleAccuracyChange('Baixa')}
          />
          <PrecisionOption
            label="Alta"
            isSelected={accuracy === 'Alta'}
            onPress={() => handleAccuracyChange('Alta')}
          />
        </View>
      </View>
      <View style={{ alignItems: 'center', marginTop: 20, width: '100%' }}>
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