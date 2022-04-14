export default function PostIndex(props) {
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
<input type="hidden" id="content-${post.id}" value="${post.content}">
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
<button id="clear-post-button" type="submit" class="btn btn-primary mb-3" onclick="document.querySelector('#add-post-id').value = 0; document.querySelector('#add-post-title').value = ''; document.querySelector('#add-post-content').value = '';">Clear</button>
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
        if(id > 0) {
            newPost.id = id;
            console.log("Ready to update this post:");
        } else {
            console.log("Ready to add this post:");
        }
        console.log(newPost);
    });
}

function createEditPostListeners() {
    $(".edit-post-button").click(function() {
        const id = $(this).data("id");
        const oldTitle = $(`#title-${id}`).html();
        const oldContent = $(`#content-${id}`).val();
        $("#add-post-id").val(id);
        $("#add-post-title").val(oldTitle);
        $("#add-post-content").val(oldContent);
    });
}

function createDeletePostListeners() {
    $(".delete-post-button").click(function() {
        const id = $(this).data("id");
        console.log("Ready to delete the post with id " + id);
    });
}
