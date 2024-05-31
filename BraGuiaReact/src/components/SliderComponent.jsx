import React from 'react';
import { View} from 'react-native';
import Slider from '@react-native-community/slider';
import SliderStyle from '../styles/Slider';
import OurText from './Text';

const OurSlider = ({ value, onValueChange, min, max, step, title, valueLabel }) => {
  return (
    <View style={SliderStyle.container}>
      <OurText content={title} fontSize={18} fontWeight="bold" textAlign="center" />
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
      <OurText content={valueLabel(value)} fontSize={16} textAlign="center" />
    </View>
  );
};

export default OurSlider;
