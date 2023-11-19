import { useEffect, useState } from "react";
import api from "../apis/api";
import UserType from "../types/entityType";

/**
 * Used to detect if the current user is authenticated or not.
 */
export default function useIsAuthenticated() {
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
    authenticated: user ? !(user.roles.length === 1 && user.roles[0].authority === 'ROLE_ANONYMOUS') : null, 
    refetch: refetch
  };
}