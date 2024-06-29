package com.bibek.core.di

import android.content.Context
import com.bibek.core.utils.connectivity.ConnectivityObserver
import com.bibek.core.utils.connectivity.ConnectivityObserverImpl
import com.bibek.core.utils.connectivity.connectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {
    /**
     * Provides a singleton instance of HttpClient for making network requests.
     * @return An instance of HttpClient configured with necessary settings.
     *
     * @author Bibekananda
     */
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {


        // Configure and return HttpClient instance
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(contentType = ContentType.Application.Json)
                json(contentType = ContentType.Application.FormUrlEncoded)
                json(contentType = ContentType.Text.Plain)
            }
            expectSuccess = true
            install(HttpTimeout) {
                socketTimeoutMillis = 60000
                requestTimeoutMillis = 60000
                connectTimeoutMillis = 60000
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
                accept(ContentType.Text.Plain)
                header("x-api-key","dd04b71f9ee94ba79f0fc7d0e6918653")
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }
        }
    }

    @Singleton
    @Provides
    fun provideConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver {
        return ConnectivityObserverImpl(context.connectivityManager)
    }
}