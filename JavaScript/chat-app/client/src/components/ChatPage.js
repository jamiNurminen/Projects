import React, { useEffect, useState } from 'react'
import ChatBar from './ChatBar'
import ChatBody from './ChatBody'
import ChatFooter from './ChatFooter'

const ChatPage = ({ socket }) => {
    const [messages, setMessages] = useState([])
    const [typingStatus, setTypingStatus] = useState('')
    const lastMessageRef = React.createRef()

    useEffect(() => {
        socket.on('messageResponse', (data) => setMessages([...messages, data]))
    }, [socket, messages])
    
    useEffect(() => {
        lastMessageRef.current?.scrollIntoView({ behavior: 'smooth' })
    }, [messages])

    useEffect(() => {
        socket.on('typingReponse', (data) => setTypingStatus(data))
    }, [socket])
    
    return (
        <div className="chat">
            <ChatBar socket={socket} />
            <div className="chat__main">
                <ChatBody messages={messages} 
                          lastMessageRef={lastMessageRef}
                          typingStatus={typingStatus}
                />
                <ChatFooter socket={socket}/>
            </div>
        </div>
    )
}

export default ChatPage;