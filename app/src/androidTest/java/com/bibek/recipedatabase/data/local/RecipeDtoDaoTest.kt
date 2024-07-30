package com.bibek.recipedatabase.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bibek.dashboard.data.local.RecipeDao
import com.bibek.dashboard.data.local.model.search.RecipeEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class RecipeDtoDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // Inject the RecipeDatabase instance using Hilt
    @Inject
    lateinit var recipeDatabase: RecipeDatabase

    private lateinit var recipeDao: RecipeDao

    @Before
    fun init() {
        hiltRule.inject() // Initialize Hilt injection
        recipeDao = recipeDatabase.recipeDao() // Get the RecipeDao instance from the database
    }

    @Test
    fun upsertAllRecipe_expected10Recipe() = runTest {
        val recipeList = createRecipeList()
        launch {
            recipeDao.upsertAll(recipeList) // Insert the list of recipes into the database
        }.join()
        val recipeListFromDb = recipeDao.getAllRecipe().first().toList() // Retrieve all recipes from the database
        assert(10 == recipeListFromDb.size) // Assert that there are 10 recipes in the database
    }
    @Test
    fun deleteAllRecipe_expected0Recipe() = runTest {
        val recipeList = createRecipeList()

        // Ensure the database is initially empty
        val recipeListFromDbBefore = recipeDao.getAllRecipe().first().toList()
        assert(recipeListFromDbBefore.isEmpty())

        // Insert the list of recipes into the database
        launch {
            recipeDao.upsertAll(recipeList)
        }.join()

        val recipeListFromDbAfterInsert = recipeDao.getAllRecipe().first().toList()
        assert(recipeListFromDbAfterInsert.size == 10) // Assert that there are 10 recipes in the database

        // Delete all recipes from the database
        launch {
            recipeDao.deleteAll()
        }.join()

        // Assert that the database is empty
        val recipeListFromDbAfterDelete = recipeDao.getAllRecipe().first().toList()
        assert(recipeListFromDbAfterDelete.isEmpty())
    }

    // Helper function to create a list of Recipe objects
    private fun createRecipeList(size: Int = 10): List<RecipeEntity> {
        return List(size) { index ->
            RecipeEntity(
                id = index,
                image = "https://example.com/image_$index.jpg",
                imageType = "jpg",
                title = "Recipe Title $index"
            )
        }
    }

    @After
    fun tearDown() {
        recipeDatabase.close() // Close the database after tests
    }
}
