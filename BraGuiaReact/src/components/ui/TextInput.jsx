import { View, TextInput } from "react-native";
import TextInputStyle from "../../styles/ui/TextInput";
import Icon from 'react-native-vector-icons/FontAwesome';
import React, { useState } from 'react';

const OurTextInput = ({ placeholder, password, onChangeText, value, icon, borderColor = 'gray' }) => {
  const [focus, setFocus] = useState(false);
  const textInputStyle = TextInputStyle(borderColor)
  return (
    <View style={[textInputStyle.inputWrapper, focus && textInputStyle.focusedInputWrapper]}>
      <Icon name={icon} size={20} color="gray" style={TextInputStyle.icon} />
      <TextInput
        placeholder={placeholder}
        secureTextEntry={password}
        onChangeText={onChangeText}
        value={value}
        placeholderTextColor={'black'}
        selectionColor={'gray'}
        onFocus={() => setFocus(true)}
        onBlur={() => setFocus(false)} />
    </View>)
}

export default OurTextInput;