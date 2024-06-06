import React from 'react';
import { View } from 'react-native';
import CardStyle from '../../styles/ui/CardView';
import OurText from './Text';
import { TouchableOpacity } from 'react-native';
import { iconsColorSecondary, textColorHeader } from '../../styles/Colors';
import OurImage from '../media/Image';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { cardViewColor, cardViewShadowColor, cardViewSeparatorColor } from '../../styles/Colors';

const IconCardView = ({ icon }) => (
  <View style={CardStyle.iconContainer}>
    <Ionicons name={icon} size={50} color={iconsColorSecondary()} />
  </View>)

const Multiple = ({ data, title }) => (
  <View>
    {
      data[title].map((d, i) => (
        Array.isArray(d) ?
          <TouchableOpacity key={i} onPress={d[1]}>
            <OurText content={d[0]} />
          </TouchableOpacity> :
          <View key={i}>
            <OurText content={d} />
          </View>
      ))
    }
  </View>
)

const Content = ({ data, title }) => (
  <View>
    {
      Array.isArray(data[title]) ?
        <Multiple data={data} title={title} /> :
        <OurText content={data[title]} />
    }
  </View>
)

const OurCardView = ({ data, imageSource, icon }) => {
  const titles = Object.keys(data);
  const hasIcon = !imageSource && icon
  const CardStyleVar = CardStyle(cardViewColor(), cardViewShadowColor(), cardViewSeparatorColor())
  return (
    <View style={CardStyleVar.container}>
      <View style={hasIcon ? [CardStyleVar.card, CardStyleVar.cardIcon] : [CardStyleVar.card]}>
        {hasIcon && <IconCardView icon={icon} />}
        {imageSource && <OurImage url={imageSource} />}
        <View style={hasIcon ? CardStyleVar.cardContent2 : CardStyleVar.cardContent}>
          {titles.map((title, index) => (
            <View key={index}>
              <OurText content={title} fontSize={20} color={textColorHeader()} />
              <Content data={data} title={title} />
              {index < titles.length - 1 && <View style={CardStyleVar.separator} />}
            </View>
          ))}
        </View>
      </View>
    </View>
  );
};

export default OurCardView;