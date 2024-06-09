import React from 'react';
import { View } from 'react-native';
import Svg, { Path } from 'react-native-svg';

export default function OurCurve({ color }) {
  return (
    <View style={{ backgroundColor: color, height: 90}}>
      <Svg
        height="100%"
        width="100%"
        viewBox="0 0 1440 310"
        style={{ position: 'absolute', top: 55 }}
      >
        <Path
          fill={color}
          d="M0,192L40,213.3C80,235,160,277,240,298.7C320,320,400,320,480,293.3C560,267,640,213,720,213.3C800,213,880,267,960,282.7C1040,299,1120,277,1200,245.3C1280,213,1360,171,1400,149.3L1440,128L1440,0L1400,0C1360,0,1280,0,1200,0C1120,0,1040,0,960,0C880,0,800,0,720,0C640,0,560,0,480,0C400,0,320,0,240,0C160,0,80,0,40,0L0,0Z"
        />
      </Svg>
    </View>
  );
}