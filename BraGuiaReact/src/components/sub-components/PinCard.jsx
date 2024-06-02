import React, { useContext } from 'react';
import { View } from 'react-native';
import OurCardView from '../ui/CardView';
import PinCardStyles from '../../styles/sub-components/PinCard';
import { ThemeContext } from '../../controler/ThemeControler';
import { textColorPrimary } from '../../styles/Colors';

const PinCard = ({ pin }) => {
  const { isDarkMode } = useContext(ThemeContext);

  return (
    <View style={[PinCardStyles.container, { backgroundColor: isDarkMode ? 'black' : 'white' }]}>
      <OurCardView
        data={{
          "Nome do Pin": pin.pin_name
        }}
        icon={'location-outline'}
        color={textColorPrimary(isDarkMode)}
      />
    </View>
  );
};

export default PinCard;