import java.util.Scanner
fun main() {
    var scanner = Scanner(System.`in`)
    var contact = ContactManager()
    println("\nWelcome to Contact Management Application")
    var choice: Int
    do {
        println("\n1.Add Contact")
        println("2.Remove Contact")
        println("3.Search for Contact")
        println("4.Display All Contacts")
        println("5.Exit\n")

        println("Enter your Choice :")
        choice = scanner.nextInt()
        scanner.nextLine()
        when (choice) {
            1 -> {
                println("Adding Contact")
                println("name :")
                var name = scanner.nextLine()
                println("Phone Number : ")
                var phoneNumber = scanner.nextLine()
                println("Email (optional) :")
                var mail = scanner.nextLine()

                if (name.isNotEmpty() && phoneNumber.isNotEmpty()) {
                    if (mail.isBlank()) {
                        contact.addContact(Contact(name, phoneNumber))
                    } else {
                        contact.addContact(Contact(name, phoneNumber, mail))
                    }
                } else {
                    println("Name or PhoneNumber is Empty")
                }
            }

            2 -> {
                println("Removing Contact")
                println("Name : ")
                var name = readLine()!!
                contact.removeContact(name)
            }

            3-> {
                println("Searching Contact")
                println("Name : ")
                var name = readLine()!!
                contact.searchContact(name)
            }

            4 -> {
                println("All Contacts :")
                contact.displayAll()
            }

            5 -> {
                println("Thanking You!!")
            }
        }
    }
        while (choice != 5)

}
 data class Contact(var name : String , var phoneNumber : String , var email : String) {
     constructor(name: String, phone: String) : this(name, phone, "Empty")
 }
class ContactManager{
    var contacts = mutableListOf<Contact>()
    fun addContact(contact : Contact){
         if(contacts.any{it.name==contact.name}){
             println("This Name Contact is already Existed")
         }
        else{
            contacts.add(contact)
             println("Contact Added Successfully")
         }
    }

    fun removeContact(name : String){
        var contact= contacts.find{it.name.equals(name,ignoreCase = true)}
        if(contact != null){
            contacts.remove(contact)
            println("Contact is Removed Successfully")
        }
        else{
            println("Contact is Not Found\n")
        }
    }

    fun searchContact(name : String){
        var contact= contacts.find{it.name.equals(name,ignoreCase = true)}
        if(contact != null){
            println("Name : ${contact.name}\n" +
                    "phone: ${contact.phoneNumber}\n" +
                    "Email : ${contact.email}\n")
        }
        else{
            println("Contact is Not Found")
        }
    }

    fun displayAll(){
        if(contacts.isNotEmpty()){
            contacts.forEachIndexed{index,contact ->
                println("${index+1}. Name : ${contact.name}, phone: ${contact.phoneNumber} , Email : ${contact.email} ")
            }
        }
        else{
            println("No Contacts Found\n")
        }
    }
}
