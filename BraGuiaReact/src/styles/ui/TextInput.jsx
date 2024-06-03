import { StyleSheet } from 'react-native';

const TextInputStyle = (borderColor,color) => StyleSheet.create({
    inputContainer: {
      flexDirection: 'row',
      alignItems: 'center',
      width: '80%',
      marginBottom: 20,
      marginTop:10,
    },
    focusedInputWrapper: {
      borderColor: 'orange',
    },
    inputWrapper: {
      backgroundColor: color,
      flexDirection: 'row',
      alignItems: 'center',
      width: '80%',
      borderColor: borderColor,
      borderWidth: 1,
      borderRadius: 5,
      paddingHorizontal: 10,
      margin: 10
    },
    icon: {
        width: '10%'
    }
});


export default TextInputStyle;