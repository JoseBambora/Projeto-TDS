import OurButton from "./Button"
import { Linking } from "react-native"

const Emergency = () => <OurButton icon={'call-outline'} title={''} color='white' iconColor='black' onPress={() => Linking.openURL('tel:112')} />


export const EmergencyCall = () => {
  return {
    headerRight: () => Emergency()
  }
}