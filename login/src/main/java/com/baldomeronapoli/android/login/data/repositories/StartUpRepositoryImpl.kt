package com.baldomeronapoli.android.login.data.repositories

import com.baldomeronapoli.android.login.domain.models.User
import com.baldomeronapoli.android.login.domain.repositories.StartUpRepository

class StartUpRepositoryImpl : StartUpRepository {
    override suspend fun startUp(rut: String): User {
        return User(rut, "Elnapoli")
    }
}