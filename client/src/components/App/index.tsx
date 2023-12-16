import { useState } from "react";
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
import AdminUsers from "../Administration/AdminUsers";
import AdminImages from "../Administration/AdminImages";
import AdminDownload from "../Administration/AdminDownload";
import Gallery from "../Gallery";

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
                fullTop={snackMessage.fullTop ?? false}
                closeAction={() => setSnackMessage(null)} 
              /> : 
              null
            }

            <div className="content">
              <Routes>
                <Route path={clientPath.GALLERY} element={<Gallery />} />
                <Route path={clientPath.VIP_GALLERY} element={<Gallery vipContent />} />
                <Route path={clientPath.PROFILE} element={<Profile />} />
                <Route path={clientPath.ADMIN} element={<Administration />}>
                  <Route path={clientPath.ADMIN_USERS} element={<AdminUsers setSnack={setSnackMessage} />} />
                  <Route path={clientPath.ADMIN_IMAGES} element={<AdminImages setSnack={setSnackMessage} />} />
                  <Route path={clientPath.ADMIN_DOWNLOAD} element={<AdminDownload setSnack={setSnackMessage} />} />
                </Route>
              </Routes>
            </div>
          </>
        </BrowserRouter>
      </div>
    </QueryClientProvider>
  )
};

export default App;