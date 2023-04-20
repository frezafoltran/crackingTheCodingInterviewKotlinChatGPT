class CallCenter {
    private val respondents: MutableList < Employee > = mutableListOf()
    private val managers: MutableList < Employee > = mutableListOf()
    private val directors: MutableList < Employee > = mutableListOf()

    init {
        for (i in 1..10) {
            respondents.add(Respondent())
        }
        for (i in 1..3) {
            managers.add(Manager())
        }
        directors.add(Director())
    }

    fun dispatchCall() {
        val availableRespondent = findAvailableEmployee(respondents)
        if (availableRespondent != null) {
            availableRespondent.handleCall()
            return
        }
        val availableManager = findAvailableEmployee(managers)
        if (availableManager != null) {
            availableManager.handleCall()
            return
        }
        val availableDirector = findAvailableEmployee(directors)
        if (availableDirector != null) {
            availableDirector.handleCall()
        } else {
            println("All employees are busy. Please wait.")
        }
    }

    private fun findAvailableEmployee(employees: List < Employee >): Employee? {
        for (employee in employees) {
            if (employee.isFree()) {
                return employee
            }
        }
        return null
    }
}


abstract class Employee {
    private var onCall = false

    fun isFree(): Boolean = !onCall

    fun handleCall() {
        onCall = true
        println("${this.javaClass.simpleName} is handling a call.")
        Thread.sleep(5000)
        onCall = false
        println("${this.javaClass.simpleName} has finished handling a call.")
    }
}


class Respondent:
    Employee()


class Manager:
    Employee()


class Director:
    Employee()


class CallCenterTest {

    @ Test
    fun `test dispatchCall`() {
        val callCenter = CallCenter()
        for (i in 1..10) {
            callCenter.dispatchCall()
        }
        // all respondents are busy
        callCenter.dispatchCall()
        // all respondents and managers are busy
        callCenter.dispatchCall()
        // all employees are busy
        callCenter.dispatchCall()
    }
}
