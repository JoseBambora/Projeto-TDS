import React, { useContext } from 'react';
import { Text } from 'react-native';
import LabelStyles from '../../styles/ui/Label';
import { textColorPrimary } from '../../styles/Colors'; 
import { ThemeContext } from '../../controler/ThemeControler';

const Label = ({ text }) => {
  const { isDarkMode } = useContext(ThemeContext); 
  return <Text style={[LabelStyles.label, { color: textColorPrimary(isDarkMode) }]}>{text}</Text>;
};

export default Label;