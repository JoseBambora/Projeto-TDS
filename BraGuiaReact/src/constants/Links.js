import { Linking } from 'react-native';

export const OpenURL = async (url) => {
  try {
    await Linking.openURL(url);
  } catch (error) {
    console.error('Failed to open URL:', error);
  }
};
