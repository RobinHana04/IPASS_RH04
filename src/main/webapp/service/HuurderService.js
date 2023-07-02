import { baseURL } from '../view/backendserverconfig.js';

export default class HuurderService {
    static getBoekingenVanHuurder(huurder) {
        const url = `${baseURL}/restservices/renter/${huurder.gebruikersnaam}`;
        return fetch(url)
            .then(response => {
                if(!response.ok) {
                    throw new Error(response.status);
                } else {
                    return response.json();
                }
            });
    }

    }
