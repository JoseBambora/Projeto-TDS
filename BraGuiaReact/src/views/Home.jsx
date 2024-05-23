import React, {useEffect, useState} from 'react';
import { View, ScrollView } from 'react-native';
import OurText from '../components/Text';
import AppRequest from '../helper/AppRequest';
import CardView from '../components/CardView';
import SocialMedia from '../components/SocialMedia';
import Contacts from '../components/Contacts';
import FooterStyle from '../styles/Footer';
import Partners from '../components/Partners';
import { GetTrails } from '../repositories/Trails';
import { resetRealm } from '../redux/DB';
import { GetPin } from '../repositories/Pins';



function makeRequest(setData,setLoading) {
  useEffect(() => {
    AppRequest()
    .then(json => setData(json))
    .catch(err => alert(err.message))
    .finally(() => setLoading(false))
  },[])
}

function Home() {
  const [isLoading, setLoading] = useState(true);
  const [data, setData] = useState([]);
  makeRequest(setData,setLoading)
  return (
    <ScrollView>
      {
        isLoading ? (<OurText content={'Loading Home Page'}/>) 
        : (
          <View>
            <View style={{padding:10, paddingBottom:30}}>
              <OurText content={data.app_name} fontSize={30} color={'red'} textAlign={'center'}/>
              <CardView title={'Descrição'} description={data.app_desc}/>
              <CardView title={'Extra'} description={data.app_landing_page_text}/>
            </View>
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
