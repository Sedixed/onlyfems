import React from "react"

import '../../../styles/Admin/Users.css'
import { SnackMessageType } from "../../../types/entityType"

type AdminUsersPropsType = {
  setSnack: (snackMessage: SnackMessageType) => void
}

const AdminUsers: React.FC<AdminUsersPropsType> = ({
  setSnack,
}) => {

  return (
    <div className="admin-users flex">
      <button onClick={() => {}}>Créer un utilisateur</button>
      <table>
        <thead>
          <tr>
            <th>Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          
        </tbody>
      </table>
        <p>xd</p>
        <p>xd</p>
        <p>xd</p>

        <p>xd</p>
        <p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p><p>xd</p>
      
    </div>
  )
}

export default AdminUsers