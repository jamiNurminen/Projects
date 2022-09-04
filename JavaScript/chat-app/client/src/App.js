import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Home from './components/Home'
import ChatPage from './components/ChatPage'
import SignIn from './components/SignIn'
import socketIO from 'socket.io-client'
const socket = socketIO.connect('https://hidden-ocean-24276.herokuapp.com/')

const App = () => {
  return (
    <BrowserRouter>
    <div>
      <Routes>
        <Route path="/" element={<Home socket={socket} />}></Route>
        <Route path="/chat" element={<ChatPage socket={socket} />}></Route>
        <Route path="/signIn" element={<SignIn socket={socket} />}></Route>
      </Routes>
    </div>
    </BrowserRouter>
  )
}

export default App;