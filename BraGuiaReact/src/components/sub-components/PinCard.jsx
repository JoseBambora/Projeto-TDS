import React from 'react';
import { View } from 'react-native';
import OurCardView from '../ui/CardView';
import PinCardStyles from '../../styles/sub-components/PinCard';

const PinCard = ({ pin }) => {
  return (
    <View style={PinCardStyles.container}>
      <OurCardView
         data={{
          "Nome do Pin": pin.pin_name
        }}
        icon={'location-outline'}
      />
    </View>
  );
};

export default PinCard;
