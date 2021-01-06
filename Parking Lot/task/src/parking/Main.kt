package parking

import java.util.*

fun main() {

    val scanner = Scanner(System.`in`)
    val parkingLot = ParkingLot()

    while (true) {

        val input = scanner.nextLine()
        val command = input.split(" ")

        when (command[0]) {

            "exit" -> break
            "create" -> parkingLot.create(command[1].toInt())
            "park" -> parkingLot.park(Car(command[1], command[2]))
            "leave" -> parkingLot.leave(command[1].toInt())
            "status" -> parkingLot.status()
            "reg_by_color" -> parkingLot.regByColor(command[1])
            "spot_by_color" -> parkingLot.spotByColor(command[1])
            "spot_by_reg" -> parkingLot.spotByReg(command[1])
        }

    }
}

class Car(val carPlate: String = "", val carColor: String = "") {

    fun info(): String {
        return "$carPlate $carColor"
    }
}


class ParkingLot {

    private var spots = Array(0) { Car() }
    private var nCars = 0

    fun create(nSpots: Int) {

        spots = Array(nSpots) { Car() }
        nCars = 0
        println("Created a parking lot with $nSpots spots.")
    }

    fun status() {

        when {
            spots.isEmpty() -> {

                println("Sorry, a parking lot has not been created.")
            }
            nCars == 0 -> {
                println("Parking lot is empty.")

            }
            else -> {

                for (i in spots.indices) {

                    if (spots[i].carPlate != "") {
                        println("${i + 1} ${spots[i].info()}")
                    }
                }
            }
        }
    }

    fun park(car: Car) {

        if (spots.isEmpty()) {

            println("Sorry, a parking lot has not been created.")

        } else if (nCars == spots.size) {

            println("Sorry, the parking lot is full.")
        } else {

            for (i in spots.indices) {

                if (spots[i].carPlate == "") {
                    nCars++
                    spots[i] = car
                    println("${car.carColor} car parked in spot ${i + 1}.")
                    break
                }
            }
        }
    }

    fun leave(space: Int) {

        if (spots.isEmpty()) {

            println("Sorry, a parking lot has not been created.")
        } else {

            if (spots[space - 1].carPlate != "") {

                spots[space - 1] = Car()
                nCars--
                println("Spot $space is free.")
            }
        }
    }

    fun regByColor(color: String) {

        val cars = mutableListOf<String>()
        when {
            spots.isEmpty() -> {

                println("Sorry, a parking lot has not been created.")
            }
            nCars == 0 -> {
                println("Parking lot is empty.")

            }
            else -> {

                for (i in spots.indices) {

                    if (spots[i].carColor.equals(color, ignoreCase = true)) {
                        cars.add(spots[i].carPlate)
                    }
                }
                if (cars.isEmpty()) {
                    println("No cars with color $color were found.")
                } else {
                    printList(cars)
                }
            }
        }
    }

    fun spotByReg(reg: String) {

        val cars = mutableListOf<String>()
        when {
            spots.isEmpty() -> {

                println("Sorry, a parking lot has not been created.")
            }
            nCars == 0 -> {
                println("Parking lot is empty.")

            }
            else -> {

                for (i in spots.indices) {

                    if (spots[i].carPlate.equals(reg, ignoreCase = true)) {
                        cars.add("${i + 1}")
                    }
                }
                if (cars.isEmpty()) {
                    println("No cars with registration number $reg were found.")
                } else {

                    printList(cars)
                }
            }
        }
    }

    fun spotByColor(color: String) {

        val cars = mutableListOf<String>()
        when {
            spots.isEmpty() -> {

                println("Sorry, a parking lot has not been created.")
            }
            nCars == 0 -> {
                println("Parking lot is empty.")

            }
            else -> {

                for (i in spots.indices) {

                    if (spots[i].carColor.equals(color, ignoreCase = true)) {
                        cars.add("${i + 1}")
                    }
                }
                if (cars.isEmpty()) {
                    println("No cars with color $color were found.")
                } else {
                    printList(cars)
                }
            }
        }
    }

    private fun printList(list: MutableList<String>) {

        for (i in 0 until list.size - 1) {

            print("${list[i]}, ")
        }
        println(list.last())
    }
}