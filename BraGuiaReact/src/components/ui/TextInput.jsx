import { View, TextInput } from "react-native";
import TextInputStyle from "../../styles/ui/TextInput";
import Icon from 'react-native-vector-icons/FontAwesome';
import React, { useState } from 'react';
import { iconsColorThird, textInputCursorColor, textInputTextColor, textInputBoxColor } from "../../styles/Colors";

const OurTextInput = ({ placeholder, password, onChangeText, value, icon, borderColor = 'gray' }) => {
  const [focus, setFocus] = useState(false);
  const textInputStyle = TextInputStyle(borderColor,textInputBoxColor(),textInputTextColor())
  return (
    <View style={[textInputStyle.inputWrapper, focus && textInputStyle.focusedInputWrapper]}>
      <Icon name={icon} size={20} color={iconsColorThird()} style={TextInputStyle.icon} />
      <TextInput
        style={textInputStyle.textColor}
        placeholder={placeholder}
        secureTextEntry={password}
        onChangeText={onChangeText}
        value={value}
        placeholderTextColor={textInputTextColor()}
        selectionColor={textInputCursorColor()}
        onFocus={() => setFocus(true)}
        onBlur={() => setFocus(false)} />
    </View>)
}

export default OurTextInput;