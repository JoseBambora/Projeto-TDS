import React, { useEffect, useState, useContext } from 'react';
import { View, ScrollView } from 'react-native';
import OurText from '../components/ui/Text';
import OurCardView from '../components/ui/CardView';
import SocialMedia from '../components/sub-components/SocialMedia';
import FooterStyle from '../styles/sub-components/Footer';
import Partners from '../components/sub-components/Partners';
import { ThemeContext } from '../controler/ThemeControler';
import { textColorHeader } from '../styles/Colors';
import { GetApp } from '../repositories/App';
import LoadingIndicator from '../components/ui/Indicator';
import { backgroundColor } from '../styles/Colors';

function makeRequest(setData, setLoading, setContacts) {
  useEffect(() => {
    GetApp()
      .then(json => {
        setData(json)
        setContacts({ 'Contactos': json.contacts.map((e) => `${e.contact_name} (${e.contact_phone})`) })
      })
      .catch(err => alert(err.message))
      .finally(() => setLoading(false))
  }, [])
}

function Home() {
  const { isDarkMode } = useContext(ThemeContext); 
  const [isLoading, setLoading] = useState(true);
  const [data, setData] = useState([]);
  const [contacts, setContacts] = useState([])
  makeRequest(setData, setLoading, setContacts)
  const content = {
    'Descrição': data.app_desc,
    'Extra': data.app_landing_page_text
  }
  return isLoading ? (<LoadingIndicator />)
    : (
      <ScrollView style={{ backgroundColor: backgroundColor(isDarkMode) }}>
        <OurText content={data.app_name} fontSize={30} color={textColorHeader()} textAlign={'center'} />
        <OurCardView data={content} />
        <OurCardView data={contacts} />
        <View style={FooterStyle.footer}>
          <SocialMedia social_links={data.socials} isDarkMode={isDarkMode} /> 
          <Partners partners={data.partners} isDarkMode={isDarkMode} />
        </View>
      </ScrollView>
    );
};

export default Home;
