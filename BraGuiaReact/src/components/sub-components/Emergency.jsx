import OurButton from "../ui/Button"
import { Linking } from "react-native"

const Emergency = () => <OurButton icon={'call-outline'} color='white' iconColor='black' onPress={() => Linking.openURL('tel:112')} />

export const EmergencyCall = () => {
  return {
    headerRight: () => Emergency()
  }
}