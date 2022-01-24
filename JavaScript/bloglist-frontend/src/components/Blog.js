import React, { useState } from 'react'

const Blog = ({ blog, like, deleteBlog }) => {
  const [ show, setShow ] = useState(false)
  const [ likes, setLikes ] = useState(0)

  const blogStyle = {
    paddingTop: 20,
    paddinBottom: 30,
    paddingLeft: 10,
    border: 'solid',
    borderWith: 1,
    marginBottom: 10,
    borderColor: 'purple',
  }

  const likeBlog = () => {
    setLikes(likes + 1)
  }

  return(

    <div className='blog' style={blogStyle}>
      <div>
        {blog.title} {blog.author}
        <button onClick={() => setShow(!show)}>{show === true ? 'Close' : 'View'}</button>
        <button onClick={deleteBlog}>delete</button>
      </div>
      {show === true &&
      <div>
        Likes: {likes}
        <button onClick={likeBlog}>Like</button>
      </div>
      }
      {show === true && blog.url}
    </div>
  )
}

export default Blog