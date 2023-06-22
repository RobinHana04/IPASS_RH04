import HuurderService from "../service/HuurderService.js";
import Huurder from "../model/Huurder.js";
import MyUser from "../model/MyUser.js";
import BoekingService from "../service/BoekingService.js";

function showError(error) {
    const errorMsg = document.querySelector('.errormsg');
    errorMsg.textContent = 'Er is iets fout gegaan!';
    throw error;
}

function renderBoeking(boeking) {
    const templateElement = document.querySelector('#boeking-template');
    const templateContent = templateElement.content.cloneNode(true);

    const boekingElements = templateContent.querySelectorAll('.boeking');
    const articleElement = templateContent.querySelector('#BoekingName');

   boekingElements.forEach((element) => {
        const nameElement = element.querySelector('h2');
        nameElement.textContent = boeking.vakantiehuis.name;

        // const imgElement = element.querySelector('img');
        // let imageurl = boeking.vakantiehuis.image;
        // let filename = imageurl.replace(/^.*\\/,"");
        // imgElement.setAttribute('src', filename);

        const adresElement = element.querySelector('.adres');
        adresElement.textContent = 'Adres: ' + boeking.vakantiehuis.adres;

        const oppervlakteElement = element.querySelector('.woonoppervlakte');
        oppervlakteElement.textContent = 'WoonOppervlakte (m2): ' + boeking.vakantiehuis.woonOppervlakte;

        const bedragElement = element.querySelector('.bedrag');
        bedragElement.textContent = 'Prijs per nacht: €' + boeking.vakantiehuis.status;

        const datumVanElement = element.querySelector(".datumVan");
        datumVanElement.textContent = 'Datum van aankomst: ' + boeking.datumVan;

        const datumTotElement = element.querySelector(".datumTot");
        datumTotElement.textContent = 'Datum van vertrek: ' + boeking.datumTot;

        articleElement.setAttribute('id', boeking.vakantiehuis.name);
    });

    const boekingContainer = document.createElement('div');
    boekingContainer.classList.add('boeking-container');
    boekingElements.forEach((element) => {
        boekingContainer.appendChild(element);
    });

    return boekingContainer;
}

function render() {
    const allBoekingenElement = document.querySelector(".boekingen");
    BoekingService.getCurrentHuurder()
        .then(currentHuurder => {
            return HuurderService.getBoekingenVanHuurder(currentHuurder);
        })
        .then(boekingen => {
            allBoekingenElement.innerHTML = '';
            boekingen.forEach(boeking => {
                const boekingElement = renderBoeking(boeking);
                allBoekingenElement.appendChild(boekingElement);
            });
        })
        .catch(error => {
            showError(error);
        });
}

document.addEventListener('DOMContentLoaded', async () => {
    window.onload = await render();
});