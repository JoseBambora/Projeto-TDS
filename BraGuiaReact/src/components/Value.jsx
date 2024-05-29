import React from 'react';
import { Text, StyleSheet } from 'react-native';

const Value = ({ text }) => {
  return <Text style={styles.value}>{text}</Text>;
};

const styles = StyleSheet.create({
  value: {
    fontSize: 16,
    flex: 1,
    flexWrap: 'wrap',
  },
});

export default Value;
