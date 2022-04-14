import createView from "../createView.js";

const POST_URI = "http://localhost:8081/api/posts"

export default function PostIndex(props) {
    // language=HTML
    return `
        <div class="container-fluid">
            <header>
                <h1>Posts Page</h1>
            </header>
            <main>
                <div id="posts-container">
                    ${props.posts.map(post => {
                        return `<h3>${post.title}</h3>
                    <p>${post.content}</p>`;
                    }).join('')}
                </div>

                <div id="add-post-container">
                    <form id="add-post-form">
                        <div class="mb-3">
                            <label for="add-post-title" class="form-label">Title</label>
                            <input type="text" class="form-control" id="add-post-title" placeholder="Enter title">
                        </div>
                        <div class="mb-3">
                            <label for="add-post-content" class="form-label">Content</label>
                            <textarea class="form-control" id="add-post-content" rows="3"
                                      placeholder="Enter content" value=""></textarea>
                        </div>
                        <button id="add-post-button" type="button" class="btn btn-primary mb-3">Save Post</button>
                    </form>
                </div>
            </main>
        </div>
    `;
}

export function PostsEvent() {
    createAddPostListener();
    // TODO: add edit post listener function
    // TODO: add delete post listener function
}

function createAddPostListener() {
    console.log("adding add post listener");
    $("#add-post-button").click(function () {
        const title = $("#add-post-title").val();
        const content = $("#add-post-content").val();
        const newPost = {
            title,
            content
        }
        console.log("Ready to add:");
        console.log(newPost);

        const request = {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(newPost)
        }
        fetch(POST_URI, request)
            .then(res => {
                console.log(res.status);
            }).catch(error => {
                console.log(error);
            }).finally(() => {
                createView("/posts")
        });
    });
}