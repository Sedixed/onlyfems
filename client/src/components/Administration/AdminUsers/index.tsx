import React, { useState } from "react"

import '../../../styles/Admin/Users.css'
import UserType, { SnackMessageType } from "../../../types/entityType"
import { useQuery } from "react-query"
import { allUsersQuery } from "../../../apis/queries"
import LoadingCircle from "../../LoadingCircle"
import { verboseHighestRole } from "../../../utils/user"

type AdminUsersPropsType = {
  setSnack: (snackMessage: SnackMessageType) => void
}

const AdminUsers: React.FC<AdminUsersPropsType> = ({
  setSnack,
}) => {
  const [showNewUserModal, setShowUserImageModal] = useState(false);

  const { data: allUsers, refetch: refetchAllUsers } = useQuery<UserType[]>({
    queryKey: ['all-users'],
    queryFn: async () => {
      const { data } = await allUsersQuery()
      return data
    }
  })

  if (!allUsers) {
    return <LoadingCircle fullscreen />
  }

  console.log(allUsers)

  const editUser = (user: UserType) => {
    console.log('TODO : édition')
  }

  const deleteUser = async (user: UserType) => {
    console.log('TODO : suppression en vérifiant que c pa lui mem');
    //await deleteImageQuery(image.id);
    refetchAllUsers()
    setSnack({
      type: 'success',
      message: 'Utilisateur supprimé avec succès !',
      fullTop: false
    })
  }

  const renderedUsers = allUsers.map(
    user => (
      <tr key={user.id}>
        <td>{user.username}</td>
        <td>{user.email}</td>
        <td>{verboseHighestRole(user)}</td>
      </tr>
    )
  )

  return (
    <div className="admin-users flex">
      {
        showNewUserModal ?
        null : // le modal plus tard TODO
        null
      }

      <div className="show-modal-btn-container">
        <button 
          className="show-modal-btn" 
          onClick={() => setShowUserImageModal(true)}
        >
          Nouvel utilisateur
        </button>
      </div>
      
      <div className="users flex">
        <table>
          <thead>
            <tr>
              <th>Pseudonyme</th>
              <th>Email</th>
              <th>Plus haut rôle</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {renderedUsers}
          </tbody>
        </table> 
      </div>     
    </div>
  )
}

export default AdminUsers