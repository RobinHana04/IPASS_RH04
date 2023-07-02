export default class Huurder {
    constructor({
                    gebruikersnaam = 'Gast',
                } = {}) {
        this.gebruikersnaam = gebruikersnaam;
    }
}