import UserType from "../types/entityType"

export const isAuthenticated = (user: UserType) => !(user.roles.length === 1 && user.roles[0] === 'ROLE_ANONYMOUS')

export const isAdmin = (user: UserType) => (
  isAuthenticated(user) && 
  user.roles.find(role => role === 'ROLE_ADMIN')
)
