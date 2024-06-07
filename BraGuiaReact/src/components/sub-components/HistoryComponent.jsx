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
    const options = { weekday: 'short', day: '2-digit', month: '2-digit', year: 'numeric' };

    data.forEach((item) => {
      const parts = new Intl.DateTimeFormat('pt-PT', options).formatToParts(new Date(item.date));
      const dateParts = {};
      parts.forEach(part => dateParts[part.type] = part.value);
      const capitalizedWeekday = dateParts.weekday.charAt(0).toUpperCase() + dateParts.weekday.slice(1);
      const date = `${capitalizedWeekday}, ${dateParts.day}/${dateParts.month}/${dateParts.year}`;

      if (!groupedData[date]) {
        groupedData[date] = [];
      }
      const id = item.id;
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
