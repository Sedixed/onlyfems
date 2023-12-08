import React, { useEffect, useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import clientPath from "../../../utils/clientPath"

import '../../../styles/Admin/Sidebar.css'

const AdminSidebar = () => {
  const [currentTab, setCurrentTab] = useState(clientPath.ADMIN_IMAGES)
  const navigate = useNavigate();

  useEffect(() => {
    navigate(clientPath.ADMIN_IMAGES)
  }, [])
  
  console.log(currentTab)

  return (
    <div className="admin-sidebar flex">
      <Link 
        className={`admin-link ${currentTab === clientPath.ADMIN_IMAGES && 'current'}`}
        to={clientPath.ADMIN_IMAGES} 
        onClick={() => setCurrentTab(clientPath.ADMIN_IMAGES)}
      >
        Images
      </Link>
      <Link 
        className={`admin-link ${currentTab === clientPath.ADMIN_USERS && 'current'}`}
        to={clientPath.ADMIN_USERS} 
        onClick={() => setCurrentTab(clientPath.ADMIN_USERS)}
      >
        Utilisateurs
      </Link>
      <Link 
        className={`admin-link ${currentTab === clientPath.ADMIN_DOWNLOAD && 'current'}`}
        to={clientPath.ADMIN_DOWNLOAD} 
        onClick={() => setCurrentTab(clientPath.ADMIN_DOWNLOAD)}
      >
        Téléchargements
      </Link>
    </div>
  )
}

export default AdminSidebar