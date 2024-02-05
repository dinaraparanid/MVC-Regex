package com.paranid5.mvc_regex.presentation

import com.paranid5.mvc_regex.domain.SubstringModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {
    @AddToEndSingle
    fun revalidateMatches(matchedList: List<SubstringModel>, totalMatches: Int)

    @AddToEndSingle
    infix fun revalidateButtonEnabled(isButtonEnabled: Boolean)
}