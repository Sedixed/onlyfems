export const isAuthenticated = (user: any) => !(user.roles.length === 1 && user.roles[0].authority === 'ROLE_ANONYMOUS')

export const isAdmin = (user: any) => (
  isAuthenticated(user) && 
  user.roles.find((role: { authority: string[] }) => role.authority.includes('ROLE_ADMIN'))
)
