import { StyleSheet } from 'react-native';

const CardStyle = StyleSheet.create({
  container: {
    padding: 10, 
    paddingBottom: 30 
  },
  card: {
    backgroundColor: 'white',
    borderRadius: 16,
    shadowColor: '#ccc',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.25,
    shadowRadius: 4,
    elevation: 3,
    padding: 15,
    marginTop: 5,
    marginBottom: 5,
  },
  cardImage: {
    width: '100%',
    height: 200,
    resizeMode: 'cover',
    borderRadius: 8
  },
  cardContent: {
    marginTop: 2,
  },
  separator: {
    borderBottomWidth: 0.5,
    borderBottomColor: 'gray',
    marginVertical: 10,
  }
});

export default CardStyle;