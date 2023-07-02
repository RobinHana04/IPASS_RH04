import Vakantiehuis from "../model/Vakantiehuis.js";
import VakantiehuisService from '../service/VakantiehuisService.js';
import VerhuurderService from "../service/VerhuurderService.js";

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
    throw error;
}

    async function extractHouseFromForm() {
        const formElement = document.querySelector('form');

        const naamInput = formElement.querySelector('input[name="naam"]');
        let imageInput = formElement.querySelector('input[name="foto"]');
        const woonInput = formElement.querySelector('input[name="woonoppervlakte"]');
        const adresInput = formElement.querySelector('input[name="adres"]');
        const bedragInput = formElement.querySelector('input[name="status"]');
        const filename = imageInput.value.replace(/^.*\\/, "");
        const Currentverhuurder = await VerhuurderService.getCurrentVerhuurder();

        const v = new Vakantiehuis({
            adres: adresInput.value,
            woonOppervlakte: woonInput.value,
            status: parseInt(bedragInput.value),
            naam: naamInput.value,
            image: filename,
            verhuurder: Currentverhuurder.verhuurder
        });
        console.log(JSON.stringify(v))
        return v;
    }

    async function formSubmit(event) {
        event.preventDefault(); // Prevent the default form submission behavior

        const houseData = await extractHouseFromForm();
        const house = new Vakantiehuis(houseData);
        VakantiehuisService.addHuis(house)
            .then(() => {
                window.location.href = '../page/index.html'; // Redirect to server URL on success
            })
            .catch(error => {
                console.log('Error:', error);
                showError(error);
            });
    }

    form.addEventListener('submit', formSubmit);

