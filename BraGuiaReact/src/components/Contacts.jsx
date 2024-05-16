import { View, TouchableOpacity, StyleSheet } from 'react-native';
import OurText from "./Text";
import Icon from 'react-native-vector-icons/FontAwesome';
import FooterStyle from "../styles/Footer";

const Contact = ({ number }) => {
  return (
    <TouchableOpacity style={FooterStyle.element}>
      <View style={FooterStyle.leftAlign}>
        <Icon name={'phone'}
          size={30}
          color={'white'} />
        <OurText content={number} color={'white'} />
      </View>
    </TouchableOpacity>
  );
};

const Contacts = ({ contacts }) => {
  return (
    <View>
      <OurText content={'Contactos'} fontSize={20} color={'white'} />
      <View style={FooterStyle.container}>
        {
          contacts.map((contact, index) => (
            <Contact key={index} number={contact.contact_phone} />
          ))}
      </View>
    </View>
  );
};

export default Contacts