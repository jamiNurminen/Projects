import React from 'react'
import { useNavigate } from 'react-router-dom'

const ChatBody = ({ messages, lastMessageRef, typingStatus }) => {
    const navigate = useNavigate()
    const handleLeaveChat = () => {
        localStorage.removeItem('userName')
        navigate('/')
        window.location.reload()
    }

    return (
        <>
            <header className="chat__mainHeader">
                <p>Keskustele muiden kanssa!</p>
                <button className="leaveChat__btn" onClick={handleLeaveChat}>
                    Poistu
                </button>
            </header>

            <div className="message__container">
                {messages.map((message) => 
                message.name === localStorage.getItem('userName') ? (
                    <div className="message__chats" key={message.id}>
                        <p className="sender__name">Sinä</p>
                        <div className="message__sender">
                            <p>{message.text}</p>
                        </div>
                    </div>
                ) : (
                    <div className="message__chats" key={message.id}>
                        <p>{message.name}</p>
                        <div className="message__recipient">
                            <p>{message.text}</p>
                            </div>
                    </div>
                )
            )}

            <div className="message__status">
                <p>{typingStatus}</p>
            </div>
            <div ref={lastMessageRef} />
        </div>
    </>
    )
}

export default ChatBody;