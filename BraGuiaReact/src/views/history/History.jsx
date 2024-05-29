import React, { useState, useCallback } from 'react';
import { View } from 'react-native';
import HistoryComponent from '../../components/HistoryComponent';
import HistoryScreen from '../../styles/History';
import { IsAuthenticated } from '../../repositories/User';
import Unauthenticated from '../Unauthenticated';
import LoadingIndicator from '../../components/Indicator';
import { useFocusEffect } from '@react-navigation/native';
import { GetTrailsHistory, GetPinsHistory } from '../../repositories/History';
const History = () => {
  const [trailsData, setTrailsData] = useState([]);
  const [pointsOfInterestData, setPointsOfInterestData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [authenticated, setAuthenticated] = useState(false);

  const checkAuthentication = useCallback(() => {
    IsAuthenticated()
      .then(isAuthenticated => {
        setAuthenticated(isAuthenticated);
        if (isAuthenticated) {
          return fetchHistoryData();
        } else {
          setLoading(false);
          return Promise.resolve({ trails: [], pointsOfInterest: [] });
        }
      })
      .then(data => {
        setTrailsData(data.trails);
        setPointsOfInterestData(data.pointsOfInterest);
        setLoading(false);
      })
      .catch(error => {
        console.error('Error fetching history:', error);
        setLoading(false);
      });
  }, []);

  useFocusEffect(
    useCallback(() => {
      checkAuthentication();
    }, [checkAuthentication])
  );

  if (loading) {
    return <LoadingIndicator />;
  }

  if (!authenticated) {
    return <Unauthenticated />;
  }

  return (
    <View style={HistoryScreen.container}>
      <HistoryComponent trailsData={trailsData} pointsOfInterestData={pointsOfInterestData} />
    </View>
  );
};

export default History;

const fetchHistoryData = () => {
  return Promise.all([GetTrailsHistory(), GetPinsHistory()]).then(([trails, pointsOfInterest]) => {
    return {
      trails,
      pointsOfInterest,
    };
  });
};
