import { useEffect, useState } from "react";
import api from "../apis/api";
import UserType from "../types/entityType";

/**
 * Used to get the current user.
 */
export default function useGetUser() {
  const [user, setUser] = useState<UserType | null>(null);
  const [shouldRefetch, refetch] = useState<{}>({});

  const getUser = async () => {
    const { data } = await api.get(
      '/authentication/user',
      { withCredentials: true }
    );
    return data as UserType;
  }

  useEffect(() => {
    getUser().then(
      user => setUser(user)
    );
  }, [shouldRefetch])

  return {
    user: user,
    refetch: refetch
  };
}