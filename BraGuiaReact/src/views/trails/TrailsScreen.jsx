import React, { useEffect, useState, useCallback } from 'react';
import { SafeAreaView, StatusBar, ActivityIndicator } from 'react-native';
import Trails from '../../components/Trails';
import { GetTrails } from '../../repositories/Trails';
import trailsScreenStyles from '../../styles/TrailsScreen';
import { IsAuthenticated } from '../../repositories/User';
import UnauthenticatedScreen from '../UnauthenticatedScreen';
import { useFocusEffect } from '@react-navigation/native';

const TrailsScreen = () => {
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
    return <UnauthenticatedScreen />;
  }

  if (loading) {
    return (
      <SafeAreaView style={trailsScreenStyles.loadingContainer}>
        <ActivityIndicator size="large" color="#0000ff" />
      </SafeAreaView>
    );
  }

  return (
    <SafeAreaView style={trailsScreenStyles.container}>
      <StatusBar barStyle="dark-content" />
      <Trails trailData={trailData} />
    </SafeAreaView>
  );
};

export default TrailsScreen;
