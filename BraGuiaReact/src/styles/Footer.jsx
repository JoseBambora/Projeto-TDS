import { StyleSheet } from 'react-native';

const FooterStyle = StyleSheet.create({
  footer: {
    backgroundColor: 'red',
    borderTopRightRadius: 16,
    borderTopLeftRadius: 16,
    shadowColor: '#ccc',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.25,
    shadowRadius: 4,
    elevation: 3,
    padding: 15,
  },

  container: {
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 15,
    marginBottom: 10,
    borderRadius: 10,
  },
  element: {
    flexDirection: 'row',
    justifyContent: 'left',
    alignItems: 'left',
    marginHorizontal: 15,
  },
  leftAlign: {
    flexDirection: 'row',
    alignItems: 'center',
  }
});

export default FooterStyle;