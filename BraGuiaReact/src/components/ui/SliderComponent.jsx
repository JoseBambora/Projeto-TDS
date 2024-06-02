import React from 'react';
import { View } from 'react-native';
import Slider from '@react-native-community/slider';
import SliderStyle from '../../styles/ui/Slider';
import OurText from './Text';

const OurSlider = ({ value, onValueChange, min, max, step, title, valueLabel, isDarkMode }) => {
  return (
    <View style={SliderStyle.container}>
      <OurText content={title} fontSize={18} fontWeight="bold" textAlign="center" color={isDarkMode ? 'white' : 'black'} />
      <Slider
        style={SliderStyle.slider}
        minimumValue={min}
        maximumValue={max}
        step={step}
        value={value}
        onValueChange={onValueChange}
        minimumTrackTintColor="#FF0000"
        maximumTrackTintColor="#d3d3d3"
        thumbTintColor="#FF0000"
      />
      <OurText content={valueLabel(value)} fontSize={16} textAlign="center" color={isDarkMode ? 'white' : 'black'} />
    </View>
  );
};

export default OurSlider;
