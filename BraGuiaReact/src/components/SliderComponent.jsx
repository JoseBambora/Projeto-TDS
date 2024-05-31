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
        minimumTrackTintColor="#1EB1FC"
        maximumTrackTintColor="#d3d3d3"
        thumbTintColor="#1EB1FC"
      />
      <OurText content={valueLabel(value)} fontSize={16} textAlign="center" />
    </View>
  );
};

export default OurSlider;
