package offline.store.weatherapp.network

import offline.store.weatherapp.model.City
import javax.inject.Inject

class ApiServiceImp @Inject constructor(private val apiService: ApiService) {

    suspend fun getCity(city:String,appId:String): City = apiService.getCityData(city,appId)
}