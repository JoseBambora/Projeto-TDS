import React, { useEffect, useState } from 'react';
import { View, ScrollView, Image } from 'react-native';
import OurText from '../components/ui/Text';
import OurCardView from '../components/ui/CardView';
import SocialMedia from '../components/sub-components/SocialMedia';
import FooterStyle from '../styles/sub-components/Footer';
import Partners from '../components/sub-components/Partners';
import { pageColor, textColorHeader } from '../styles/Colors';
import { GetApp } from '../repositories/App';
import LoadingIndicator from '../components/ui/Indicator';
import PageStyle from '../styles/ui/Pages';
import { refreshIfDarkModeChanges } from './utils/RefreshDarkMode';
import HomeStyle from '../styles/ui/Home';

function makeRequest(setData, setLoading, setContacts) {
  useEffect(() => {
    GetApp()
      .then(json => {
        setData(json);
        setContacts({ 'Contactos': json.contacts.map((e) => `${e.contact_name} (${e.contact_phone})`) });
      })
      .catch(err => alert(err.message))
      .finally(() => setLoading(false));
  }, []);
}

function Home() {
  const [isLoading, setLoading] = useState(true);
  const [data, setData] = useState([]);
  const [contacts, setContacts] = useState([]);
  makeRequest(setData, setLoading, setContacts);
  refreshIfDarkModeChanges();
  const content = {
    'Descrição': data.app_desc,
    'Extra': data.app_landing_page_text
  };
  const PageStyleVar = PageStyle(pageColor())
  return isLoading ? (
    <LoadingIndicator />
  ) : (
    <ScrollView style={PageStyleVar.page}>
      <OurText content={data.app_name} fontSize={30} color={textColorHeader()} textAlign={'center'} />
      <View style={PageStyleVar.center}>
        <Image
          source={require('../../android/app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png')}
          style={HomeStyle.logo}
        />
      </View>
      <OurCardView data={content} />
      <OurCardView data={contacts} />
      <View style={FooterStyle.footer}>
        <SocialMedia social_links={data.socials} />
        <Partners partners={data.partners} />
      </View>
    </ScrollView>
  );
}

export default Home;
