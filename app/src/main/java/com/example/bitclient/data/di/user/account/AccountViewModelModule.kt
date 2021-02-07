package com.example.bitclient.data.di.user.account

import androidx.lifecycle.ViewModel
import com.example.bitclient.data.di.ViewModelKey
import com.example.bitclient.ui.viewmodels.AccountViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AccountViewModelModule {
    @AccountScope
    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(viewModel: AccountViewModel): ViewModel
}