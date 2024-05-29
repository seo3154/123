package com.daelim.database.small

import com.daelim.database.core.entity.UserEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import net.datafaker.Faker
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

@SpringBootTest
class UserSpec {
    private val faker = Faker()

    private lateinit var randomUser: UserEntity

    @BeforeEach
    fun setup() {
        val username = faker.internet().username()
        val password = faker.internet().password()
        randomUser = UserEntity(
            username = username,
            password = password,
            address = address
        )
    }

    @Test
    @DisplayName("8~16 자리의 비밀 번호")
    fun `8~16 자리의 비밀 번호`() {
        // Given - setup()에서 이미 처리됨

        // When
        val password = randomUser.password

        // Then
        assertTrue(password.length in 8..16)
    }
}