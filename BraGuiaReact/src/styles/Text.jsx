
import { StyleSheet } from 'react-native';

const TextStyle = (fontSize,color,textAlign) => StyleSheet.create({
    padding:10, 
    color: color,
    textAlign : textAlign,
    fontSize: fontSize
});

export default TextStyle;