package pr.peyman.movieapptest.models.home


import com.google.gson.annotations.SerializedName

/**
{
  "data": [
    {
      "id": 1,
      "title": "The Shawshank Redemption",
      "poster": "https://moviesapi.ir/images/tt0111161_poster.jpg",
      "year": "1994",
      "country": "USA",
      "imdb_rating": "9.3",
      "genres": [
        "Crime",
        "Drama"
      ],
      "images": [
        "https://moviesapi.ir/images/tt0111161_screenshot1.jpg",
        "https://moviesapi.ir/images/tt0111161_screenshot2.jpg",
        "https://moviesapi.ir/images/tt0111161_screenshot3.jpg"
      ]
    },
    {
      "id": 2,
      "title": "The Godfather",
      "poster": "https://moviesapi.ir/images/tt0068646_poster.jpg",
      "year": "1972",
      "country": "USA",
      "imdb_rating": "9.2",
      "genres": [
        "Crime",
        "Drama"
      ],
      "images": [
        "https://moviesapi.ir/images/tt0068646_screenshot1.jpg",
        "https://moviesapi.ir/images/tt0068646_screenshot2.jpg",
        "https://moviesapi.ir/images/tt0068646_screenshot3.jpg"
      ]
    },
    {
      "id": 3,
      "title": "The Godfather: Part II",
      "poster": "https://moviesapi.ir/images/tt0071562_poster.jpg",
      "year": "1974",
      "country": "USA",
      "imdb_rating": "9.0",
      "genres": [
        "Crime",
        "Drama"
      ],
      "images": [
        "https://moviesapi.ir/images/tt0071562_screenshot1.jpg",
        "https://moviesapi.ir/images/tt0071562_screenshot2.jpg",
        "https://moviesapi.ir/images/tt0071562_screenshot3.jpg"
      ]
    },
    {
      "id": 4,
      "title": "The Dark Knight",
      "poster": "https://moviesapi.ir/images/tt0468569_poster.jpg",
      "year": "2008",
      "country": "USA, UK",
      "imdb_rating": "9.0",
      "genres": [
        "Action",
        "Crime",
        "Drama"
      ],
      "images": [
        "https://moviesapi.ir/images/tt0468569_screenshot1.jpg",
        "https://moviesapi.ir/images/tt0468569_screenshot2.jpg",
        "https://moviesapi.ir/images/tt0468569_screenshot3.jpg"
      ]
    },
    {
      "id": 5,
      "title": "12 Angry Men",
      "poster": "https://moviesapi.ir/images/tt0050083_poster.jpg",
      "year": "1957",
      "country": "USA",
      "imdb_rating": "8.9",
      "genres": [
        "Crime",
        "Drama"
      ],
      "images": [
        "https://moviesapi.ir/images/tt0050083_screenshot1.jpg",
        "https://moviesapi.ir/images/tt0050083_screenshot2.jpg",
        "https://moviesapi.ir/images/tt0050083_screenshot3.jpg"
      ]
    },
    {
      "id": 7,
      "title": "Pulp Fiction",
      "poster": "https://moviesapi.ir/images/tt0110912_poster.jpg",
      "year": "1994",
      "country": "USA",
      "imdb_rating": "8.9",
      "genres": [
        "Crime",
        "Drama"
      ],
      "images": [
        "https://moviesapi.ir/images/tt0110912_screenshot1.jpg",
        "https://moviesapi.ir/images/tt0110912_screenshot2.jpg",
        "https://moviesapi.ir/images/tt0110912_screenshot3.jpg"
      ]
    },
    {
      "id": 17,
      "title": "Goodfellas",
      "poster": "https://moviesapi.ir/images/tt0099685_poster.jpg",
      "year": "1990",
      "country": "USA",
      "imdb_rating": "8.7",
      "genres": [
        "Biography",
        "Crime",
        "Drama"
      ],
      "images": [
        "https://moviesapi.ir/images/tt0099685_screenshot1.jpg",
        "https://moviesapi.ir/images/tt0099685_screenshot2.jpg",
        "https://moviesapi.ir/images/tt0099685_screenshot3.jpg"
      ]
    },
    {
      "id": 21,
      "title": "City of God",
      "poster": "https://moviesapi.ir/images/tt0317248_poster.jpg",
      "year": "2002",
      "country": "Brazil, France",
      "imdb_rating": "8.7",
      "genres": [
        "Crime",
        "Drama"
      ]
    },
    {
      "id": 22,
      "title": "Se7en",
      "poster": "https://moviesapi.ir/images/tt0114369_poster.jpg",
      "year": "1995",
      "country": "USA",
      "imdb_rating": "8.6",
      "genres": [
        "Crime",
        "Drama",
        "Mystery"
      ],
      "images": [
        "https://moviesapi.ir/images/tt0114369_screenshot1.jpg",
        "https://moviesapi.ir/images/tt0114369_screenshot2.jpg",
        "https://moviesapi.ir/images/tt0114369_screenshot3.jpg"
      ]
    },
    {
      "id": 23,
      "title": "The Silence of the Lambs",
      "poster": "https://moviesapi.ir/images/tt0102926_poster.jpg",
      "year": "1991",
      "country": "USA",
      "imdb_rating": "8.6",
      "genres": [
        "Crime",
        "Drama",
        "Thriller"
      ],
      "images": [
        "https://moviesapi.ir/images/tt0102926_screenshot1.jpg",
        "https://moviesapi.ir/images/tt0102926_screenshot2.jpg",
        "https://moviesapi.ir/images/tt0102926_screenshot3.jpg"
      ]
    }
  ],
  "metadata": {
    "current_page": "1",
    "per_page": 10,
    "page_count": 6,
    "total_count": 59
  }
}
*/
data class ResponseMovies(
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("metadata")
    val metadata: Metadata?
) {
    data class Data(
        @SerializedName("country")
        val country: String?, // USA
        @SerializedName("genres")
        val genres: List<String?>?,
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("images")
        val images: List<String?>?,
        @SerializedName("imdb_rating")
        val imdbRating: String?, // 9.3
        @SerializedName("poster")
        val poster: String?, // https://moviesapi.ir/images/tt0111161_poster.jpg
        @SerializedName("title")
        val title: String?, // The Shawshank Redemption
        @SerializedName("year")
        val year: String? // 1994
    )

    data class Metadata(
        @SerializedName("current_page")
        val currentPage: String?, // 1
        @SerializedName("page_count")
        val pageCount: Int?, // 6
        @SerializedName("per_page")
        val perPage: Int?, // 10
        @SerializedName("total_count")
        val totalCount: Int? // 59
    )
}