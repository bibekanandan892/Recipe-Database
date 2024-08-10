package com.bibek.recipedatabase.di

//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [RoomDatabaseModule::class]
//)
//object RoomDatabaseModuleTest {
//    @Singleton
//    @Provides
//    fun provideRoomDataBase(@ApplicationContext context: Context): RecipeDatabase =
//        Room.inMemoryDatabaseBuilder(
//            context.applicationContext,
//            RecipeDatabase::class.java,
//        )
//            .allowMainThreadQueries()
//            .build()
//
//    @Singleton
//    @Provides
//    fun provideRecipeDao(recipeDatabase: RecipeDatabase): RecipeDao = recipeDatabase.recipeDao()
//
//    @Singleton
//    @Provides
//    fun provideRecipeAlarmDao(recipeDatabase: RecipeDatabase): RecipeAlarmDao {
//        return recipeDatabase.recipeAlarmDao()
//    }
//
//}