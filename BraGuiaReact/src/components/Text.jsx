import React from 'react';
import { Text } from 'react-native';
import TextStyle from '../styles/Text';
import { textColorPrimary } from '../styles/Colors';


function OurText({ content, fontSize, color, textAlign, width}) {
  if (fontSize == undefined)
    fontSize = 16
  if (color == undefined)
    color = textColorPrimary()
  if (textAlign == undefined)
    textAlign = 'left'
  return (
    <Text style={TextStyle(fontSize,color,textAlign,width).textStyle}>{content}</Text>
  );
}

export default OurText;
