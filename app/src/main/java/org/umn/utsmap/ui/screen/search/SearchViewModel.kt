package org.umn.utsmap.ui.screen.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.umn.utsmap.data.dummy.DummyData
import org.umn.utsmap.data.model.Kos

class SearchViewModel : ViewModel() {
    private val _searchQuery = MutableStateFlow("UGM, Yogyakarta")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _kosList = MutableStateFlow(DummyData.kosList)
    val kosList: StateFlow<List<Kos>> = _kosList.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}