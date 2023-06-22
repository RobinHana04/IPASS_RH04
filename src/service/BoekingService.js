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
                console.log(username); // Check the value of username
                const usernameValue = username[0].username; // Extract the username value
                console.log(usernameValue); // Verify the extracted username value

                // Check if the username corresponds to a Huurder
                return fetch(`${baseURL}/restservices/renter/search/${usernameValue}`)
                    .then(response => {
                        console.log(response); // Check the response from the endpoint
                        if (response.ok) {
                            // Huurder exists, retrieve the Huurder object
                            return response.json();
                        } else if (response.status === 404) {
                            // Huurder does not exist, create a new Huurder object
                            return fetch(`${baseURL}/restservices/renter`, {
                                method: 'POST',
                                headers: {
                                    'Content-Type': 'application/json;charset=utf-8',
                                },
                                body: JSON.stringify({ username: usernameValue }),
                            })
                                .then(response => {
                                    console.log(response); // Check the response from the endpoint
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
                        console.log(huurder); // Check the retrieved huurder object
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
}