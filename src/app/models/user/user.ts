export class User {
    id?: number;
    username!: string;
    role!: string;
    email!: string;
    confirmEmail: string | null;
    password?: string;
    confirmPassword: string | null;

    constructor(id: number, username: string, role: string, email: string, confirmEmail: string | null, confirmPassword: string | null) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.email = email;
        this.confirmEmail = confirmEmail;
        this.confirmPassword = confirmPassword;
    }
}
