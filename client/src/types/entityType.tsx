/**
 * User role type.
 */
export default interface RoleType {
  authority: string,
}

/**
 * User type.
 */
export default interface UserType {
  email: string,
  roles: RoleType[]
}

/**
 * Snack message type.
 */
export type SnackMessageType = {
  type: string,
  message: string
}