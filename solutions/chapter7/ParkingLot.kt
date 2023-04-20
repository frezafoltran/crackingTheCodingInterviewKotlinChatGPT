class ParkingLot(val numFloors: Int, val spotsPerFloor: Int) {
    val spots = mutableListOf < Spot > ()
    init {
        for (floor in 1..numFloors) {
            for (spotNumber in 1..spotsPerFloor) {
                val spotType = when {
                    spotNumber % 5 == 0 -> SpotType.LARGE
                    spotNumber % 3 == 0 -> SpotType.COMPACT
                    else -> SpotType.REGULAR
                }
                val spot = Spot(floor, spotType, spotNumber)
                spots.add(spot)
            }
        }
    }

    fun park(vehicle: Vehicle): Spot? {
        for (spot in spots) {
            if (spot.canFitVehicle(vehicle)) {
                spot.park(vehicle)
                return spot
            }
        }
        return null
    }

    fun remove(vehicle: Vehicle) {
        for (spot in spots) {
            if (spot.vehicle == vehicle) {
                spot.removeVehicle()
                break
            }
        }
    }
}


enum class SpotType {
    REGULAR,
    COMPACT,
    LARGE
}


class Spot(val floor: Int, val spotType: SpotType, val number: Int) {
    var vehicle: Vehicle? = null

    fun canFitVehicle(vehicle: Vehicle): Boolean {
        return when(spotType) {
            SpotType.REGULAR -> vehicle is Car
            SpotType.COMPACT -> vehicle is Motorcycle | | vehicle is Car
            SpotType.LARGE -> vehicle is Bus | | vehicle is Car
        }
    }

    fun park(vehicle: Vehicle) {
        this.vehicle = vehicle
    }

    fun removeVehicle() {
        vehicle = null
    }

    fun isAvailable(): Boolean {
        return vehicle == null
    }
}


//sealed class Vehicle


class Car:
    Vehicle()


class Motorcycle:
    Vehicle()


class Bus:
    Vehicle()


class ParkingLotTest {

    @ Test
    fun `test park and remove`() {
        val parkingLot = ParkingLot(numFloors=2, spotsPerFloor=50)
        val car1 = Car()
        val car2 = Car()
        val motorcycle = Motorcycle()
        val bus = Bus()

        val spot1 = parkingLot.park(car1)
        assertNotNull(spot1)
        assertEquals(1, spot1?.floor)
        assertEquals(SpotType.REGULAR, spot1?.spotType)
        assertEquals(1, spot1?.number)

        val spot2 = parkingLot.park(motorcycle)
        assertNotNull(spot2)
        assertEquals(1, spot2?.floor)
        assertEquals(SpotType.COMPACT, spot2?.spotType)
        assertEquals(2, spot2?.number)

        val spot3 = parkingLot.park(bus)
        assertNull(spot3)

        parkingLot.remove(car1)
        assertTrue(spot1?.isAvailable() ?: false)

        val spot4 = parkingLot.park(bus)
        assertNotNull(spot4)
        assertEquals(1, spot4?.floor)
        assertEquals(SpotType.LARGE, spot4?.spotType)
        assertEquals(1, spot4?.number)

        val spot5 = parkingLot.park(car2)
        assertNotNull(spot5)
        assertEquals(1, spot5?.floor)
        assertEquals(SpotType.REGULAR, spot5?.spotType)
        assertEquals(1, spot5?.number)
    }
}
