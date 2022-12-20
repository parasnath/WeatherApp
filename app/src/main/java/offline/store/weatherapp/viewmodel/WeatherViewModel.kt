package offline.store.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import offline.store.weatherapp.model.City
import offline.store.weatherapp.model.Main
import offline.store.weatherapp.model.Weather
import offline.store.weatherapp.model.Wind
import offline.store.weatherapp.repository.WeatherRepository
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val _weatherResponse: MutableStateFlow<City> = MutableStateFlow(
        City(listOf(Weather()),
        Main(),
        Wind())
    )
    val weatherResponse: StateFlow<City> = _weatherResponse
    private val searchChannel = MutableSharedFlow<String>(1)


    fun setSearchQuery(search: String) {
        searchChannel.tryEmit(search)
    }

    init {
        getCityData()
    }


    private fun getCityData() {
        viewModelScope.launch {
            searchChannel
                .flatMapLatest { search ->
                    weatherRepository.getCityData(search)
                }.catch { e ->
                    Log.d("main", "${e.message}")
                }.collect { response ->
                    _weatherResponse.value = response
                    Log.d("response---", "getCityData: "+response)
                }
        }
    }


}