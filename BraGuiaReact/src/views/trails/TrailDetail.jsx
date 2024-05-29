import React from 'react';
import { ScrollView, View, StyleSheet } from 'react-native';
import OurImage from '../../components/media/Image';
import OurText from '../../components/Text'; 
import { textColorHeader, activityColorPrimary } from '../../styles/Colors';
import OurCardView from '../../components/CardView';
import OurButton from '../../components/Button';
import EdgeCard from '../../components/EdgeCard';
import PinCard from '../../components/PinCard';
import Ionicons from 'react-native-vector-icons/Ionicons';

const getAllPins = (trail) => {
  const pinSet = new Set();
  const aux = [];
  if (trail.edges && trail.edges.length > 0) {
    trail.edges.forEach((edge) => {
      if(!aux.includes(edge.edge_start.pin_name)){
        aux.push(edge.edge_start.pin_name)
        pinSet.add(edge.edge_start);
      }
      if(!aux.includes(edge.edge_end.pin_name)){
        aux.push(edge.edge_end.pin_name)
        pinSet.add(edge.edge_end);
      }
    });
  }
  return Array.from(pinSet);
};

const TrailDetail = ({ route }) => {
  const { trail } = route.params;
  const pins = getAllPins(trail);

  return (
    <ScrollView>
      <OurImage url={trail.trail_img}/> 
      <OurText content={trail.trail_name} fontSize={30} color={textColorHeader()} textAlign={'center'}  />
      <OurCardView data={{ "Id": trail.id, "Nome do Trilho": trail.trail_name, "Descrição": trail.trail_desc, "Duração": trail.trail_duration + " minutos", "Dificuldade": trail.trail_difficulty }} />
      <OurText content={"Pontos de interesse"} fontSize={30} color={textColorHeader()} textAlign={'center'}  />

      {pins.map((pin, index) => (
        <View key={index}>
          <PinCard pin={pin} />
          {index < pins.length - 1 && <Ionicons name="arrow-down" size={24} color="black" style={styles.arrow} />}
        </View>
      ))}
      
      <OurText content={"Detalhes do trajeto"} fontSize={30} color={textColorHeader()} textAlign={'center'}  />
      {trail.edges && trail.edges.length > 0 &&
        trail.edges.map((edge, index) => (
          <EdgeCard key={index} edge={edge} />
        ))
      }
      <OurButton
        icon={"play-sharp"}
        title={"Iniciar Trilho"}
        color={activityColorPrimary()}
      />
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  arrow: {
    alignSelf: 'center',
    marginTop: -35,
  },
});

export default TrailDetail;
