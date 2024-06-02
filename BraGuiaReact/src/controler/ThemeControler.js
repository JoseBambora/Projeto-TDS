import React, { createContext, useState, useEffect } from 'react';
import { GetSettings, SaveDarkMode } from '../repositories/Settings';

export const ThemeContext = createContext();

export const ThemeProvider = ({ children }) => {
  const [isDarkMode, setIsDarkMode] = useState(false);

  useEffect(() => {
    const settings = GetSettings();
    setIsDarkMode(settings.dark_mode);
  }, []);

  const toggleDarkMode = () => {
    const newValue = !isDarkMode;
    setIsDarkMode(newValue);
    SaveDarkMode(newValue);
  };

  return (
    <ThemeContext.Provider value={{ isDarkMode, toggleDarkMode }}>
      {children}
    </ThemeContext.Provider>
  );
};
