import React, { useState } from "react"
import useGetUser from "../../hooks/useGetUser"
import { isAuthenticated } from "../../utils/user"
import { useNavigate } from "react-router-dom"
import clientPath from "../../utils/clientPath"
import LoadingCircle from "../LoadingCircle"

import '../../styles/Profile.css'
import { EditUserType } from "../../types/queryType"

const Profile = () => {
  const { user } = useGetUser()
  const [email, setEmail] = useState(user?.email);
  const [username, setUsername] = useState(user?.username);
  const [currentPassword, setCurrentPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');
  const [pwdLengthValid, setPwdLengthValid] = useState(false);
  const [pwdDigitValid, setPwdDigitValid] = useState(false);
  const [pwdCharValid, setPwdCharValid] = useState(false);

  const navigate = useNavigate()

  if (!user) {
    return <LoadingCircle />
  }

  if (!isAuthenticated(user)) {
    navigate(clientPath.HOME)
  }

  const resetFields = () => {
    setEmail(user.email);
    setUsername(user.username)
    setCurrentPassword('')
    setNewPassword('')
  }

  const editProfile = (e : React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    // Voir pour intégrer le current password / le new etc
    const body: EditUserType = {
      username,
    }

    if (email !== user.email) {
      body.email = email
    }
    // TODO avec les mutations
  }

  const updateNewPassword = (newPass: string) => {
    setNewPassword(newPass)
    setPwdLengthValid(newPass.length >= 8)
    setPwdCharValid(/[a-z]/i.test(newPass))
    setPwdDigitValid(/[0-9]/.test(newPass))
  }

  return (
    <div className="profile flex">
      <div className="content flex">
        <h1>Votre profil</h1>
        <form className="profile-form flex" onSubmit={e => editProfile(e)}>
          <div className="field">
            <label htmlFor="email">Email</label>
            <input type="email" name="email" value={email ?? user.email} onChange={e => setEmail(e.target.value)} required />
          </div>

          <div className="field">
            <label htmlFor="username">Pseudonyme</label>
            <input type="text" name="username" value={username ?? user.username} onChange={e => setUsername(e.target.value)} required />
          </div>

          <div className="flex passwords-container">
            <div className="flex passwords">
              <div className="field">
                <label htmlFor="currentPassword">Mot de passe actuel<span className="red">*</span></label>
                <input type="password" name="currentPassword" value={currentPassword} onChange={e => setCurrentPassword(e.target.value)} />
              </div>

              <div className="field">
                <label htmlFor="newPassword">Nouveau mot de passe</label>
                <input type="password" name="newPassword" value={newPassword} onChange={e => updateNewPassword(e.target.value)} />
              </div>
            </div>

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


          </div>

          <div className="buttons flex">
            <button className="cancel" onClick={resetFields}>Annuler les modifications</button>
            <button className="submit" type="submit" disabled={currentPassword === ''}>Enregistrer</button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default Profile