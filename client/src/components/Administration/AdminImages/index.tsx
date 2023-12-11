import React, { useState } from "react"

import '../../../styles/Admin/Images.css'
import NewImageModal from "./NewImageModal";

const AdminImages = () => {
  const [showNewImageModal, setShowNewImageModal] = useState(false);


  return (
    <div className="admin-images flex">
      {
        showNewImageModal ?
        <NewImageModal closeCallback={() => setShowNewImageModal(false)} /> : 
        null
      }
      <button onClick={() => setShowNewImageModal(true)}>cc</button>
    </div>
  )
}

export default AdminImages