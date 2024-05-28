import React, { useEffect, useState } from 'react';
import { View, ScrollView, Image } from 'react-native';
import OurText from '../components/Text';
import OurCardView from '../components/CardView';
import SocialMedia from '../components/SocialMedia';
import Contacts from '../components/Contacts';
import FooterStyle from '../styles/Footer';
import Partners from '../components/Partners';
import { textColorHeader } from '../styles/Colors';
import { GetApp } from '../repositories/App';
import LoadingIndicator from '../components/Indicator';

function makeRequest(setData, setLoading,setContacts) {
  useEffect(() => {
    GetApp()
      .then(json => {
        setData(json)
        setContacts({'Contacts': json.contacts.map((e) => `${e.contact_name} (${e.contact_phone})`)})
      })
      .catch(err => alert(err.message))
      .finally(() => setLoading(false))
  }, [])
}

function Home() {
  const [isLoading, setLoading] = useState(true);
  const [data, setData] = useState([]);
  const [contacts, setContacts] = useState([])
  makeRequest(setData, setLoading,setContacts)
  const content = {
    'Descrição': data.app_desc,
    'Extra': data.app_landing_page_text
  }
  return isLoading ? (<LoadingIndicator />)
    : (
      <ScrollView>
        <OurText content={data.app_name} fontSize={30} color={textColorHeader()} textAlign={'center'} />
        <OurCardView data={content} />
        <OurCardView data={contacts} />
        <View style={FooterStyle.footer}>
          <SocialMedia social_links={data.socials} />
          <Partners partners={data.partners} />
        </View>
      </ScrollView>
    );
};

export default Home;
