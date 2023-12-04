import React, { useState } from "react";
import { useMutation } from "react-query";
import LoginType from "../../../types/queryType";
import { loginMutation } from "../../../apis/queries";

type LoginFormPropsTypes = {
  setIsLoading: (state: boolean) => void,
  refetchLogin: () => void
};

const LoginForm: React.FC<LoginFormPropsTypes> = ({ 
  setIsLoading,
  refetchLogin
}) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  
  const handleLoginSuccess = () => {
    setIsLoading(false);
    console.log('t login frr');
    refetchLogin();
  }

  const handleLoginFailure = () => {
    setIsLoading(false);
    console.log('t nul frr');
  }

  const loginMut = useMutation(
    async (payload: LoginType) => {
      return await loginMutation(payload);
    },
    {
      onSuccess: handleLoginSuccess,
      onError: handleLoginFailure
    }
  )

  const login = (e : React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    loginMut.mutate({username, password} as LoginType);
    setIsLoading(true);
  }

  return (
    
    <div className="login-form-container flex">
      <p className="form-title">Connexion</p>
        <form className="login-form flex" action="" onSubmit={e => login(e)}>
          <label htmlFor="username">Pseudonyme</label>
          <input type="text" name="username" value={username} onChange={(e) => setUsername(e.target.value)} />

          <label htmlFor="password">Mot de passe</label>
          <input type="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} />
          
          <button type="submit">Se connecter</button>
        </form>
    </div>
  )
}

export default LoginForm;