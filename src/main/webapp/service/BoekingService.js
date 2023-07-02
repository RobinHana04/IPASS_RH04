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

    static getCurrentHuurder() {
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

                // Check if the username corresponds to a Huurder
                return fetch(`${baseURL}/restservices/renter/search/${usernameValue}`)
                    .then(response => {
                        if (response.ok) {
                            // Huurder exists, retrieve the Huurder object
                            return response.json();
                        } else if (response.status === 404) {
                            return fetch(`${baseURL}/restservices/renter`, {
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
                    .then(huurder => {
                        return { gebruikersnaam: usernameValue, huurder };
                    });
            });
    }


    static addBoeking(booking) {
        const url = `${baseURL}/restservices/booking/`;
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
            },
            body: JSON.stringify(booking),
        };

            return fetch(url, options)
                .then((response) => {
                    if (!response.ok) {
                        throw new Error(response.status);
                    }
                    return response.json();
                });
        };


    static deleteBoeking(key) {
        const url = `${baseURL}/restservices/booking/${key}`;
        const options = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json;charset=utf-8',
            },
            body: JSON.stringify(key),
        };

        return fetch(url, options)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`Delete request failed with status: ${response.status}`);
                }
                return response.json();
            })
            .catch((error) => {
                throw error;
            });
    }
}