import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import '../../styles/App.css';
import clientPath from '../../utils/clientPath';
import Navbar from "../Navbar";
import useIsAuthenticated from "../../hooks/useIsAuthenticated";
import NoAuthPage from "../NoAuthPage";

const App = () => {
  const authenticated = useIsAuthenticated();
  return (
    <div className="app">
      <BrowserRouter>
        { authenticated ?
          (
            <React.Fragment>
              <Navbar />
              <div className="content">
                <Routes>
                  <Route path={clientPath.LOGIN} exact element={<></>} />
                  <Route path={clientPath.REGISTER} exact element={<></>} />
                </Routes>
                </div>
            </React.Fragment>
          ) : (
            <NoAuthPage />
          )
        } 
        
      </BrowserRouter>
    </div>
  )
};

export default App;