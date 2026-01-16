export interface IBreadcrumbItem {
  name: string
  path: string
}
export type Menu = {
  id: string
  name: string
  isDir: boolean
  link?: string
  iconClass?: string
  isOpen?: boolean
  children?: Menu[]
}
