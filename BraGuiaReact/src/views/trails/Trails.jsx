import React, { useState, useCallback } from 'react';
import { SafeAreaView, StatusBar } from 'react-native';
import TrailsComponent from '../../components/TrailsComponent';
import { GetTrails } from '../../repositories/Trails';
import TrailsScreen from '../../styles/Trails';
import { IsAuthenticated } from '../../repositories/User';
import Unauthenticated from '../Unauthenticated';
import LoadingIndicator from '../../components/Indicator';
import { useFocusEffect } from '@react-navigation/native';

const Trails = () => {
  const [trailData, setTrailData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [authenticated, setAuthenticated] = useState(false);

  const checkAuthentication = useCallback(() => {
    IsAuthenticated()
      .then(isAuthenticated => {
        setAuthenticated(isAuthenticated);
        if (isAuthenticated) {
          return GetTrails();
        } else {
          setLoading(false);
          return Promise.resolve([]);
        }
      })
      .then(data => {
        if (data.length > 0) {
          setTrailData(data);
        }
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching trails:', error);
        setLoading(false);
      });
  }, []);

  useFocusEffect(
    useCallback(() => {
      checkAuthentication();
    }, [checkAuthentication])
  );

  if (!authenticated) {
    return <Unauthenticated />;
  }

  if (loading) {
    return (
      <LoadingIndicator/>
    );
  }

  return (
    <SafeAreaView style={TrailsScreen.container}>
      <StatusBar barStyle="dark-content" />
      <TrailsComponent trailData={trailData} />
    </SafeAreaView>
  );
};

export default Trails;
