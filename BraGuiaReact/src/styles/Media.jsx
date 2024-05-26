import { StyleSheet } from "react-native";

const MediaStyle = StyleSheet.create({
  image: {
    flex: 1,
    aspectRatio: 16/9,
    resizeMode: 'cover',
    borderRadius: 8
  },
  videoPlayer: {
    backgroundColor: 'white',
    padding: 10,
    borderRadius: 8
  },
  videoBox: {
    padding: 10,
  },
  video: {
    flex: 1,
    aspectRatio: 16/9,
    resizeMode: 'cover',
  },
  controller: {
    flexDirection: 'row',
    justifyContent: 'center',
    marginTop: 5
  }
})


export default MediaStyle