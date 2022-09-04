import React, { useState } from "react";
import { useNavigate } from 'react-router-dom'
import usersService from '../services/users'

const SignIn = (socket) => {
    const navigate = useNavigate()
    const [password, setPassword] = useState('')
    const [passwordVerify, setPasswordVerify] = useState('')
    const [username, setUserName] = useState('')

    const handleSubmit = async (e) => {

        if(username !== '') {
            try {
                const users = await usersService.getUsers()
                const isTaken = users.includes(username)
                if (password === passwordVerify) {
                    if (isTaken === false) {
                        if (username.length >= 6) {
                            try {
                                const createdUser = await usersService.createUser({
                                    username: username,
                                    password: password
                                })
                                alert(`User ${createdUser.username} is created!`)
                                navigate('/')
                   
                            } catch (e) {
                                console.error(e.message)
                            }
                        } else {
                            alert('Username must contain atleast 6 characters')
                        }
                   } else {
                       alert('Username is already taken')
                   }
                } else {
                    alert('Password does not match')

                }
                
            } catch(e) {
                console.error(e.message)
            }
        } else {
            alert('Insert username')
        }

    }

    return(
        <>
            <form className="home__container"  onSubmit={(() => {handleSubmit()})}>
                <h2 className="home__header">Luo uusi tunnus</h2>
                <label htmlFor="username">Käyttäjänimi</label>
                <input
                    type="text"
                    name="username"
                    id="username"
                    className="username__input"
                    onChange={(e) => setUserName(e.target.value)}
                />
                <label htmlFor="password">Salasana</label>
                <input
                    type="password"
                    name="password"
                    id="newPassword"
                    className="username__input"
                    onChange={(e) => setPassword(e.target.value)}
                />
                <label htmlFor="password">Salasana uudelleen</label>
                <input
                    type="password"
                    name="password"
                    id="repeatPassword"
                    className="username__input"
                    onChange={(e) => setPasswordVerify(e.target.value)}
                />
            </form>
            <div className="sign__container">
                <button className="home__cta" onClick={handleSubmit}>Luo tunnus</button>
                <button className="cancel__cta" onClick={() => {navigate('/')}}>Peruuta </button>
            </div>
        </>
    )
}

export default SignIn;