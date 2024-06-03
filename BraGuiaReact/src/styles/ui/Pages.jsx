import { StyleSheet } from 'react-native';

const PageStyle = (color) => StyleSheet.create({
  center: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  },
  bottomleft: {
    backgroundColor: color,
    borderColor: color,
    padding: 5, 
    paddingBottom: 10, 
    paddingRight: 20, 
    alignItems: 'flex-end'
  },
  page: {
    backgroundColor:color
  },
  pagecenter: {
    backgroundColor:color,
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  }
})

export default PageStyle