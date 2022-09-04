function sendNotification(message, user) {
    document.onvisibilitychange = () => {
        if(document.hidden) {
            const notification = new Notification("new message from MitäsÄij", {
                icon: "https://cdn-icons-png.flaticon.com/512/733/733585.png",
                body: `${user}: ${message}`
            })
            notification.onclick = ()=> function() {
                window.open("http://localhost:3000/chat")
            } 
        }
    }
}

const checkPageStatus = (message, user) => {
    if(user === localStorage.getItem('userName')) {
        if(!("Notification" in window)) {
            alert("This browser does not support system notifications!")
          } else if(Notification.permission === "granted") {
            sendNotification(message, user)
          }else if(Notification.permission !== "denied") {
             Notification.requestPermission((permission)=> {
                if (permission === "granted") {
                  sendNotification(message, user)
                }
             })
          }
    }
    
}

export default checkPageStatus;