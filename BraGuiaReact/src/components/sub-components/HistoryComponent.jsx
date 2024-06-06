import React from 'react';
import { ScrollView, View } from 'react-native';
import OurCardView from '../ui/CardView';
import HistoryStyles from '../../styles/sub-components/HistoryComponent';
import OurText from '../ui/Text';
import { pageColor } from '../../styles/Colors';
import { useNavigation } from '@react-navigation/native';

const HistoryComponent = ({ trailsData, pointsOfInterestData }) => {

  const groupByDate = (data, itemType, page, navigation) => {
    const groupedData = {};
    data.forEach((item) => {
      const date = new Date(item.date).toLocaleDateString();
      if (!groupedData[date]) {
        groupedData[date] = [];
      }
      const id = item.id
      groupedData[date].push([item[itemType], () => navigation.navigate(page, { id })]);
    });
    return groupedData;
  };
  const navigation = useNavigation();
  const groupedTrailsData = groupByDate(trailsData, 'trailName', 'TrailDetail', navigation);
  const groupedPointsOfInterestData = groupByDate(pointsOfInterestData, 'pointOfInterestName', 'PinDetail', navigation);
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
