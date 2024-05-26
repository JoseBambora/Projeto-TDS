import OurMedia from "./Media";
import Sound from "react-native-sound";
import { useState, useEffect } from "react"
import { View, Platform, ProgressViewIOS, ProgressBarAndroidComponent } from "react-native";
import OurButton from "../Button";
import MediaStyle from "../../styles/Media";
import CardStyle from "../../styles/CardView";
import OurText from "../Text";
import { progressBarColor, textColorHeader } from "../../styles/Colors";
import { ProgressBar } from '@react-native-community/progress-bar-android';
import Controller from "./Controllers";


const OurAudio = ({ url }) => <OurMedia url={url} type={1} />


export const AudioPlayer = ({ mediaUrl }) => {
  const [sound, setSound] = useState(null);
  const [isPlaying, setIsPlaying] = useState(false);
  const [duration, setDuration] = useState(0);
  const [currentTime, setCurrentTime] = useState(0);
  const [ended, setEnd] = useState(false);
  useEffect(() => {
    const sound = new Sound(mediaUrl, null, (error) => {
      if (error) {
        console.log('Failed to load the sound', error);
        return;
      }
      setDuration(sound.getDuration());
      const intervalId = setInterval(() => {
        sound.getCurrentTime((seconds, isPlaying) => {
          setCurrentTime(seconds);
        });
      }, 1000);
      return () => clearInterval(intervalId);
    });
    setSound(sound);

    return () => {
      if (sound) {
        sound.release();
      }
    };
  }, [mediaUrl]);
  const playSound = () => {
    if (sound) {
      setIsPlaying(true);
      sound.play();
    }
  };
  const pauseSound = () => {
    if (sound) {
      sound.pause();
      setIsPlaying(false);
    }
  };

  const restartSound = () => {
    if (sound) {
      sound.stop();
      sound.setCurrentTime(0)
      sound.play();
      setCurrentTime(0);
      setEnd(false)
    }
  };

  const stopSound = () => {
    if (sound) {
      sound.stop();
      sound.setCurrentTime(0)
      setCurrentTime(0);
      setIsPlaying(false)
      setEnd(false)
    }
  }

  const onSeekPress = (time) => {
    if (sound) {
      const newTime1 = Math.min(time, duration);
      const newTime2 = Math.max(newTime1, 0);
      sound.setCurrentTime(newTime2);
      setCurrentTime(newTime2);
    }
  }

  useEffect(() => {
    if (currentTime > 0 && currentTime >= duration * 0.99) {
      setEnd(true);
    }
  }, [currentTime, duration]);

  const renderProgressBar = () => {
    const progress = currentTime === 0 ? 0 : (currentTime / duration);
    if (Platform.OS === 'ios') {
      return <ProgressViewIOS progress={progress} />;
    } else {
      return <ProgressBar
        color={progressBarColor()}
        styleAttr="Horizontal"
        indeterminate={false}
        progress={progress}
      />;
    }
  };

  return (
    <View style={CardStyle.container}>
      <View style={CardStyle.card}>
        <OurText content={'Áudio'} textAlign="center" color={textColorHeader()} fontSize={24} />
        {renderProgressBar()}
        <Controller onSeekPress={onSeekPress} stop={stopSound} restart={restartSound} play={playSound} pause={pauseSound} hasEnded={ended} isPlaying={isPlaying} currentTime={currentTime} />
      </View>
    </View>
  );
};

export default OurAudio;