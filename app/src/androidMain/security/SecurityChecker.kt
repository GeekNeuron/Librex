package com.geekneuron.librex.security

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.util.Base64
import com.google.android.gms.safetynet.SafetyNet
import com.google.android.gms.safetynet.SafetyNetApi
import com.google.android.gms.tasks.Tasks
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Inject

class AndroidSecurityChecker @Inject constructor(private val context: Context) {
    fun verifyAppIntegrity(): Boolean {
        val signature = getSignature(context)
        if (signature != "YOUR_EXPECTED_SIGNATURE") {
            return false
        }

        val safetyNetTask = SafetyNet.getClient(context).attest(byteArrayOf(), "YOUR_API_KEY")
        try {
            val response = Tasks.await(safetyNetTask)
            val jwsResult = response.jwsResult
            // Verify JWS result
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    private fun getSignature(context: Context): String? {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(
                context.packageName,
                PackageManager.GET_SIGNATURES
            )
            val signature = packageInfo.signatures[0]
            sha256(signature.toByteArray())
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }

    private fun sha256(bytes: ByteArray): String {
        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            val hashBytes = digest.digest(bytes)
            Base64.encodeToString(hashBytes, Base64.NO_WRAP)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            ""
        }
    }
}
