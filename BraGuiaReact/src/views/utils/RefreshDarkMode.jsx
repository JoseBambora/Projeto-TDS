import { useState } from "react";
import { useFocusEffect } from "@react-navigation/native";
import { IsDarkMode } from "../../repositories/Settings";


export const refreshIfDarkModeChanges = () => {
  const [isDark, setIsDark] = useState(IsDarkMode())
  useFocusEffect(() => {
    if(isDark != IsDarkMode())
      setIsDark(IsDarkMode())
  })
}