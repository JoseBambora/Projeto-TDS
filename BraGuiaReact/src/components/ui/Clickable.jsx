import React from 'react';
import { TouchableOpacity } from 'react-native';
import OurText from './Text';

function OurClickable({ onPress, title, text }) {
  return (
    <TouchableOpacity onPress={onPress}>
      <OurText content={text} textAlign={'center'} />
      <OurText content={title} color={'blue'} textAlign={'center'} />
    </TouchableOpacity>
  );
}


export default OurClickable;
