package com.jetbrains.handson.httpapi

import com.jetbrains.handson.httpapi.routes.customerRouting
import com.jetbrains.handson.httpapi.routes.getOrderRoute
import com.jetbrains.handson.httpapi.routes.getOrderTotalRoute
import com.jetbrains.handson.httpapi.routes.listOrderRoute
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.routing.routing
import io.ktor.serialization.json


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    registerCustomerRoutes()
    registerOrderRoutes()
}

fun Application.registerCustomerRoutes() {
    routing {
        customerRouting()
    }
}

fun Application.registerOrderRoutes() {
    routing {
        listOrderRoute()
        getOrderTotalRoute()
        getOrderRoute()
    }
}