import { useNavigate } from "react-router-dom"
import useGetUser from "../../hooks/useGetUser"
import { isVIP } from "../../utils/user"
import LoadingCircle from "../LoadingCircle"
import clientPath from "../../utils/clientPath"

type GalleryPropsType = {
  vipContent?: boolean
}

const Gallery: React.FC<GalleryPropsType> = ({
  vipContent = false,
}) => {

  const navigate = useNavigate()
  const { user } = useGetUser()
  
  if (!user) {
    return <LoadingCircle />
  }

  if (vipContent && !isVIP(user)) {
    // TODO
    //navigate(clientPath.GALLERY)
  }

  return (
    <div className="gallery flex">
      Gallery
      { vipContent ?
      <p>Pour les VIP !</p> :
      null
      }
    </div>
  )
}

export default Gallery