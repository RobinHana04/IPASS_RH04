import VakantiehuisService from "../service/VakantiehuisService.js";

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
        console.log(oppervlakteElement);
        oppervlakteElement.textContent = 'WoonOppervlakte (m2): ' + house.woonOppervlakte;

        const bedragElement = element.querySelector('.bedrag');
        console.log(bedragElement);
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
            if (booking) {
                showError(new Error("House is already booked"));
            } else {
                VakantiehuisService.getHuis(homeKey)
                    .then(home => {
                        titleElement.textContent = home.name;
                        dialog.showModal();
                    })
                    .catch(error => {
                        showError(new Error("Error fetching the home: " + error));
                    });
            }
        })
        .catch(error => {
            showError(new Error("Error checking bookings: " + error));
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
// function extractBookingFromDialog() {
//     const dialogElement = document.querySelector("#boekingDialog");
//     const nameElementOfHome = dialogElement.querySelector(".hnbd");
//     const datumVanElement = dialogElement.querySelector('input[name="datumVan"]');
//     const datumTotElement = dialogElement.querySelector('input[name="datumTot"]');
//
//     return new Promise((resolve, reject) => {
//         VakantiehuisService.getHuis(nameElementOfHome)
//             .then(huis => {
//                 const booking = new Boeking({
//                     transactieNr: null,
//                     datumVan: datumVanElement.value,
//                     datumTot: datumTotElement.value,
//                     vakantiehuis: huis,
//                     huurder: null,
//                 });
//                 resolve(booking);
//             })
//             .catch(error => {
//                 console.error('Error:', error);
//                 reject(error);
//             });
//     });
// }


// async function dialogBookingSubmit(event) {
//     event.preventDefault(); // Prevent the default form submission behavior
//
//     try {
//         const bookingData = await extractBookingFromDialog();
//         const booking = new Boeking(bookingData); // Instantiate a new Booking instance
//         await BoekingService.addBoeking(booking);
//         window.location.href = '../page/mijnBoekingen.html'; // Redirect to server URL on success
//     } catch (error) {
//         console.error('Error:', error);
//         showError(error);
//     }
// }

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
const dialogCloseBtn = document.querySelector("#boekingDialog #closeDialogButton");
document.addEventListener(dialogCloseBtn, closeDialog);

document.addEventListener('DOMContentLoaded', async () => {
    window.onload = await render();

});

