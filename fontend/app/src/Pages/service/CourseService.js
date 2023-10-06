import React, { useEffect, useState } from "react";
import axios from "axios";

function CourseService() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [newPost, setNewPost] = useState({ title: "", body: "" });

  useEffect(() => {
    // Fetch initial data using GET request.
    axios
      .get("https://jsonplaceholder.typicode.com/posts")
      .then((response) => {
        setData(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
        setLoading(false);
      });
  }, []);

  const handleDelete = (postId) => {
    // Delete a post using DELETE request.
    axios
      .delete(`https://jsonplaceholder.typicode.com/posts/${postId}`)
      .then(() => {
        // Remove the deleted post from the data array.
        setData(data.filter((post) => post.id !== postId));
      })
      .catch((error) => {
        console.error("Error deleting post:", error);
      });
  };

  const handlePostSubmit = () => {
    // Create a new post using POST request.
    axios
      .post("https://jsonplaceholder.typicode.com/posts", newPost)
      .then((response) => {
        // Add the new post to the data array.
        setData([...data, response.data]);
        setNewPost({ title: "", body: "" });
      })
      .catch((error) => {
        console.error("Error creating post:", error);
      });
  };

  return (
    <div>
      <h1>Fetch, Create, and Delete Posts</h1>

      {/* Create a new post */}
      <div>
        <h2>Create a New Post</h2>
        <input
          type="text"
          placeholder="Title"
          value={newPost.title}
          onChange={(e) => setNewPost({ ...newPost, title: e.target.value })}
        />
        <textarea
          placeholder="Body"
          value={newPost.body}
          onChange={(e) => setNewPost({ ...newPost, body: e.target.value })}
        />
        <button onClick={handlePostSubmit}>Create Post</button>
      </div>

      {/* List of posts */}
      <h2>Posts</h2>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <ul>
          {data.map((post) => (
            <li key={post.id}>
              <strong>{post.title}</strong>
              <p>{post.body}</p>
              <button onClick={() => handleDelete(post.id)}>Delete</button>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default App;
