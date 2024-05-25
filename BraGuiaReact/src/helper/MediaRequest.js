import RNFS from 'react-native-fs';

export const downloadContent = (url, localFilePath) => {
  return RNFS.downloadFile({ fromUrl: url, toFile: localFilePath }).promise
    .then(() => localFilePath)
    .catch(error => {
      console.error('Failed to download video', error);
      throw error;
    });
};
