package com.example.desafio_android_joao_ricardi.extensios

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException



//fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)

fun getMD5EncryptedString(encTarget: String): String? {
    var mdEnc: MessageDigest? = null
    try {
        mdEnc = MessageDigest.getInstance("MD5")
    } catch (e: NoSuchAlgorithmException) {
        println("Exception while encrypting to md5")
        e.printStackTrace()
    } // Encryption algorithm
    mdEnc!!.update(encTarget.toByteArray(), 0, encTarget.length)
    var md5: String = BigInteger(1, mdEnc!!.digest()).toString(16)
    while (md5.length < 32) {
        md5 = "0$md5"
    }
    return md5
}
