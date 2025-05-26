import React, { JSX } from "react";

const Dashboard = React.lazy(() => import('./views/dashboard/Dashboard'))
export interface Route {
    path: string;
    name: string;
    element: React.LazyExoticComponent<() => JSX.Element>;
    exact?: boolean;
}

const routes = [
    {
        path: '/',
        exact: true,
        name: 'Home'
    },
    { path: '/dashboard', name: 'Dashboard', element: Dashboard }
]
export default routes