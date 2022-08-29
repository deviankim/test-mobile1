package com.rsupport.mobile1.test.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.rsupport.mobile1.test.TestCoroutineRule
import com.rsupport.mobile1.test.data.model.Photo
import com.rsupport.mobile1.test.data.repository.DataRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: DataRepository

    @Mock
    private lateinit var liveDataObserver: Observer<List<Photo>>

    private lateinit var viewModel: MainViewModel

    private val page = 1

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(repository)
    }

    @Test
    fun crawlFromWeb_repositoryShouldBeCalled() {
        testCoroutineRule.runBlockingTest {
            viewModel.crawlFromWeb(page)
            verify(repository, times(1)).crawlFromWeb(page)
        }
    }

    @Test
    fun crawlFromWeb_liveDataShouldBeCorrect() {
        testCoroutineRule.runBlockingTest {
            val list = listOf(Photo("test1"), Photo("test2"))

            doReturn(list)
                .`when`(repository)
                .crawlFromWeb(page)

            viewModel.crawlFromWeb(page)
            viewModel.photos.observeForever(liveDataObserver)
            verify(repository).crawlFromWeb(page)
            verify(liveDataObserver).onChanged(list)
            viewModel.photos.removeObserver(liveDataObserver)
        }
    }
}