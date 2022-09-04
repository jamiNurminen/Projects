import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import loginService from '../services/login'

const Home = ({ socket }) => {
    const navigate = useNavigate()
    const [userName, setUserName] = useState('')
    const [password, setPassword] = useState('')

    //* Tähän funktioon jäätiin, ei ole testattu //*
    const handleLogin = async (e) => {
        e.preventDefault()

        try {
            const user = await loginService.login({
                username: userName,
                password: password
            })
            console.log(user)

            localStorage.setItem('userName', userName)
            socket.emit('newUser', { userName, socketID: socket.id })
            navigate('/chat')
        } catch (e) {
            alert('Invalid username or password')
            console.error(e)
        }
        
    }

    return (
        <>
            <form className="home__container" onSubmit={handleLogin}>
                <h2 className="home__header">MitäsÄij - Keskustelualusta</h2>
                <label htmlFor="username">Käyttäjänimi</label>
                <input
                    type="text"
                    minLength={6}
                    name="username"
                    id="username"
                    className="username__input"
                    value={userName}
                    onChange={(e) => setUserName(e.target.value)}
                />
                <label htmlFor="password">Salasana</label>
                <input
                    type="password"
                    name="password"
                    id="password"
                    className="username__input"
                    onChange={(pass) => setPassword(pass.target.value)}
                />
                <button className="home__cta">Log In</button>
            </form>
            <div className="sign__container">
                <button className="sign__cta" onClick={() => {navigate('/signIn')}}>Sign In</button>
                <button className="forgot__btn" onClick={() => {alert('Ei kannattais unohdella salasanoja :)')}}>Forgot password? </button>
            </div>
        </>
    )
}

export default Home;