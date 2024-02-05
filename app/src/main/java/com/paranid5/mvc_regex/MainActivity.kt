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
import com.paranid5.mvc_regex.domain.SubstringModel
import com.paranid5.mvc_regex.views.FindButton
import com.paranid5.mvc_regex.views.MatchesAdapter
import com.paranid5.mvc_regex.views.MatchesFoundText
import com.paranid5.mvc_regex.views.MatchesList
import com.paranid5.mvc_regex.views.RegexInput
import com.paranid5.mvc_regex.views.TakeInput
import com.paranid5.mvc_regex.views.TextInput

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

    fun revalidateMatches(
        shownMatchesList: List<SubstringModel>,
        totalMatches: Int
    ) {
        matchesAdapter submitList shownMatchesList
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
        matchesView = MatchesList(matchesAdapter)
        matchesFound = MatchesFoundText()
        initMatches()
    }

    private fun initMatches(): Unit =
        viewModel.updateMatches(this)
}

private fun MainActivity.applyInsets() =
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }