export const HeaderConstant = {

    menus: [
        {
            path: 'dashboard',
            title: 'Dashboard',
            roles: ['ADMIN', 'USER'],
            icon: 'dashboard',
            class: 'fa fa-home'
        },
        {
            path: 'pending',
            title: 'Pending',
            roles: ['ADMIN'],
            icon: 'pending',
            class: 'fa fa-home'
        },
        {
            path: 'approval',
            title: 'Approval',
            roles: ['ADMIN'],
            icon: 'approval',
            class: 'fa fa-home'
        },
        {
            path: 'denied',
            title: 'Denied',
            roles: ['ADMIN'],
            icon: 'denied',
            class: 'fa fa-home'
        },

    ]
}
