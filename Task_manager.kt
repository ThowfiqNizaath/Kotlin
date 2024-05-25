import java.util.*
val scanner = Scanner(System.`in`)
fun main(){
    var taskmanager = TaskManager()
    println("\nWellcome to Task Manager")
    do{
        println("\n1. Add Task")
        println("2. Remove Task")
        println("3. Search Task ")
        println("4. Change Task Priority")
        println("5. Display All Tasks")
        println("6. Exit")
        print("Select your choice : ")
        var choice : Int = scanner.nextInt()
        scanner.nextLine()

        when(choice){
            1 -> {
                print("Enter Task Name : ")
                var name = scanner.nextLine()
                print("Enter Task Priority[Low , High , Medium , Important , Critical] : ")
                var priority = priority.valueOf(scanner.nextLine().uppercase())
                taskmanager.addTask(name , priority)
            }

            2 -> {
                print("Enter Task Id : ")
                var id = scanner.nextInt()
                taskmanager.RemoveTask(id)
            }

            3 -> {
                print("Enter Priority : ")
                val priority = priority.valueOf(scanner.nextLine().uppercase())
                taskmanager.SearchPriority(priority)
            }

            4 -> {
                print("Enter Task Id : ")
                var id = scanner.nextInt()
                scanner.nextLine()
                print("Change Task Priority [Low , High , Medium , Important , Critical] : ")
                var priority = priority.valueOf(scanner.nextLine().uppercase())
                taskmanager.ChangePriority(id , priority)
            }

            5 -> {
                println("All Tasks :")
                taskmanager.displayAllTask()
            }

            6 -> {
                println("See you next time! Have a great day!")
                break
            }
            else -> println("Invalid Option")
        }
    }
        while(true)
}
enum class priority{
    LOW,
    MEDIUM,
    HIGH,
    IMPORTANT,
    CRITICAL;
}
data class Task(var taskid : Int , var taskName : String , var priority : priority)

class TaskManager{
    var taskId = 1
    val tasks = mutableListOf<Task>()

    fun addTask(name : String , priority : priority){
        if(tasks.none{it.taskName == name}){
            tasks.add(Task(taskId, name , priority))
            println("Task Add Successfully!")
        }
        else{
            println("Already Exist")
        }
    }

    fun RemoveTask(id : Int){
        var task = tasks.find{it.taskid == id}
        if(task != null){
            tasks.remove(task)
            println("Task Removed Successfully!")
        }
        else{
            println("Task not found")
        }
    }

    fun SearchPriority(priority: priority){
        val tasks = tasks.filter{it.priority == priority}
        println("Task Priority : ${priority}")
        if(tasks.isNotEmpty()) {
            tasks.forEach {
                println("Id : ${it.taskid} , Task : ${it.taskName} ")
            }
        }
        else{
            println("Task not found")
        }
    }

    fun ChangePriority(id:Int , priority: priority){
        val task = tasks.find{it.taskid == id}
        if(task != null){
            task.priority = priority
            println("Task Priority Changed Successfully!")
        }
        else{
            println("Task not found")
        }
    }

    fun displayAllTask(){
        if(tasks.isNotEmpty()) {
            tasks.forEach {
                println("Id : ${it.taskid} , Task : ${it.taskName} , Priority : ${it.priority}")
            }
        }
        else{
            println("Task not found")
        }
    }
}