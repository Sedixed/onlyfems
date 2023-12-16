import UserType, { ImageType } from "../types/entityType";
import { EditImageType, LoginType, NewImageType, NewUserType } from "../types/queryType";
import apiRoute from "../utils/apiRoute";
import api from "./api";
import temporaryApi from "./temporaryApi";


// ****************************************************************************
// Authentication
// ****************************************************************************

export const loginMutation = async (credentials: LoginType) => {
  const res = await api.post(
    apiRoute.AUTH_LOGIN,
    credentials,
    {
      withCredentials: true
    }
  )
  return res;
}

export const logoutQuery = async () => {
  const res = await api.get(
    apiRoute.AUTH_LOGOUT,
    {
      withCredentials: true
    }
  )
  return res;
}

// ****************************************************************************
// Users
// ****************************************************************************

export const newUserMutation = async (newUserPayload: NewUserType) => {
  const res = await api.post(
    apiRoute.AUTH_REGISTER,
    newUserPayload,
    {
      withCredentials: true
    }
  )
  return res;
}

export const allUsersQuery = async () => {
  const res = await temporaryApi.get<UserType[]>(
    apiRoute.USERS,
    {
      withCredentials: true
    }
  )
  return res;
}

// ****************************************************************************
// Images
// ****************************************************************************

export const newImageMutation = async (newImagePayload: NewImageType) => {
  const res = await api.post(
    apiRoute.IMAGES,
    newImagePayload,
    {
      withCredentials: true
    }
  )
  return res;
}

export const editImageMutation = async (
  imageId: number,
  editImagePayload: EditImageType
) => {
  const res = await api.patch(
    `${apiRoute.IMAGE}/${imageId}`,
    editImagePayload,
    {
      withCredentials: true
    }
  )
  return res;
}

export const allImagesQuery = async () => {
  const res = await api.get<ImageType[]>(
    apiRoute.IMAGES,
    {
      withCredentials: true
    }
  )
  return res;
}

export const deleteImageQuery = async (imageId: number) => {
  const res = await api.delete(
    `${apiRoute.IMAGE}/${imageId}`,
    {
      withCredentials: true
    }
  )
  return res;
}
