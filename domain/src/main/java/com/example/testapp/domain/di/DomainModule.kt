package com.example.testapp.domain.di

import com.example.testapp.domain.exception.ErrorConverter
import com.example.testapp.domain.exception.ErrorConverterImpl
import com.example.testapp.domain.exception.ErrorMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


const val IO_CONTEXT = "IO_CONTEXT"
const val ERROR_MAPPER_NETWORK = "ERROR_MAPPER_NETWORK"

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideErrorConverter(@Named(ERROR_MAPPER_NETWORK) errorMapper: ErrorMapper): ErrorConverter {
        return ErrorConverterImpl(setOf(errorMapper))
    }
}