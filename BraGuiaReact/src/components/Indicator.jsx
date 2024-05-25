import React from 'react';
import { SafeAreaView, ActivityIndicator } from 'react-native';
import Indicator from '../styles/Indicator';
import { activityColorPrimary } from '../styles/Colors';

const LoadingIndicator = ({size, color}) => (
  <SafeAreaView style={Indicator.defaultStyle}>
    <ActivityIndicator size={size} color={color} />
  </SafeAreaView>
);

LoadingIndicator.defaultProps = {
  size: 'large',
  color: activityColorPrimary()
};

export default LoadingIndicator;
