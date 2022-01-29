package com.deneyehayir.deneysiz.domain.model

const val LEAPING_BUNNY = "Leaping Bunny"
const val BEAUTY_WITHOUT_BUNNIES = "Beauty Without Bunnies"
const val VEGAN_SOCIETY = "Vegan Society"
const val V_LABEL = "V-Label"

enum class CertificateType(val type: String) {
    LeapingBunny(LEAPING_BUNNY),
    BeautyWithoutBunnies(BEAUTY_WITHOUT_BUNNIES),
    VeganSociety(VEGAN_SOCIETY),
    VLabel(V_LABEL),
    None("")
}
