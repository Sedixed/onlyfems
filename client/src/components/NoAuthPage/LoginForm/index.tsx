import React, { useState } from "react";
import { useMutation } from "react-query";
import LoginType from "../../../types/queryType";
import { loginMutation } from "../../../apis/queries";
import { AxiosError, AxiosResponse } from "axios";

const LoginForm = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const loginMut = useMutation(
    async (payload: LoginType) => {
      return await loginMutation(payload);
    },
    {
      onSuccess: () => console.log('t login frr'),
      onError: () => console.log('nan t nul')
    }
  )

  const login = () => {
    // TODO
    loginMut.mutate({username, password} as LoginType);
    console.log(loginMut.data)
    console.log(username);
    console.log(password);
  }

  return (
    <div className="login-form flex">
      <p className="form-title">Connexion</p>
      <label htmlFor="username">Pseudonyme</label>
      <input type="text" name="username" value={username} onChange={(e) => setUsername(e.target.value)} />

      <label htmlFor="password">Mot de passe</label>
      <input type="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />
      
      <button type="submit" onClick={login}>Se connecter</button>
    </div>
  )
}

export default LoginForm;