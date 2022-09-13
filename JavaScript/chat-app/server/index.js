const app = require('express')()
const PORT = 4000
const express = require('express')
const mongoose = require('mongoose')
const config = require('./utils/config')
const usersRouter = require('./controllers/users')
const loginRouter = require('./controllers/login')

const http = require('http').Server(app)
const cors = require('cors')

let users = []

const socketIO = require('socket.io')(http, {
    cors: {
        origin: "http://localhost:3000"
    }
})

mongoose.connect(config.MONGODB_URI)
    .then(() => {
        console.log('connected to MongoDb')
    })
    .catch((error) => {
        console.error('error connecting to MongoDB', error.message)
    })

socketIO.on('connection', (socket) => {
    console.log(`${socket.id} user just connected!`)

    socket.on('message', (data) => {
        socketIO.emit('messageResponse', data)
    })

    socket.on('newUser', (data) => {
        users.push(data)
        console.log('Serverin käyttäjä: ', data.userName, ' /n Käyttäjän Socketin id: ', data.socketID)
        socketIO.emit('newUserResponse', users)
    })
    
    socket.on('disconnect', () => {
        console.log(`A user disconnected`)
        users = users.filter((user) => user.socketID !== socket.id)
        console.log('Filtteröidyt käyttäjät: ', users)
        socketIO.emit('newUserResponse', users)
        socket.disconnect()
    })

    socket.on('typing', (data) => socket.broadcast.emit('typingReponse', data))
})
app.use(express.static('build'))
app.use(express.json())
app.use(cors())
app.use('/users', usersRouter)
app.use('/login', loginRouter)

app.get('/api', (req, res) => {
    res.json({
        message:'Hello World',
    })
})

http.listen(config.PORT, () => {
    console.log(`Server is running on ${PORT}`)
})