import MyUser from "../model/MyUser.js";
import LoginService from '../service/LoginService.js';

const form = document.querySelector('form');
const usernameInput = form.querySelector('#uname');

function showError(error) {
    const errorMsg = document.querySelector('.errormsg');

    if (error.message === "Wrong username/password") {
        errorMsg.textContent = 'Combinatie wachtwoord & username niet correct!';
        usernameInput.classList.add('error');
    } else {
        errorMsg.textContent = 'Oeps, er is iets misgegaan!';
    }

    throw error;
}
    function clearError(event) {
    const errorMsg = document.querySelector('.errormsg');
    errorMsg.textContent = ''; // Clear the error message
}

function extractUserFromForm() {
    const formElement = document.querySelector('form');
    const usernameInput = formElement.querySelector('input[name="username"]');
    const passwordInput = formElement.querySelector('input[name="password"]');

    return new MyUser({
        username: usernameInput.value,
        password: passwordInput.value,
    });
}

function formSubmit(event) {
    event.preventDefault();

    const userData = extractUserFromForm();
    const user = new MyUser(userData);
    LoginService.login(user)
        .then(() => {
            window.location.href = '../page/addHome.html';
        })
        .catch(error => {
            showError(error);
        });
}

form.addEventListener('submit', formSubmit);
