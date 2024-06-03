import React from "react"
import { View } from "react-native"
import OurText from "./Text"
import Ionicons from 'react-native-vector-icons/Ionicons';
import OurCurve from "./Curve"
import { iconsColorPrimary, textColorSecondary } from "../../styles/Colors"

export const OurHeaderCurve = ({ icon, content }) => {
  return (
    <View style={{ marginBottom: 50 }}>
      <OurCurve color={'red'} />
      <View style={{ alignItems: 'center', marginTop: -80 }}>
        <Ionicons name={icon} size={50} color={iconsColorPrimary()} />
        <OurText content={content} fontSize={20} color={textColorSecondary()} />
      </View>
    </View>
  )
}