import { View, TouchableOpacity } from 'react-native';
import { useContext } from 'react';
import Icon from 'react-native-vector-icons/FontAwesome';
import OurText from "../ui/Text";
import FooterStyle from "../../styles/sub-components/Footer";
import { textColorSecondary, iconsColorPrimary } from '../../styles/Colors';
import { OpenURL } from '../../constants/Links';
import { ThemeContext } from '../../controler/ThemeControler';

const SocialIcon = ({ name, link }) => {
  const handlePress = () => {
    OpenURL(link);
  };
  const { isDarkMode } = useContext(ThemeContext); 
  return (
    <TouchableOpacity style={FooterStyle.element} onPress={handlePress}>
      <Icon name={name} size={30} color={iconsColorPrimary(isDarkMode)} />
    </TouchableOpacity>
  );
};


const SocialMedia = ({ social_links }) => {
  return (
    <View>
      <OurText content={'Redes Sociais'} fontSize={15} color={textColorSecondary()} />
      {
        social_links.map((icon, index) => (
          <SocialIcon key={index} name={icon.social_name} link={icon.social_url} />
        ))}
    </View>
  );
};

export default SocialMedia