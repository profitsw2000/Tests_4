package com.geekbrains.tests.di

import com.geekbrains.tests.presenter.RepositoryContract
import com.geekbrains.tests.repository.FakeGitHubRepository
import com.geekbrains.tests.repository.GitHubApi
import com.geekbrains.tests.repository.GitHubRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<String>(named("base_url")) { "https://api.github.com/" }
    single { Retrofit.Builder()
        .baseUrl(get<String>(named("base_url")))
        .addConverterFactory(get())
        .build() }
    single<GitHubApi> { get<Retrofit>().create(GitHubApi::class.java) }
    single(named("gitHubRepository")) { GitHubRepository(get()) }
    single(named("fakeGitHubRepository")) { FakeGitHubRepository() }

    factory<Converter.Factory> { GsonConverterFactory.create() }
}