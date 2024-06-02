import React, { useState, useEffect, useContext } from 'react';
import { SafeAreaView, StatusBar } from 'react-native';
import TrailsComponent from '../../components/sub-components/TrailsComponent';
import { GetTrails } from '../../repositories/Trails';
import TrailsScreen from '../../styles/sub-components/Trails';
import { ThemeContext } from '../../controler/ThemeControler';
import { backgroundColor } from '../../styles/Colors';

const Trails = () => {
  const [trailData, setTrailData] = useState([]);
  const { isDarkMode } = useContext(ThemeContext); 

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
    <SafeAreaView style={[TrailsScreen.container, { backgroundColor: backgroundColor(isDarkMode)}]}>
      <StatusBar barStyle="dark-content" />
      <TrailsComponent trailData={trailData} />
    </SafeAreaView>
  );
};

export default Trails;

