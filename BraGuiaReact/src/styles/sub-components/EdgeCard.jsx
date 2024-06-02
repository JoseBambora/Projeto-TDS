import { StyleSheet } from 'react-native';

const EdgeCardStyles = StyleSheet.create({
  container: {
    backgroundColor: '#fff',
    padding: 20,
    borderRadius: 10,
    marginHorizontal: 10,
    marginBottom: 5,
    elevation: 2,
    alignItems: 'center',
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 10,
    alignItems: 'center',
    width: '100%',
  },
});

export default EdgeCardStyles;
