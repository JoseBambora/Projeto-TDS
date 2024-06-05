import React from 'react';
import { Text } from 'react-native';
import ValueStyles from '../../styles/ui/Value';
import { refreshIfDarkModeChanges } from '../../views/utils/RefreshDarkMode';
import { textColorPrimary } from '../../styles/Colors';


const Value = ({ text }) => {
  refreshIfDarkModeChanges();
  const ValueStyleVar = ValueStyles(textColorPrimary())
  return <Text style={ValueStyleVar.value}>{text}</Text>;
};

export default Value;
