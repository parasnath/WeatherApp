package offline.store.weatherapp.repository


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import offline.store.weatherapp.model.City
import offline.store.weatherapp.network.ApiServiceImp
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    fun getCityData(city:String):Flow<City> = flow {
        val response= apiServiceImp.getCity(city,"65002d69cfaa634d47485d12984deeae")
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()
}