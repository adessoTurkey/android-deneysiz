package com.deneyehayir.deneysiz.data.local.model

import com.deneyehayir.deneysiz.domain.model.BankAccountDomainModel
import com.deneyehayir.deneysiz.domain.model.DonationDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class DonationDataModel(
    val description: String,
    val bankAccount: BankAccountDataModel,
    val donationUrl: String
)

@Serializable
data class BankAccountDataModel(
    val name: String,
    val currency: String,
    val address: String,
    val iban: String
)

fun DonationDataModel.toDomain() = DonationDomainModel(
    description = description,
    bankAccountDomainModel = bankAccount.toDomain(),
    donationUrl = donationUrl
)

fun BankAccountDataModel.toDomain() = BankAccountDomainModel(
    name = name,
    currency = currency,
    address = address,
    iban = iban
)
