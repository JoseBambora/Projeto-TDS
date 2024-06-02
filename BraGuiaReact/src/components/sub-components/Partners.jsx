import { View, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
import OurText from "../ui/Text";
import FooterStyle from "../../styles/sub-components/Footer";
import { textColorSecondary, iconsColorPrimary } from '../../styles/Colors';
import { OpenURL } from '../../constants/Links';

const Partner = ({ name, link }) => {
  const handlePress = () => {
    OpenURL(link);
  };

  return (
    <TouchableOpacity style={FooterStyle.element} onPress={handlePress}>
      <Icon name={'university'} size={30} color={iconsColorPrimary()} />
      <OurText content={name} color={textColorSecondary()} />
    </TouchableOpacity>
  );
};

const Partners = ({ partners }) => {
  return (
    <View>
      <OurText content={'Parceiros'} fontSize={15} color={textColorSecondary()} />
      {
        partners.map((partner, index) => (
          <Partner key={index} name={partner.partner_name} link={partner.partner_url} />
        ))}
    </View>
  );
};

export default Partners