import React from 'react';
import { TouchableOpacity, Text, View } from 'react-native';
import Ionicons from 'react-native-vector-icons/Ionicons'; // Assuming you're using FontAwesome icons
import ButtonStyle from '../styles/Button';
import { buttonColorPrimary, iconsColorPrimary } from '../styles/Colors';

function OurButton({ onPress, title, icon, color=buttonColorPrimary(), iconColor=iconsColorPrimary() }) {
  return (
    <TouchableOpacity style={{... ButtonStyle.button, backgroundColor:color}} onPress={onPress}>
      <View style={ButtonStyle.buttonContent}>
        {icon && <Ionicons name={icon} size={20} color={iconColor} />}
        {title && <Text style={ButtonStyle.buttonText}>{title}</Text>}
      </View>
    </TouchableOpacity>
  );
}


export default OurButton;
