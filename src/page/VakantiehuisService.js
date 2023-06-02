export default class VakantiehuisService {
    static addHuis(huis) {
        const url = `http://localhost:8080/restservices/homes`;
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

    getHuizen() {
        const url = `http://localhost:8080/restservices/homes`;
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
        const url = `http://localhost:8080/restservices/homes/${huis.naam}`;
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
        const url = `http://localhost:8080/restservices/homes/${huis.naam}`;
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