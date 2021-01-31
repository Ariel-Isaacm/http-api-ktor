package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.models.Customer
import com.jetbrains.handson.httpapi.models.customerStorage
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*


fun Route.customerRouting() {
    route("/customer") {
        get {
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            }
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id", status = HttpStatusCode.NotFound
            )
            val customer = customerStorage.find { it.id == id } ?: return@get call.respondText(
                "No customer with id \$id", status = HttpStatusCode.NotFound
            )
            call.respond(customer)
        }
        post {
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadGateway)
            if (customerStorage.removeIf { it.id == id }) {
                call.respondText("Customer removed with id: $id", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Customer not found", status = HttpStatusCode.NotFound)
            }
        }

    }
}