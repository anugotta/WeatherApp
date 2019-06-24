package com.asp.weatherapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM : ViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: VM

    @LayoutRes
    protected abstract fun provideLayout(): Int

    protected abstract fun provideViewModelClass(): Class<VM>

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayout())
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(provideViewModelClass())
    }

    protected fun getViewModel(): VM {
        return viewModel
    }
}
