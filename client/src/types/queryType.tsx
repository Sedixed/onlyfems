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
  password: string,
  roles: string[]
}