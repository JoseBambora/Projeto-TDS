import React from 'react';
import { ScrollView, View } from 'react-native';
import OurCardView from '../ui/CardView';
import HistoryStyles from '../../styles/sub-components/HistoryComponent';
import OurText from '../ui/Text';
import { pageColor } from '../../styles/Colors';

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
  const HistoryStyleVar = HistoryStyles(pageColor)
  return (
    <ScrollView style={HistoryStyleVar.container}>
      <View style={HistoryStyleVar.section}>
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
      <View style={HistoryStyleVar.section}>
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
