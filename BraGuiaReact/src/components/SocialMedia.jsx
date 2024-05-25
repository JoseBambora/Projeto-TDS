import { View, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
import OurText from "./Text";
import FooterStyle from "../styles/Footer";


const SocialIcon = ({ name }) => {
  return (
    <TouchableOpacity style={FooterStyle.element}>
      <Icon name={name}
        size={30}
        color={'white'} />
    </TouchableOpacity>
  );
};

const SocialMedia = ({ social_links }) => {
  return (
    <View>
      <OurText content={'Redes Sociais'} fontSize={20} color={'white'} />
      <View style={FooterStyle.container}>
        {
          social_links.map((icon, index) => (
            <SocialIcon key={index} name={icon.social_name} />
          ))}
      </View>
    </View>
  );
};

export default SocialMedia