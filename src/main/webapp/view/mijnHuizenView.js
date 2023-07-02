import VerhuurderService from "../service/VerhuurderService.js";
function showError(error) {
    const errorMsg = document.querySelector('.errormsg');
    errorMsg.textContent = 'Je hebt geen vakantiehuizen!';
    throw error;
}

async function render() {
    const allhousesElement = document.querySelector('.mijnHuizen');
    const currentVerhuurder = await VerhuurderService.getCurrentVerhuurder();
    return VerhuurderService.getAllHomesOfRentee(currentVerhuurder.gebruikersnaam)
        .then(houses => {
            allhousesElement.innerHTML = '';
            houses.forEach(house => {
                const houseElement = renderHouse(house);
                allhousesElement.appendChild(houseElement);
            });
        })
        .catch(error => {
            showError(error);
        });
}

function renderHouse(house) {
    const templateElement = document.querySelector('#huis-template');
    const templateContent = templateElement.content.cloneNode(true);

    const houseElements = templateContent.querySelectorAll('.huis');
    const articleElement = templateContent.querySelector('.hn'); // Updated selector

    houseElements.forEach((element) => {
        const nameElement = element.querySelector('h2');
        nameElement.textContent = house.name;

        const adresElement = element.querySelector('.adres');
        adresElement.textContent = 'Adres: ' + house.adres;

        const oppervlakteElement = element.querySelector('.woonoppervlakte');
        oppervlakteElement.textContent = 'WoonOppervlakte (m2): ' + house.woonOppervlakte;

        const bedragElement = element.querySelector('.bedrag');
        bedragElement.textContent = 'Prijs per nacht: €' + house.status;

        articleElement.setAttribute('id', house.name);
    });

    const houseContainer = document.createElement('div');
    houseContainer.classList.add('house-container');
    houseElements.forEach((element) => {
        houseContainer.appendChild(element);
    });

    return houseContainer;
}
document.addEventListener('DOMContentLoaded', async () => {
    window.onload = await render();
});