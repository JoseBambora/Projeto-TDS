import { useState, useCallback, useEffect } from "react"
import { LoadContent } from "../../repositories/Media"
import { IsPremium } from "../../repositories/User"
import LoadingIndicator from "../ui/Indicator"
import { Image } from "react-native"
import MediaStyle from "../../styles/media/Media"
import { AudioPlayer } from "./Audio"
import { VideoPlayer } from "./Video"
import OurText from "../ui/Text"
import { useFocusEffect } from "@react-navigation/native"

const Premium = (url, setContent) => {
  LoadContent(url)
    .then(f => {
      setContent({ loading: false, isPremium:true, mediaUrl: `file://${f}` })
    })
    .catch(e => console.log(e))
}

const Standard = (url, setContent) => {
  setContent({ loading: false, isPremium:false, mediaUrl: url })
}

const Media = (mediaUrl, type) => {
  return type == 0 ? <Image source={{ uri: mediaUrl }} style={MediaStyle.image} /> :
    type == 1 ? <AudioPlayer mediaUrl={mediaUrl} /> :
      <VideoPlayer mediaUrl={mediaUrl}/>
}

const OurMedia = ({ url, type }) => {
  const [content, setContent] = useState({ loading: true, mediaUrl: '', isPremium : false})

  const fetchData = useCallback(() => {
    IsPremium()
      .then(b => b ? Premium(url, setContent) : Standard(url, setContent))
      .catch(e => console.log(e))
  }, [url]);

  useFocusEffect(fetchData);
  return content.loading ? <LoadingIndicator /> : 
  content.isPremium ? content.mediaUrl != '' && Media(content.mediaUrl, type) :
  <OurText content={'Conteúdo multimédia apenas disponível para utilizadores premium'} textAlign="center"/>
}


export default OurMedia;