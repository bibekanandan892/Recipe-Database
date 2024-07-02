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
enum class Destination{
    HOME,
    RECIPE_DETAILS,
    ADD_RECIPE
}
