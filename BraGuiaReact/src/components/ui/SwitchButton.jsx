import React, { useContext } from 'react';
import { View, Switch } from 'react-native';
import OurText from './Text';
import SwitchButtonStyle from '../../styles/ui/SwitchButton';
import { ThemeContext } from '../../controler/ThemeControler';
import { navBarColorSelected, navBarColorNotSelected, iconsColorPrimary, activityColorPrimary, iconsColorSecondary } from '../../styles/Colors';
import Ionicons from 'react-native-vector-icons/Ionicons';

const SwitchButton = ({ label, value, onValueChange, iconName }) => {
  const { isDarkMode } = useContext(ThemeContext);

  return (
    <View style={[SwitchButtonStyle.switchContainer, { backgroundColor: isDarkMode ? 'black' : 'white' }]}>
      {iconName && (
        <Ionicons name={iconName} size={20} color={iconsColorSecondary(isDarkMode)} style={{ marginRight: 10 }} />
      )}
      <OurText content={label} isDarkMode={isDarkMode} style={SwitchButtonStyle.text} />
      <Switch
        trackColor={{ false: navBarColorNotSelected(isDarkMode), true: activityColorPrimary(isDarkMode) }}
        thumbColor={value ? navBarColorSelected(isDarkMode) : iconsColorPrimary(isDarkMode)}
        ios_backgroundColor={navBarColorNotSelected(isDarkMode)}
        onValueChange={onValueChange}
        value={value}
      />
    </View>
  );
};

export default SwitchButton;
