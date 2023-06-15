package com.example.testapp.presentation.scene.sign_up

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.testapp.presentation.R
import com.example.testapp.presentation.base.BaseFragment
import com.example.testapp.presentation.databinding.FragmentConfirmationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmationFragment : BaseFragment<Unit, Unit, ConfirmationViewModel, FragmentConfirmationBinding>() {

    override val bindingCallback: (LayoutInflater, ViewGroup?, Boolean) -> FragmentConfirmationBinding
        get() = FragmentConfirmationBinding::inflate
    override val viewModel: ConfirmationViewModel by viewModels()
    private val args: ConfirmationFragmentArgs by navArgs()

    override val bindViews: FragmentConfirmationBinding.() -> Unit = {

        header.text = getString(R.string.confirmation_greating, args.user.firstname)
        emailAddress.text = args.user.emailAddress
        firstName.text = args.user.firstname
        website.text = args.user.website

        Glide.with(requireContext())
            .load(args.user.photo)
            .placeholder(R.drawable.ic_profile)
            .into(binding.profile)

    }
}