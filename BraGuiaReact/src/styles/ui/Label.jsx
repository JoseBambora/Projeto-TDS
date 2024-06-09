import { StyleSheet } from 'react-native';

const LabelStyles = (color) => StyleSheet.create({
  label: {
    fontWeight: 'bold',
    fontSize: 16,
    marginRight: 10,
    color: color
  },
});

export default LabelStyles;
