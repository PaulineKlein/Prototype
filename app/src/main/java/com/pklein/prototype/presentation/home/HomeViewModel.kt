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
    object ResetDetails : HomeAction()
    data class Search(val keyword: String) : HomeAction()
    data class GetDetails(val productId: Long) : HomeAction()
}

class HomeViewModel(
    private val homeInteractor: HomeInteractor = HomeInteractor(),
    initialState: HomeState = HomeState()
) : BaseViewModel<HomeState, HomeAction>(initialState) {

    var productList: List<Product>? = listOf()

    override fun handle(action: HomeAction) {
        when (action) {
            is HomeAction.ResetDetails -> resetDetails()
            is HomeAction.Search -> search(action.keyword)
            is HomeAction.GetDetails -> getDetails(action.productId)
        }
    }

    private fun resetDetails() {
        // clean details
        updateState {
            it.copy(
                detailsToShow = null
            )
        }
    }

    private fun search(keyword: String) {
        viewModelScope.launch {
            productList = homeInteractor.search(keyword)
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