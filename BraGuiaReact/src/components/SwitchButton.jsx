import React from 'react';
import { View, Switch } from 'react-native';
import OurText from './Text';
import SwitchButtonStyle from '../styles/SwitchButton';
import { navBarColorSelected, navBarColorNotSelected, iconsColorPrimary, activityColorPrimary, iconsColorSecondary } from '../styles/Colors';
import Ionicons from 'react-native-vector-icons/Ionicons';

const SwitchButton = ({ label, value, onValueChange, iconName }) => {
  return (
    <View style={SwitchButtonStyle.switchContainer}>
      {iconName && (
        <Ionicons name={iconName} size={20} color={iconsColorSecondary()} style={{ marginRight: 10 }} />
      )}
      <OurText content={label} style={SwitchButtonStyle.text} />
      <Switch
        trackColor={{ false: navBarColorNotSelected(), true: activityColorPrimary() }}
        thumbColor={value ? navBarColorSelected() : iconsColorPrimary()}
        ios_backgroundColor={navBarColorNotSelected()}
        onValueChange={onValueChange}
        value={value}
      />
    </View>
  );
};

export default SwitchButton;
