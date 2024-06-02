import React from 'react';
import { ScrollView, TouchableOpacity } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import OurCardView from '../ui/CardView';
import TrailsStyles from '../../styles/sub-components/TrailsComponent';

const TrailsComponent = ({ trailData }) => {
  const navigation = useNavigation();

  const handleTrailPress = (trail) => {
    navigation.navigate('TrailDetail', { trail });
  };

  return (
    <ScrollView contentContainerStyle={TrailsStyles.container}>
      {trailData.map((trail) => (
        <TouchableOpacity
          key={trail.id}
          style={TrailsStyles.cardWrapper}
          onPress={() => handleTrailPress(trail)}
        >
          <OurCardView
            data={{
              "Nome do trilho": trail.trail_name
            }}
            imageSource={trail.trail_img}
          />
        </TouchableOpacity>
      ))}
    </ScrollView>
  );
};

export default TrailsComponent;
