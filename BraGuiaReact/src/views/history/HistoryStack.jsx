import TrailDetail from "../trails/TrailDetail";
import PinDetail from "../trails/PinDetail";
import History from "./History";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { HeaderProps } from "../../components/sub-components/Header";

const Stack = createNativeStackNavigator();


function StackHistory() {
  return (
    <Stack.Navigator initialRouteName={'HistoryPage'}>
      <Stack.Screen name="HistoryPage" component={History} options={HeaderProps()} />
      <Stack.Screen name="TrailDetail" component={TrailDetail} options={HeaderProps()} />
      <Stack.Screen name="PinDetail" component={PinDetail} options={HeaderProps()} />
    </Stack.Navigator>
  );
}

export default StackHistory