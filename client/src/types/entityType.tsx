/**
 * User type.
 */
export default interface UserType {
  id: number,
  email: string,
  username: string
  roles: string[]
}
/**
 * Snack message type.
 */
export type SnackMessageType = {
  type: 'success' | 'info' | 'warning' | 'error',
  fullTop?: boolean,
  message: string
}

export type ImageType = {
  id: number,
  name: string,
  // TODO remettre fileName au lieu de name dans des entités
  fileName: string,
  description: string,
  public: boolean, 
  base64: string
}