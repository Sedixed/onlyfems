import React, { useState } from "react";

import '../../styles/NoAuthPage.css';
import LoginForm from "./LoginForm";
import RegisterForm from "./RegisterForm";
import LoadingCircle from "../LoadingCircle";

const NoAuthPage = () => {
  const [displayLogin, setDisplayLogin] = useState(false);
  const [displayRegister, setDisplayRegister] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const setLoginTrue = () => {
    setDisplayLogin(true);
    setDisplayRegister(false);
  }

  const setRegisterTrue = () => {
    setDisplayRegister(true);
    setDisplayLogin(false);
  }

  const setLoadingState = (state: boolean): void => {
    setIsLoading(state);
  } 

  return (
    <div className="no-auth-page flex">
      { isLoading ? <LoadingCircle /> : null }
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
        <LoginForm setIsLoading={setLoadingState} /> :
        null
      }
      {
        displayRegister ?
        <RegisterForm setIsLoading={setLoadingState} /> :
        null
      }
    </div>
  );
};

export default NoAuthPage;