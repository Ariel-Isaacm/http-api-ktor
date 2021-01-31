package com.jetbrains.handson.httpapi.models

import kotlinx.serialization.Serializable

@Serializable
data class Order(val number: String, val contents: List<OrderItem>)

@Serializable
data class OrderItem(val item: String, val amount: Int, val price: Double)

val orderStorage = listOf<Order>(Order(
    "2020-04-06-01", listOf<OrderItem>(
        OrderItem("Ham Sandwich", 2, 15.0),
        OrderItem("Water", 3, 20.0),
        OrderItem("Beer", 6, 5.0),
        OrderItem("CheeseCake", 1, 20.3)
    )),
    Order(
        "2020-03-08-01",  listOf<OrderItem>(
        OrderItem("Cheeseburger", 1, 8.50),
        OrderItem("Water", 2, 1.50),
        OrderItem("Coke", 2, 1.76),
        OrderItem("Ice Cream", 1, 2.35)
        )
    )
)