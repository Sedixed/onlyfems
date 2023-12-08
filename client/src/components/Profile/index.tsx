import React from "react"
import useGetUser from "../../hooks/useGetUser"
import { isAuthenticated } from "../../utils/user"
import { useNavigate } from "react-router-dom"
import clientPath from "../../utils/clientPath"
import LoadingCircle from "../LoadingCircle"

const Profile = () => {
  const { user } = useGetUser()
  const navigate = useNavigate()

  if (!user) {
    return <LoadingCircle />
  }

  if (!isAuthenticated(user)) {
    navigate(clientPath.HOME)
  }
  return (
    <div className="profile">
      PROFILE
    </div>
  )
}

export default Profile