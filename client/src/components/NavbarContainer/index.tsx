import React from "react";
import useGetUser from "../../hooks/useGetUser";
import { Outlet } from "react-router-dom";
import LoadingCircle from "../LoadingCircle";
import Navbar from "../Navbar";

import '../../styles/Admin/Index.css'
import { SnackMessageType } from "../../types/entityType";
import SnackMessage from "../SnackMessage";

type NavbarContainerPropsType = {
  snackMessage: SnackMessageType | null,
  setSnack: (smt: SnackMessageType | null) => void,
}

const NavbarContainer: React.FC<NavbarContainerPropsType> = ({
  snackMessage,
  setSnack,
}) => {
  const { user } = useGetUser();

  if (!user) {
    return <LoadingCircle />
  }

  return (
    <div>
      <Navbar setSnack={setSnack} />

      { snackMessage ? 
        <SnackMessage 
          snackMessage={snackMessage}
          fullTop={snackMessage.fullTop ?? false}
          closeAction={() => setSnack(null)} 
        /> : 
        null
      }

      <div className="content">
        <Outlet/>
      </div>
    </div>
  )
}

export default NavbarContainer