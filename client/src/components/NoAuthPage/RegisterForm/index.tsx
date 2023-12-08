import React, { useState } from "react";
import { useMutation } from "react-query";
import { RegisterType } from "../../../types/queryType";
import { registerMutation } from "../../../apis/queries";
import { SnackMessageType } from "../../../types/entityType";
import { useNavigate } from "react-router-dom";
import clientPath from "../../../utils/clientPath";

type RegisterFormPropsTypes = {
  setIsLoading: (state: boolean) => void,
  setSnack: (smt: SnackMessageType) => void
};

const RegisterForm: React.FC<RegisterFormPropsTypes> = ({
  setIsLoading,
  setSnack,
}) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [pwdLengthValid, setPwdLengthValid] = useState(false);
  const [pwdDigitValid, setPwdDigitValid] = useState(false);
  const [pwdCharValid, setPwdCharValid] = useState(false);

  const navigate = useNavigate();

  const handleRegisterSuccess = () => {
    setIsLoading(false);
    navigate(clientPath.TEST)
    setSnack({
      type: 'success',
      message: 'Inscription réalisée avec succès'
    })
  }

  const handleRegisterFailure = () => {
    setIsLoading(false);
  }

  const registerMut = useMutation(
    async (payload: RegisterType) => {
      return await registerMutation(payload);
    },
    {
      onSuccess: handleRegisterSuccess,
      onError: handleRegisterFailure
    }
  )

  const register = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    registerMut.mutate({ email, password, roles: ['ROLE_USER']} as RegisterType);
    setIsLoading(true);
  }

  const updatePassword = (newPassword: string) => {
    setPassword(newPassword)
    setPwdLengthValid(newPassword.length >= 8)
    setPwdCharValid(/[a-z]/i.test(newPassword))
    setPwdDigitValid(/[0-9]/.test(newPassword))
  }

  return (
    <div className="register-form-container flex">
      <p className="form-title">Inscription</p>
      <form className="register-form flex" onSubmit={e => register(e)}>
        <label htmlFor="email">Email</label>
        <input type="email" name="email" value={email} onChange={(e) => setEmail(e.target.value)} required />

        <label htmlFor="password">Mot de passe</label>
        <input type="text" name="password" value={password} onChange={(e) => updatePassword(e.target.value)} required />

        <div className="criterias flex">
          <div className="criteria flex">
            <i className={`fa fa-${pwdLengthValid ? 'check' : 'xmark'}`}></i>
            <p>8 caractères minimum</p>
          </div>

          <div className="criteria flex">
            <i className={`fa fa-${pwdDigitValid ? 'check' : 'xmark'}`}></i>
            <p>Au moins un chiffre</p>
          </div>

          <div className="criteria flex">
            <i className={`fa fa-${pwdCharValid ? 'check' : 'xmark'}`}></i>
            <p>Au moins une lettre</p>
          </div>
        </div>
        
        <button 
          type="submit"
          disabled={!(
            pwdLengthValid &&
            pwdCharValid &&
            pwdDigitValid
          )}
        >
          S'inscrire
        </button>
      </form>
    </div>
  )
};

export default RegisterForm;