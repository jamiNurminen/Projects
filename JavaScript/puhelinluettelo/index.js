require('dotenv').config()
const express = require('express')
const morgan = require('morgan')
const app = express()
const cors = require('cors')
const Person = require('./models/Person')

const errorHandler = (error, request, response, next) => {

    if (error.name === 'CastError') {
        return response.status(400).send({ error: 'malformatted id ' })
    } else if (error.name === 'ValidationError') {
        try {
            if ( error.errors.name.properties.type === 'unique') return response.status(400).send({ error: 'Name must be unique ' })
        }
        finally {return response.status(400).send({ error: error.message })}
    }

    next(error)
}

const requestLogger = (request, response, next) => {
    console.log('Method: ', request.method)
    console.log('Path:   ', request.path)
    console.log('Body:   ', request.body)
    console.log('--------')
    next()
}

app.use(morgan('tiny'))
app.use(requestLogger)
app.use(express.json())
app.use(cors())
app.use(express.static('build'))

app.post('/api/persons', (request, response, next) => {
    const body = request.body
    if(body.name === undefined) {
        return response.status(400).json({
            error: 'Name missing'
        })
    }

    if(body.number === undefined) {
        return response.status(400).json({
            error: 'Number missing'
        })
    }

    const person = new Person({
        name: body.name,
        number: body.number || '',
    })

    person
        .save()
        .then(savedPerson => savedPerson.toJSON())
        .then(savedAndFormattedPerson => {
            response.json(savedAndFormattedPerson)
        })
        .catch(error => next(error))
})

app.get('/', (req, res) => {
    res.send('<h1>Hello World!</h1>')
})

app.get('/api/persons', (req, res) => {
    Person.find({}).then(persons => {
        res.json(persons)
    })
})

app.get('/api/persons/:id', (request, response, next) => {
    Person.findById(request.params.id).then(person => {
        if (person) {
            response.json(person)
        } else {
            response.status(404).end()
        }
    })
        .catch(error => next(error))
})

app.delete('/api/persons/:id', (request, response) => {
    Person.findByIdAndRemove(request.params.id)
        .then(() => {
            response.status(204).end()
        })
        .catch(error => {
            console.log(error)
        })
})

app.get('/api/info', (req, res) => {
    Person.find({}).then(persons => {
        res.send(
            `Phonebook has info for ${persons.length} people <br>
            <br>
            ${Date()}`
        )
    })
})

app.put('/api/persons/:id', (request, response, next) => {
    const body = request.body

    const person = {
        name: body.name,
        number: body.number,
    }

    Person.findByIdAndUpdate(request.params.id, person)
        .then(updatedPerson => {
            response.json(updatedPerson)
        })
        .catch(error => next(error))
})

const unknownEndpoint = (request, response) => {
    response.status(404).send({ error: 'unknown endpoint ' })
}

app.use(unknownEndpoint)

app.use(errorHandler)

const PORT = process.env.PORT
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`)
})