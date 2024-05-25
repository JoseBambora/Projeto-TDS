import React from 'react';
import { ScrollView, View } from 'react-native';
import OurCardView from './CardView';
import TrailsStyles from '../styles/TrailsComponent';

const TrailsComponent = ({ trailData }) => {
  return (
    <ScrollView contentContainerStyle={TrailsStyles.container}>
      {trailData.map((trail) => (
        <View key={trail.id} style={TrailsStyles.cardWrapper}>
          <OurCardView
            data={{
              "Nome do trilho": trail.trail_name
            }}
            imageSource={trail.trail_img}
          />
        </View>
      ))}
    </ScrollView>
  );
};

export default TrailsComponent;
