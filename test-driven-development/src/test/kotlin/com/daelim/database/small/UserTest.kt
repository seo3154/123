package com.daelim.database.small

import com.daelim.database.core.entity.UserEntity
import net.datafaker.Faker
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

@SpringBootTest
class UserTest {
    private val faker = Faker(Locale.KOREA)

    private lateinit var randomUser: UserEntity

    @BeforeEach
    fun createTest() {
        val id = faker.internet().username()
        val address = faker.address().streetAddress()
        val password = faker.internet().password()

        randomUser = UserEntity(
            username = id,
            address = address,
            password = password
        )
    }

    @Test
    @DisplayName("6~20 자리 비번")
    fun `6~20 자리 비번`() {
        val password = randomUser.password
        assertTrue(password.length in 6.. 20)
    }

    @Test
    @DisplayName("아무 주소")
    fun `아무 주소`() {
        val address = randomUser.address
    }
}
