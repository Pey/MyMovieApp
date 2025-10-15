package pr.peyman.movieapptest.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pr.peyman.movieapptest.models.register.BodyRegister
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RegisterModual {

    @Provides
    @Singleton
    fun provideBodyRegister() = BodyRegister()
}