import { StyleSheet } from 'react-native';
import { textColorPrimary } from '../Colors';

const SwitchButtonStyle = StyleSheet.create({
  container: {
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    paddingTop: 10,
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
