import {showNotification} from "../messaging.js";
import {getUser} from "../auth.js";
// import {BACKEND_HOST, BACKEND_PORT} from "../config.js";

const BASE_URI = `http://${BACKEND_HOST}:${BACKEND_PORT}/api/s3/download`;

export default function Home(props) {
    console.log("The frontend did it. HER FAULT");
    return `
        <header>
            <h1>Home Page</h1>
        </header>
        <main>
            <div>
                <p>
                    This is the home page text.
                </p>    
            </div>
        </main>
    `;
}

export function HomeEvents() {
    // TODO: use an enum for message type
    // const authority = getUserRole();
    const user = getUser();
    if(!user) {
        showNotification("Welcome visitor", "secondary");
    } else {
        showNotification("Welcome " + user.userName, "info");
    }
}