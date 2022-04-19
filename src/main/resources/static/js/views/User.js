import createView from "../createView.js";

const BASE_URI = 'http://localhost:8081/api/users';

export default function UserIndex(props) {
    console.log(props);
    // language=HTML
    return `
        <header>
            <h1>Your Page</h1>
        </header>
        <main>
            <form id="register-form">
                <label for="username">Username</label>
                <input disabled id="username" name="username" value="${props.users.username}" type="text"/>
                <label for="email">Email</label>
                <input disabled id="email" name="email" type="email" value="${props.users.email}">
                <label for="old-password">Old Password</label>
                <input disabled id="old-password" name="old-password" type="password" value="this is not your real password"/>
                <label for="new-password">New Password</label>
                <input id="new-password" name="new-password" type="password" value="this is not your real password"/>
                <button id="change-password-button" type="button">Change Password</button>
            </form>
        </main>
    `;
}

export function UserEvents() {
    $("#change-password-button").click(function() {
        const id = 1; // $("#add-post-id").val();
        let uriExtra = '/1/updatePassword';
        const oldPassword = $("#old-password").val()
        const newPassword = $("#new-password").val()

        const request = {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json'
            }
        }
        fetch(`${BASE_URI}${uriExtra}?oldPassword=${oldPassword}&newPassword=${newPassword}`, request)
            .then(res => {
                console.log(`${request.method} SUCCESS: ${res.status}`);
            }).catch(error => {
            console.log(`${request.method} ERROR: ${error}`);
        }).finally(() => {
            createView("/users");
        });

    });
}