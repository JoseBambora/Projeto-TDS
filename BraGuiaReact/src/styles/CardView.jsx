import { StyleSheet } from 'react-native';

const CardStyle = StyleSheet.create({
    card: {
      backgroundColor: 'white',
      borderRadius: 16,
      shadowColor: '#ccc',
      shadowOffset: { width: 0, height: 2 },
      shadowOpacity: 0.25,
      shadowRadius: 4,
      elevation: 3,
      padding: 15,
      marginBottom: 10,
    },
    cardImage: {
      width: '100%',
      height: 200, // Adjust height as needed
      resizeMode: 'cover', // Adapt image to container
      borderRadius: 8, // Match card corner radius
    },
    cardContent: {
      marginTop: 10, // Add space between image and content
    },
  });
  
  export default CardStyle;