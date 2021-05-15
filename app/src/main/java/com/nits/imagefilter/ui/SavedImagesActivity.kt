package com.nits.imagefilter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.FileProvider
import com.nits.imagefilter.R
import com.nits.imagefilter.databinding.ActivitySavedImagesBinding
import com.nits.imagefilter.listeners.SavedImageListener
import com.nits.imagefilter.ui.adapters.SavedImagesAdapter
import com.nits.imagefilter.utilities.displayToast
import com.nits.imagefilter.utilities.hide
import com.nits.imagefilter.utilities.show
import com.nits.imagefilter.viewmodel.SavedImagesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class SavedImagesActivity : AppCompatActivity(), SavedImageListener {
    private lateinit var binding: ActivitySavedImagesBinding

    private val viewModel: SavedImagesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        setListeners()
        viewModel.loadSavedImages()
    }

    private fun setupObservers() {
        viewModel.savedImagesUiState.observe(this, {
            val savedImagesDataState = it ?: return@observe

            if (savedImagesDataState.isLoading) {
                binding.savedImagesProgressBar.show()
            } else {
                binding.savedImagesProgressBar.hide()
            }
            savedImagesDataState.savedImages?.let { savedImages ->
                SavedImagesAdapter(savedImages, this).also { adapter ->
                    with(binding.rvSavedImages) {
                        this.adapter = adapter
                        visibility = View.VISIBLE
                    }

                }

            } ?: kotlin.run {
                savedImagesDataState.error?.let { error ->
                    displayToast(error)
                }
            }

        })
    }

    private fun setListeners() {
        binding.imgBack.setOnClickListener { onBackPressed() }
    }

    override fun onImageClicked(file: File) {
        val fileUri = FileProvider.getUriForFile(
            applicationContext,
            "${packageName}.provider",
            file
        )
        Intent(
            applicationContext,
            FilteredImageActivity::class.java
        ).also { filteredIntent ->
            filteredIntent.putExtra(EditImageActivity.KEY_FILTERED_IMAGE_URI, fileUri)
            startActivity(filteredIntent)
        }
    }
}