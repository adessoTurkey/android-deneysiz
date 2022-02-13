package com.deneyehayir.deneysiz.scene.donation.model

import androidx.annotation.DrawableRes
import com.deneyehayir.deneysiz.R
import com.deneyehayir.deneysiz.domain.model.BankAccountDomainModel
import com.deneyehayir.deneysiz.domain.model.DonationDomainModel

data class DonationUiModel(
    val description: String,
    val bankAccountUiModel: BankAccountUiModel,
    val donationUrl: String
)

data class BankAccountUiModel(
    val name: String,
    val currency: String,
    val address: String,
    val iban: String,
    @DrawableRes val iconResId: Int
) {
    val displayTitle = "$name - $currency"
}

fun DonationDomainModel.toUiModel() = DonationUiModel(
    description = description,
    bankAccountUiModel = bankAccountDomainModel.toUiModel(),
    donationUrl = donationUrl
)

fun BankAccountDomainModel.toUiModel() = BankAccountUiModel(
    name = name,
    currency = currency,
    address = address,
    iban = iban,
    iconResId = R.drawable.ic_currency_try
)
