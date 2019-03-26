package com.mutualmobile.praxis.ui.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

  private var viewModelJob = SupervisorJob()
  //Use viewModelScope of Co-routines to perform any job on main thread.
  protected val workerScope = CoroutineScope(Dispatchers.IO + viewModelJob)

  override fun onCleared() {
    super.onCleared()
    Timber.d("unsubscribeFromDataStore(): ")
    viewModelJob.cancel()
  }
}
