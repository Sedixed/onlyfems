/**
 * Login type.
 */
export interface LoginType {
  email: string,
  password: string
}

/**
 * New user type.
 */
export interface NewUserType {
  email: string,
  username: string,
  password: string,
  roles: string[]
}

/**
 * New image type
 */
export interface NewImageType {
  description: string,
  privacy: boolean,
  file: string
}