import axios from 'axios'
const baseUrl = 'http://localhost:3003/api/blogs'

let token = null

const setToken = newToken => {
  token = `bearer ${newToken}`
}

const getAll = async () => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.get(baseUrl, config)
  return response.data
}

const getBlog = async (id) => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.get(`${baseUrl}/${id}`, config)
  return response.data
}

const getBlogs = async (blogs) => {
  var blogsArray = []

  const promiseArray = blogs.map(blog => getBlog(blog))
  await Promise.all(promiseArray).then(blog => {
    blogsArray = blogsArray.concat(blog)
  })
  return blogsArray
}

const deleteBlog = async (id) => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.delete(`${baseUrl}/${id}`, config)
  return response.data
}

const create = async newObject => {
  const config = {
    headers: { Authorization: token },
  }

  const response = await axios.post(baseUrl, newObject, config)
  return response.data
}

const likeBlog = async (id) => {
  //const config = {
  //headers: { Authorization: token },
  //}

  const blog = await getBlog(id)
  blog.likes = blog.likes + 1
  console.log('Blogi', blog)

  const response = await axios.put(`${baseUrl}/${blog.id}`, blog)
  console.log(response.data)
  return response.data
}

const exportedObjects = {
  getAll, getBlog, getBlogs, setToken, create, deleteBlog, likeBlog
}

export default exportedObjects