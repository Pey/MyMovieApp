package pr.peyman.movieapptest.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import kotlinx.coroutines.flow.map

class DataStore @Inject constructor(@ApplicationContext val context: Context) {


    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constance.USER_DATA_INFO)
        val userToken = stringPreferencesKey(Constance.USER_TOKEN)
    }

    suspend fun setUserToken(token: String) {
        context.dataStore.edit {

            it[userToken] = token

        }

    }


    suspend fun getUserToken() = context.dataStore.data

    //or

//    suspend fun getUserToken() = context.dataStore.data.map {
//        it[userToken] ?: ""
//    }

}