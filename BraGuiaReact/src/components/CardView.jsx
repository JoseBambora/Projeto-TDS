import React from 'react';
import { View, Image } from 'react-native';
import CardStyle from '../styles/CardView';
import OurText from './Text';
import { textColorHeader } from '../styles/Colors';
import OurImage from './media/Image';

const OurCardView = ({ data, imageSource }) => {
  const titles = Object.keys(data);
  return (
    <View style={CardStyle.container}>
      <View style={CardStyle.card}>
        {imageSource && <OurImage url={imageSource} />}
        <View style={CardStyle.cardContent}>
          {titles.map((title, index) => (
            <View key={index}>
              <OurText content={title} fontSize={20} color={textColorHeader()} />
              {Array.isArray(data[title]) ?
                data[title].map((d, i) => (
                  <View key={i}>
                    <OurText content={`${i+1}. ${d}`} />
                    {i < data[title].length - 1 && <View style={CardStyle.separator} />}
                  </View>))
                : (<OurText content={data[title]} />)}
              {index < titles.length - 1 && <View style={CardStyle.separator} />}
            </View>
          ))}
        </View>
      </View>
    </View>
  );
};

export default OurCardView;
