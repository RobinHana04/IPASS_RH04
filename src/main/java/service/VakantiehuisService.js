import {baseURL} from '/view/backendserverconfig';
import Vakantiehuis from "/model/Vakantiehuis";

export default class VakantiehuisService {
    addHuis(huis) {
        const url = `${baseURL}/restservices/homes/`;
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
            },
            body: JSON.stringify(huis), // Use 'huis' instead of 'trip'
        };

        return fetch(url, options)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();
            });
    }

    getHuizen() {
        const url = `${baseURL}/restservices/homes/`;
        return fetch(url)
            .then((response) => response.json());
    }

    getHuis(key) {
        const url = `${baseURL}/restservices/homes/${key}`;
        return fetch(url)
            .then((response) => {
                return response.json();
            });
    }

    updateHuis(huis) {
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

}