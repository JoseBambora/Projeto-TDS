import React, { useState, useEffect } from 'react';
import { SafeAreaView, StatusBar } from 'react-native';
import TrailsComponent from '../../components/sub-components/TrailsComponent';
import { GetTrails } from '../../repositories/Trails';
import TrailsScreen from '../../styles/sub-components/Trails';

const Trails = () => {
  const [trailData, setTrailData] = useState([]);

  useEffect(() => {
    GetTrails()
      .then(data => {
        setTrailData(data);
      })
      .catch(error => {
        console.error('Error fetching trails:', error);
      });
  }, []);

  return (
    <SafeAreaView style={TrailsScreen.container}>
      <StatusBar barStyle="dark-content" />
      <TrailsComponent trailData={trailData} />
    </SafeAreaView>
  );
};

export default Trails;
