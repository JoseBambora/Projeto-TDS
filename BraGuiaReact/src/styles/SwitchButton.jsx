import { StyleSheet } from 'react-native';
import { textColorPrimary } from './Colors';

const SwitchButtonStyle = StyleSheet.create({
  container: {
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    flex: 1,
  },
  switchContainer: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  text: {
    color: textColorPrimary(),
    marginRight: 10,
  },
});

export default SwitchButtonStyle;
