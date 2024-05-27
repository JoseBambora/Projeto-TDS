import React from 'react';
import { TouchableOpacity, Text, View } from 'react-native';
import Ionicons from 'react-native-vector-icons/Ionicons'; // Assuming you're using FontAwesome icons
import ButtonStyle from '../styles/Button';
import { iconsColorPrimary } from '../styles/Colors';

function OurButton({ onPress, title, icon}) {
  return (
    <TouchableOpacity style={ButtonStyle.button} onPress={onPress}>
      <View style={ButtonStyle.buttonContent}>
        {icon && <Ionicons name={icon} size={20} color={iconsColorPrimary()} />}
        <Text style={ButtonStyle.buttonText}>{title}</Text>
      </View>
    </TouchableOpacity>
  );
}


export default OurButton;
