import axios from 'axios'
const baseUrl = 'http://localhost:4000/login'

const login = async credentials => {
    const response = await axios.post(baseUrl, credentials)
    console.log(response)
    return response.data
}

const exportedObject = {
    login
}

export default exportedObject;