
import { AddPinHistoryDB, AddTrailHistoryDB, GetPinsHistoryDB, GetTrailsHistoryDB, CleanHistoryDB } from "../redux/History"

export const AddPinHistory = (pin) => AddPinHistoryDB(pin)
export const AddTrailHistory = (trail) => AddTrailHistoryDB(trail)
export const GetTrailsHistory = () => GetTrailsHistoryDB()
export const GetPinsHistory = () => GetPinsHistoryDB()
export const CleanHistory = () => CleanHistoryDB()