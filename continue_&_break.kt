fun main() {
   var prompt1 = "Enter a Range Upto : "
   var prompt2 = "Enter a Start Number From : "
   var number = getinputstart(prompt1)
   var i = getinputlast(prompt2)
   while( i <= number){
      i++
      if(i % 2 == 1){
         continue
      }
      println(i)
   }
}
fun getinputstart(prompt : String):Int {
   println(prompt)
   return readLine()?.toIntOrNull() ?: run() {
      println("Enter a Integer Number")
      getinputstart(prompt)
   }
}
fun getinputlast(prompt:String):Int{
   println(prompt)
   return readLine() ?. toIntOrNull() ?: run(){
      println("Enter a Integer Number")
      getinputlast(prompt)
   }

}