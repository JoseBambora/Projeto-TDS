import React from 'react';
import { ScrollView, View } from 'react-native';
import OurCardView from '../ui/CardView';
import HistoryStyles from '../../styles/sub-components/HistoryComponent';
import OurText from '../ui/Text';

const HistoryComponent = ({ trailsData, pointsOfInterestData }) => {
  
  const groupTrailsByDate = (data) => {
    const groupedData = {};
    data.forEach((item) => {
      const date = new Date(item.date).toLocaleDateString();
      if (!groupedData[date]) {
        groupedData[date] = [];
      }
      groupedData[date].push(item.trailName);
    });
    return groupedData;
  };

  const groupPointsOfInterestByDate = (data) => {
    const groupedData = {};
    data.forEach((item) => {
      const date = new Date(item.date).toLocaleDateString();
      if (!groupedData[date]) {
        groupedData[date] = [];
      }
      groupedData[date].push(item.pointOfInterestName);
    });
    return groupedData;
  };

  const groupedTrailsData = groupTrailsByDate(trailsData);
  const groupedPointsOfInterestData = groupPointsOfInterestByDate(pointsOfInterestData);

  return (
    <ScrollView contentContainerStyle={HistoryStyles.container}>
      <View style={HistoryStyles.section}>
        <OurText 
          content={'Trilhos Visitados'} 
          fontSize={22} 
          color="grey" 
          textAlign="center" 
          fontWeight="bold"
        />
        <OurCardView 
          data={groupedTrailsData} 
        />
      </View>
      <View style={HistoryStyles.section}>
        <OurText 
          content={'Pins Visitados'} 
          fontSize={22} 
          color="grey" 
          textAlign="center" 
          fontWeight="bold"
        />
        <OurCardView 
          data={groupedPointsOfInterestData} 
        />
      </View>
    </ScrollView>
  );
};

export default HistoryComponent;
