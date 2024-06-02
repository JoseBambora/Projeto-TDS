import { StyleSheet } from 'react-native';
import { cardViewColor, cardViewSeparatorColor, cardViewShadowColor } from '../Colors';

const CardStyle = (color, shadowColor, separatorColor) => StyleSheet.create({
  container: {
    padding: 10, 
    paddingBottom: 30,
  },
  card: {
    backgroundColor: color,
    borderRadius: 16,
    shadowColor: shadowColor,
    shadowOffset: { width: 10, height: 10 },
    shadowOpacity: 0.25,
    shadowRadius: 4,
    elevation: 3,
    padding: 15,
    marginTop: 5,
    marginBottom: 5,
  },
  cardIcon: {
    flex:1,
    flexDirection: 'row',
    justifyContent: 'space-between'
  },
  iconContainer: {
    alignItems: 'center',
    justifyContent:'center',
    marginRight: 10,
  },
  cardContent: {
    width:"100%",
    marginTop: 2,
  },
  cardContent2 : {
    width:"80%",
    marginTop: 2,
  },
  separator: {
    borderBottomWidth: 0.5,
    borderBottomColor: separatorColor,
    marginVertical: 10,
  }
});

export default CardStyle;