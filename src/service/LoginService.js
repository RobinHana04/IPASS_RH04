import { baseURL } from '../view/backendserverconfig.js';

export default class LoginService {
    static login(user) {
        console.log("login function started");
        return fetch(`${baseURL}/restservices/authentication`, {
            method: "POST",
            headers: {
                "Content-Type": 'application/json;charset=utf-8',
            },
            body: JSON.stringify(user)
        })
            .then(function (response) {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Wrong username/password"); // Throw an error object
                }
            })
            .then(myJson => {
                window.sessionStorage.setItem("myJWT", myJson.JWT);
                console.log(JSON.stringify(myJson));
            });
    }
}

