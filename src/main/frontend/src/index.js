import React from 'react';
// import ReactDOM from 'react-dom';
import './index.css';
import App from '../src/shared/App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import { createStore } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import rootReducer from './modules/index';
// import {hydrateRoot} from "react-dom/client";
import { createRoot } from 'react-dom/client';

const store = createStore(rootReducer, composeWithDevTools());
//
// ReactDOM.render(
//     <Provider store={store}>
//         <BrowserRouter>
//             <App />
//         </BrowserRouter>
//     </Provider>
//     , document.getElementById("root"));
//
//
// const container = document.getElementById('root');
// const root = hydrateRoot(container,
//     <Provider store={store}>
//         <BrowserRouter>
//             <App />
//         </BrowserRouter>
//     </Provider>
// );


const container = document.getElementById('root');
const root = createRoot(container); // createRoot(container!) if you use TypeScript
root.render(
    <Provider store={store}>
        <BrowserRouter>
            <App />
        </BrowserRouter>
    </Provider>
);