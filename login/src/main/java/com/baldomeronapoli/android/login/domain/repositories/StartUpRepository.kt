package com.baldomeronapoli.android.login.domain.repositories

import com.baldomeronapoli.android.login.domain.models.User

interface StartUpRepository {
    suspend fun startUp(rut: String): User
}