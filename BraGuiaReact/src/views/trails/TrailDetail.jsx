import React from 'react';
import { ScrollView } from 'react-native';
import OurImage from '../../components/media/Image';
import OurText from '../../components/Text'; 
import { textColorHeader } from '../../styles/Colors';
import OurCardView from '../../components/CardView';
import OurButton from '../../components/Button';
import { activityColorPrimary } from '../../styles/Colors';

const TrailDetail = ({ route }) => {
  const { trail } = route.params;

  const formatRelTrails = (relTrails) => {
    const formattedTrails = relTrails.map(trail => ({
      "Attribute": trail.attrib,
      "Id": trail.id,
      "Value": trail.value
    }));
    return formattedTrails.map(trail => `${trail.Attribute}: ${trail.Value}`);;
  }

  const trailDetails = {
    "Id": trail.id,
    "Nome do Trilho": trail.trail_name,
    "Descrição": trail.trail_desc,
    "Duração": trail.trail_duration + " minutos",
    "Dificuldade": trail.trail_difficulty,
  }

  if (trail.rel_trail && trail.rel_trail.length > 0) {
    trailDetails["Informações Extra"] = formatRelTrails(trail.rel_trail);
  }

  return (
    <ScrollView>
      <OurImage url={trail.trail_img}/> 
      <OurText content={trail.trail_name} fontSize={30} color={textColorHeader()} textAlign={'center'}  />
      <OurCardView data={trailDetails} />
      <OurText content={"Trajeto"} fontSize={30} color={textColorHeader()} textAlign={'center'}  />
      {/*ADICIONAR AQUI COMPONENTE PARA A LISTA DE PINS E FUNÇÃO PARA INICIAR O TRILHO*/}
      <OurButton
          icon={"play-sharp"}
          title={"Iniciar Trilho"}
          color={activityColorPrimary()}>
          </OurButton>
    </ScrollView>
  );
};

export default TrailDetail;
