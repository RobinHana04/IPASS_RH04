import VakantiehuisService from "../service/VakantiehuisService.js";
import Vakantiehuis from "../model/Vakantiehuis.js";

function renderHouse(house) {
    const templateElement = document.querySelector('#home-template');
    const templateContent = templateElement.content;
    const allhousesElement = templateContent.querySelector('.home');
    const articleElement = templateContent.querySelector('#HomeName');

    const nameElement = allhousesElement.querySelector('h2').textContent = house.name || "Geen naam";
    const adresElement = allhousesElement.querySelector('.adres').textContent = house.adres || "Geen adres";
    const oppervlakteElement = allhousesElement.querySelector('.woonoppervlakte').textContent = house.woonOppervlakte || "Geen woonOppervlakte";
    const bedragElement = allhousesElement.querySelector('.bedrag').textContent = house.status || "Geen bedrag";

    articleElement.setAttribute('id', house.name);

    return templateContent;
}

function render() {
    const allhousesElement = document.querySelector(".homes");
    console.log(allhousesElement);
    return VakantiehuisService.getHuizen()
        .then(houses => {
            console.log(houses);
            allhousesElement.innerHTML = '';
            houses.forEach(house => {
                const houseElement = renderHouse(house);
                console.log(houseElement);
                console.log(allhousesElement.appendChild(houseElement));

            });
        })
        .catch(error => {
            console.error('Error:', error);
            throw error;
        });
}

document.addEventListener('DOMContentLoaded', () => {
    window.onload = render();
});
