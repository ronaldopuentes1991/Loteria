import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoteriaViewModel : ViewModel() {
    private val _currentNumber = mutableStateOf<Int?>(null)
    val currentNumber: State<Int?> = _currentNumber

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun generateLotoNumbers() {
        viewModelScope.launch {
            val numbers = (1..60).shuffled().take(6).sorted()
            for (number in numbers) {
                _isLoading.value = true
                delay(2000)
                _isLoading.value = false
                _currentNumber.value = number
                delay(2000)
            }
            _currentNumber.value = null
        }
    }
}
