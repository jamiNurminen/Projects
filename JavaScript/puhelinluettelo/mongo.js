const mongoose = require('mongoose')

if (process.argv.length<3) {
    console.log('Anna salasana argumentiksi')
    process.exit(1)
}

const password = process.argv[2]

const url = `mongodb://japenurm:${password}@cluster0-shard-00-00.dnffi.mongodb.net:27017,cluster0-shard-00-01.dnffi.mongodb.net:27017,cluster0-shard-00-02.dnffi.mongodb.net:27017/persons?ssl=true&replicaSet=atlas-xg4fe1-shard-0&authSource=admin&retryWrites=true`

mongoose.connect(url)

const personSchema = new mongoose.Schema ({
    name: String,
    number: String,
})

const Person = mongoose.model('Person', personSchema)

if (process.argv.length === 5) {
    const person = new Person({
        name: process.argv[3],
        number: process.argv[4],
    })

    person.save().then(() => {
        console.log(`added ${person.name} number ${person.number} to the phonebook`)
        mongoose.connection.close()
    })
}

if (process.argv.length === 4) {
    console.log('Nimi tai numero puuttuu!')
    mongoose.connection.close()
}

if (process.argv.length === 3) {
    Person.find({}).then(result => {
        result.forEach(person => {
            console.log(`${person.name} ${person.number}`)
        })
        mongoose.connection.close()
    })
}
