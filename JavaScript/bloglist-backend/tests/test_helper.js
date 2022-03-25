const Blog = require('../models/blog')

const initialBlogs = [
    {
    title: "Jarkon blogi",
    author: "Jarkko Makinen",
    url: "https://jarkkomakinen.fi",
    likes: 20
    },
    {
    title: "Tanelin blogi",
    author: "Taneli Kaukonen",
    url: "https://TaneliKaukonen.fi",
    likes: 1
    },
    {
    title: "Toivon blogi",
    author: "Toivo Reipas",
    url: "https://ToivoReipas.fi",
    likes: 12
    }
]

const nonExistingId = async () => {
    const blog = new Blog({ title: 'Kohtapoistetaan', author: 'eikukaan', url: 'eiole', likes: 0})
    await blog.save()
    await blog.remove

    return blog._id.toString()
}

const blogsInDb = async () => {
    const blogs = await Blog.find({})
    return blogs.map(blog => blog.toJSON())
}

module.exports = {
    initialBlogs, nonExistingId, blogsInDb
}