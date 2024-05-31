import React from 'react';
import OurButton from './Button';
import { buttonColorPrimary, iconsColorPrimary, iconsColorSecondary } from '../styles/Colors';

const PrecisionOption = ({ label, isSelected, onPress, choosenicon }) => {
    const selectedColor = isSelected ? buttonColorPrimary() : 'grey';
    const iconColora = iconsColorPrimary()
  
    return (
      <OurButton 
        onPress={onPress} 
        title={label} 
        color={selectedColor} 
        icon={choosenicon}
        iconColor={iconColora}
      />
    );
  };
  
  export default PrecisionOption;
