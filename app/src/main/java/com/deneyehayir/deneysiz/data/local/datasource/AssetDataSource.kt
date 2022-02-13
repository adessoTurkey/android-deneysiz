package com.deneyehayir.deneysiz.data.local.datasource

import android.content.Context
import com.deneyehayir.deneysiz.data.local.model.CertificateDataModel
import com.deneyehayir.deneysiz.data.local.model.DoYouKnowDataModel
import com.deneyehayir.deneysiz.data.local.model.DonationDataModel
import com.deneyehayir.deneysiz.data.local.model.SupportDataModel
import com.deneyehayir.deneysiz.data.local.model.WhoWeAreDataModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AssetDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun getCertificates(): CertificateDataModel = invoke(CERTIFICATES)

    suspend fun getDoYouKnowData(): DoYouKnowDataModel = invoke(DO_YOU_KNOW)

    suspend fun getWhoWeAreData(): WhoWeAreDataModel = invoke(WHO_WE_ARE)

    suspend fun getDonationData(): DonationDataModel = invoke(DONATION)

    suspend fun getSupportData(): SupportDataModel = invoke(SUPPORT)

    private suspend inline fun <reified T> invoke(jsonPath: String): T {
        return suspendCancellableCoroutine { continuation ->
            continuation.resume(
                value = readFromJson(jsonPath).getResultAsDataModel(),
                onCancellation = { continuation.cancel() }
            )
        }
    }

    private fun readFromJson(filePath: String): String {
        return context.resources
            .assets
            .open(filePath)
            .bufferedReader()
            .readText()
    }

    private inline fun <reified T> String.getResultAsDataModel(): T {
        return Json.decodeFromString(string = this)
    }

    companion object {
        const val CERTIFICATES = "local/response_certificates.json"
        const val DO_YOU_KNOW = "local/response_do_you_know.json"
        const val WHO_WE_ARE = "local/response_who_we_are.json"
        const val DONATION = "local/response_donation.json"
        const val SUPPORT = "local/response_support.json"
    }
}
