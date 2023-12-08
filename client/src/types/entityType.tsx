/**
 * User type.
 */
export default interface UserType {
  email: string,
  username: string
  roles: string[]
}

/**
 * Snack message type.
 */
export type SnackMessageType = {
  type: string,
  fullTop?: boolean,
  message: string
}