package com.bibek.core.utils

/**
 * Author: Bibekananda Nayak
 *
 * Date: 01-05-2024
 *
 * Description: This sealed class represents various destinations within an application's navigation flow.
 * Each destination is defined as an object within the sealed class and is initialized with a route string.
 * The sealed class restricts inheritance, allowing only the defined objects within it.
 *
 */
sealed class Destination (val route : String){
    data object Home : Destination("Home")
    data object RecipeDetails : Destination("RecipeDetails")
    data object AddRecipe : Destination("AddRecipe")
    data object CustomerDetailsScreen : Destination("CustomerDetailsScreen")
    data object AddressDetailsScreen : Destination("AddressDetailsScreen")
    data object UploadLoanDetailsScreen : Destination("UploadLoanDetailsScreen")

    data object CaseDetailsScreen : Destination("CaseDetailsScreen")
    data object AssetDetailsScreen : Destination("AssetDetailsScreen")
    data object ApplicantDetailsScreen : Destination("ApplicantDetailsScreen")
    data object ComAddressDetailsScreen : Destination("ComAddressDetailsScreen")
}
