import React, { useState } from "react";

const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const login = () => {
    // TODO
    console.log(username);
    console.log(password);
  }

  return (
    <div className="login-form flex">
      <label for="username">Pseudonyme</label>
      <input type="text" name="username" value={username} onChange={(e) => setUsername(e.target.value)} />

      <label for="password">Mot de passe</label>
      <input type="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      
      <button type="submit" onClick={login}>Se connecter</button>
    </div>
  )
}

export default LoginForm;