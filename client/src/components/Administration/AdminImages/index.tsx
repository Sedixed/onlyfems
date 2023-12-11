import React, { useState } from "react"

import '../../../styles/Admin/Images.css'
import NewImageModal from "./NewImageModal";
import { useQuery } from "react-query";
import { ImageType, SnackMessageType } from "../../../types/entityType";
import { allImagesQuery, deleteImageQuery } from "../../../apis/queries";
import LoadingCircle from "../../LoadingCircle";
import ImageCard from "./ImageCard";

type AdminImagesPropsType = {
  setSnack: (snackMessage: SnackMessageType) => void
}

const AdminImages: React.FC<AdminImagesPropsType> = ({
  setSnack,
}) => {
  const [showNewImageModal, setShowNewImageModal] = useState(false);

  const { data: allImages, refetch: refetchAllImages } = useQuery<ImageType[]>({
    queryKey: ['all-images'],
    queryFn: async () => {
      const { data } = await allImagesQuery()
      return data
    }
  })

  if (!allImages) {
    return <LoadingCircle fullscreen />
  }
  
  const editImage = (image: ImageType) => {
    console.log('TODO : édition')
  }

  const deleteImage = async (image: ImageType) => {
    const res = await deleteImageQuery(image.id);
    refetchAllImages()
    setSnack({
      type: 'success',
      message: 'Image supprimée avec succès !',
      fullTop: false
    })
  }

  const renderedImages = allImages.map(image => 
    <ImageCard 
      key={image.id} 
      image={image} 
      editCallback={() => editImage(image)}
      deleteCallback={() => deleteImage(image)}
    />
  )

  return (
    <div className="admin-images flex">
      {
        showNewImageModal ?
        <NewImageModal closeCallback={() => setShowNewImageModal(false)} /> : 
        null
      }
      <div className="show-modal-btn-container">
        <button 
          className="show-modal-btn"
          onClick={() => setShowNewImageModal(true)}
        >
          Nouvelle image
        </button>
      </div>
      
      {
        renderedImages.length > 0 ?
        (
          <div className="images flex">
            {renderedImages}
          </div>
        ) : (
          <div className="no-images">
            <p>Pas d'images</p>
          </div>
        )
      }

    </div>
  )
}

export default AdminImages