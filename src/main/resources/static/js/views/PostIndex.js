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
                    return `
<div class="card">
    <h4 class="card-header">
        <span id="title-${post.id}">${post.title}</span>
        <span style="float:right" id="author-${post.id}">Author: ${post.author.username}</span>
    </h4>
    <div class="card-body">
        <p id="content-${post.id}" class="card-text">${post.content}</p>
    </div>
    <div class="card-footer text-muted">            
        ${post.categories.map(category => {
                    return `<span class="border border-primary rounded">${category.name}</span>`
                }).join('')}
        <span><a href="#" class="edit-post-button" data-id="${post.id}">Edit</a></span>
        <span><a href="#" class="delete-post-button" data-id="${post.id}">Delete</a></span>
    </div>
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
        // create the post body that will become the request body
        const newPost = {
            title: $("#add-post-title").val(),
            content: $("#add-post-content").val()
        }

        // we use id to know if this is add or edit
        const id = $("#add-post-id").val();

        // make the request
        const request = {};
        let uriExtra = "";
        if(id > 0) {
            // newPost.id = id; // don't need this for a put
            request.method = "PUT";
            uriExtra = `/${id}`;
            console.log("Ready to update this post:");
        } else {
            // newPost.id = 99999; // this doesn't need to be there
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
        // get the id of the blog post, stored as the button's data-id attribute
        const id = $(this).data("id");

        // get the title and content associated with the blog post
        const oldTitle = $(`#title-${id}`).html();
        const oldContent = $(`#content-${id}`).text();

        // set add form fields with existing blog post data
        $("#add-post-id").val(id);
        $("#add-post-title").val(oldTitle);
        $("#add-post-content").val(oldContent);
    });
}

function createDeletePostListeners() {
    $(".delete-post-button").click(function() {
        // grab the id of the post to be deleted
        const id = $(this).data("id");
        console.log("Ready to delete the post with id " + id);

        // make the request
        const request = {
            method: 'DELETE'
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
