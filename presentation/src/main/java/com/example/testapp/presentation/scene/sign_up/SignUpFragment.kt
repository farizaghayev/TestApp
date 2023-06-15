package com.example.testapp.presentation.scene.sign_up

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.testapp.domain.model.User
import com.example.testapp.presentation.base.BaseFragment
import com.example.testapp.presentation.databinding.FragmentSignUpBinding
import com.example.testapp.presentation.utils.NavigationCommand
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import android.util.Patterns
import com.example.testapp.presentation.R

@AndroidEntryPoint
class SignUpFragment : BaseFragment<Unit, Unit, SignUpViewModel, FragmentSignUpBinding>() {

    override val bindingCallback: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSignUpBinding
        get() = FragmentSignUpBinding::inflate

    override val viewModel: SignUpViewModel by viewModels()
    private var takePictureUri: Uri? = null


    override val bindViews: FragmentSignUpBinding.() -> Unit = {

        submit.setOnClickListener {
            viewModel.navigate(
                NavigationCommand.To(
                    SignUpFragmentDirections.actionSignUpFragmentToConfirmationFragment(
                        user = User(
                            firstname = firstNameTextField.text.toString(),
                            emailAddress = emailAddressTextField.text.toString(),
                            website = websiteTextField.text.toString(),
                            photo = takePictureUri?.toString(),
                            password = passwordTextField.text.toString()
                        )
                    )
                )
            )
        }
        profile.setOnClickListener {
            ImagePicker.with(this@SignUpFragment)
                .galleryMimeTypes(  //Exclude gif images
                    mimeTypes = arrayOf(
                        "image/png",
                        "image/jpg",
                        "image/jpeg"
                    )
                )
                .cameraOnly()
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        firstNameTextField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(search: Editable) {
                submit.isEnabled =  validateFirstName() && validateAllFilled()
            }
        })

        websiteTextField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(search: Editable) {
                submit.isEnabled =  validateWebsite() && validateAllFilled()
            }
        })

        passwordTextField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(search: Editable) {
                submit.isEnabled =  validatePassword() && validateAllFilled()
            }
        })


        emailAddressTextField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(search: Editable) {
                submit.isEnabled =  validateEmail() && validateAllFilled()
            }
        })

        firstNameTextField.setOnFocusChangeListener { view, hasFocus -> if (!hasFocus) hideKeyboard(view) }
        emailAddressTextField.setOnFocusChangeListener { view, hasFocus -> if (!hasFocus) hideKeyboard(view) }
        passwordTextField.setOnFocusChangeListener { view, hasFocus -> if (!hasFocus) hideKeyboard(view) }
        websiteTextField.setOnFocusChangeListener { view, hasFocus -> if (!hasFocus) hideKeyboard(view) }

    }


    private fun FragmentSignUpBinding.validateAllFilled() = firstNameTextField.text.toString().isNotBlank() && passwordTextField.text.toString().isNotBlank() && emailAddressTextField.text.toString().isNotBlank()

    private fun FragmentSignUpBinding.validateFirstName(): Boolean {
        if (firstNameTextField.text.toString().trim().isEmpty()) {
            firstName.error =  getString(R.string.general_required_field)
            firstName.requestFocus()
            return false
        } else {
            binding.firstName.isErrorEnabled = false
        }
        return true
    }
    private fun FragmentSignUpBinding.validatePassword(): Boolean {
        if (passwordTextField.text.toString().trim().isEmpty()) {
            password.error = getString(R.string.general_required_field)
            password.requestFocus()
            return false
        } else {
            password.isErrorEnabled = false
        }
        return true
    }

    private fun FragmentSignUpBinding.validateEmail(): Boolean {
        val email = emailAddressTextField.text.toString().trim()
        if (email.isEmpty()) {
            emailAddress.error =  getString(R.string.general_required_field)
            emailAddress.requestFocus()
            return false
        } else if (Patterns.EMAIL_ADDRESS.matcher(email).matches().not()) {
            emailAddress.error = getString(R.string.general_invalid_email)
            emailAddress.requestFocus()
            return false
        } else {
            emailAddress.isErrorEnabled = false
        }
        return true
    }

    private fun FragmentSignUpBinding.validateWebsite(): Boolean {
        val web = websiteTextField.text.toString().trim()

        if (web.isEmpty() || Patterns.WEB_URL.matcher(web).matches().not()) {
            website.error = getString(R.string.genral_invalid_website)
            website.requestFocus()
            return false
        } else {
            website.isErrorEnabled = false
        }
        return true
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {

            Activity.RESULT_OK -> {
                takePictureUri = data?.data
                Glide.with(requireContext())
                    .load(takePictureUri?.toString())
                    .placeholder(R.drawable.ic_profile)
                    .into(binding.profile)
            }

            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
        }
    }
}