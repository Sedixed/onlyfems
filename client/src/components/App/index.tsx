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
import useGetUser from "../../hooks/useGetUser";

const App = () => {
  const queryClient = new QueryClient();
  const [snackMessage, setSnackMessage] = useState<SnackMessageType | null>(null);

  const {user, refetch} = useGetUser();

  if (!user) {
    return <LoadingCircle />
  }
  
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
                  refetchCallback={() => refetch({})}
                />
              } 
            />
          </Routes>

          <>
            <Navbar setSnack={setSnackMessage} user={user} refetch={() => refetch({})} />

            { snackMessage ? 
              <SnackMessage 
                snackMessage={snackMessage}
                fullTop={snackMessage.fullTop ?? false}
                closeAction={() => setSnackMessage(null)} 
              /> : 
              null
            }

            <div className="content">
              <Routes>
                <Route path={clientPath.TEST} element={<LoadingCircle />} />
                <Route path={clientPath.PROFILE} element={<Profile />} />
                <Route index path={clientPath.ADMIN} element={<Administration setSnack={setSnackMessage} />} />
              </Routes>
            </div>
          </>
        </BrowserRouter>
      </div>
    </QueryClientProvider>
  )
};

export default App;