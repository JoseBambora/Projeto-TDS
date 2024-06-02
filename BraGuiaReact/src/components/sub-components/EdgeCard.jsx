import React, { useContext } from 'react';
import { View} from 'react-native';
import Label from '../ui/Label';
import Value from '../ui/Value';
import EdgeCardStyles from '../../styles/sub-components/EdgeCard';
import { ThemeContext } from '../../controler/ThemeControler';
import { textColorPrimary } from '../../styles/Colors';

const EdgeCard = ({ edge }) => {
  const { isDarkMode } = useContext(ThemeContext);
  const transportType = edge.edge_transport === 'D' ? 'Condução' : 'Caminhada';


  return (
    <View style={[EdgeCardStyles.container, { backgroundColor: isDarkMode ? '#1a1a1a' : '#fff' }]}>
      <View style={EdgeCardStyles.row}>
        <Label text="Observações:" />
        <Value text={edge.edge_desc} />
      </View>
      <View style={EdgeCardStyles.row}>
        <Label text="Condução / Caminhada:" />
        <Value text={transportType}  />
      </View>
      <View style={EdgeCardStyles.row}>
        <Label text="Duração:"  />
        <Value text={`${edge.edge_duration} minutos`}  />
      </View>
      <View style={EdgeCardStyles.row}>
        <Label text="Partida:"  />
        <Value text={edge.edge_start.pin_name} />
      </View>
      <View style={EdgeCardStyles.row}>
        <Label text="Chegada:"  />
        <Value text={edge.edge_end.pin_name}  />
      </View>
    </View>
  );
};

export default EdgeCard;
