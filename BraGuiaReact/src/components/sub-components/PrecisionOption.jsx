import React from 'react';
import OurButton from '../ui/Button';
import { buttonColorPrimary, iconsColorPrimary } from '../../styles/Colors';

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
