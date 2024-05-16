import java.beans.BeanDescriptor
import java.util.Scanner
fun main(){
    val scanner = Scanner(System.`in`)
    val toDoList = ToDoList()

    println("Welcome to ToDoList Application! \n")

    var choice = 0
    while(choice != 5){
        println("\n1. Add Task")
        println("2. Mark Task as Completed")
        println("3. view All Tasks")
        println("4. Remove Task")
        println("5. Exit \n")

        println("Enter Your Choice :")
        choice = scanner.nextInt()

        when(choice){
            1 -> {
                println("Enter task description :")
                val description = readLine()!!
                toDoList.addTask(description)
            }
            2 -> {
                println("Enter the Id Number is Completed")
                var taskId = scanner.nextInt()
                toDoList.markTaskCompleted(taskId)
            }
            3 -> {
                 toDoList.DisplayTasks()
            }
            4 -> {
                println("Enter the task Id to Remove")
                val taskId = scanner.nextInt()
                toDoList.removeTask(taskId)
            }
            5 -> println("Good Bye!!")
            else -> {
                println("Invalid Choice")
            }
        }
    }
}

data class Task(val id : Int , val description : String , var isCompleted : Boolean = false)

class ToDoList{
    private val tasks = mutableListOf<Task>()
    private var taskIdCounter = 1

    fun addTask(description : String){
        val task = Task(taskIdCounter++ , description)
        tasks.add(task)
        println("Task added Successfully")
    }

    fun markTaskCompleted(taskId : Int){
        var task = tasks.find {it.id == taskId}
        if(task != null){
            task.isCompleted = true
            println("Task Id : ${task.id} marked as Completed")
        }
        else{
            println("Task is not found")
        }
    }

    fun DisplayTasks(){
        println("Tasks \n")
         tasks.forEach{task ->
            val status = if(task.isCompleted){
                "Completed"
            }
            else{
                "Not Completed"
            }
            println("Task Id : ${task.id} , Description : '${task.description}' , Status : ${status} ")
        }
    }

    fun removeTask(taskId:Int){
        val task = tasks.find{it.id == taskId}
        if(task != null){
            tasks.remove(task)
            println("Task is removed from list")
        }
        else{
            println("THE TASK IS NOT FOUNDED")
        }
    }

}