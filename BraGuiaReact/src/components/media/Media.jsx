import { useState, useCallback, useEffect } from "react"
import { LoadContent } from "../../repositories/Media"
import { IsPremium } from "../../repositories/User"
import LoadingIndicator from "../Indicator"
import { Image } from "react-native"
import MediaStyle from "../../styles/Media"
import { AudioPlayer } from "./Audio"
import { VideoPlayer } from "./Video"

const Local = (url, setContent) => {
  console.log('local ' + url)
  LoadContent(url)
    .then(f => {
      setContent({ loading: false, mediaUrl: `file://${f}` })
    })
    .catch(e => console.log(e))
}

const Request = (url, setContent) => {
  console.log('request ' + url)
  setContent({ loading: false, mediaUrl: url })
}

const Media = (mediaUrl, type) => {
  return type == 0 ? <Image source={{ uri: mediaUrl }} style={MediaStyle.image} /> :
    type == 1 ? <AudioPlayer mediaUrl={mediaUrl} /> :
      <VideoPlayer mediaUrl={mediaUrl}/>
}

const OurMedia = ({ url, type }) => {
  const [content, setContent] = useState({ loading: true, mediaUrl: '' })

  const fetchData = useCallback(() => {
    IsPremium()
      .then(b => b ? Local(url, setContent) : Request(url, setContent))
      .catch(e => console.log(e))
  }, [url]);

  useEffect(() => {
    fetchData();
  }, [fetchData]);
  return content.loading ? <LoadingIndicator /> : content.mediaUrl != '' && Media(content.mediaUrl, type)
}


export default OurMedia;