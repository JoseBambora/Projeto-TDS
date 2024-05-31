import { StyleSheet } from 'react-native';

const PrecisionOptionStyle = StyleSheet.create({
  container: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingHorizontal: 20,
    marginTop: 10,
  },
  optionButton: {
    backgroundColor: 'white',
    padding: 5,
    borderRadius: 8,
    borderWidth: 1,
    borderColor: 'gray',
    width: '40%',
  },
  selectedOption: {
    backgroundColor: 'lightblue',
    borderColor: 'blue',
  },
  optionText: {
    fontSize: 16,
    color: 'black',
    textAlign: 'center',
  },
});

export default PrecisionOptionStyle;
