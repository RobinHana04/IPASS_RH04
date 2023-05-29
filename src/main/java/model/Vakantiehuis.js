export default class Vakantiehuis {
    constructor({
                    naam = 'Geen naam',
                    adres = 'Geen adres',
                    woonoppervlakte = "0m2",
                    status = 0,
                } = {}) {
        this.naam = naam;
        this.adres = adres;
        this.woonoppervlakte = woonoppervlakte;
        this.status = status;
    }
}