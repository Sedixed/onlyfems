import LoginType from "../types/queryType";
import api from "./api";

export const loginMutation = async (credentials: LoginType) => {
  const res = await api.post(
    '/authentication/login',
    credentials
  )
  console.log(res);
}