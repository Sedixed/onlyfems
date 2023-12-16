import { ChangeEvent, FormEvent, useRef, useState } from "react";
import { useMutation } from "react-query";
import { EditImageType, NewImageType } from "../../../../types/queryType";
import { editImageMutation, newImageMutation } from "../../../../apis/queries";
import { toBase64 } from "../../../../utils/file";
import { ImageType } from "../../../../types/entityType";

type EditImageModalPropsType = {
  image: ImageType,
  closeCallback: () => void
}

const EditImageModal: React.FC<EditImageModalPropsType> = ({
  image,
  closeCallback,
}) => {
  const [newImage, setNewImage] = useState<File | null>(null);
  const [newDescription, setNewDescription] = useState(image.description);

  const newImageRef = useRef<HTMLInputElement>(null);
  const privacyRef = useRef<HTMLInputElement>(null);

  const handleNewImage = (e: ChangeEvent<HTMLInputElement>) => {
    if (e.target.files) {
      setNewImage(e.target.files[0])
    }
  }

  const addNewImage = async (e : FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    // TODO : fix quand mathieu aura fini

    const isPrivate = privacyRef.current?.checked as boolean;

    const body: EditImageType = {
      description: newDescription,
      privacy: isPrivate
    }
    
    if (newImage) {
      const base64image =  await toBase64(newImage) as string
      body.file = base64image
    }
    editImageMut.mutate(body)
  }

  const handleEditImageSuccess = () => {
    console.log('TODO : success')
  }

  const handleEditImageFailure = () => {
    console.log('TODO : failure');
  }

  const editImageMut = useMutation(
    async (payload: EditImageType) => {
      return await editImageMutation(image.id, payload)
    },
    {
      onSuccess: handleEditImageSuccess,
      onError: handleEditImageFailure
    }
  )

  return (
    <div className="fullscreen-dimmer flex">
      <div className="edit-image-modal flex">
        <div className="top-bar flex">
          <p>Image {image.name}</p>
          <i className="fa fa-times" onClick={closeCallback}></i>
        </div>

        <form className="edit-image-form flex" onSubmit={e => addNewImage(e)}>
          <div className="field image-input flex">
            <input
              ref={newImageRef}
              className="edit-image-input"
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
            <p className="image-name">{newImage?.name ?? image.name}</p>
          </div>

          <div className="field">
            <label htmlFor="description">Description</label>
            <textarea
              name="description"
              className="edit-image-description"
              value={newDescription}
              rows={10}
              cols={50}
              onChange={e => setNewDescription(e.target.value)}
            ></textarea>
          </div>

          <div className="field flex privacy-field">
            <label htmlFor="privacy">Privée</label>
            <input
              name="privacy"
              className="edit-image-privacy"
              type="checkbox"
              defaultChecked={!image.public}
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
              className="update"
            >
              Enregistrer
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default EditImageModal