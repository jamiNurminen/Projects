const mongoose = require('mongoose')
const supertest = require('supertest')
const helper = require('./test_helper')
const app = require('../app')
const api = supertest(app)

const Blog = require('../models/blog')

beforeEach(async () => {
    await Blog.deleteMany({})
    await Blog.insertMany(helper.initialBlogs)
})

test('blogs are returned as json', async () => {
    const response = await api
      .get('/api/blogs')
      .expect(200)
      .expect('Content-Type', /application\/json/)

    expect(response.body).toHaveLength(helper.initialBlogs.length)
    
})

test('a valid blog can be added ', async () => {
    const newBlog = {
        title: 'Uusi Blogi',
        author: 'R4ndom',
        url: 'https://eioo',
    }
    if (!newBlog.likes) newBlog.likes = 0
    await api
      .post('/api/blogs')
      .send(newBlog)
      .expect(200)
      .expect('Content-Type', /application\/json/)
    
    const blogsAtEnd = await helper.blogsInDb()
    expect(blogsAtEnd).toHaveLength(helper.initialBlogs.length +1)

    const contents = blogsAtEnd.map(n => n.author)
    expect(contents).toContain(
        'R4ndom'
    )
    expect(newBlog.likes).toBe(0)

    
})

test('added blog has id', async () => {
    const newBlog = { 
        title: "MyFunnyLife",
        author: "Siiri Siirinen",
        url: "httjoku:12312324",
        likes: 12308
    }

    const result = await api
        .post('/api/blogs')
        .send(newBlog)
        .expect(200)

    expect(result.body.id).toBeDefined()

})

test('url missing --> bad request', async () => {
    const newBlog = {
        author: "Siiri Siirinen",
        url: "httjoku:12312324",
        likes: 12308
    }
    await api
        .post('/api/blogs')
        .send(newBlog)
        .expect(400)
})

test('title missing --> bad request', async () => {
    const newBlog = {
        title: "MyFunnyLife",
        author: "Siiri Siirinen",
        likes: 12308
    }

    await api
        .post('/api/blogs')
        .send(newBlog)
        .expect(400)
})

test('url and title missing --> bad request', async () => {
    const newBlog = {
        author: "Siiri Siirinen",
        likes: 12308
    }

    await api
        .post('/api/blogs')
        .send(newBlog)
        .expect(400)

})

afterAll(() => {
    mongoose.connection.close()
})