import { downloadContent } from "../helper/MediaRequest";
import { hasContent } from "../redux/Media"
import { CreatePromise } from "./Util";
import RNFS from 'react-native-fs';

const getFileName = (url) => url.split('/').pop();

export const LoadContent = (url) => {
  const localFilePath = `${RNFS.DocumentDirectoryPath}/${getFileName(url)}`
  return hasContent(localFilePath)
    .then(b => {
      if (!b) {
        return downloadContent(url, localFilePath)
          .then(_ => localFilePath)
          .catch(e => { throw e })
      }
      else
        return CreatePromise(localFilePath)
    })
}