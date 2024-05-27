import React from 'react';
import { ScrollView, View, Text } from 'react-native';
import OurCardView from './CardView';
import HistoryStyles from '../styles/HistoryComponent';
import OurText from './Text';

const HistoryComponent = ({ trailsData, pointsOfInterestData }) => {
  const trailsContent = trailsData.reduce((acc, trail, index) => {
    acc[`${index + 1}º Trilho`] = trail.trailName;
    return acc;
  }, {});


  const pointsOfInterestContent = pointsOfInterestData.reduce((acc, point, index) => {
    acc[`${index + 1}º Pin`] = point.pointOfInterestName;
    return acc;
  }, {});

  return (
    <ScrollView contentContainerStyle={HistoryStyles.container}>
      <View style={HistoryStyles.section}>
      <OurText 
          content="Trilhos Visitados" 
          fontSize={22} 
          color="grey" 
          textAlign="center" 
          fontWeight="bold"
        />
        <OurCardView data={trailsContent} />
      </View>
      <View style={HistoryStyles.section}>
      <OurText 
          content="Pins Visitados" 
          fontSize={22} 
          color="grey" 
          textAlign="center"
          fontWeight="bold" 
        />
        <OurCardView data={pointsOfInterestContent} />
      </View>
    </ScrollView>
  );
};

export default HistoryComponent;
