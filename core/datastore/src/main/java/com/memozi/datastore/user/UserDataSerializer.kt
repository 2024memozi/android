package com.memozi.datastore.user

import androidx.datastore.core.Serializer
import com.memozi.common.security.SecurityInterface
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class UserDataSerializer @Inject constructor(
    private val cryptoManager: SecurityInterface
) : Serializer<User> {
    private val securityKeyAlias = "data-store"
    override val defaultValue: User
        get() = User()

    override suspend fun readFrom(input: InputStream): User {
        val encryptedDataWithIv = input.readBytes()
        if (encryptedDataWithIv.size < 12) {
            return defaultValue
        }
        val iv = encryptedDataWithIv.copyOfRange(0, 12)
        val encryptedData = encryptedDataWithIv.copyOfRange(12, encryptedDataWithIv.size)
        val decryptedBytes = cryptoManager.decryptData(securityKeyAlias, encryptedData, iv)
        return try {
            Json.decodeFromString(
                deserializer = User.serializer(),
                string = decryptedBytes.decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: User, output: OutputStream) {
        cryptoManager.encryptData(
            securityKeyAlias,
            Json.encodeToString(
                serializer = User.serializer(),
                value = t
            )
        ).let {
            output.write(it.second + it.first)
        }
    }
}
