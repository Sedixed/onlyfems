import React, { useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import '../../styles/App.css';
import clientPath from '../../utils/clientPath';
import Navbar from "../Navbar";
import useIsAuthenticated from "../../hooks/useIsAuthenticated";
import NoAuthPage from "../NoAuthPage";
import { QueryClient, QueryClientProvider } from 'react-query';
import LoadingCircle from "../LoadingCircle";
import SnackMessage from "../SnackMessage";
import { SnackMessageType } from "../../types/entityType";

const App = () => {
  const queryClient = new QueryClient();
  const { authenticated, refetch } = useIsAuthenticated()!;
  const [snackMessage, setSnackMessage] = useState<SnackMessageType | null>(null);

  if (authenticated === null) {
    return <LoadingCircle />
  }

  return (
    <QueryClientProvider client={queryClient}>
      <div className="app">
        <BrowserRouter>
          { authenticated ?
            (
              <React.Fragment>
                <Navbar refetch={() => refetch({})} setSnack={setSnackMessage} />

                { snackMessage ? 
                  <SnackMessage 
                    message={snackMessage}
                    closeAction={() => setSnackMessage(null)} 
                  /> : 
                  null
                }

                <div className="content">
                  <Routes>
                    <Route path={clientPath.LOGIN} element={<></>} />
                    <Route path={clientPath.REGISTER} element={<></>} />
                  </Routes>
                  </div>
              </React.Fragment>
            ) : (
              <NoAuthPage refetch={() => refetch({})} setSnack={setSnackMessage}/>
            )
          } 
        </BrowserRouter>
      </div>
    </QueryClientProvider>
  )
};

export default App;