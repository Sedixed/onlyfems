import { ChangeEvent, FormEvent, useRef, useState } from "react";

type NewImageModalPropsType = {
  closeCallback: () => void
}

const NewImageModal: React.FC<NewImageModalPropsType> = ({
  closeCallback,
}) => {
  const [image, setImage] = useState<File | null>(null);
  const [description, setDescription] = useState('');
  const [privacy, setPrivacy] = useState(false);

  const newImageRef = useRef<HTMLInputElement>(null);
  const privacyRef = useRef<HTMLInputElement>(null);

  const handleNewImage = (e: ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setImage(e.target.files[0])
    }
  }

  const addNewImage = (e : FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    if (!image) {
      return
    }
    console.log(image)
  }

  return (
    <div className="fullscreen-dimmer flex">
      <div className="new-image-modal flex">
        <div className="top-bar flex">
          <p>Nouvelle image</p>
          <i className="fa fa-times" onClick={closeCallback}></i>
        </div>

        <form className="new-image-form flex" onSubmit={e => addNewImage(e)}>
          <div className="field image-input flex">
            <input
              ref={newImageRef}
              className="new-image-input"
              onChange={e => handleNewImage(e)}
              type="file"
              accept="image/png, image/jpeg, image/jpg, image/svg, image/gif" 
            />
            <button
              className="import-image-button"
              onClick={() => newImageRef.current?.click()}
            >
              Importer une image
            </button>
            {
              image ? 
              <p className="image-name">{image.name}</p> :
              null
            } 
          </div>

          <div className="field">
            <label htmlFor="description">Description</label>
            <textarea
              name="description"
              className="new-image-description"
              value={description}
              rows={10}
              cols={50}
              onChange={e => setDescription(e.target.value)}
            ></textarea>
          </div>

          <div className="field flex privacy-field">
            <label htmlFor="privacy">Privée</label>
            <input
              name="privacy"
              className="new-image-privacy"
              type="checkbox"
              ref={privacyRef}
            ></input>
          </div>

          <div className="buttons flex">
            <button
              className="cancel"
              onClick={closeCallback}
            >
              Annuler
            </button>
            <button
              type="submit"
              className="upload"
              disabled={!image}
            >
              Ajouter
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default NewImageModal