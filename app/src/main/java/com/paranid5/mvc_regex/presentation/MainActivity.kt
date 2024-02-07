package com.paranid5.mvc_regex.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.paranid5.mvc_regex.MainPresenter
import com.paranid5.mvc_regex.R
import com.paranid5.mvc_regex.domain.SubstringModel
import com.paranid5.mvc_regex.presentation.views.FindButton
import com.paranid5.mvc_regex.presentation.views.MatchesAdapter
import com.paranid5.mvc_regex.presentation.views.MatchesFound
import com.paranid5.mvc_regex.presentation.views.MatchesView
import com.paranid5.mvc_regex.presentation.views.RegexInput
import com.paranid5.mvc_regex.presentation.views.TakeInput
import com.paranid5.mvc_regex.presentation.views.TextInput
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var textInput: EditText
    private lateinit var regexInput: EditText
    private lateinit var takeInput: EditText
    private lateinit var findButton: Button
    private lateinit var matchesView: RecyclerView
    private lateinit var matchesFound: TextView

    @Inject
    lateinit var presenterProvider: Provider<MainPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

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

    override fun revalidateMatches(matchedList: List<SubstringModel>, totalMatches: Int) {
        matchesAdapter submitList matchedList
        matchesFound.text = getString(R.string.matches_found, totalMatches)
    }

    override infix fun revalidateButtonEnabled(isButtonEnabled: Boolean) {
        findButton.isEnabled = isButtonEnabled
    }

    private fun initViews() {
        textInput = TextInput(presenter)
        regexInput = RegexInput(presenter)
        takeInput = TakeInput(presenter)
        findButton = FindButton(presenter)
        matchesView = MatchesView(matchesAdapter)
        matchesFound = MatchesFound()
        initMatches()
    }

    private fun initMatches(): Unit =
        presenter.initMatchesView()
}

private fun MainActivity.applyInsets() =
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }