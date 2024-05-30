import React from 'react';
import { Text } from 'react-native';
import LabelStyles from '../styles/Label';

const Label = ({ text }) => {
  return <Text style={LabelStyles.label}>{text}</Text>;
};

export default Label;
