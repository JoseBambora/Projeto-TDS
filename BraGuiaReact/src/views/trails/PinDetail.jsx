import React, { useState, useCallback, useEffect } from 'react';
import { useFocusEffect } from '@react-navigation/native';
import { ScrollView } from 'react-native';
import OurCardView from '../../components/ui/CardView';
import OurImage from '../../components/media/Image';
import OurAudio from '../../components/media/Audio';
import OurVideo from '../../components/media/Video';
import { IsPremium } from '../../repositories/User';
import LoadingIndicator from '../../components/ui/Indicator';
import OurText from '../../components/ui/Text';
import PinDetailStyles from '../../styles/sub-components/PinDetail';
import { refreshIfDarkModeChanges } from '../utils/RefreshDarkMode';
import { pageColor } from '../../styles/Colors';
import { GetPin } from '../../repositories/Pins';

const getPinData = (pin) => {
  const pinData = {
    "Nome do Ponto": pin.pin_name,
    "Descrição": pin.pin_desc,
    "Coordenadas": `Latitude: ${pin.pin_lat}\nLongitude: ${pin.pin_lng}\nAltitude: ${pin.pin_alt}`,
  };

  if (pin.rel_pin && pin.rel_pin.length > 0) {
    pinData["Informações Extra"] = pin.rel_pin.map(rel => `${rel.attrib}: ${rel.value}`).join("\n");
  }
  return pinData
}

const getMediaData = (pin) => (
  pin.media.map(media => ({
    url: media.media_file,
    type: media.media_type,
  }))
)

const PinDetail = ({ route }) => {
  refreshIfDarkModeChanges();
  const PinDetailStyleVar = PinDetailStyles(pageColor())

  const [pin, setPin] = useState(null);
  const [pinData, setPinData] = useState(null);
  const [mediaData, setMediaData] = useState(null)
  const { id } = route.params;
  useEffect(() => {
    GetPin(id)
      .then(p => { 
        setMediaData(getMediaData(p))
        setPinData(getPinData(p))
        setPin(p) 
      })
      .catch(e => console.log(e.message))
  }, [])
  const [isPremium, setIsPremium] = useState(null);

  useFocusEffect(useCallback(() => {
    IsPremium()
      .then(premiumStatus => setIsPremium(premiumStatus))
      .catch(error => console.error('Error checking premium status', error));
  }, []));


  if (isPremium === null) {
    return <LoadingIndicator />;
  }

  return pin ? (
    <ScrollView contentContainerStyle={PinDetailStyleVar.container}>
      {isPremium ? (
        mediaData.map((media, index) => {
          switch (media.type) {
            case 'I':
              return <OurImage key={index} url={media.url} />;
            case 'R':
              return <OurAudio key={index} url={media.url} />;
            case 'V':
              return <OurVideo key={index} url={media.url} />;
            default:
              return null;
          }
        })
      ) : (
        <OurText content={'Conteúdo multimédia apenas disponível para utilizadores premium'} textAlign="center" />
      )}
      <OurCardView data={pinData} imageSource={pin.pin_img} />
    </ScrollView>
  ) : <LoadingIndicator />;
};

export default PinDetail;
