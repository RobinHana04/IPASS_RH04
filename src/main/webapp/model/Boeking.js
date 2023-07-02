import Vakantiehuis from "./Vakantiehuis.js";
import Huurder from "./Huurder.js";

const date = new Date();
const datePlusOne = new Date(date.setDate(date.getDate() + 1));
export default class Boeking {
    constructor({
                    huurder = new Huurder(),
                    datumVan = date,
                    datumTot = datePlusOne,
                    vakantiehuis = new Vakantiehuis(),
                } = {}) {
        this.huurder = huurder;
        this.datumVan = datumVan;
        this.datumTot = datumTot;
        this.vakantiehuis = vakantiehuis;
    }
}