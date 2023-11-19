import React from "react";

import '../../styles/Navbar.css';
import { logoutQuery } from "../../apis/queries";
import { SnackMessageType } from "../../types/entityType";

type NavbarPropsType = {
  refetch: () => void,
  setSnack: (smt: SnackMessageType) => void
}

const Navbar: React.FC<NavbarPropsType> = ({
  refetch,
  setSnack
}) => {

  const logout = async () => {
    const res = await logoutQuery();
    console.log(res);
    if (res.status === 200) {
      refetch();
    } else {
      setSnack({
        message: 'Une erreur est survenue lors de votre déconnexion',
        type: 'error'
      })
    }
  }

  return (
    <div className="navbar flex">
      <p>OnlyFems</p>
      <p>Un lien</p>
      <p>Un autre lien</p>
      <p>Encore un autre lien</p>

      <button className="logout-btn" onClick={logout}>Déconnexion</button>
    </div>
  )
};

export default Navbar;