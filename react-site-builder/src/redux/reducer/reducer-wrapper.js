import {combineReducers as reducerWrapper} from 'redux';

import { reducerAction } from './reducer-function';

export default reducerWrapper({reducerAction});
