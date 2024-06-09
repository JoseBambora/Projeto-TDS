import React, { useState, useEffect } from 'react';
import { SafeAreaView, StatusBar } from 'react-native';
import TrailsComponent from '../../components/sub-components/TrailsComponent';
import { GetTrails } from '../../repositories/Trails';
import { refreshIfDarkModeChanges } from '../utils/RefreshDarkMode';
import PageStyle from '../../styles/ui/Pages';
import { pageColor } from '../../styles/Colors';
import LoadingIndicator from '../../components/ui/Indicator';

const Trails = () => {
  const [trailData, setTrailData] = useState(null);
  refreshIfDarkModeChanges();
  useEffect(() => {
    GetTrails()
      .then(data => {
        setTrailData(data);
      })
      .catch(error => {
        console.error('Error fetching trails:', error);
      });
  }, []);

  return trailData ? (
    <SafeAreaView style={PageStyle(pageColor()).page}>
      <StatusBar barStyle="dark-content" />
      <TrailsComponent trailData={trailData} />
    </SafeAreaView>
  ) : (<LoadingIndicator />);
};

export default Trails;
