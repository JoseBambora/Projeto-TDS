import { IsDarkMode } from "../repositories/Settings"

export const textColorPrimary = () => IsDarkMode() ? 'white' : 'black'
export const textColorSecondary = () => 'white'
export const textColorHeader = () => 'red'
export const headerColor = () => 'red'
export const headerTextColor = () => 'white'
export const navbarColor = () => 'dimgray'
export const navBarColorSelected = () => 'orange'
export const navBarColorNotSelected = () => 'white'
export const iconsColorPrimary = () => 'white'
export const iconsColorSecondary = () => IsDarkMode() ? 'white' : 'black'
export const iconsColorThird = () => IsDarkMode() ? 'white' : 'gray'
export const activityColorPrimary = () => 'red'
export const progressBarColor = () => 'red'
export const buttonColorPrimary = () => 'red'
export const pageColor = () => IsDarkMode() ? 'black' : 'whitesmoke'
export const cardViewColor = () => IsDarkMode() ? '#333333' : 'white'
export const cardViewShadowColor = () => IsDarkMode() ? 'silver' : 'silver'
export const cardViewSeparatorColor = () => IsDarkMode() ? 'white' : 'gray'
export const clickableColor = () => IsDarkMode() ? 'deepskyblue' : 'blue'
export const textInputTextColor = () => IsDarkMode() ? 'white' : 'black'
export const textInputCursorColor = () => IsDarkMode() ? 'white' : 'gray'
export const textInputBoxColor = () => IsDarkMode() ? '#333333' : 'lightgray'
export const mediaBackgroundColor = () => IsDarkMode() ? '#333333' : 'white'
export const loadingIndicatorColor = () => IsDarkMode() ? 'black' : 'white'