import React from 'react';
import { View } from 'react-native';
import Label from '../ui/Label';
import Value from '../ui/Value';
import EdgeCardStyles from '../../styles/sub-components/EdgeCard';
import { refreshIfDarkModeChanges } from '../../views/utils/RefreshDarkMode';
import { cardViewColor } from '../../styles/Colors';

const EdgeCard = ({ edge }) => {
  refreshIfDarkModeChanges();
  const EdgeCardStyleVar = EdgeCardStyles(cardViewColor())
  const transportType = edge.edge_transport === 'D' ? 'Condução' : 'Caminhada';

  return (
    <View style={EdgeCardStyleVar.container}>
      <View style={EdgeCardStyleVar.row}>
        <Label text="Observações: " />
        <Value text={edge.edge_desc} />
      </View>
      <View style={EdgeCardStyleVar.row}>
        <Label text="Condução / Caminhada: " />
        <Value text={transportType} />
      </View>
      <View style={EdgeCardStyleVar.row}>
        <Label text="Duração: " />
        <Value text={`${edge.edge_duration} minutos`} />
      </View>
      <View style={EdgeCardStyleVar.row}>
        <Label text="Partida: " />
        <Value text={edge.edge_start.pin_name} />
      </View>
      <View style={EdgeCardStyleVar.row}>
        <Label text="Chegada: " />
        <Value text={edge.edge_end.pin_name} />
      </View>
    </View>
  );
};

export default EdgeCard;
