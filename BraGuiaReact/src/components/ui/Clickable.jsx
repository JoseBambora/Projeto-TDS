import React, { useContext }  from 'react';
import { TouchableOpacity } from 'react-native';
import OurText from './Text';
import { textColorPrimary } from '../../styles/Colors';
import { ThemeContext } from '../../controler/ThemeControler';

function OurClickable({ onPress, title, text}) {
  const { isDarkMode } = useContext(ThemeContext);
  return (
    <TouchableOpacity onPress={onPress}>
      <OurText content={text} textAlign={'center'} color={textColorPrimary(isDarkMode)} />
      <OurText content={title} color={'blue'} textAlign={'center'} />
    </TouchableOpacity>
  );
}

export default OurClickable;
