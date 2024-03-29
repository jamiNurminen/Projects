import React, { useState } from 'react'

const ChatFooter = ({ socket }) => {
    const [message, setMessage] = useState('')

    const handleTyping = () => {
        socket.emit('typing', `${localStorage.getItem('userName')} Kirjoittaa...`)
        setTimeout(() => {socket.emit('typing', '')}, 2000)
    }

    const handleSendMessage = (e) => {
        e.preventDefault();
        if (message.trim() && localStorage.getItem('userName')) {
          socket.emit('message', {
            text: message,
            name: localStorage.getItem('userName'),
            id: `${socket.id}${Math.random()}`,
            socketID: socket.id,
          })

        //checkPageStatus(message, localStorage.getItem('userName'))
        }
        setMessage('');
      }

    return (
        <div className="chat__footer">
            <form className="form" onSubmit={handleSendMessage}>
                <input
                    type="text"
                    placeholder="Kirjoita Viesti"
                    className="message"
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                    onKeyDown={handleTyping}
                />
                <button className="sendBtn">LÄHETÄ</button>
            </form>
        </div>
    )
}

export default ChatFooter;