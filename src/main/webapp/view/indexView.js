import VakantiehuisService from "../service/VakantiehuisService.js";
import Boeking from "../model/Boeking.js";
import BoekingService from "../service/BoekingService.js";

function showError(error) {
    const errorMsg = document.querySelector('.errormsg');
    errorMsg.textContent = 'Dit huis is al geboekt!';
    throw error;
}

function renderHouse(house) {
    const templateElement = document.querySelector('#home-template');
    const templateContent = templateElement.content.cloneNode(true);

    const houseElements = templateContent.querySelectorAll('.home');
    const articleElement = templateContent.querySelector('#HomeName');

    houseElements.forEach((element) => {
        const nameElement = element.querySelector('h2');
        nameElement.textContent = house.name;

        const imgElement = element.querySelector('img');
        let imageurl = house.image;
        let filename = imageurl.replace(/^.*\\/,"");
        imgElement.setAttribute('src', filename);

        const adresElement = element.querySelector('.adres');
        adresElement.textContent = 'Adres: ' + house.adres;

        const oppervlakteElement = element.querySelector('.woonoppervlakte');
        oppervlakteElement.textContent = 'WoonOppervlakte (m2): ' + house.woonOppervlakte;

        const bedragElement = element.querySelector('.bedrag');
        bedragElement.textContent = 'Prijs per nacht: €' + house.status;

        articleElement.setAttribute('id', house.name);
        articleElement.addEventListener("click", showDialog);
    });

    const houseContainer = document.createElement('div');
    houseContainer.classList.add('house-container');
    houseElements.forEach((element) => {
        houseContainer.appendChild(element);
    });

    return houseContainer;
}

function showDialog(event) {
    const homeKey = event.currentTarget.id;
    const dialog = document.querySelector("#boekingDialog");
    const titleElement = dialog.querySelector(".hnbd");
    VakantiehuisService.checkIfHuisHasBooking(homeKey)
        .then(booking => {
            if (booking && booking.length > 0) {
                throw new Error("House is already booked");
            } else {
                VakantiehuisService.getHuis(homeKey)
                    .then(home => {
                        titleElement.textContent = home.name;
                        dialog.showModal();
                    })
                    .catch(error => {
                        showError(error);
                    });
            }
        })
        .catch(error => {
            showError(error);
        });
}

    function closeDialog() {
    const dialog = document.querySelector("#boekingDialog");
    const cancelButton = document.querySelector("#closeDialogButton");

    cancelButton.addEventListener("click", function(event) {
        event.preventDefault();
        dialog.close();
    });
}
async function extractBookingFromDialog() {
    const dialogElement = document.querySelector("#boekingDialog");
    const nameElementOfHome = dialogElement.querySelector(".hnbd");
    const datumVanElement = dialogElement.querySelector('input[name="datumVan"]').value;
    const datumTotElement = dialogElement.querySelector('input[name="datumTot"]').value;

    const huisName = nameElementOfHome.textContent.trim();
    try {
        const huis = await VakantiehuisService.getHuis(huisName);
        const currentHuurder = await BoekingService.getCurrentHuurder();

        const datumVan = convertDateToArray(datumVanElement);
        const datumTot = convertDateToArray(datumTotElement);

        // Modify the huis object
        huis.naam = huis.name;
        delete huis.name;

        const b1 = new Boeking({
            datumVan: datumVan,
            datumTot: datumTot,
            vakantiehuis: huis,
            huurder: currentHuurder.huurder.gebruikersnaam,
        });

        return b1;
    } catch (error) {
        throw error;
    }
}

function convertDateToArray(dateString) {
    const date = new Date(dateString);
    return [date.getFullYear(), date.getMonth() + 1, date.getDate()];
}

async function dialogBookingSubmit(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    try {
        const bookingData = await extractBookingFromDialog();
        const booking = new Boeking(bookingData); // Instantiate a new Booking instance
        await BoekingService.addBoeking(booking);
        window.location.href = '../page/mijnBoekingen.html';
    } catch (error) {
        console.error('Error:', error);
        showError(error);
    }
}

function render() {
    const allhousesElement = document.querySelector(".homes");
    return VakantiehuisService.getHuizen()
        .then(houses => {
            allhousesElement.innerHTML = '';
            houses.forEach(house => {
                const houseElement = renderHouse(house);
                allhousesElement.appendChild(houseElement);

            });
        })
        .catch(error => {
            console.error('Error:', error);
            throw error;
        });
}

const dialog = document.querySelector("#boekingDialog")
dialog.addEventListener('button', closeDialog);
dialog.addEventListener('submit', dialogBookingSubmit);

document.addEventListener('DOMContentLoaded', async () => {
    window.onload = await render();

});


