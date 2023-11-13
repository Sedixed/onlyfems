import React, { useState } from "react";

import '../../styles/NoAuthPage.css';
import LoginForm from "./LoginForm";
import RegisterForm from "./RegisterForm";

const NoAuthPage = () => {
  const [displayLogin, setDisplayLogin] = useState(false);
  const [displayRegister, setDisplayRegister] = useState(false);

  const setLoginTrue = () => {
    setDisplayLogin(true);
    setDisplayRegister(false);
  }

  const setRegisterTrue = () => {
    setDisplayRegister(true);
    setDisplayLogin(false);
  }

  return (
    <div className="no-auth-page flex">
      <div className="presentation flex">
        <h1 className="title">OnlyFems</h1>
        <p className="abstract">Un portfolio de ZINZIN <span className="u-cant-c-me">(chokbar de bz)</span></p>
        <div className="buttons-group flex">
          <button onClick={setLoginTrue}>Connexion</button>
          <button onClick={setRegisterTrue}>Inscription</button>
        </div>
      </div>
      {
        displayLogin ?
        <LoginForm /> :
        null
      }
      {
        displayRegister ?
        <RegisterForm /> :
        null
      }
    </div>
  );
};

export default NoAuthPage;