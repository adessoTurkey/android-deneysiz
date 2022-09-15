package com.deneyehayir.deneysiz.domain.model

const val LEAPING_BUNNY = "Leaping Bunny"
const val BEAUTY_WITHOUT_BUNNIES = "Beauty Without Bunnies"
const val VEGAN_SOCIETY = "Vegan Society"
const val V_LABEL = "V-Label"

enum class CertificateType(
    val type: String,
    val id: Int // should be read from json?
) {
    LeapingBunny(LEAPING_BUNNY, 0),
    VeganSociety(VEGAN_SOCIETY, 1),
    BeautyWithoutBunnies(BEAUTY_WITHOUT_BUNNIES, 2),
    VLabel(V_LABEL, 3),
    None("", -1)
}
