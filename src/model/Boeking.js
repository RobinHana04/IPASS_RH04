import Vakantiehuis from "./Vakantiehuis.js";
import Huurder from "./Huurder.js";

const date = new Date();
const datePlusOne = new Date(date.setDate(date.getDate() + 1));
export default class Boeking {
    constructor({
                    boekingNr = null,
                    huurder = new Huurder(),
                    datumVan = date,
                    datumTot = datePlusOne,
                    vakantiehuis = new Vakantiehuis(),
                } = {}) {
        this.boekingNr = boekingNr;
        this.huurder = huurder;
        this.datumVan = datumVan;
        this.datumTot = datumTot;
        this.vakantiehuis = vakantiehuis;
    }
}