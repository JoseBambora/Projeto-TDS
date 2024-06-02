import OurMedia from "./Media";
import Video from "react-native-video";
import { useState, useRef } from "react"
import { View } from "react-native";
import Controller from "./Controllers";
import MediaStyle from "../../styles/media/Media";
import CardStyle from "../../styles/ui/CardView";
import OurText from "../ui/Text";
import { textColorHeader } from "../../styles/Colors";

const OurVideo = ({ url }) => <OurMedia url={url} type={2} />

export const VideoPlayer = ({ mediaUrl }) => {
  const videoRef = useRef(null);
  const [isPlaying, setIsPlaying] = useState(false);
  const [currentTime, setCurrentTime] = useState(0);
  const [ended, setEnd] = useState(false);
  const [duration, setDuration] = useState(0);

  const playVideo = () => {
    setIsPlaying(true);
  };

  const pauseVideo = () => {
    setIsPlaying(false)
  }
  const onSeekPress = (time) => {
    if (videoRef.current) {
      const newTime1 = Math.min(time, duration);
      const newTime2 = Math.max(newTime1, 0);
      videoRef.current.seek(newTime2);
      setCurrentTime(newTime2);
    }
  };

  const restartVideo = () => {
    setIsPlaying(true)
    setEnd(false)
    videoRef.current.seek(0)
    setCurrentTime(0)
  }

  const onProgress = (data) => {
    setCurrentTime(data.currentTime);
  };
  const onEnd = () => {
    setEnd(true)
  }

  const onLoad = (data) => {
    setDuration(data.duration);
  };

  const stopVideo = () => {
    videoRef.current.seek(0)
    setCurrentTime(0)
    setIsPlaying(false)
    setEnd(false)
  }

  return (
    <View style={CardStyle.container}>
      <View style={CardStyle.card}>
        <OurText content={'Vídeo'} textAlign="center" color={textColorHeader()} fontSize={24} />
        <View style={MediaStyle.videoBox}>
          <Video
            ref={videoRef}
            source={{ uri: mediaUrl }}
            style={MediaStyle.video}
            paused={!isPlaying}
            onLoad={onLoad}
            onProgress={onProgress}
            onEnd={onEnd}
            resizeMode="contain"
            controls={false}
          />
        </View>
        <Controller onSeekPress={onSeekPress} stop={stopVideo} restart={restartVideo} play={playVideo} pause={pauseVideo} hasEnded={ended} isPlaying={isPlaying} currentTime={currentTime} />
      </View>
    </View>
  );
};

export default OurVideo;