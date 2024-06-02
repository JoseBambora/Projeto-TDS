import React, { useState, useEffect, useContext } from 'react';
import { View, ScrollView } from 'react-native';
import SwitchButtonStyle from '../../styles/ui/SwitchButton';
import { ThemeContext } from '../../controler/ThemeControler';
import SwitchButton from '../../components/ui/SwitchButton';
import SettingsStyles from '../../styles/sub-components/Settings';
import { OurHeaderCurve } from '../../components/ui/HeaderCurve';
import { GetSettings, SaveLocationOn, SaveNotificationOn, SaveAccuracyLocation, SaveTimeoutLocation } from '../../repositories/Settings';
import PrecisionOption from '../../components/sub-components/PrecisionOption';
import OurText from '../../components/ui/Text';
import OurSlider from '../../components/ui/SliderComponent';
import { backgroundColor} from '../../styles/Colors';

function Settings() {
  const { isDarkMode, toggleDarkMode } = useContext(ThemeContext);
  const [isLocationEnabled, setIsLocationEnabled] = useState(false);
  const [areNotificationsEnabled, setAreNotificationsEnabled] = useState(false);
  const [delay, setDelay] = useState(1);
  const [accuracy, setAccuracy] = useState(false);

  useEffect(() => {
    const settings = GetSettings();
    setIsLocationEnabled(settings.location_on);
    setAreNotificationsEnabled(settings.notification_on);
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

  const handleAccuracyChange = (isHighAccuracy) => {
    setAccuracy(isHighAccuracy);
    SaveAccuracyLocation(isHighAccuracy);
  };

  const handleDelayChange = (newDelay) => {
    setDelay(newDelay);
    SaveTimeoutLocation(newDelay * 1000);
  };

  return (
    <ScrollView style={{ flex: 2, backgroundColor: backgroundColor(isDarkMode) }}>
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
          value={isDarkMode}
          onValueChange={toggleDarkMode}
          iconName="moon"
        />
      </View>
      <View style={SettingsStyles.container}>
        <OurText content="Precisão da localização" fontSize={18} fontWeight="bold" isDarkMode={isDarkMode} />
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
          isDarkMode={isDarkMode}
        />
      </View>
    </ScrollView>
  );
}

export default Settings;
