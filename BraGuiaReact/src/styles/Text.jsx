
import { StyleSheet } from 'react-native';

const TextStyle = (fontSize,color,textAlign,width) => StyleSheet.create({
    textStyle : {
        padding:10, 
        color: color,
        textAlign : textAlign,
        fontSize: fontSize,
        width:width
    }
});

export default TextStyle;