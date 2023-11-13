import React, { useState } from "react";

const RegisterForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const register = () => {
    // TODO
    console.log(username);
    console.log(password);
  }

  return (
    <div className="register-form flex">
      <p className="form-title">Inscription</p>
      <label htmlFor="username">Pseudonyme</label>
      <input type="text" name="username" value={username} onChange={(e) => setUsername(e.target.value)} />

      <label htmlFor="password">Mot de passe</label>
      <input type="text" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      
      <button type="submit" onClick={register}>S'inscrire</button>
    </div>
  )
};

export default RegisterForm;