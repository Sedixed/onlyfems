import React from "react"

import '../../../styles/Admin/Download.css'

const AdminDownload = () => {
  const download = () => {
    // TODO
  }


  return (
    <div className="admin-download flex">
      <button onClick={download} className="download-button flex">
        <i className="fa fa-download"></i>
        Télécharger toutes les images
      </button>
    </div>
  )
}

export default AdminDownload