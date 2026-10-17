package org.umn.utsmap.ui.screen.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.umn.utsmap.data.dummy.DummyData
import org.umn.utsmap.data.model.Kos

class SearchViewModel : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _kosList = MutableStateFlow(DummyData.kosList)
    val kosList: StateFlow<List<Kos>> = _kosList.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        _kosList.value = if (query.isEmpty()) {
            DummyData.kosList
        } else {
            DummyData.kosList.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.address.contains(query, ignoreCase = true)
            }
        }
    }
}