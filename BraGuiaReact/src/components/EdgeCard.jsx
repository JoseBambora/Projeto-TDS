import React from 'react';
import { View, StyleSheet } from 'react-native';
import Label from './Label';
import Value from './Value';

const EdgeCard = ({ edge }) => {
  const transportType = edge.edge_transport === 'D' ? 'Condução' : 'Caminhada';

  return (
    <View style={styles.container}>
      <View style={styles.row}>
        <Label text="Observações:" />
        <Value text={edge.edge_desc} />
      </View>
      <View style={styles.row}>
        <Label text="Condução / Caminhada:" />
        <Value text={transportType} />
      </View>
      <View style={styles.row}>
        <Label text="Duração:" />
        <Value text={`${edge.edge_duration} minutos`} />
      </View>
      <View style={styles.row}>
        <Label text="Partida:" />
        <Value text={edge.edge_start.pin_name} />
      </View>
      <View style={styles.row}>
        <Label text="Chegada:" />
        <Value text={edge.edge_end.pin_name} />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#fff',
    padding: 20,
    borderRadius: 10,
    marginHorizontal: 10,
    marginBottom: 5,
    elevation: 2,
    alignItems: 'center',
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 10,
    alignItems: 'center',
    width: '100%',
  },
});

export default EdgeCard;
