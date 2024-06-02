import React, { useState, useCallback, useContext } from 'react';
import { View } from 'react-native';
import HistoryComponent from '../../components/sub-components/HistoryComponent';
import HistoryScreen from '../../styles/sub-components/History';
import { IsAuthenticated } from '../../repositories/User';
import Unauthenticated from '../user/Unauthenticated';
import LoadingIndicator from '../../components/ui/Indicator';
import { useFocusEffect } from '@react-navigation/native';
import { GetTrailsHistory, GetPinsHistory, CleanHistory } from '../../repositories/History';
import OurButton from '../../components/ui/Button';
import PageStyle from '../../styles/ui/Pages';
import { ThemeContext } from '../../controler/ThemeControler';
import { backgroundColor } from '../../styles/Colors';

const History = () => {
  const { isDarkMode } = useContext(ThemeContext); 
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

  const handleCleanHistory = () => {
    CleanHistory();
    setTrailsData([]);
    setPointsOfInterestData([]);
  };

  if (loading) {
    return <LoadingIndicator />;
  }

  if (!authenticated) {
    return <Unauthenticated />;
  }

  return (
    <View style={[HistoryScreen.container, { backgroundColor: backgroundColor(isDarkMode) }]}>
      <HistoryComponent trailsData={trailsData} pointsOfInterestData={pointsOfInterestData} />
      {(trailsData.length > 0 || pointsOfInterestData.length > 0) && (
        <View style={PageStyle.bottomleft}> 
          <OurButton 
            title="" 
            onPress={handleCleanHistory} 
            icon="trash" 
            color="#ff4d4d" 
            iconColor="#fff" 
          />
        </View>
      )}
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
