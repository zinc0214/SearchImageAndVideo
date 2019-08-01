package han.ayeon.searchimgandvideo.di

import han.ayeon.searchimgandvideo.data.repository.searchlist.ImageRepositoryImpl
import han.ayeon.searchimgandvideo.data.repository.searchlist.MediaRepositoryImpl
import han.ayeon.searchimgandvideo.data.repository.searchlist.VideoRepositoryImpl
import han.ayeon.searchimgandvideo.domain.repository.searchlist.ImageRepository
import han.ayeon.searchimgandvideo.domain.repository.searchlist.MediaRepository
import han.ayeon.searchimgandvideo.domain.repository.searchlist.VideoRepository
import han.ayeon.searchimgandvideo.domain.usecases.searchlist.GetSearchMediaList
import han.ayeon.searchimgandvideo.presentation.viewmodel.SearchResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by HanAYeon on 2019-07-30.
 */

val viewModelModule = module {
    viewModel { SearchResultViewModel(get())}
}

val repositoryModule = module {

    single<MediaRepository> { MediaRepositoryImpl(get(), get()) }
    single<VideoRepository> { VideoRepositoryImpl() }
    single<ImageRepository> { ImageRepositoryImpl() }

}

val usecaseModule = module {
    factory { GetSearchMediaList(get()) }
}