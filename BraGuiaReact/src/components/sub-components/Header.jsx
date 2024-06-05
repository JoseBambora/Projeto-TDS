import { Image } from "react-native"
import { headerColor, headerTextColor } from "../../styles/Colors"
import HeaderStyle from "../../styles/sub-components/Header"
import OurButton from "../ui/Button"
import { Linking } from "react-native"

const Emergency = () => <OurButton icon={'call-outline'} color={headerColor()} iconColor='white' onPress={() => Linking.openURL('tel:112')} />

const IconLogo = () => <Image style={HeaderStyle.logo} source={require('../../../android/app/src/main/res/mipmap-mdpi/ic_launcher_foreground.png')} />

export const HeaderProps = () => {
  return {
    headerTitle: 'BraGuia',
    headerTitleAlign: 'center',
    headerTitleStyle: HeaderStyle.title,
    headerLeft: () => <IconLogo />,
    headerRight: () => <Emergency />,
    headerStyle: HeaderStyle.header,
    headerTintColor: headerTextColor(),
  }
}