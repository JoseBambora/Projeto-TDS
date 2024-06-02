import React, { useContext } from 'react';
import { View } from "react-native"
import OurText from "./Text"
import Ionicons from 'react-native-vector-icons/Ionicons';
import OurCurve from "./Curve"
import { iconsColorPrimary, textColorSecondary } from "../../styles/Colors"
import { ThemeContext } from '../../controler/ThemeControler';

export const OurHeaderCurve = ({ icon, content }) => {
  const { isDarkMode } = useContext(ThemeContext);
  return (
    <View style={{ marginBottom: 40 }}>
      <OurCurve color={'red'} />
      <View style={{ alignItems: 'center', marginTop: -130 }}>
        <Ionicons name={icon} size={100} color={iconsColorPrimary(isDarkMode)} />
        <OurText content={content} fontSize={20} color={textColorSecondary(isDarkMode)} />
      </View>
    </View>
  )
}
