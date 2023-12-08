import React from "react";

import '../../styles/Navbar.css';
import { logoutQuery } from "../../apis/queries";
import { SnackMessageType } from "../../types/entityType";
import { Link, useNavigate } from "react-router-dom";
import useGetUser from "../../hooks/useGetUser";
import { isAdmin, isAuthenticated } from "../../utils/user";
import LoadingCircle from "../LoadingCircle";
import clientPath from "../../utils/clientPath";

type NavbarPropsType = {
  setSnack: (smt: SnackMessageType) => void
}

const Navbar: React.FC<NavbarPropsType> = ({
  setSnack
}) => {
  const navigate = useNavigate();
  const { user, refetch } = useGetUser();

  if (!user) {
    return <LoadingCircle />
  }

  const authenticated = isAuthenticated(user)

  console.log(user)
  console.log(isAuthenticated(user))

  const logout = async () => {
    const res = await logoutQuery();
    if (res.status === 200) {
      navigate(clientPath.HOME)
      refetch({});
    } else {
      setSnack({
        message: 'Une erreur est survenue lors de votre déconnexion',
        type: 'error'
      })
    }
  }

  return (
    <div className="navbar flex">
      <p className="title">OnlyFems</p>
      <div className="links flex">
        {
          isAdmin(user) ?
          <Link to={clientPath.ADMIN}>Administration</Link> :
          null
        }
        
        <p>Un lien</p>
        <p>Un autre lien</p>
        <p>Encore un autre lien</p>
      </div>
      

      <div className="btns flex">
        {
          authenticated ?
          (
            <div className="profile btn flex" onClick={() => navigate(clientPath.PROFILE)}>
              Profil
              <i className="fa fa-user"></i>
            </div>
          ) : 
          null
        }
        <div className="action btn" onClick={authenticated ? logout : () => navigate(clientPath.HOME)}>
          <i className={`fa fa-sign-${authenticated ? 'out' : 'in'}`}></i>
        </div>
      </div>
    </div>
  )
};

export default Navbar;