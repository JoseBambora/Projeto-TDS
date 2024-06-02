import React from 'react';
import { SafeAreaView, ActivityIndicator } from 'react-native';
import Indicator from '../../styles/ui/Indicator';
import { activityColorPrimary } from '../../styles/Colors';

const LoadingIndicator = ({ size = 'large', color = activityColorPrimary(), isDarkMode }) => (
  <SafeAreaView style={[Indicator.defaultStyle, { backgroundColor: isDarkMode ? 'black' : 'white' }]}>
    <ActivityIndicator size={size} color={color} />
  </SafeAreaView>
);

export default LoadingIndicator;
