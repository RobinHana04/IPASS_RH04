import Vakantiehuis from "../model/Vakantiehuis.js";
import VakantiehuisService from '../service/VakantiehuisService.js';

const form = document.querySelector('form');
const naamInput = form.querySelector('#naam');

function showError(error) {
    const errorMsg = document.querySelector('.errormsg');
    const NaamInput = document.querySelector('#naam');

    if (error.message === '409') {
        errorMsg.textContent = 'Er bestaat al een huis met deze informatie!';
        NaamInput.classList.add('error');
    } else {
        errorMsg.textContent = 'Oeps, er is iets misgegaan!';
    }

    throw error;
}

function clearError(event) {
    const naamInput = document.querySelector('#naam');
    const errorMsg = document.querySelector('.errormsg');

    naamInput.addEventListener('input', () => {
        errorMsg.textContent = ''; // Clear the error message
    });
}
function extractHouseFromForm() {
    const formElement = document.querySelector('form');

    const naamInput = formElement.querySelector('input[name="naam"]');
    const woonInput = formElement.querySelector('input[name="woonoppervlakte"]');
    const adresInput = formElement.querySelector('input[name="adres"]');
    const bedragInput = formElement.querySelector('input[name="status"]');

    return new Vakantiehuis({
        adres: adresInput.value,
        woonoppervlakte: woonInput.value,
        bedrag: parseInt(bedragInput.value),
        naam: naamInput.value,
    });
}

function formSubmit(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    const houseData = extractHouseFromForm();
    const house = new Vakantiehuis(houseData); // Instantiate a new House instance
    VakantiehuisService.addHuis(house)
        .then(() => {
            window.location.href = '../page/index.html'; // Redirect to server URL on success
        })
        .catch(error => {
            console.log('Error:', error);
            showError(error);
        });
}

naamInput.addEventListener('input', clearError);
form.addEventListener('submit', formSubmit);