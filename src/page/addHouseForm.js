import Vakantiehuis from "./Vakantiehuis.js";
import VakantiehuisService from './VakantiehuisService.js';
console.log(Vakantiehuis);
const form = document.querySelector('form');

function showError(error) {
    const errorMsg = document.querySelector('.errormsg');
    const NaamInput = document.querySelector('#naam');

    if (error.message === '409') {
        errorMsg.textContent = 'Er bestaat al een huis met deze informatie!';
        NaamInput.classList.add('error');
    } else {
        errorMsg.textContent = 'Oeps, er is iets misgegaan!';
    }

    // Optionally, you can throw the error again to stop the execution flow
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
    const statusInput = formElement.querySelector('input[name="status"]');

    const vh = new Vakantiehuis({
        adres: woonInput.value,
        woonoppervlakte: adresInput.value,
        status: parseInt(statusInput.value),
        naam: naamInput.value,
    });

    return vh;
}

function formSubmit(event) {
    console.log('FormSubmit called');
    event.preventDefault(); // Prevent the default form submission behavior

    const houseData = extractHouseFromForm();
    const house = new Vakantiehuis(houseData); // Instantiate a new Trip instance
    console.log('HUIS DATA:', JSON.stringify(houseData));
    console.log('HUIS OBJECT:', JSON.stringify(house));
    VakantiehuisService.addHuis(house)
        .then(() => {
            window.location.href = 'http://localhost:8080/restservices/homes/'; // Redirect to index.html on success
        })
        .catch(error => {
            console.log('Error:', error);
            showError(error);
        });
}

const naamInput = form.querySelector('#naam');
naamInput.addEventListener('input', clearError);
form.addEventListener('submit', formSubmit);