import {View, TextInput } from "react-native";
import TextInputStyle from "../styles/TextInput";
import Icon from 'react-native-vector-icons/FontAwesome';
import React, {useState} from 'react';


const OurTextInput = ({placeholder,password, onChangeText, value, icon}) => {
    const [focus, setFocus] = useState(false);
    
    return (
    <View style={[TextInputStyle.inputWrapper, focus && TextInputStyle.focusedInputWrapper]}>
        <Icon name={icon} size={20} color="gray" style={TextInputStyle.icon} />
        <TextInput
            placeholder={placeholder} 
            secureTextEntry={password} 
            onChangeText={onChangeText} 
            value={value} 
            placeholderTextColor={'black'} 
            selectionColor={'gray'}
            onFocus={() => setFocus(true)}
            onBlur={() => setFocus(false)}/>
    </View>)
}

export default OurTextInput;