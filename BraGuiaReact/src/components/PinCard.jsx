import React from 'react';
import { View, StyleSheet } from 'react-native';
import OurCardView from './CardView';

const PinCard = ({ pin }) => {
  return (
    <View style={styles.container}>
      <OurCardView
         data={{
          "Nome do Pin": pin.pin_name
        }}
        icon={'location-outline'}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    marginBottom: 10,
  },
  arrow: {
    alignSelf: 'center',
    marginTop: 10, // Adjust as needed for spacing
  },
});

export default PinCard;
