package com.memozi.datastore

import androidx.datastore.core.Serializer
import com.memozi.common.security.SecurityInterface
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class MemoziLocalDataSerializer @Inject constructor(
    private val securityManager: SecurityInterface
) : Serializer<MemoziLocalData> {
    private val securityKeyAlias = "data-store"
    override val defaultValue: MemoziLocalData
        get() = MemoziLocalData()

    override suspend fun readFrom(input: InputStream): MemoziLocalData {
        val encryptedDataWithIv = input.readBytes()
        if (encryptedDataWithIv.size < 12) {
            return defaultValue
        }
        val iv = encryptedDataWithIv.copyOfRange(0, 12)
        val encryptedData = encryptedDataWithIv.copyOfRange(12, encryptedDataWithIv.size)
        val decryptedBytes = securityManager.decryptData(securityKeyAlias, encryptedData, iv)
        return try {
            Json.decodeFromString(
                deserializer = MemoziLocalData.serializer(),
                string = decryptedBytes.decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: MemoziLocalData, output: OutputStream) {
        securityManager.encryptData(
            securityKeyAlias,
            Json.encodeToString(
                serializer = MemoziLocalData.serializer(),
                value = t
            )
        ).let {
            output.write(it.second + it.first)
        }
    }
}
