import HuurderService from "../service/HuurderService.js";
import BoekingService from "../service/BoekingService.js";

const template = document.querySelector(".container .boekingen")
function showError(error) {
    const errorMsg = document.querySelector('.errormsg');
    errorMsg.textContent = 'Jouw boekingen zijn leeg!';
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
    await render();
    function closeDialog() {
        const dialog = document.querySelector("#annuleerDialog");
        const cancelButton = document.querySelector("#closeDialogButton");

        cancelButton.addEventListener("click", function(event) {
            event.preventDefault();
            dialog.close();
        });
    }

    async function dialogBookingCancellationSubmit(event) {
        event.preventDefault();

        const nameElement = document.querySelector("#annuleerDialog h2");
        const houseName = nameElement.textContent;

        try {
            await BoekingService.deleteBoeking(houseName);
            window.location.href = '../page/index.html';
        } catch (error) {
            showError(error);
        }
    }

    function showDialog(event) {
        const dialog = document.querySelector("#annuleerDialog");
        const houseName = event.target.closest('.boeking').querySelector('h2').textContent;
        const nameElement = dialog.querySelector('h2');
        nameElement.textContent = houseName;
        dialog.showModal();
    }

    const annuleerDialog = document.querySelector('#annuleerDialog');
    const annuleerButton = document.querySelector('.container');
    const submitButton = document.querySelector('#submitBtn');

    if (annuleerButton) {
        annuleerButton.addEventListener('click', showDialog);
        submitButton.addEventListener('click', dialogBookingCancellationSubmit);
        annuleerDialog.addEventListener('click', closeDialog);
    }
})