package ali.com.peopleviewerapp.network


import java.io.IOException

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


object ApiClient {

    val URL_BASE = "https://reqres.in"

    private var retrofit: Retrofit? = null

    val retrofitInstance: Retrofit
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(URL_BASE)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

            }
            return retrofit!!
        }
    // set your desired log level
    // .baseUrl(URL_BASE)
    val clientAuthentication: Retrofit?
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            val builder = Retrofit.Builder()
                    .baseUrl(URL_BASE)
            val interceptor = AuthenticationInterceptor(
                    Credentials.basic("", ""))
            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor)
                httpClient.addInterceptor(logging)
                builder.client(httpClient.build())
                retrofit = builder
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient.build())
                        .build()

            }

            return retrofit
        }

    class AuthenticationInterceptor(private val authToken: String) : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val original = chain.request()

            val builder = original.newBuilder()
                    .header("Authorization", authToken)//Remember header() vs addHeader

            val request = builder.build()
            return chain.proceed(request)
        }
    }
}
