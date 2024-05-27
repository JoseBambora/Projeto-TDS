import React from 'react';
import { Text } from 'react-native';
import TextStyle from '../styles/Text';
import { textColorPrimary } from '../styles/Colors';


const OurText = ({ content, fontSize=16, color=textColorPrimary(), textAlign='left', width}) => (
  <Text style={TextStyle(fontSize,color,textAlign,width).textStyle}>{content}</Text>
)

export default OurText;
