import React, { useState, useCallback } from 'react';
import { View } from 'react-native';
import HistoryComponent from '../../components/HistoryComponent';
import HistoryScreen from '../../styles/History';
import { IsAuthenticated } from '../../repositories/User';
import Unauthenticated from '../Unauthenticated';
import LoadingIndicator from '../../components/Indicator';
import { useFocusEffect } from '@react-navigation/native';

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
          return fetchHistoryData(); // Placeholder so para teste de interface
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
  return Promise.resolve({
    trails: [
      { id: 1, trailName: 'Holy Trail' },
      { id: 2, trailName: 'Student Trail' },
    ],
    pointsOfInterest: [
      { id: 1, pointOfInterestName: 'Bom Jesus' },
      { id: 2, pointOfInterestName: 'Casa da Ribas' },
    ],
  });
};
