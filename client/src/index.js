import React from "react";
import ReactDOM from "react-dom/client";

import App from "./components/App";
import { QueryClientProvider } from "react-query";


const rootEl = document.querySelector('#root');
const root = ReactDOM.createRoot(rootEl);

root.render(<App />);
