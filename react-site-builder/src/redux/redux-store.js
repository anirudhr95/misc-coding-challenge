import {createStore, applyMiddleware} from 'redux';
import thunk from 'redux-thunk';
import reducerWrapper from './reducer/reducer-wrapper';

export const store = createStore(reducerWrapper, applyMiddleware(thunk));
