import React, {useEffect, useState} from 'react';
import { View, ScrollView, Image } from 'react-native';
import OurText from '../components/Text';
import OurCardView from '../components/CardView';
import SocialMedia from '../components/SocialMedia';
import Contacts from '../components/Contacts';
import FooterStyle from '../styles/Footer';
import Partners from '../components/Partners';
import { textColorHeader } from '../styles/Colors';
import { GetApp } from '../repositories/App';
import OurAudio from '../components/media/Audio';
import OurVideo from '../components/media/Video';

function makeRequest(setData,setLoading) {
  useEffect(() => {
    GetApp()
    .then(json => setData(json))
    .catch(err => alert(err.message))
    .finally(() => setLoading(false))
  },[])
}

function Home() {
  const [isLoading, setLoading] = useState(true);
  const [data, setData] = useState([]);
  makeRequest(setData,setLoading)
  const content = {
    'Descrição': data.app_desc,
    'Extra':  data.app_landing_page_text
  }
  return (
    <ScrollView>
      {
        isLoading ? (<OurText content={'Loading Home Page'}/>) 
        : (
          <View>
            <OurText content={data.app_name} fontSize={30} color={textColorHeader()} textAlign={'center'}/>
            <OurCardView data={content}/>
            <View style={FooterStyle.footer}>
              <SocialMedia social_links={data.socials}/>
              <Partners partners={data.partners}/>
              <Contacts contacts={data.contacts}/>
            </View>
          </View>
        )
      }
    </ScrollView>
  );
};

export default Home;
