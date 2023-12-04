import React, { useState } from "react";
import { useMutation } from "react-query";
import { RegisterType } from "../../../types/queryType";
import { registerMutation } from "../../../apis/queries";
import { SnackMessageType } from "../../../types/entityType";

type RegisterFormPropsTypes = {
  setIsLoading: (state: boolean) => void,
  refetchLogin: () => void,
  setSnack: (smt: SnackMessageType) => void
};

const RegisterForm: React.FC<RegisterFormPropsTypes> = ({
  setIsLoading,
  refetchLogin,
  setSnack,
}) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLoginSuccess = () => {
    setIsLoading(false);
    refetchLogin();
    setSnack({
      type: 'success',
      message: 'Inscription réalisée avec succès'
    })
  }

  const handleLoginFailure = () => {
    setIsLoading(false);
    console.log('t nul frr');
  }

  const registerMut = useMutation(
    async (payload: RegisterType) => {
      return await registerMutation(payload);
    },
    {
      onSuccess: handleLoginSuccess,
      onError: handleLoginFailure
    }
  )

  const register = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    e.preventDefault();
    registerMut.mutate({ username, password, roles: ['USER_ROLE']} as RegisterType);
    setIsLoading(true);
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