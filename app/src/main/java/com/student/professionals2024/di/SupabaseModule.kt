package com.student.professionals2024.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.realtime.realtime
import io.github.jan.supabase.serializer.JacksonSerializer
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SupabaseModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://elnobcponnyeswpnupdi.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImVsbm9iY3Bvbm55ZXN3cG51cGRpIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDkwNDM1MDQsImV4cCI6MjAyNDYxOTUwNH0.QXNyYoaAguvutp4JyYZdK5_H1lcDAYr1B7kcrZyRm1M",
        ) {
            install(Auth)
            install(Postgrest)
            install(Realtime)
            defaultSerializer = JacksonSerializer()
        }
    }

    @Provides
    @Singleton
    fun provideAuth(client: SupabaseClient): Auth {
        return client.auth
    }

    @Provides
    @Singleton
    fun providePostgrest(client: SupabaseClient): Postgrest {
        return client.postgrest
    }

    @Provides
    @Singleton
    fun provideRealtime(client: SupabaseClient): Realtime {
        return client.realtime
    }
}