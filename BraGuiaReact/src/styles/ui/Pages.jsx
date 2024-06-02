import { StyleSheet } from 'react-native';
import { pageColor } from '../Colors';

const PageStyle = (color) => StyleSheet.create({
  center: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  },
  bottomleft: {
    padding: 5, 
    paddingBottom: 10, 
    paddingRight: 20, 
    alignItems: 'flex-end'
  },
  page: {
    backgroundColor:color
  }  
})

export default PageStyle