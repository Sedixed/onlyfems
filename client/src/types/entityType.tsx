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

export type ImageType = {
  id: number,
  name: string,
  description: string,
  public: boolean, 
  base64Encoded: string
}