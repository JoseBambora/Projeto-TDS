import React from 'react';
import OurButton from './Button';
import { buttonColorPrimary, iconsColorPrimary, iconsColorSecondary } from '../styles/Colors';

const PrecisionOption = ({ label, isSelected, onPress }) => {
    const selectedColor = isSelected ? buttonColorPrimary() : 'white';
  
    return (
      <OurButton 
        onPress={onPress} 
        title={label} 
        color={selectedColor} 
        icon={null}
      />
    );
  };
  
  export default PrecisionOption;
