import { baseURL } from '../view/backendserverconfig.js';

export default class BoekingService {
    static getAlleBoekingen() {
        const url = `${baseURL}/restservices/booking/`;
        return fetch(url)
            .then(response => {
                if(!response.ok) {
                    throw new Error(response.status);
                } else {
                    return response.json();
                }
            })
    }

    static addBoeking(booking) {
        //TODO: methode implementeren.
    }
}