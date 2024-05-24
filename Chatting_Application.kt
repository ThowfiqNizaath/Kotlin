import java.util.* 
public var scanner = Scanner(System.`in`)
fun main(){
    println("\nWelcome To ChatApp!")
    var LogIn = false
    do{
        if(!LogIn){
        println("\n1. Add User")
        println("2. LogIn")
        println("3. Exit")
        println("Enter your Chioce :")
        var choice = scanner.nextInt()
        scanner.nextLine()
        when(choice){
                1 -> {
                    println("Enter Name :")
                    var username = scanner.nextLine()
                    println("Enter Password :")
                    var password = scanner.nextLine()
                    if(username.isNotEmpty() && password.isNotEmpty()) {
                        ChatApp.AddUser(username, password)
                    }
                    else{
                        println("Please enter both user name and password")
                    }
                }

                2 -> {
                    println("Enter Username :")
                    var name = scanner.nextLine()
                    println("Enter password :")
                    var password = scanner.nextLine()
                    ChatApp.LogIn(name, password)
                    LogIn = ChatApp.currentuser != null
                }

                3 -> {
                    println("See you soon! Thank you!")
                    break
                }
                else -> {
                    println("Error: Invalid Choice\n")
                }
            }
        }
        else{
            println("\n1. Add Contact")
            println("2. Send Message")
            println("3. Received Message")
            println("4. Display All Message")
            println("5. LogOut")
            println("Enter your choice :")
            var choice = scanner.nextInt()
            scanner.nextLine()

            when(choice){
                1 -> {
                    println("Enter Name :")
                    var name = scanner.nextLine()
                    println("Enter PhoneNumber")
                    var number = scanner.nextLine()
                    ChatApp.AddContact(name , number)
                }

                2 -> {
                    println("Enter Receiver Name :")
                    var receiver = scanner.nextLine()
                    println("Enter Message :")
                    var message = scanner.nextLine()
                    ChatApp.SendingMessage(receiver , message)
                }

                3 -> {
                    ChatApp.receivedMessage()
                }

                4 -> {
                    ChatApp.DisplayAllMessages()
                }

                5 -> {
                    ChatApp.LogOut()
                    LogIn = false
                }
            }
        }
    }
        while(true)
}

data class User(val username: String , val password : String)
data class Contact ( var name : String , var phoneNumber: String)
data class Message(var sender : String , var receiver : String , var message : String , var time : Long)

object ChatApp{
    var users =  mutableListOf<User>()
    var contacts = mutableListOf<Contact>()
    var messages = mutableListOf<Message>()
    var currentuser : User? = null

    fun AddUser(username : String , password : String){
            if (users.none { it.username == username }) {
                users.add(User(username, password))
                println("User : ${username} is Added Successfully!")
            }
            else {
                println("Already Exist")
            }
    }

    fun LogIn(name : String , password : String ){
        var user = users.find{it.username == name && it.password == password}
        if(user != null){
            println("LoggIn Successfull!")
            currentuser = user
        }
        else{
            println("User not found")
        }
    }

    fun AddContact(name : String , phoneNumber : String){
        if(contacts.any{it.name == name && it.phoneNumber == phoneNumber}){
            println("Already Exist")
        }
        else{
            contacts.add(Contact(name,phoneNumber))
            println("Contact Added Successfully!")
        }
    }

    fun SendingMessage(receiver : String , message : String){
        var user = users.find{it.username == receiver}
        if(user != null){
            var receiver = user.username
            var sender = currentuser ?. username ?: "Unknown"
            var time = System.currentTimeMillis()
            messages.add(Message(sender,receiver,message,time))
            println("Message sent to ${receiver} successfully!")
        }
        else{
            println("User not found")
        }
    }

    fun receivedMessage(){
        var receivedMessage = messages.filter{it.receiver == currentuser?.username}
        if(receivedMessage.isNotEmpty()){
             receivedMessage.forEachIndexed{
                 index,message -> println("${index +1}. Sender : ${message.sender} , Message : ${message.message}")
             }
        }
        else {
            println("No message\n")
        }
    }

    fun DisplayAllMessages(){
        var messages = messages.filter{it.sender == currentuser?.username || it.receiver == currentuser?.username }
        if(messages.isNotEmpty()){
            messages.forEachIndexed{ index , message ->
                 println("${index + 1 }. Sender: ${message.sender} | Receiver: ${message.receiver} - ${message.message}")
            }
        }
        else{
            println("No message")
        }
    }
    fun LogOut(){
        currentuser = null
        println("Logged Out!")
    }
}
