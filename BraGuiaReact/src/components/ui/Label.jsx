import React from 'react';
import { Text } from 'react-native';
import LabelStyles from '../../styles/ui/Label';
import { refreshIfDarkModeChanges } from '../../views/utils/RefreshDarkMode';
import { textColorPrimary } from '../../styles/Colors';


const Label = ({ text }) => {
  refreshIfDarkModeChanges();
  const LabelStylesVar = LabelStyles(textColorPrimary())
  return <Text style={LabelStylesVar.label}>{text}</Text>;
};

export default Label;
