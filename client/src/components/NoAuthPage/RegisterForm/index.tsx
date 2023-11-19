import React, { useState } from "react";

type RegisterFormPropsTypes = {
  setIsLoading: (state: boolean) => void,
  refetchLogin: () => void
};

const RegisterForm: React.FC<RegisterFormPropsTypes> = ({
  setIsLoading,
  refetchLogin
}) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const register = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();
    // TODO
    console.log(username);
    console.log(password);
    refetchLogin();
  }

  return (
    <div className="register-form-container flex">
      <p className="form-title">Inscription</p>
      <form className="register-form flex">
        <label htmlFor="username">Pseudonyme</label>
        <input type="text" name="username" value={username} onChange={(e) => setUsername(e.target.value)} />

        <label htmlFor="password">Mot de passe</label>
        <input type="text" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />
        
        <button type="submit" onClick={e => register(e)}>S'inscrire</button>
      </form>
    </div>
  )
};

export default RegisterForm;