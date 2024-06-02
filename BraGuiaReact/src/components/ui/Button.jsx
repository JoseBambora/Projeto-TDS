import React, { useContext } from 'react';
import { TouchableOpacity, Text, View } from 'react-native';
import Ionicons from 'react-native-vector-icons/Ionicons';
import ButtonStyle from '../../styles/ui/Button';
import { buttonColorPrimary, iconsColorPrimary } from '../../styles/Colors';
import { ThemeContext } from '../../controler/ThemeControler';

function OurButton({ onPress, title, icon, color, iconColor }) {
  const { isDarkMode } = useContext(ThemeContext);

  const dynamicColor = color || buttonColorPrimary(isDarkMode);
  const dynamicIconColor = iconColor || iconsColorPrimary(isDarkMode);

  return (
    <TouchableOpacity style={{ ...ButtonStyle.button, backgroundColor: dynamicColor }} onPress={onPress}>
      <View style={ButtonStyle.buttonContent}>
        {icon && <Ionicons name={icon} size={20} color={dynamicIconColor} />}
        {title && <Text style={[ButtonStyle.buttonText, { color: isDarkMode ? 'white' : 'black' }]}>{title}</Text>}
      </View>
    </TouchableOpacity>
  );
}

export default OurButton;
