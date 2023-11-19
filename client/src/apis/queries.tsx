import LoginType from "../types/queryType";
import api from "./api";

export const loginMutation = async (credentials: LoginType) => {
  const res = await api.post(
    '/authentication/login',
    credentials,
    {
      withCredentials: true
    }
  )
  return res;
}

export const logoutQuery = async () => {
  const res = await api.get(
    '/authentication/logout',
    {
      withCredentials: true
    }
  )
  return res;
}