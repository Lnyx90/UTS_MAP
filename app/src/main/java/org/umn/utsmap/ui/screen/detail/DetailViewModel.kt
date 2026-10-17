package org.umn.utsmap.ui.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.umn.utsmap.data.dummy.DummyData
import org.umn.utsmap.data.model.Kos

class DetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val kosId: Int = savedStateHandle.get<String>("kosId")?.toIntOrNull() ?: 1

    private val _kos = MutableStateFlow<Kos?>(null)
    val kos: StateFlow<Kos?> = _kos.asStateFlow()

    init {
        loadKosDetail()
    }

    private fun loadKosDetail() {
        val foundKos = DummyData.kosList.find { it.id == kosId } ?: DummyData.kosList.first()
        _kos.value = foundKos
    }
}