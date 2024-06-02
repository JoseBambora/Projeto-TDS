import React, { useContext } from 'react';
import { Text } from 'react-native';
import TextStyle from '../../styles/ui/Text';
import { textColorPrimary } from '../../styles/Colors';

const OurText = ({ content, fontSize = 16, color, textAlign = 'left', width, fontWeight = 'normal', isDarkMode }) => {
  const dynamicColor = color || textColorPrimary(isDarkMode);

  return (
    <Text style={TextStyle(fontSize, dynamicColor, textAlign, width, fontWeight).textStyle}>
      {content}
    </Text>
  );
};

export default OurText;
