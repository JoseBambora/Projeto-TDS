
import { StyleSheet } from 'react-native';

const ButtonStyle = StyleSheet.create({
  button: {
    backgroundColor: 'red',
    borderRadius: 20,
    padding: 10,
    marginHorizontal: 5,
    alignItems: 'center',
  },
  buttonContent: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  buttonText: {
    color: 'white',
    fontSize: 16,
    marginLeft: 5,
  },
});

export default ButtonStyle;