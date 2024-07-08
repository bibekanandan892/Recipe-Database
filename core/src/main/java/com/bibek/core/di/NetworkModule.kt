package com.bibek.core.di

import android.content.Context
import com.bibek.core.BuildConfig
import com.bibek.core.utils.NetworkLogger
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
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
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
                header("x-api-key",BuildConfig.API_KEY)
            }
            install(Logging) {
                logger = NetworkLogger()
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