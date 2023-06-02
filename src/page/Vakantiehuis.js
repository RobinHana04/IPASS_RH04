export default class Vakantiehuis {
    constructor({
                    naam = 'Geen naam',
                    adres = 'Geen adres',
                    woonOppervlakte = "0m2",
                    status = 0,
                } = {}) {
        this.adres = adres;
        this.woonOppervlakte = woonOppervlakte;
        this.status = status;
        this.naam = naam;
    }
}