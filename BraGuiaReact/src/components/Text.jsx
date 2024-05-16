import React from 'react';
import { Text } from 'react-native';
import TextStyle from '../styles/Text';


function OurText({ content, fontSize, color, textAlign}) {
  if (fontSize == undefined)
    fontSize = 16
  if (color == undefined)
    color = 'black'
  if (textAlign == undefined)
    textAlign = 'left'
  return (
    <Text style={TextStyle(fontSize,color,textAlign)}>{content}</Text>
  );
}

export default OurText;
