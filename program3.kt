
// FINDING NUMBER IS MAXIMUM AND MINIMUM USING ARRAY

fun main(){
    val number = intArrayOf(43,56,6765,645,56675,87575,5433456,35462,676,123,423)
    val max = maxfinder(number)
    val min = minfinder(number)
    println("Maximum number is : $max")
    println("Minimum number is : $min")
}
fun maxfinder(array:IntArray):Int{
    var default = array[0]
    for ( num in array){
        if(num > default){
            default = num
        }
    }
    return default
}
fun minfinder(array:IntArray) :Int {
    var default = array[0]
    for ( num in array){
        if(num < default){
            default = num
        }
    }
    return default
}
