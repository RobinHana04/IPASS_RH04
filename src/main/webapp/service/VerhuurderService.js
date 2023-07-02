import { baseURL } from '../view/backendserverconfig.js';

export default class VerhuurderService {

    static getAllHomesOfRentee(key) {
        const url = `${baseURL}/restservices/rentee/allHomes/${key}`;
        return fetch(url)
            .then(response => {
                if(!response.ok) {
                    throw new Error(response.status);
                } else {
                    return response.json();
                }
            });
    }

    static getCurrentVerhuurder() {
                const url = `${baseURL}/restservices/username/`;
                return fetch(url)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(response.status);
                        } else {
                            return response.json();
                        }
                    })
                    .then(username => {
                        const usernameValue = username[0].username; // Extract the username value
                        return fetch(`${baseURL}/restservices/rentee/search/${usernameValue}`)
                            .then(response => {
                                if (response.ok) {
                                    // verhuurder exists, retrieve the Huurder object
                                    return response.json();
                                } else if (response.status === 404) {
                                    return fetch(`${baseURL}/restservices/rentee`, {
                                        method: 'POST',
                                        headers: {
                                            'Content-Type': 'application/json;charset=utf-8',
                                        },
                                        body: JSON.stringify({ username: usernameValue }),
                                    })
                                        .then(response => {
                                            if (response.ok) {
                                                return response.json();
                                            } else {
                                                throw new Error(response.status);
                                            }
                                        });
                                } else {
                                    throw new Error(response.status);
                                }
                            })
                            .then(verhuurder => {
                                return { gebruikersnaam: usernameValue, verhuurder };
                            });
                    });
    }

}