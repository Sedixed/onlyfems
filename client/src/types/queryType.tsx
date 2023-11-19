/**
 * Login type.
 */
export default interface LoginType {
  username: string,
  password: string
}

/**
 * Register type.
 */
export default interface RegisterType {
  username: string,
  password: string,
  roles: string[]
}