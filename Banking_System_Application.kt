import java.util.Scanner

data class Account(var name : String , var accountNumber : String , var balance : Double)

class Bank{
    var accounts = mutableListOf<Account>()
    var scanner = Scanner(System.`in`)

    fun createAccount(name : String){
        var accountholder =  name
        var accountNumber = getaccountNumber()
        var balance : Double = 0.0
        accounts.add(Account(accountholder,accountNumber,balance))
        println("New AccountNumber : ${accountNumber}")
        println("Account is Created Successfully \n")
    }

    fun DisplayAllAccounts(){
        if(accounts.isNotEmpty()) {
            println("All Accounts :")
            accounts.forEachIndexed { index, value ->
                println("${index + 1}. Accountholder Name:${value.name} , Account Number:${value.accountNumber} , Balance : ${value.balance}")
            }
            println("\n")
        }
        else{
            println(" Accounts List Empty \n ")
        }
    }

    fun depositCash(accountNumber: String){
        var account = accounts.find{it.accountNumber == accountNumber}
        if(account != null){
            println("Accountholder Name : ${account.name} | AccountNumber : ${account.accountNumber}")
            println("Enter Amount :")
            var amount = scanner.nextDouble()
            if(amount > 0) {
                account.balance += amount
                println("Amount is deposited Succesfully. Available Balance : ${account.balance}\n")
            }
            else{
                println("Deposit More than Zero \n")
            }
        }
        else{
            println("Account Not Found , Check Your AccountNumber:${accountNumber}\n")
        }
    }

    fun checkingBalance(accountNumber:String){
        var account = accounts.find{it.accountNumber == accountNumber}
        if(account != null){
            println("Available Balance : ${account.balance}\n")
        }
        else{
            println("Account Not Found\n")
        }
    }
    fun withdrawMoney(accountNumber: String) {
        var account = accounts.find { it.accountNumber == accountNumber }
        if (account != null) {
            println("Accountholder Name : ${account.name} | AccountNumber : ${account.accountNumber}")
            while(true) {
                println("Enter Amount : ")
                var amount = scanner.nextDouble()
                if(amount == null) {
                    println("Error : Please Enter Amount")
                }
                else {
                    if (amount > 0){
                        if (amount <= account.balance) {
                            account.balance -= amount
                            println("Withdraw Successfully! , Available Balance : ${account.balance}\n")
                        }
                        else {
                            println("Sorry! Avilable Balance Rs.${account.balance} Only")
                        }
                    }
                    else{
                        println("Just Enter Amount .Minus Symbol  No Needed")
                    }
                    break
                }
            }
        }
        else{
            println("Account Not Found\n")
        }
    }

    fun moneyTransfer(fromAccount : String , toAccount : String){
        var formAccount = accounts.find{it.accountNumber == fromAccount}
        var toAccount = accounts.find{it.accountNumber == toAccount}
        if(formAccount != null && toAccount != null){
           while(true){
               println("Enter Amount :")
               var amount = scanner.nextInt()
               if(amount != null){
                   if(amount > 0){
                       if(amount < formAccount.balance){
                           formAccount.balance -= amount
                           toAccount.balance += amount
                           println("Money Transfered Successfully! , Available Balance : ${formAccount.balance}\n")
                       }
                       else{
                           println("Sorry! Avilable Balance Rs.${formAccount.balance} Only\n")
                       }
                   }
                   else{
                       println("Please Enter Amount without negative\n")
                   }
                   break
               }
               else{
                   println("Please Enter Amount For Transfer Money")
               }
           }
        }
        else{
            println("Account Not Found\n")
        }
    }
    private fun getaccountNumber():String{
        return (1000001000 + accounts.size).toString()
    }
}

fun main(){
    var scanner  = Scanner(System.`in`)
    var banking = Bank()
    println("\nWellcome to Demo Banking System")
    var choice: Int = 0
    do {
        println(
            """
           |1. Create Account
           |2. Deposit Money
           |3. Withdraw Money
           |4. Check Balance
           |5. Transfer Money
           |6. Display All Accounts
           |7. Exit
           """.trimMargin()
        )
        println("Enter Your Choice :")
        choice = scanner.nextInt()
        scanner.nextLine()
        when (choice) {
            1 -> {
                println("Creating Account")
                while (true) {
                    var name: String
                    println("New Account UserName : ")
                    name = scanner.nextLine()
                    if (name.isBlank()) {
                        println("Please enter name for creating Account")
                    } else {
                        banking.createAccount(name)
                        break
                    }
                }
            }

            2 -> {
                println("Deposit Cash")
                while(true){
                    println("Account Number : ")
                    var accountNumber = scanner.nextLine()
                    if (accountNumber.isBlank()) {
                        println("Error : Please Enter AccountNumber")
                    } else {
                        banking.depositCash(accountNumber)
                        break
                    }
                }
            }
            3 -> {
                println("Withdray Money")
                while(true){
                    println("Enter AccoutNumber :")
                    var accountNumber = scanner.nextLine()
                    if(accountNumber.isBlank()){
                        println("Error : Please enter AccountNumber for Withdraw Money")
                    }
                    else{
                        banking.withdrawMoney(accountNumber)
                        break
                    }
                }
            }
            4 -> {
                println("Checking Balance")
                while (true) {
                    println("Enter AccountNumber :")
                    var accountNumber = scanner.nextLine()
                    if (accountNumber.isBlank()) {
                        println("Please enter AccountNumber for Checking Balance!")
                    } else {
                        banking.checkingBalance(accountNumber)
                        break
                    }
                }
            }
            5 -> {
                println("Money Transfer")
                while(true){
                    println("Enter Form AccountNumber :")
                    var formAccount = scanner.nextLine()
                    println("Enter To AccountNumber :")
                    var toAccount = scanner.nextLine()
                    if(formAccount.isNotBlank() && toAccount.isNotBlank()){
                        if(formAccount != toAccount){
                            banking.moneyTransfer(formAccount , toAccount)
                        }
                        else{
                            println("Both Account are Same\n")
                        }
                        break
                    }
                    else{
                        println("Error : Please Enter AccountNumber")
                    }
                }
            }
            6 -> {
                banking.DisplayAllAccounts()
            }
            7 -> {
                println("Thanks for banking with us! Have a wonderful day!")
            }
            else -> {
                println("Error : Invalid Opition\n")
            }
        }
    }
    while(choice != 7)
}

