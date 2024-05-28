import java.util.*

val scanner = Scanner(System.`in`)

sealed class Vehicle( var VehicleType: String, var RegisterNumber: String , var owner : String){
    class Car(VehicleType :String , RegisterNumber : String , owner : String , val capacity : Int):Vehicle(VehicleType, RegisterNumber , owner)
    class Bike(VehicleType : String, RegisterNumber : String , owner : String , val Cylinder : Int):Vehicle(VehicleType,RegisterNumber , owner)
    class Truck(VehicleType : String, RegisterNumber : String , owner : String , val LoadCapacity : String):Vehicle(VehicleType , RegisterNumber , owner)
    class nullvehicle:Vehicle("","","")
}

enum class Type{
    CAR,
    BIKE,
    TRUCK;
}

class VehicleRegistry{
    var vehicles = mutableListOf<Vehicle>()
    fun addVechile(vehicle : Vehicle){
        if(vehicle.RegisterNumber != "" || vehicle.owner != "") {
            if (vehicles.none { it.RegisterNumber == vehicle.RegisterNumber || it.owner == vehicle.owner}) {
                vehicles.add(vehicle)
                println("Vehicle Add Succssfully!")
            } else {
                println("Vehicle Owner Name Or Registor Number Already Exist")
            }
        }
    }

    fun removeVehicle(detail : String){
        var vehicle = vehicles.find{it.RegisterNumber.equals(detail) || it.owner.equals(detail, ignoreCase = true)}
        if (vehicle != null){
            vehicles.remove(vehicle)
            println("Vehicle removed Successfully!")
        }
        else{
            println("Vehicle Not Found")
        }
    }

    fun searchVechile(detail: String){
        var it = vehicles.find{it.RegisterNumber.equals(detail) || it.owner.equals(detail, ignoreCase = true)}
        if (it != null){
            var spec : String
            when(it){
                is Vehicle.Car -> {println(" Vehicle : ${it.VehicleType} , RegNo : ${it.RegisterNumber} , Owner : ${it.owner} , Seat Capacity : ${it.capacity}")}
                is Vehicle.Bike -> {println(" Vehicle : ${it.VehicleType} , RegNo : ${it.RegisterNumber} , Owner : ${it.owner} , Number Of Cylinder : ${it.Cylinder}")}
                is Vehicle.Truck -> {println(" Vehicle : ${it.VehicleType} , RegNo : ${it.RegisterNumber} , Owner : ${it.owner} , Load Capacity : ${it.LoadCapacity}")}
                else -> println(" - ")
            }
        }
        else{
            println("Vehicle Not Found")
        }
    }

    fun DisplayAllVehicle(){
        println("\n All Vehicles : ")
        if(vehicles.isNotEmpty()){
            vehicles.forEach{it ->
                when(it){
                    is Vehicle.Car -> {println(" Vehicle : ${it.VehicleType} , RegNo : ${it.RegisterNumber} , Owner : ${it.owner} , Seat Capacity : ${it.capacity}")}
                    is Vehicle.Bike -> {println(" Vehicle : ${it.VehicleType} , RegNo : ${it.RegisterNumber} , Owner : ${it.owner} , Number Of Cylinder : ${it.Cylinder}")}
                    is Vehicle.Truck -> {println(" Vehicle : ${it.VehicleType} , RegNo : ${it.RegisterNumber} , Owner : ${it.owner} , Load Capacity : ${it.LoadCapacity}")}
                    else -> { println("Vehicles Not Found")}
                }
            }
        }
        else{
            println(" Empty ")
        }
    }

}
fun main(){
    println("\nWelcome to Vehicle Registration System")
    var registry = VehicleRegistry()
    do{
        println("\n1. Add Vehicle")
        println("2. Remove Vehicle")
        println("3. Search Vehicle")
        println("4. Display All Vehicle")
        println("5. Exit")
        print("Select your choice : ")
        var choice : Int = scanner.nextInt()
        scanner.nextLine()

        when(choice){
            1 -> {
                print("Enter Type of Vehicle [Car,Bike,Truck]: ")
                var type = Type.valueOf(scanner.nextLine().uppercase())
                var details = TypeSpec(type)
                registry.addVechile(details)
            }

            2 -> {
                print("Enter Name or Register Number :")
                var detail = scanner.nextLine()
                if (detail.isNotEmpty()) {
                    registry.removeVehicle(detail)
                }
                else{
                    println("Error : Please enter Name and Register Number")
                }
            }

            3 -> {
                print("Enter Name or Register Number :")
                var detail = scanner.nextLine()
                if (detail.isNotEmpty()) {
                    registry.searchVechile(detail)
                }
                else{
                    println("Error : Please enter Name and Register Number")
                }
            }

            4 -> {
                registry.DisplayAllVehicle()
            }

            5 -> {
                println("Thank you for using the Vehicle Registration System. Goodbye!")
                break
            }

            else -> {
                println("Invalid Option")
            }
        }
    }
        while(true)
}

fun TypeSpec(type : Type):Vehicle{
    return when(type){
       Type.CAR -> {
            print("Owner Name : ")
            var name = scanner.nextLine()
            print("Register Number : ")
            var number = scanner.nextLine()
            print("Seat Capacity : ")
            var seat = scanner.nextInt()
            scanner.nextLine()
            if(name.isNotEmpty()&& number.isNotEmpty()){
                 Vehicle.Car("car" , number , name , seat)
            }
            else{
                println("Error : Please enter Name and Register Number")
                Vehicle.nullvehicle()
            }
        }

        Type.BIKE -> {
            print("Owner Name : ")
            var name = scanner.nextLine()
            print("Register Number : ")
            var number = scanner.nextLine()
            print("Number Of Cylinders : ")
            var cylinder = scanner.nextInt()
            scanner.nextLine()
            if(name.isNotEmpty()&& number.isNotEmpty()){
                Vehicle.Bike("Bike" , number , name , cylinder)
            }
            else{
                println("Error : Please enter Name and Register Number")
                Vehicle.nullvehicle()
            }
        }

        Type.TRUCK -> {
            print("Owner Name : ")
            var name = scanner.nextLine()
            print("Register Number : ")
            var number = scanner.nextLine()
            print("Load Capacity : ")
            var load = scanner.nextLine()
            if(name.isNotEmpty()&& number.isNotEmpty()){
                Vehicle.Truck("truck ", number, name ,  load)
            }
            else{
                println("Error : Please enter Name and Register Number")
                Vehicle.nullvehicle()
            }
        }
    }
}
