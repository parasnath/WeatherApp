package offline.store.weatherapp.model

data class City(
   /* val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,*/
    /*val sys: Sys,
    val timezone: Int,
    val visibility: Int,*/
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val name: String=""
)