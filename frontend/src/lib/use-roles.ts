export type UserRole = 'standard' | 'admin';

export interface User {
    id: string;
    name: string;
    email: string;
    role: UserRole;
}

export const hasPermission = (user: User, permission: string): boolean => {
    switch (user.role) {
        case 'admin':
            return true;
        case 'standard':
            return ['view_dashboard', 'view_investments', 'view_community', 'participate_forum'].includes(permission);
        default:
            return false;
    }
};

