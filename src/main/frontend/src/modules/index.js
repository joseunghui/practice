import { combineReducers } from "redux";
import auth from "../modules/Auth";

const rootReducer = combineReducers({
    auth,
});

export default rootReducer;