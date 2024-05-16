import { View, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/FontAwesome';
import OurText from "./Text";
import FooterStyle from "../styles/Footer";


const Partner = ({ name }) => {
  return (
    <TouchableOpacity style={FooterStyle.element}>
      <Icon name={'university'} size={30} color={'white'}/>
      <OurText content={name} color={'white'}/>
    </TouchableOpacity>
  );
};

const Partners = ({ partners }) => {
  return (
    <View>        
      <OurText content={'Parceiros'} fontSize={20} color={'white'} />
      <View style={FooterStyle.container}>
        {
          partners.map((partner, index) => (
            <Partner key={index} name={partner.partner_name} />
          ))}
      </View>
    </View>
  );
};

export default Partners