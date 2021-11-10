package com.me.repo.base

import androidx.lifecycle.MutableLiveData



class StateLiveData<T> : MutableLiveData<BaseResp<T>>() {}