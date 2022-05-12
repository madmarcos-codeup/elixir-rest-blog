import createView from "../createView.js"
import {getHeaders, isLoggedIn} from "../auth.js";
import {BACKEND_HOST, BACKEND_PORT} from "../constants.js";

console.log("backend 2 constant " + BACKEND_URL_2);

const BASE_URI = `http://${BACKEND_HOST}:${BACKEND_PORT}/api/users/create`;

export default function Register(props) {
    // language=HTML
    return `
    <!DOCTYPE html>
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Register</title>
            </head>
            <body>
                <h1>Register</h1>
        
                <form id="register-form">
                    <label for="username">Username</label>
                    <input id="username" name="username" type="text"/>
                    <label for="email">Email</label>
                    <input id="email" name="email" type="email">
                    <label for="password">Password</label>
                    <input id="password" name="password" type="password"/>
                    <button id="register-btn" type="button">Register</button>
                </form>
            </body>
        </html>
`;
}

export function RegisterEvent(){
    console.log("am I logged in ? " + isLoggedIn());

    $("#register-btn").click(function(){

        // make a new user object from the provided fields
        let newUser = {
            username: $("#username").val(),
            email: $("#email").val(),
            password: $("#password").val()
        }

        console.log(newUser);

        // make the request to send to the backend
        let request = {
            method: "POST",
            headers: getHeaders(),
            body: JSON.stringify(newUser)
        }

        fetch(BASE_URI, request)
            .then(response => {
                console.log(response.status);
                createView("/");
            })

    })
}