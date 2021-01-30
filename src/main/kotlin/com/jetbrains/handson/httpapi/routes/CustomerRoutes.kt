package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.models.customerStorage
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
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

        }
        delete("{id}") {

        }

    }
}