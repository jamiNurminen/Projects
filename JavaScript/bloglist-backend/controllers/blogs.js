const blogsRouter = require('express').Router()
const Blog = require('../models/blog')
const jwt = require('jsonwebtoken')

blogsRouter.get('/', async(request, response) => {
    
    const blogs = await Blog
        .find({}).populate('user', { username: 1, name: 1 })
    
    response.json(blogs.map(blog => blog.toJSON()))
})

blogsRouter.post('/', async (request, response) => {
    const body = request.body
    if(!body.likes) body.likes = 0
    if(!body.title || !body.url) return response.status(400).end()
    const token = request.token
    const decodedToken = jwt.verify(request.token, process.env.SECRET)
    if (!token || !decodedToken.id) {
        return response.status(401).json({ error: 'token missing or invalid' })
    }
    const user = request.user
  
    const blog = new Blog({
        title: body.title,
        url: body.url,
        author: body.author,
        likes: body.likes,
        user: user._id
    })
    const savedBlog = await blog.save()

    user.blogs = user.blogs.concat(savedBlog.id)
    await user.save()
    response.json(savedBlog.toJSON())
  })

blogsRouter.delete('/:id', async (request, response, next) => {
    const token = request.token
    const decodedToken = jwt.verify(request.token, process.env.SECRET)
    if (!token || !decodedToken) {
        return response.status(401).json({ error: 'token missing or invalid '})
    }
    const user = request.user
    const blog = await Blog.findById(request.params.id)
    if (user.id.toString() === blog.user.toString()) {
        await Blog.findByIdAndRemove(request.params.id)
        response.status(204).end()
    } else {
        response.status(401).json({ error: 'User can delete only own blogs'})
    }
})

blogsRouter.put('/:id', async (request, response, next) => {
    const updatedBlog = await Blog.findByIdAndUpdate(request.params.id, request.body)
    response.json(updatedBlog)
})

blogsRouter.get('/:id', async (request, response, next) => {
    
    const blog = await Blog.findById(request.params.id)
    response.json(blog)
})


module.exports = blogsRouter