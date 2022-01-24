import React, { useState, useEffect, useRef } from 'react'
import Blog from './components/Blog'
import Notification from './components/Notification'
import BlogForm from './components/BlogForm'
import blogService from './services/blogs'
import loginService from './services/login'
import Togglable from './components/Togglable'

const App = () => {
  const [ blogs, setBlogs ] = useState([])
  const [ username, setUsername ] = useState('')
  const [ password, setPassword ] = useState('')
  const [ user, setUser ] = useState(null)
  const [ succesMessage, setSuccesMessage] = useState(null)
  const [ errorMessage, setErrorMessage] = useState(null)

  useEffect(() => {
    const loggedUserJSON = window.localStorage.getItem('loggedUser')
    if (loggedUserJSON) {
      const user = JSON.parse(loggedUserJSON)
      setUser(user)
      blogService.setToken(user.token)
      refreshBlogs(user)
    }
  }, [])

  const blogFormRef = useRef()

  const addBlog = (blogObject) => {
    blogFormRef.current.toggleVisibility()

    blogService
      .create(blogObject)
      .then(returnedBlog => {
        setBlogs(blogs.concat(returnedBlog))
        user.blogs = user.blogs.concat(returnedBlog.id)
        window.localStorage.setItem(
          'loggedUser', JSON.stringify(user)
        )
        setSuccesMessage(`A NEW BLOG ${returnedBlog.title} by ${returnedBlog.author} added`)
        setTimeout(() => {
          setSuccesMessage(null)
        }, 2500)
      })
      .catch(error => {
        setErrorMessage('Error occured while adding blog')
        setTimeout(() => {
          setErrorMessage(null)
        }, 2500)
      })
  }


  const loginForm = () => (
    <form onSubmit={handleLogin}>
      <div>
        username
        <input
          id="username"
          type="text"
          value={username}
          name="Username"
          onChange={({ target }) => setUsername(target.value)}
        />
      </div>
      <div>
        password
        <input
          id="password"
          type="password"
          value={password}
          name="Password"
          onChange={({ target }) => setPassword(target.value)}
        />
      </div>
      <button id="login-button" type="submit">login</button>
    </form>
  )

  const logout = () => {
    window.localStorage.clear()
    setUser(null)
  }

  const refreshBlogs = (user) => {
    const request = blogService.getBlogs(user.blogs)
    request.then(response => setBlogs(response))
  }

  const blogForm = () => (
    <Togglable buttonLabel='new blog' ref={blogFormRef}>
      <BlogForm createBlog={addBlog} />
    </Togglable>
  )

  const like = (blog) => {
    blog.likes = blog.likes + 1
  }

  const deleteBlog = (id) => {
    const removedBlog = blogs.find(b => b.id === id)
    //const request = blogService.deleteBlog(id)
    setBlogs(blogs.filter(blog => blog.id !== removedBlog.id))
    console.log('Frontendistä poistettiin blogi')
  }

  const handleLogin = async (event) => {
    event.preventDefault()

    try {
      const user = await loginService.login({
        username, password
      })
      window.localStorage.setItem(
        'loggedUser', JSON.stringify(user)
      )
      blogService.setToken(user.token)
      setUser(user)
      setUsername('')
      setPassword('')
      refreshBlogs(user)
      setSuccesMessage('Logged in')
      setTimeout(() => {
        setSuccesMessage(null)
      }, 2500)
    } catch (e) {
      setErrorMessage('Wrong username or password')
      setTimeout(() => {
        setErrorMessage(null)
      }, 2500)
    }
  }

  if (user === null) {
    return (
      <div>
        <h2>Log in to application</h2>
        <Notification message={errorMessage} status="error" />
        {loginForm()}
      </div>
    )
  }

  return (
    <div>
      <h2>Blogs</h2>
      <Notification message={succesMessage} status="succes" />
      <Notification message={errorMessage} status="error" />
      <p>{user.name} logged in <button onClick={logout}>Logout</button> </p>
      {blogForm()}
      {blogs.map(blog =>
        <Blog
          key={blog.id}
          blog={blog}
          like={() => like(blog)}
          deleteBlog={() => deleteBlog(blog.id)}
        />
      )}
    </div>
  )
}

export default App