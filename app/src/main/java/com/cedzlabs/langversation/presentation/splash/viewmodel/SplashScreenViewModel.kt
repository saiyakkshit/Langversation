package com.cedzlabs.langversation.presentation.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cedzlabs.langversation.domain.models.Response
import com.cedzlabs.langversation.domain.usecase.CheckUserAuthSignedInUseCase
import com.cedzlabs.langversation.presentation.splash.SplashScreenContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class SplashScreenViewModel @Inject constructor(
    private val checkUserAuthSignedInUseCase: CheckUserAuthSignedInUseCase
) : ViewModel(), SplashScreenContract.SplashScreenViewModel {

    private val _signInSuccess = MutableLiveData<Response<Boolean>>()
    val signInSuccess: LiveData<Response<Boolean>> = _signInSuccess

    override fun checkUserAuthSignedIn() {
        viewModelScope.launch(Dispatchers.Main) {
            checkUserAuthSignedInUseCase.execute().collect { _signInSuccess.postValue(it) }
        }
    }
}