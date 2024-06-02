import { View } from "react-native"
import OurButton from "../ui/Button"
import MediaStyle from "../../styles/media/Media"

const Controller = ({ onSeekPress, play, restart, pause, stop, hasEnded, isPlaying, currentTime }) => {
  return (
    <View style={MediaStyle.controller}>
      <OurButton onPress={() => onSeekPress(currentTime - 15)} icon={'play-back-outline'} />
      <OurButton onPress={stop} icon={'stop-circle-outline'} />
      {
        hasEnded ? <OurButton onPress={restart} icon={'reload-circle-outline'} /> :
          isPlaying ? <OurButton onPress={pause} icon={'pause-circle-outline'} /> : <OurButton onPress={play} icon={'play-circle-outline'} />
      }
      <OurButton onPress={() => onSeekPress(currentTime + 15)} icon={'play-forward-outline'} />
    </View>)
}

export default Controller;