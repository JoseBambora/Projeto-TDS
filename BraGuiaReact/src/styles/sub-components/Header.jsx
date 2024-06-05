import { StyleSheet } from "react-native";
import { headerColor } from "../Colors";

const HeaderStyle = StyleSheet.create({
  header: {
    backgroundColor:headerColor(),
  },
  logo: {
    width: 75,
    height:75,
    marginLeft:10,
  },
  title: { 
    fontWeight: 'bold',
    fontStyle: 'italic',
    fontSize:30
  }
})

export default HeaderStyle