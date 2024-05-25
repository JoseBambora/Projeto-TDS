import React from 'react';
import { View, Image } from 'react-native';
import CardStyle from '../styles/CardView';
import OurText from './Text';
import { textColorHeader } from '../styles/Colors';

const OurCardView = ({ data, imageSource }) => {
  const titles = Object.keys(data);

  return (
    <View style={CardStyle.container}>
      <View style={CardStyle.card}>
        {imageSource && <Image source={{uri:imageSource}} style={CardStyle.cardImage} />}
        <View style={CardStyle.cardContent}>
          {titles.map((title, index) => (
            <View key={index}>
              <OurText content={title} fontSize={20} color={textColorHeader()} />
              <OurText content={data[title]} />
              {index < titles.length - 1 && <View style={CardStyle.separator} />}
            </View>
          ))}
        </View>
      </View>
    </View>
  );
};

export default OurCardView;
