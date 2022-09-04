import axios from 'axios'
const baseUrl = '/users'

const createUser = async credentials => {
    const response = await axios.post(baseUrl, credentials)
    return response.data
}

const getUsers = async () => {
    const response = await axios.get(baseUrl)
    return response.data
}

const exportedObject = {
    createUser,
    getUsers
}

export default exportedObject;