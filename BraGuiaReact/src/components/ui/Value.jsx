import React, { useContext } from 'react';
import { Text } from 'react-native';
import ValueStyles from '../../styles/ui/Value';
import { textColorPrimary } from '../../styles/Colors';
import { ThemeContext } from '../../controler/ThemeControler';

const Value = ({ text }) => {
  const { isDarkMode } = useContext(ThemeContext); 
  return <Text style={[ValueStyles.value, { color: textColorPrimary(isDarkMode) }]}>{text}</Text>;
};
export default Value;
