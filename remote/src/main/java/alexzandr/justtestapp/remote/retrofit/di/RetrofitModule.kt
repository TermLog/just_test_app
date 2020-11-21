package alexzandr.justtestapp.remote.retrofit.di

import alexzandr.justtestapp.remote.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [
        ApiModule::class
    ]
)
class RetrofitModule {

    companion object {
        const val HTTP_LOGGING = "RetrofitModule.HTTP_LOGGING"

        const val TIMEOUT = 30L
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    @Named(HTTP_LOGGING)
    fun provideHttpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClientBuilder(
        @Named(HTTP_LOGGING) loggingInterceptor: Interceptor
    ): OkHttpClient.Builder {

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .callTimeout(TIMEOUT, TimeUnit.SECONDS)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
        okHttpClientBuilder: OkHttpClient.Builder
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_V3)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}