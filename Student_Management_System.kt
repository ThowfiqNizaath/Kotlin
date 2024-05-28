import java.util.*
import java.text.*
val scanner = Scanner(System.`in`)

data class Student(val name: String, var RollNo: Int, val Rank: Int, val DateOfBirth: Date) : Comparable<Student>{
    override fun compareTo(other: Student): Int {
       return this.Rank.compareTo(other.Rank)
    }
}

class StudentManager{
    val Students = mutableListOf<Student>()

    fun addStudent(name : String , RollNo : Int ,age : Int , DateOfBirth : Date){
        if(Students.none{it.name == name || it.RollNo == RollNo}){
            Students.add(Student(name,RollNo,age,DateOfBirth))
            println("Student Add Successfully!")
        }
        else{
            println("Already Exist")
        }
    }

    fun removeStudent(name : String){
        var student= Students.find{it.name.equals(name , ignoreCase = true)}
        if(student != null){
            Students.remove(student)
            println("Student Removed Successfully!")
        }
        else{
            println("Student Not Found")
        }
    }

    fun searchStudent(name : String){
        var student= Students.find{it.name .equals(name , ignoreCase = true)}
        if(student != null){
            println("Name : ${student.name} , RollNo : ${student.RollNo} , Rank : ${student.Rank} , Date Of Birth : ${student.DateOfBirth}")
        }
        else{
            println("Student Not Found")
        }
    }

    fun sortedByName(){
        if(Students.isNotEmpty()) {
            var students = Students.sortedWith(compareBy<Student> { it.name })
            students.forEach{student ->
                println("Name : ${student.name} , RollNo : ${student.RollNo} ")
            }
        }
        else{
            println("Students Not Found")
        }
    }

    fun sortedByRank(){
        if(Students.isNotEmpty()) {
            var students = Students.sortedWith(compareBy<Student> { it.Rank})
            students.forEach{student ->
                println("Name : ${student.name} , RollNo : ${student.RollNo} , Rank : ${student.Rank} ")
            }
        }
        else{
            println("Students Not Found")
        }
    }
}

fun main() {
    val manager = StudentManager()
    var dateformat = SimpleDateFormat("dd-mm-yyyy")
    var choice: Int
    println("\nWelcome to Student Management System")
    do {
        println("\n1. Add Student")
        println("2. Remove Student")
        println("3. Search Student")
        println("4. Display Student sorted By Name")
        println("5. Display Student sorted By Rank")
        println("6. Exit")
        print("Select Option : ")
        choice = scanner.nextInt()
        scanner.nextLine()

        when (choice) {
            1 -> {
                print("Student Name : ")
                var name = scanner.nextLine()
                print("Student RollNO : ")
                var rollno = scanner.nextInt()
                print("Student Rank : ")
                var rank = scanner.nextInt()
                scanner.nextLine()
                try {
                    print("Student Date Of Birth(dd-mm-yyyy) : ")
                    var dateOfbirth = scanner.nextLine()
                    var date: Date = dateformat.parse(dateOfbirth)
                    if (name.isNotEmpty()) {
                        manager.addStudent(name, rollno, rank, date)
                    }
                    else {
                        println("Error : Please enter Student Name ")
                    }
                }
                catch (e: Exception) {
                    println("Error : Please enter Student's Date Of Birth")
                }
            }

            2 -> {
                print("Student Name : ")
                var name = scanner.nextLine()
                if (name.isNotEmpty()) {
                    manager.removeStudent(name)
                }
                else{
                    println("Error : Please enter Student Name ")
                }
            }

            3 -> {
                print("Student Name : ")
                var name = scanner.nextLine()
                if (name.isNotEmpty()) {
                    manager.searchStudent(name)
                }
                else{
                    println("Error : Please enter Student Name ")
                }
            }

            4 -> {
                manager.sortedByName()
            }

            5 -> {
                manager.sortedByRank()
            }

            6 -> {
                println("Thank You!")
            }

        }
    }
    while (choice != 6)
}