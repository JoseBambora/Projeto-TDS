import { StyleSheet } from 'react-native';

const Indicator = (color) => StyleSheet.create({
  defaultStyle: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: color
  },
});

export default Indicator;
