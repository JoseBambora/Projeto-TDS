import React from 'react';
import { ScrollView, View, Text } from 'react-native';
import OurCardView from './CardView';
import HistoryStyles from '../styles/HistoryComponent';

const HistoryComponent = ({ trailsData, pointsOfInterestData }) => {
  return (
    <ScrollView contentContainerStyle={HistoryStyles.container}>
      <View style={HistoryStyles.section}>
        <Text style={HistoryStyles.sectionTitle}>Trilhos Visitados</Text>
        {trailsData.map((trail) => (
          <View key={trail.id} style={HistoryStyles.cardWrapper}>
            <OurCardView
              data={{
                "Nome do trilho": trail.trailName
              }}
            />
          </View>
        ))}
      </View>
      <View style={HistoryStyles.section}>
        <Text style={HistoryStyles.sectionTitle}>Pontos de Interesse Visitados</Text>
        {pointsOfInterestData.map((point) => (
          <View key={point.id} style={HistoryStyles.cardWrapper}>
            <OurCardView
              data={{
                "Ponto de Interesse": point.pointOfInterestName
              }}
            />
          </View>
        ))}
      </View>
    </ScrollView>
  );
};

export default HistoryComponent;
