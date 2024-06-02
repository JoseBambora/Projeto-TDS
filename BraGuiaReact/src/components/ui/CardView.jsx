import React, { useContext } from 'react';
import { View } from 'react-native';
import CardStyle from '../../styles/ui/CardView';
import OurText from './Text';
import { iconsColorSecondary, textColorHeader, backgroundColor, textColorPrimary } from '../../styles/Colors'; 
import OurImage from '../media/Image';
import Ionicons from 'react-native-vector-icons/Ionicons';
import { ThemeContext } from '../../controler/ThemeControler';

const IconCardView = ({ icon, isDarkMode }) => {
  const iconColor = isDarkMode ? 'white' : 'black'; 
  return (
    <View style={[CardStyle.iconContainer]}>
      <Ionicons name={icon} size={50} color={iconColor} />
    </View>
  );
};
const OurCardView = ({ data, imageSource, icon }) => {
  const titles = Object.keys(data);
  const hasIcon = !imageSource && icon;
  const { isDarkMode } = useContext(ThemeContext);
  return (
    <View style={[CardStyle.container, { backgroundColor: isDarkMode ? 'black' : 'white' }]}>
       <View style={hasIcon ? [CardStyle.card, CardStyle.cardIcon, { shadowColor }] : [CardStyle.card, { shadowColor: isDarkMode ? '#a9a9a9' : '#ccc' }]}>
        {hasIcon && <IconCardView icon={icon} isDarkMode={isDarkMode} />}
        {imageSource && <OurImage url={imageSource} />}
        <View style={hasIcon ? CardStyle.cardContent2 : CardStyle.cardContent}>
          {titles.map((title, index) => (
            <View key={index}>
              <OurText content={title} fontSize={20} color={textColorHeader(isDarkMode)} />
              {Array.isArray(data[title]) ?
                data[title].map((d, i) => (
                  <View key={i}>
                    <OurText content={d} color={textColorPrimary(isDarkMode)} />
                  </View>))
                : (<OurText content={data[title]} color={textColorPrimary(isDarkMode)}/> ) }
              {index < titles.length - 1 && <View style={CardStyle.separator} />}
            </View>
          ))}
        </View>
      </View>
    </View>
  );
};

export default OurCardView;
