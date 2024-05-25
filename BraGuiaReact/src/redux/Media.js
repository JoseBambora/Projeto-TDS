import RNFS from 'react-native-fs';

export const hasContent = (localFilePath) => {
  return RNFS.exists(localFilePath)
    .then(exists => exists)
    .catch(error => {
      console.error('Failed to check if file exists', error);
      throw error;
    });
};