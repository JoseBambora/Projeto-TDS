import React, { useEffect, useState } from 'react';
import { ScrollView, View, TouchableOpacity } from 'react-native';
import OurImage from '../../components/media/Image';
import OurText from '../../components/ui/Text';
import { textColorHeader, activityColorPrimary, pageColor } from '../../styles/Colors';
import OurCardView from '../../components/ui/CardView';
import OurButton from '../../components/ui/Button';
import EdgeCard from '../../components/sub-components/EdgeCard';
import PinCard from '../../components/sub-components/PinCard';
import Ionicons from 'react-native-vector-icons/Ionicons';
import TrailDetailStyles from '../../styles/sub-components/TrailDetail';
import { IsPremium } from '../../repositories/User';
import { AddTrailHistory, AddPinHistory } from '../../repositories/History';
import { OpenURL } from '../../constants/Links';
import { startBackgroundTask, stopBackgroundTask } from '../../background/Service';
import PageStyle from '../../styles/ui/Pages';
import { refreshIfDarkModeChanges } from '../utils/RefreshDarkMode';
import { GetTrail } from '../../repositories/Trails';
import LoadingIndicator from '../../components/ui/Indicator';
import { iconsColorSecondary } from '../../styles/Colors';

const add = (edge, aux, pins) => {
  if (!aux.has(edge.pin_name)) {
    aux.add(edge.pin_name);
    pins.push(edge);
  }
};

const getAllPins = (trail) => {
  const pins = [];
  const aux = new Set();
  if (trail.edges && trail.edges.length > 0) {
    trail.edges.forEach((edge) => {
      add(edge.edge_start, aux, pins);
      add(edge.edge_end, aux, pins);
    });
  }
  return pins;
};

const buildUrl = (pins) => {
  const encodedPins = pins.map(p => encodeURIComponent(`${p.pin_lat},${p.pin_lng}`));
  return `https://www.google.com/maps/dir/${encodedPins.join('/')}/`;
};

const openGoogleMaps = (pins) => OpenURL(buildUrl(pins));

const TrailDetail = ({ route, navigation }) => {
  refreshIfDarkModeChanges();
  const [trail, setTrail] = useState(null);
  const [pins, setPins] = useState(null);
  const { id } = route.params;
  useEffect(() => {
    GetTrail(id)
    .then(t => {
      setPins(getAllPins(t))
      setTrail(t)
    })
    .catch(e => console.log(e.message))
  }, [])
  const [isTraversing, setIsTraversing] = useState(false);
  const handleStartTrailPress = () => {
    IsPremium()
      .then(premiumStatus => {
        if (premiumStatus) {
          if (!isTraversing) {
            openGoogleMaps(pins);
            AddTrailHistory(trail);
            pins.forEach(pin => AddPinHistory(pin))
            startBackgroundTask();
            setIsTraversing(true);
          } else {
            stopBackgroundTask();
            setIsTraversing(false);
          }
        } else {
          alert('Funcionalidade apenas para utilizadores premium.');
        }
      })
      .catch(error => console.error('Error checking premium status', error));
  };

  const handlePinPress = (pin) => {
    const id = pin.id
    navigation.navigate('PinDetail', { id });
  }

  return trail ? (
    <ScrollView style={PageStyle(pageColor()).page}>

      <OurImage url={trail.trail_img} />
      <OurText content={trail.trail_name} fontSize={30} color={textColorHeader()} textAlign={'center'} />

      <OurButton
        icon={isTraversing ? "stop-sharp" : "play-sharp"}
        title={isTraversing ? "Interromper Trilho" : "Iniciar Trilho"}
        color={activityColorPrimary()}
        onPress={handleStartTrailPress}
      />

      <OurCardView data={{ "Descrição": trail.trail_desc, "Duração": trail.trail_duration + " minutos", "Dificuldade": trail.trail_difficulty }} />
      <OurText content={"Pontos de interesse"} fontSize={30} color={textColorHeader()} textAlign={'center'} />

      {pins.map((pin, index) => (
        <View key={index}>
          <TouchableOpacity onPress={() => handlePinPress(pin)}>
            <PinCard pin={pin} />
          </TouchableOpacity>
          {index < pins.length - 1 && <Ionicons name="arrow-down" size={24} color={iconsColorSecondary()} style={TrailDetailStyles.arrow} />}
        </View>
      ))}

      <OurText content={"Detalhes do trajeto"} fontSize={30} color={textColorHeader()} textAlign={'center'} />
      {trail.edges && trail.edges.length > 0 &&
        trail.edges.map((edge, index) => (
          <EdgeCard key={index} edge={edge} />
        ))
      }
    </ScrollView>
  ) : <LoadingIndicator />;
};

export default TrailDetail;
