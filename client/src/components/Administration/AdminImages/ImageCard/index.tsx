import { ImageType } from "../../../../types/entityType"

type ImageCardPropsType = {
  image: ImageType,
  editCallback: () => void,
  deleteCallback: () => void,
}

const ImageCard: React.FC<ImageCardPropsType> = ({
  image,
  editCallback,
  deleteCallback
}) => {
  const extension = image.name.split('.').pop();
  return (
    <div className="image-card flex">
      <div className="image-container flex">
        <img loading="lazy" src={`data:image/${extension};base64, ${image.base64Encoded}`} alt={image.name} />
      </div>
      <p className="image-name">{image.name}</p>
      <div className="overlay flex">
        {
          image.public ?
          null :
          <span className="privacy">Privée</span>
        }
        <div className="actions flex">
          <i className="fa fa-pen" onClick={editCallback}></i>
          <i className="fa fa-trash" onClick={deleteCallback}></i>
        </div>
        <p className="description">{image.description}</p>
      </div>
    </div>
  )
}

export default ImageCard