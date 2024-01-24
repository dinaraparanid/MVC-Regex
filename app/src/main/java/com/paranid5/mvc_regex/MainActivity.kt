package com.paranid5.mvc_regex

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.paranid5.mvc_regex.data.SubstringRepository
import com.paranid5.mvc_regex.ui.FindButton
import com.paranid5.mvc_regex.ui.MatchesAdapter
import com.paranid5.mvc_regex.ui.MatchesFound
import com.paranid5.mvc_regex.ui.MatchesView
import com.paranid5.mvc_regex.ui.RegexInput
import com.paranid5.mvc_regex.ui.TakeInput
import com.paranid5.mvc_regex.ui.TextInput

class MainActivity : AppCompatActivity() {
    private lateinit var textInput: EditText
    private lateinit var regexInput: EditText
    private lateinit var takeInput: EditText
    private lateinit var findButton: Button
    private lateinit var matchesView: RecyclerView
    private lateinit var matchesFound: TextView

    private val viewModel by viewModels<MainActivityViewModel>()

    private val matchesAdapter by lazy {
        MatchesAdapter().apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        applyInsets()
        initViews()
    }

    infix fun revalidateMatches(substringRepository: SubstringRepository) {
        val (matchesList, totalMatches) = substringRepository.matchedSubstringsAndTotal
        matchesAdapter submitList matchesList
        matchesFound.text = getString(R.string.matches_found, totalMatches)
    }

    infix fun revalidateButtonEnabled(isButtonEnabled: Boolean) {
        findButton.isEnabled = isButtonEnabled
    }

    private fun initViews() {
        textInput = TextInput(viewModel)
        regexInput = RegexInput(viewModel)
        takeInput = TakeInput(viewModel)
        findButton = FindButton(viewModel)
        matchesView = MatchesView(matchesAdapter)
        matchesFound = MatchesFound()
    }
}

private fun MainActivity.applyInsets() =
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }