import React, { useEffect, useState, useCallback } from 'react';
import { SafeAreaView, StatusBar, ActivityIndicator } from 'react-native';
import Trails from '../../components/Trails';
import { GetTrails } from '../../repositories/Trails';
import trailsScreenStyles from '../../styles/TrailsScreen';
import { IsAuthenticated } from '../../redux/SessionState';
import UnauthenticatedScreen from '../UnauthenticatedScreen';
import { useFocusEffect } from '@react-navigation/native'; // Import useFocusEffect hook

const TrailsScreen = () => {
  const [trailData, setTrailData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [authenticated, setAuthenticated] = useState(false);

  const checkAuthentication = useCallback(async () => {
    try {
      const isAuthenticated = await IsAuthenticated();
      setAuthenticated(isAuthenticated);

      if (isAuthenticated) {
        const data = await GetTrails();
        setTrailData(data);
        setLoading(false);
      } else {
        setLoading(false);
      }
    } catch (error) {
      console.error('Error fetching trails:', error);
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    checkAuthentication();
  }, [checkAuthentication]);

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
