package com.example.movie.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.domain.PeopleWithColorUseCase
import com.example.movie.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    peopleWithColorUseCase: PeopleWithColorUseCase
) : ViewModel() {
    val people = peopleWithColorUseCase.people

    private val _selectedPerson = MutableLiveData<Person>()
    val selectedPerson: LiveData<Person>
        get() = _selectedPerson

    init {
        viewModelScope.launch {
            peopleWithColorUseCase()
        }
    }

    fun selectPerson(person: Person) {
        _selectedPerson.value = person
    }
}