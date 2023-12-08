import React, { useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import '../../styles/App.css';
import clientPath from '../../utils/clientPath';
import Navbar from "../Navbar";
import NoAuthPage from "../NoAuthPage";
import { QueryClient, QueryClientProvider } from 'react-query';
import LoadingCircle from "../LoadingCircle";
import SnackMessage from "../SnackMessage";
import { SnackMessageType } from "../../types/entityType";
import Profile from "../Profile";
import Administration from "../Administration";

const App = () => {
  const queryClient = new QueryClient();
  const [snackMessage, setSnackMessage] = useState<SnackMessageType | null>(null);
  
  return (
    <QueryClientProvider client={queryClient}>
      <div className="app">
        <BrowserRouter>
          <Routes>
            <Route 
              path={clientPath.HOME} 
              element={
                <NoAuthPage 
                  setSnack={setSnackMessage}
                />
              } 
            />
          </Routes>

          <>
            <Navbar setSnack={setSnackMessage} />

            { snackMessage ? 
              <SnackMessage 
                snackMessage={snackMessage}
                closeAction={() => setSnackMessage(null)} 
              /> : 
              null
            }

            <div className="content">
              <Routes>
                <Route path={clientPath.TEST} element={<LoadingCircle />} />
                <Route path={clientPath.PROFILE} element={<Profile />} />
                <Route index path={clientPath.ADMIN} element={<Administration />} />
              </Routes>
            </div>
          </>
        </BrowserRouter>
      </div>
    </QueryClientProvider>
  )
};

export default App;