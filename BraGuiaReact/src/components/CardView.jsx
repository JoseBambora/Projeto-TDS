import React from 'react';
import { View } from 'react-native';
import CardStyle from '../styles/CardView';
import OurText from './Text';

const OurCardView = ({ title, description, imageSource }) => {
  return (
    <View style={CardStyle.card}>
      {imageSource && <Image source={imageSource} style={CardStyle.cardImage} />}
      <View style={CardStyle.cardContent}>
        <OurText content={title} fontSize={20} color={'red'}/>
        <OurText content={description} />
      </View>
    </View>
  );
};


export default OurCardView;