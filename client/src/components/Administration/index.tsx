import React from "react";
import useGetUser from "../../hooks/useGetUser";
import { Route, Routes, useNavigate } from "react-router-dom";
import LoadingCircle from "../LoadingCircle";
import { isAdmin } from "../../utils/user";
import clientPath from "../../utils/clientPath";
import AdminSidebar from "./AdminSidebar";
import AdminUsers from "./AdminUsers";
import AdminImages from "./AdminImages";
import AdminDownload from "./AdminDownload";

import '../../styles/Admin/Index.css'

const Administration = () => {
  const { user } = useGetUser();
  const navigate = useNavigate();

  if (!user) {
    return <LoadingCircle />
  }

  if (!isAdmin(user)) {
    // TODO REMETTRE
    //navigate(clientPath.HOME)
  }

  return (
    <div className="administration flex">
      <AdminSidebar />
      <Routes>
        <Route path={clientPath.ADMIN_USERS} element={<AdminUsers />} />
        <Route path={clientPath.ADMIN_IMAGES} element={<AdminImages />} />
        <Route path={clientPath.ADMIN_DOWNLOAD} element={<AdminDownload />} />
      </Routes>
    </div>
  )
}

export default Administration