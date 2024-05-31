import React from 'react';
import { Text } from 'react-native';
import ValueStyles from '../styles/Value';

const Value = ({ text }) => {
  return <Text style={ValueStyles.value}>{text}</Text>;
};

export default Value;
