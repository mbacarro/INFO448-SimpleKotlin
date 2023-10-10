package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(input: Any): String {
    when (input) {
        "Hello" -> return("world")
        "Howdy", "Bonjour" -> return("Say what?")
        is String -> return("I don't understand")
        0 -> return("zero")
        1 -> return("one")
        in 2..10 -> return("low number")
        is Int -> return("a number")
        else -> return("I don't understand")
    }
}
// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(x: Int, y: Int): Int {
    return x + y
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(x: Int, y: Int): Int {
    return x - y
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(x: Int, y: Int, func: (Int, Int) -> Int): Int {
    return(func(x,y))
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}


// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
data class Money(val amount: Int, val currency: String) {
    init {
        if (amount < 0) {
            throw IllegalArgumentException("Amount less than 0")
        }

        val validCurrencies = setOf("USD", "EUR", "CAN", "GBP")

        if (currency !in validCurrencies) {
            throw IllegalArgumentException("Invalid New Currency")
        }
    }
    
    fun convert(newCurrency: String): Money {
        val validCurrencies = setOf("USD", "EUR", "CAN", "GBP")

        if (newCurrency !in validCurrencies) {
            throw IllegalArgumentException("Invalid New Currency")
        }

        if (currency == newCurrency) {
            return Money(amount, currency)
        }

        val conversionRate = when (currency) {
            "USD" -> when (newCurrency) {
                "GBP" -> 1.0 / 2.0
                "EUR" -> 3.0 / 2.0
                "CAN" -> 5.0 / 4.0
                else -> 1.0
            }

            "GBP" -> when (newCurrency) {
                "USD" -> 2.0
                "EUR" -> 3.0
                "CAN" -> 5.0
                else -> 1.0
            }

            "EUR" -> when (newCurrency) {
                "USD" -> 2.0 / 3.0
                "CAN" -> 5.0 / 6.0
                "GBP" -> 1.0 / 3.0
                else -> 1.0
            }

            "CAN" -> when (newCurrency) {
                "USD" -> 4.0 / 5.0
                "EUR" -> 6.0 / 5.0
                "GBP" -> 2.0 / 5.0
                else -> 1.0
            }
            else -> 1.0
        }

        val newAmount = (amount * conversionRate).toInt()
        return Money(newAmount, newCurrency)
    }

    operator fun plus(other: Money): Money {
        val convertedOther = other.convert(currency)
        return Money(amount + convertedOther.amount, currency)
    }
}