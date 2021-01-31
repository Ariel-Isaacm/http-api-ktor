package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.models.orderStorage
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*

fun Route.listOrderRoute() {
    get("/order") {
        if (orderStorage.isNotEmpty()) {
            call.respond(orderStorage)
        }
    }
}

fun Route.getOrderRoute() {
    get("/order/{id}") {
        val id = call.parameters["id"] ?: return@get call.respondText("Missing id", status = HttpStatusCode.BadRequest)
        val order =
            orderStorage.find { it.number == id } ?: return@get call.respondText(
                "Order not found",
                status = HttpStatusCode.NotFound
            )
        call.respond(order)
    }
}

fun Route.getOrderTotalRoute() {
    get("/order/{id}") {
        val id = call.parameters["id"] ?: return@get call.respondText("Missing id", status = HttpStatusCode.BadRequest)
        val order = orderStorage.find { it.number == id } ?: return@get call.respondText(
            "Id not found",
            status = HttpStatusCode.NotFound
        )
        val total = order.contents.map {it.price * it.amount}.sum()
        call.respond(total)
    }
}