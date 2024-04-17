// FINDING YEAR IS LEAP YEAR OR NOT USINF IF ELSE STATEMENT

fun main(){
    val prompt = "Enter the year : "
    val year = findleapyear(prompt)
    if(year % 4 == 0){
        println("The Year $year is Leap Year")
    }
    else{
        println("The Year $year is Not Leap Year")
    }
}
fun  findleapyear(prompt:String) : Int{
    print(prompt)
    return readLine() ?. toIntOrNull() ?: run(){
        println("Enter a integer year ")
        findleapyear(prompt)
    }
}
