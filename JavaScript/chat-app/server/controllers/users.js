const bcrypt = require('bcrypt')
const usersRouter = require('express').Router()
const User = require('../models/user')

usersRouter.post('/', async (request, response) => {
    const body = request.body
    if (!body.password) return response.status(400).json({ error: 'Insert password' })
    
    const saltRounds = 10
    const passwordHash = await bcrypt.hash(body.password, saltRounds)

    const user = new User({
        username: body.username,
        passwordHash: passwordHash
    })

    const savedUser = await user.save()

    response.json(savedUser)
})

usersRouter.get('/', async (request,response) => {
    const users = await User.find({})
    response.json(users.map(u => u.username))
})

module.exports = usersRouter