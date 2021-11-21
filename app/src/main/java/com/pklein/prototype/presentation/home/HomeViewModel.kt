package com.pklein.prototype.presentation.home

import androidx.lifecycle.viewModelScope
import com.pklein.prototype.model.Product
import com.pklein.prototype.model.details.ProductDetails
import com.pklein.prototype.presentation.BaseViewModel
import com.pklein.prototype.utils.Action
import com.pklein.prototype.utils.State
import kotlinx.coroutines.launch

data class HomeState(
    var productsToShow: List<Product>? = null,
    var detailsToShow: ProductDetails? = null,
) : State

sealed class HomeAction : Action {
    object Search : HomeAction()
    data class GetDetails(val productId: Long) : HomeAction()
}

class HomeViewModel(
    private val homeInteractor: HomeInteractor = HomeInteractor(),
    initialState: HomeState = HomeState()
) : BaseViewModel<HomeState, HomeAction>(initialState) {

    var productList: List<Product>? = listOf()

    override fun handle(action: HomeAction) {
        when (action) {
            is HomeAction.Search -> search()
            is HomeAction.GetDetails -> getDetails(action.productId)
        }
    }

    private fun search() {
        viewModelScope.launch {
            productList = homeInteractor.search()
            updateState {
                it.copy(
                    productsToShow = productList
                )
            }
        }
    }

    private fun getDetails(productId: Long) {
        viewModelScope.launch {
            val details = homeInteractor.getDetails(productId)
            updateState {
                it.copy(
                    detailsToShow = details
                )
            }
        }
    }

}