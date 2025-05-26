import { useLocation } from 'react-router-dom'
import {
    CBreadcrumb, CBreadcrumbItem
} from '@coreui/react'

import routes, { Route } from '../routes'

const AppBreadcrumb = () => {
    const currentLocation = useLocation().pathname

    const getRouteName = (pathname: string, routes: Route[]) => {
        const currentRoute = routes.find((route) => route.path === pathname)
        return currentRoute ? currentRoute.name : false
    }
    const getBreadcrumbs = (location: string) => {
        const breadcrumbs = []
       
    }

    return (
        <></>
    )
}
export default AppBreadcrumb