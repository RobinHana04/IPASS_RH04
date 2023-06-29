// import VakantiehuisService from "../service/VakantiehuisService";
//
// function render() {
//     const allhousesElement = document.querySelector(".homes");
//     return VakantiehuisService.getHuizen()
//         .then(houses => {
//             allhousesElement.innerHTML = '';
//             houses.forEach(house => {
//                 const houseElement = renderHouse(house);
//                 allhousesElement.appendChild(houseElement);
//
//             });
//         })
//         .catch(error => {
//             console.error('Error:', error);
//             throw error;
//         });
// }
//
// function renderHouse(house) {
//     const templateElement = document.querySelector('#home-template');
//     const templateContent = templateElement.content.cloneNode(true);
//
//     const houseElements = templateContent.querySelectorAll('.home');
//     const articleElement = templateContent.querySelector('#HomeName');
//
//     houseElements.forEach((element) => {
//         const nameElement = element.querySelector('h2');
//         nameElement.textContent = house.name;
//
//         const imgElement = element.querySelector('img');
//         let imageurl = house.image;
//         let filename = imageurl.replace(/^.*\\/,"");
//         imgElement.setAttribute('src', filename);
//
//         const adresElement = element.querySelector('.adres');
//         adresElement.textContent = 'Adres: ' + house.adres;
//
//         const oppervlakteElement = element.querySelector('.woonoppervlakte');
//         oppervlakteElement.textContent = 'WoonOppervlakte (m2): ' + house.woonOppervlakte;
//
//         const bedragElement = element.querySelector('.bedrag');
//         bedragElement.textContent = 'Prijs per nacht: €' + house.status;
//
//         articleElement.setAttribute('id', house.name);
//         articleElement.addEventListener("click", showDialog);
//     });
//
//     const houseContainer = document.createElement('div');
//     houseContainer.classList.add('house-container');
//     houseElements.forEach((element) => {
//         houseContainer.appendChild(element);
//     });
//
//     return houseContainer;
// }