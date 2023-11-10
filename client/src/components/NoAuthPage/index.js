import React from "react";

import '../../styles/NoAuthPage.css';

const NoAuthPage = () => {
  return (
    <div className="no-auth-page flex">
      <div className="presentation flex">
        <h1 className="title">OnlyFems</h1>
        <p className="abstract">Un portfolio de ZINZIN (chokbar de bz)</p>
        <div className="buttons-group flex">
          <button>Connexion</button>
          <button>Inscription</button>
        </div>
      </div>
    </div>
  );
};

export default NoAuthPage;