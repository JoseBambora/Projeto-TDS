import { View, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
import OurText from "./Text";
import FooterStyle from "../styles/Footer";
import { textColorSecondary } from '../styles/Colors';
import { OpenURL } from '../constants/Links';

const SocialIcon = ({ name, link }) => {
  return (
    <TouchableOpacity style={FooterStyle.element} onPress={OpenURL(link)}>
      <Icon name={name}
        size={30}
        color={'white'} />
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