import React, { useState } from "react"
import useGetUser from "../../hooks/useGetUser"
import { isAuthenticated, verboseHighestRole } from "../../utils/user"
import { useNavigate } from "react-router-dom"
import clientPath from "../../utils/clientPath"
import LoadingCircle from "../LoadingCircle"

import '../../styles/Profile.css'

const Profile = () => {
  const { user } = useGetUser()
  const [email, setEmail] = useState(user?.email);
  const [username, setUsername] = useState(user?.username);
  const [currentPassword, setCurrentPassword] = useState('');
  const [newPassword, setNewPassword] = useState('');

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
  }

  return (
    <div className="profile flex">
      <div className="content flex">
        <h1>Votre profil</h1>
        <p className="permissions">Rôle le plus élevé : {verboseHighestRole(user)}</p>
        <form className="profile-form flex" onSubmit={e => editProfile(e)}>
          <div className="field">
            <label htmlFor="email">Email</label>
            <input type="email" name="email" value={email ?? user.email} onChange={e => setEmail(e.target.value)} required />
          </div>

          <div className="field">
            <label htmlFor="username">Pseudonyme</label>
            <input type="text" name="username" value={username ?? user.username} onChange={e => setUsername(e.target.value)} required />
          </div>

          <div className="field first-password-field">
            <label htmlFor="currentPassword">Mot de passe actuel</label>
            <input type="password" name="currentPassword" value={currentPassword} onChange={e => setCurrentPassword(e.target.value)} />
          </div>

          <div className="field">
            <label htmlFor="newPassword">Nouveau mot de passe</label>
            <input type="password" name="newPassword" value={newPassword} onChange={e => setNewPassword(e.target.value)} />
          </div>

          <div className="buttons flex">
            <button className="cancel" onClick={resetFields}>Annuler les modifications</button>
            <button className="submit" type="submit">Enregistrer</button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default Profile