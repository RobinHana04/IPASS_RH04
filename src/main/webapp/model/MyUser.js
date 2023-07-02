export default class MyUser {
    constructor({
                    username = 'Gast',
                    password = '',
                } = {}) {
        this.username = username;
        this.password = password;
    }
}