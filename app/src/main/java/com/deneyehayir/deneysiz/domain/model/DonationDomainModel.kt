package com.deneyehayir.deneysiz.domain.model

data class DonationDomainModel(
    val description: String,
    val bankAccountDomainModel: BankAccountDomainModel,
    val donationUrl: String
)

data class BankAccountDomainModel(
    val name: String,
    val currency: String,
    val address: String,
    val iban: String
)
