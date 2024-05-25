import React from 'react';
import { View } from 'react-native';
import CardStyle from '../styles/CardView';
import OurText from './Text';
import { StyleSheet } from 'react-native';
import { textColorHeader } from '../styles/Colors';

const OurCardView = ({ data, imageSource }) => {
  const titles = Object.keys(data);

  return (
    <View style={CardStyle.container}>
      <View style={CardStyle.card}>
        {imageSource && <Image source={imageSource} style={CardStyle.cardImage} />}
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
