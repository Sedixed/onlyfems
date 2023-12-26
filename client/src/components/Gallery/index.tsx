import { useNavigate } from "react-router-dom"
import useGetUser from "../../hooks/useGetUser"
import { isVIP } from "../../utils/user"
import LoadingCircle from "../LoadingCircle"
import clientPath from "../../utils/clientPath"

import '../../styles/Gallery.css'
import { ImageType } from "../../types/entityType"
import { useQuery } from "react-query"
import { allImagesQuery } from "../../apis/queries"
import BigImageCard from "./BigImageCard"
import { AllImagesType } from "../../types/queryType"

type GalleryPropsType = {
  vipContent?: boolean
}

const Gallery: React.FC<GalleryPropsType> = ({
  vipContent = false,
}) => {
  const navigate = useNavigate()
  const { user } = useGetUser()

  // TODO adapter selon VIP ou non
  const { data: images, isLoading } = useQuery<ImageType[]>({
    queryKey: ['all-images'],
    queryFn: async () => {
      const { images } = await allImagesQuery()
      return images
    }
  })
  
  if (!user || isLoading) {
    return <LoadingCircle />
  }

  if (vipContent && !isVIP(user)) {
    // TODO
    //navigate(clientPath.GALLERY)
  }

  const renderedImages = images ? 
    images.map(
      (image, index) => <BigImageCard key={image.id} even={index % 2 === 0} image={image} />
    ) : []

  return (
    <div className="gallery-container flex">
      <div className="gallery flex">
        <div className="images flex">
          {renderedImages}
        </div>
      </div>
    </div>
  )
}

export default Gallery