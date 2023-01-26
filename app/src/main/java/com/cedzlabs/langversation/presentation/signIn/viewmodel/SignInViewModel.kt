package com.cedzlabs.langversation.presentation.signIn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cedzlabs.langversation.domain.models.Response
import com.cedzlabs.langversation.domain.models.UserAuth
import com.cedzlabs.langversation.domain.usecase.DownloadLangRussianEnglishPackUseCase
import com.cedzlabs.langversation.domain.usecase.SignInUserAuthUseCase
import com.cedzlabs.langversation.presentation.signIn.SignInContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class SignInViewModel @Inject constructor(
    private val signInUserAuthUseCase: SignInUserAuthUseCase,
    private val downloadLangRussianEnglishPackUseCase: DownloadLangRussianEnglishPackUseCase
) : ViewModel(), SignInContract.SignInViewModel {

    private val _progressBarSignIn = MutableLiveData<Boolean>()
    val progressBarSignIn: LiveData<Boolean> = _progressBarSignIn

    private val _signInSuccess = MutableLiveData<Response<Boolean>>()
    val signInSuccess: LiveData<Response<Boolean>> = _signInSuccess

    private val _loadRussianEnglishPack = MutableLiveData<Response<Boolean>>()
    val loadRussianEnglishPack: LiveData<Response<Boolean>> = _loadRussianEnglishPack

    override fun signInUserAuth(userAuth: UserAuth) {
        viewModelScope.launch(Dispatchers.IO) {
            signInUserAuthUseCase.execute(userAuth = userAuth).collect { result ->
                when (result) {
                    is Response.Loading -> _progressBarSignIn.postValue(true)
                    is Response.Fail -> {
                        _progressBarSignIn.postValue(false)
                        _signInSuccess.postValue(Response.Fail(e = result.e))
                    }
                    is Response.Success -> {
                        _progressBarSignIn.postValue(false)
                        _signInSuccess.postValue(Response.Success(data = true))
                    }
                }
            }
        }
    }

    override fun downloadLangRussianEnglishPack() {
        viewModelScope.launch(Dispatchers.IO) {
            downloadLangRussianEnglishPackUseCase.execute().collect { result ->
                when (result) {
                    is Response.Loading -> {}
                    is Response.Fail -> _loadRussianEnglishPack.postValue(Response.Fail(e = result.e))
                    is Response.Success -> {}
                }
            }
        }
    }
}