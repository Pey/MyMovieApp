package pr.peyman.movieapptest.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pr.peyman.movieapptest.db.MovieDataBase
import pr.peyman.movieapptest.db.MovieEntity
import pr.peyman.movieapptest.utils.Constance
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): RoomDatabase =
        Room.databaseBuilder(
            context,
            MovieDataBase::class.java, Constance.DB_NAME
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideDao(db: MovieDataBase) = db.movieDao()

    @Provides
    @Singleton
    fun provideEntity() = MovieEntity()
}