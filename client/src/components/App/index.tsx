import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import '../../styles/App.css';
import clientPath from '../../utils/clientPath';
import Navbar from "../Navbar";
import useIsAuthenticated from "../../hooks/useIsAuthenticated";
import NoAuthPage from "../NoAuthPage";
import { QueryClient, QueryClientProvider } from 'react-query';

const App = () => {
  const queryClient = new QueryClient();
  const authenticated = useIsAuthenticated();
  return (
    <QueryClientProvider client={queryClient}>
      <div className="app">
        <BrowserRouter>
          { authenticated ?
            (
              <React.Fragment>
                <Navbar />
                <div className="content">
                  <Routes>
                    <Route path={clientPath.LOGIN} element={<></>} />
                    <Route path={clientPath.REGISTER} element={<></>} />
                  </Routes>
                  </div>
              </React.Fragment>
            ) : (
              <NoAuthPage />
            )
          } 
        </BrowserRouter>
      </div>
    </QueryClientProvider>
  )
};

export default App;