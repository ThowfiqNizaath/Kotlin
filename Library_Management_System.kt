import java.util.Scanner
fun main(){
    val library =  Library()
    val scanner = Scanner(System.`in`)
    println("Welcome to Library Management System!")
    var choice = 0
    while(choice != 6){
        println("\n1.Add Books")
        println("2.Remove Books")
        println("3.Search for Book")
        println("4.Check Out Book")
        println("5.Return Book")
        println("6.Exit\n")

        println("Enter Your Choice :")
        choice = scanner.nextInt()
        when(choice){
            1 -> {
                println("Enter Book Title :")
                var title = readLine()!!
                println("Enter Author Name :")
                var author = readLine()!!
                println("Enter ISBN Number : ")
                var Isbn = readLine()!!
                println("Enter Number Of Copies :")
                var copies = scanner.nextInt()
                library.addBooks(title,author,Isbn,copies)
            }
            2 -> {
                var loop = 0
                println("Choose the Options for Removing")
                while(loop != 4){
                    println("1.Title")
                    println("2.Author")
                    println("3.ISBN")
                    println("4.Cancel")

                    println("Enter the Option :")
                    loop = scanner.nextInt()
                    when(loop){
                        2 -> {
                            println("Author name for Remove Book :")
                            var author = readLine()!!
                            library.removebook(author)
                        }
                        1 -> {
                            println("Title name for Remove Book")
                            var Title = readLine()!!
                            library.removebook(Title)
                        }
                        3 -> {
                            println("ISBN Number for Remove Book")
                            var ISBN = readLine()!!
                            library.removebook(ISBN)
                        }
                        4 -> {
                            println("Canceled!!")
                        }
                        else -> {
                            println("Enter Given Option Only")
                        }
                    }
                }
            }
            3 -> {
                println("Enter the Title of the Book")
                var keybook = readLine()!!
                library.searchbook(keybook)
            }
            4 -> {
                println("Enter the Book Title :")
                var title = readLine()!!
                println("Enter Number Of Book Checkout :")
                var checkout = scanner.nextInt()
                library.checkout(title,checkout)
            }
            5 -> {
                println("Returning Book title :")
                var returnbook = readLine()!!
                println("Number Of Copies Returning :")
                var returncopies = scanner.nextInt()
                library.returnBook(returnbook,returncopies)
            }
            6 -> {
                println("Thanking You for Visiting!")
            }
        }
    }
}

data class Book(var title : String , var author : String , var ISBN : String , var copies : Int)

class Library{
    var books = mutableListOf<Book>()
    fun addBooks(title : String,author : String ,ISBN: String ,copies : Int){
        var title = title.uppercase()
        var book = Book(title ,author, ISBN ,copies)
        books.add(book)
        println("'${book.title}' book is Added Successfully")
    }

    fun removebook(option : String){
        var option = option.uppercase()
        var book = books.find{(it.title == option || it.author == option || it.ISBN == option)}
        if(book != null){
            books.remove(book)
            println("${book.title} book is removed Successfully")
        }
        else{
            println("Book is Not founded")
        }
    }

    fun searchbook(keyWord : String){
        var results = books.filter{book -> book.title.contains(keyWord , ignoreCase = true)}
        if(results.isNotEmpty()){
            println("Results")
            results.forEachIndexed { index, book ->
                if (book.copies != 0) {
                    println("${index+1}.Book Title : ${book.title} , Author : ${book.author} , ISBN Number : ${book.ISBN} , Available copies : ${book.copies}")
                }
                else{
                    println("Book Title : ${book.title} , Author : ${book.author} , ISBN Number : ${book.ISBN} , Not Available ")
                }
            }
        }
        else{
            println("Book is Not Found")
        }
    }

    fun checkout(keyword : String,checkout:Int){
        var keyword = keyword.uppercase()
        var book = books.find{book -> book.title == keyword}
        if(book != null){
            if(book.copies > 0) {
                if (book.copies >= checkout) {
                    book.copies -= checkout
                    println("Book Checked Out Successfully!")
                }
                else{
                    println("Only ${book.copies} Copies Available")
                }
            }
            else{
                println("Sorry!,Book is Not Available")
            }
        }
        else{
            println("Book is Not Founded")
        }
    }

    fun returnBook(returnbook : String , returncopies : Int){
        var returnbook = returnbook.uppercase()
        var book = books.find{it.title == returnbook}
        if(book != null ){
              book.copies += returncopies
            println("Book is Returned Successfully!")
        }
        else{
            println("Book is Not Founded")
        }
    }
}