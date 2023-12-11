import { LoginType, NewImageType, RegisterType } from "../types/queryType";
import apiRoute from "../utils/apiRoute";
import api from "./api";

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

export const registerMutation = async (registerPayload: RegisterType) => {
  const res = await api.post(
    apiRoute.AUTH_REGISTER,
    registerPayload,
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