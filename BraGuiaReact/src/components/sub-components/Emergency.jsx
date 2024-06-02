import { headerColor, headerTextColor } from "../../styles/Colors"
import HeaderStyle from "../../styles/sub-components/Header"
import OurButton from "../ui/Button"
import { Linking } from "react-native"

const Emergency = () => <OurButton icon={'call-outline'} color={headerColor()} iconColor='white' onPress={() => Linking.openURL('tel:112')} />

export const HeaderProps = () => {
  return {
    headerRight: () => Emergency(),
    headerStyle: HeaderStyle.header,
    headerTintColor: headerTextColor()
  }
}