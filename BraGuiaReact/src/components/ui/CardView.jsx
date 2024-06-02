import React from 'react';
import { View } from 'react-native';
import CardStyle from '../../styles/ui/CardView';
import OurText from './Text';
import { iconsColorSecondary, textColorHeader } from '../../styles/Colors';
import OurImage from '../media/Image';
import Ionicons from 'react-native-vector-icons/Ionicons';

const IconCardView = ({ icon }) => (
  <View style={CardStyle.iconContainer}>
    <Ionicons name={icon} size={50} color={iconsColorSecondary()} />
  </View>)

const OurCardView = ({ data, imageSource, icon }) => {
  const titles = Object.keys(data);
  const hasIcon = !imageSource && icon
  return (
    <View style={CardStyle.container}>
      <View style={hasIcon ? [CardStyle.card, CardStyle.cardIcon] : [CardStyle.card]}>
        {hasIcon && <IconCardView icon={icon} />}
        {imageSource && <OurImage url={imageSource} />}
        <View style={hasIcon ? CardStyle.cardContent2 : CardStyle.cardContent}>
          {titles.map((title, index) => (
            <View key={index}>
              <OurText content={title} fontSize={20} color={textColorHeader()} />
              {Array.isArray(data[title]) ?
                data[title].map((d, i) => (
                  <View key={i}>
                    <OurText content={d} />
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