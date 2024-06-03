import { StyleSheet } from 'react-native';

const HistoryStyles = (color)=> StyleSheet.create({
  container: {
    backgroundColor : color,
    paddingVertical: 10,
  },
  section: {
    backgroundColor : color,
    paddingHorizontal: 10,
  },
  sectionTitle: {
    fontSize: 22,
    fontWeight: 'bold',
    marginBottom: 10,
    textAlign: 'center',
  },
  cardWrapper: {
    backgroundColor : color,
    marginBottom: 20,
  },
});

export default HistoryStyles;
