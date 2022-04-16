import createView from "../createView.js";

const BASE_URI = 'http://localhost:8081/api/posts';

export default function PostIndex(props) {
    // language=HTML
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <h3>Posts (make this a sweet table!)</h3>
            <div id="posts-container">
                ${props.posts.map(post => {
                    return `<div>
<h4 id="title-${post.id}">${post.title}</h4>
<p id="content-${post.id}">${post.content}</p>
<span><a href="#" class="edit-post-button" data-id="${post.id}">Edit</a></span>
<span><a href="#" class="delete-post-button" data-id="${post.id}">Delete</a></span>
</div>`;
                }).join('')}
            </div>
            <hr>
            <h3>Add a Post</h3>
            <form id="add-post-form">
                <div class="mb-3">
                    <input disabled type="text" class="form-control" id="add-post-id" value="0">
                </div>
                <div class="mb-3">
                    <label for="add-post-title" class="form-label">Title</label>
                    <input type="text" class="form-control" id="add-post-title" placeholder="Post title">
                </div>
                <label for="add-post-content" class="form-label">Content</label>
                <textarea class="form-control" id="add-post-content" rows="3" placeholder="Post content"></textarea>
                </div>
                <br>
                <button id="clear-post-button" type="submit" class="btn btn-primary mb-3"
                        onclick="document.querySelector('#add-post-id').value = 0; document.querySelector('#add-post-title').value = ''; document.querySelector('#add-post-content').value = '';">
                    Clear
                </button>
                <button id="add-post-button" type="submit" class="btn btn-primary mb-3">Submit</button>
            </form>
        </main>
    `;
}

export function PostEvents() {
    createAddPostListener();
    createEditPostListeners();
    createDeletePostListeners();
}

function createAddPostListener() {
    $("#add-post-button").click(function() {
        const newPost = {
            title: $("#add-post-title").val(),
            content: $("#add-post-content").val()
        }
        const id = $("#add-post-id").val();
        const request = {};
        let uriExtra = "";
        if(id > 0) {
            newPost.id = id;
            request.method = "PUT";
            uriExtra = `/${id}`;
            console.log("Ready to update this post:");
        } else {
            newPost.id = 99999;
            request.method = "POST";
            console.log("Ready to add this post:");
        }
        request.headers = {
            'Content-Type': 'application/json'
        };
        request.body = JSON.stringify(newPost);
        fetch(`${BASE_URI}${uriExtra}`, request)
            .then(res => {
            console.log(`${request.method} SUCCESS: ${res.status}`);
        }).catch(error => {
            console.log(`${request.method} ERROR: ${error}`);
        }).finally(() => {
            createView("/posts");
        });
    });
}

function createEditPostListeners() {
    $(".edit-post-button").click(function() {
        const id = $(this).data("id");
        const oldTitle = $(`#title-${id}`).html();
        const oldContent = $(`#content-${id}`).text();
        $("#add-post-id").val(id);
        $("#add-post-title").val(oldTitle);
        $("#add-post-content").val(oldContent);
    });
}

function createDeletePostListeners() {
    $(".delete-post-button").click(function() {
        const id = $(this).data("id");
        console.log("Ready to delete the post with id " + id);

        const request = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        };
        fetch(`${BASE_URI}/${id}`, request)
            .then(res => {
                console.log("DELETE SUCCESS: " + res.status);
            }).catch(error => {
            console.log("DELETE ERROR: " + error);
        }).finally(() => {
            createView("/posts");
        });
    });
}
