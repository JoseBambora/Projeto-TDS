import React from 'react';
import { View } from 'react-native';
import CardStyle from '../styles/CardView';
import OurText from './Text';

const CardView = ({ title, description, imageSource, social_media }) => {
  return (
    <View style={CardStyle.card}>
      {imageSource && <Image source={imageSource} style={CardStyle.cardImage} />}
      <View style={CardStyle.cardContent}>
        <OurText content={title} fontSize={24} color={'red'}/>
        <OurText content={description} />
      </View>
    </View>
  );
};


export default CardView;