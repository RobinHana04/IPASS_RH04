import { baseURL } from '../view/backendserverconfig.js';
export default class VakantiehuisService {
    static addHuis(huis) {
        const url = `${baseURL}/restservices/homes`;
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
            },
            body: JSON.stringify(huis),
        };

        return fetch(url, options)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();
            });
    }

    static getHuizen() {
        const url = `${baseURL}/restservices/homes`;
        return fetch(url)
            .then((response) => response.json());
    }

    static getHuis(key) {
        const url = `${baseURL}/restservices/homes/${key}`;
        return fetch(url)
            .then((response) => {
                return response.json();
            });
    }

    static updateHuis(huis) {
        const url = `${baseURL}/restservices/homes/${huis.naam}`;
        const options = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
            },
            body: JSON.stringify(huis),
        }

        return fetch(url, options)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();
            });

    }

    deleteHuis(huis) {
        const url = `${baseURL}/restservices/homes/${huis.naam}`;
        const options = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
            },
        }

        return fetch(url, options)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();
            });
    }

    static checkIfHuisHasBooking(huis) {
        const encodedHuis = encodeURIComponent(huis);
        const url = `${baseURL}/restservices/homes/booking/${encodedHuis}`;
        console.log(url);
        console.log("AA");
        return fetch(url)
            .then(response => {
                console.log("RESPONSE:", response);
                if (!response.ok) {
                    console.log("RESPONSE NOT OK");
                    throw new Error(response.status);
                } else {
                    return response.json();
                }
            })
            .catch(error => {
                console.log("ERROR:", error);
                throw error;
            });
    }






}