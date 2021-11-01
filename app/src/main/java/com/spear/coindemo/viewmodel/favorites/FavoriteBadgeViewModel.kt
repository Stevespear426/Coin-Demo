package com.spear.coindemo.viewmodel.favorites

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spear.coindemo.common.Resource
import com.spear.coindemo.repository.use_case.favorites.AddFavoriteUseCase
import com.spear.coindemo.repository.use_case.favorites.DeleteFavoriteUseCase
import com.spear.coindemo.repository.use_case.favorites.IsFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesBadgeViewModel @Inject constructor(
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(FavoriteState())
    private val state: State<FavoriteState> = _state


    fun isFavorite(favId: String?) {
        favId?.let {
            isFavoriteUseCase(it).flowOn(Dispatchers.IO).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = FavoriteState(isFavorite = result.data)
                    }
                    is Resource.Error -> {
                        _state.value =
                            FavoriteState(isFavorite = result.data, error = result.message)
                    }
                    is Resource.Loading -> {
                        _state.value = FavoriteState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun addFavorite(favId: String) {
        addFavoriteUseCase(favId).flowOn(Dispatchers.IO).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = FavoriteState(isFavorite = result.data)
                }
                is Resource.Error -> {
                    _state.value = FavoriteState(isFavorite = result.data, error = result.message)
                }
                is Resource.Loading -> {
                    _state.value = FavoriteState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun deleteFavorite(favId: String) {
        deleteFavoriteUseCase(favId).flowOn(Dispatchers.IO).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = FavoriteState(isFavorite = result.data != true)
                }
                is Resource.Error -> {
                    _state.value = FavoriteState(isFavorite = result.data, error = result.message)
                }
                is Resource.Loading -> {
                    _state.value = FavoriteState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    @Composable
    fun MainContent(favId: String) {
        val isFav = state.value.isFavorite == true
        val size = animateDpAsState(targetValue = if (isFav) 24.dp else 20.dp)
        Icon(
            imageVector = if (isFav) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
            modifier = Modifier
                .testTag("Test FavoriteBadge")
                .size(size.value)
                .clickable {
                    if (isFav) deleteFavorite(favId)
                    else addFavorite(favId)
                },
            contentDescription = ""
        )
    }
}

data class FavoriteState(
    val isLoading: Boolean = false,
    val isFavorite: Boolean? = null,
    val error: String? = ""
)