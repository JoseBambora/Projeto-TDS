import { StyleSheet } from 'react-native';

const TextStyle = (fontSize, color, textAlign, width,fontWeight) => StyleSheet.create({
  textStyle: {
    fontWeight: fontWeight,
    padding: 10,
    color: color,
    textAlign: textAlign,
    fontSize: fontSize,
    width: width
  }
});

export default TextStyle;