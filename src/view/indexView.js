import VakantiehuisService from "../service/VakantiehuisService.js";

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

    VakantiehuisService.getHuis(homeKey)
        .then(home => {
            titleElement.textContent = home.name;

            dialog.showModal();
        })
        .catch(error => {
            console.error("Error fetching the homes" + error);
        })
}

function closeDialog() {
    const dialog = document.querySelector("#boekingDialog");
    const cancelButton = document.querySelector("#closeDialogButton");

    cancelButton.addEventListener("click", function(event) {
        event.preventDefault();
        dialog.close();
    });
}

function render() {
    const allhousesElement = document.querySelector(".homes");
    return VakantiehuisService.getHuizen()
        .then(houses => {
            console.log(houses);
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
const dialog = document.querySelector("#boekingDialog #closeDialogButton");
document.addEventListener(dialog, closeDialog);

document.addEventListener('DOMContentLoaded', () => {
    window.onload = render();

});

