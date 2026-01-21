package com.example.restaurantepopular.domain.session

object SessionManager {
    var userRole: UserRole? = null

    fun clear() {
        userRole = null
    }
}