/**
 * Login type.
 */
export interface LoginType {
  email: string,
  password: string
}

/**
 * Register type.
 */
export interface RegisterType {
  email: string,
  username: string,
  password: string,
  roles: string[]
}