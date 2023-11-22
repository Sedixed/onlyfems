/**
 * Login type.
 */
export interface LoginType {
  username: string,
  password: string
}

/**
 * Register type.
 */
export interface RegisterType {
  username: string,
  password: string,
  roles: string[]
}