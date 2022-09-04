const bcrypt = require('bcrypt')
const loginRouter = require('express').Router()
const User = require('../models/user')

loginRouter.post('/', async (request, response) => {
    const body = request.body

    const user = await User.findOne({ username: body.username })
    const passwordCorrect = user === null
        ? false
        : await bcrypt.compare(body.password, user.passwordHash)

    console.log(passwordCorrect)

    if(!(user && passwordCorrect)) {
        return response.status(401).json({
            error: 'Invalid username or password'
        })
    } else {
        response
            .status(200)
            .send()   
    }

})

module.exports = loginRouter
