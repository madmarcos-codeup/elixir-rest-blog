export default function PostIndex(props) {
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
<h3>Posts (make this a sweet table!)</h3>
            <div id="posts-container">
                ${props.posts.map(post => `<h4>${post.title}</h4>`).join('')}   
            </div>
<hr>
            <h3>Add a Post</h3>
            <form id="add-post-form">
            <div class="mb-3">
  <label for="add-post-title" class="form-label">Title</label>
  <input type="text" class="form-control" id="add-post-title" placeholder="Post title">
</div>
  <label for="add-post-content" class="form-label">Content</label>
  <textarea class="form-control" id="add-post-content" rows="3" placeholder="Post content"></textarea>
</div>
<br>
<button id="add-post-button" type="submit" class="btn btn-primary mb-3">Submit</button>
</form>
        </main>
    `;
}

export function PostEvents() {
    createAddPostListener();
}

function createAddPostListener() {
    $("#add-post-button").click(function() {
        const newPost = {
            title: $("#add-post-title").val(),
            content: $("#add-post-content").val()
        }
        console.log("Ready to add this post:");
        console.log(newPost);
    });
}

