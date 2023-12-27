import { ImageType } from "./entityType"

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
 * Edit user type.
 */
export interface EditUserType {
  email?: string,
  username?: string,
  password?: string,
  roles?: string[]
}

/**
 * New image type.
 */
export interface NewImageType {
  description: string,
  privacy: boolean,
  base64: string,
  name: string,
  contentType: string
}

/**
 * Edit image type.
 */
export interface EditImageType {
  description?: string,
  privacy?: boolean,
  base64?: string,
  name?: string,
  contentType?: string,
}

/**
 * Get all images type.
 */
export type AllImagesType = {
  images: ImageType[]
}